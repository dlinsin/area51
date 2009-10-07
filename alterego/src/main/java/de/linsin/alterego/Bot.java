package de.linsin.alterego;

import de.linsin.alterego.notification.NotificationService;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Bot which uses a {@link NotificationService} implementation to notify other systems
 *
 * @author David Linsin - linsin@synyx.de
 */
public class Bot extends PircBot {
    protected final Logger logger = Logger.getLogger(Bot.class.getName());
    private final String alterEgoName;
    private boolean silentMode;
    private Collection<NotificationService> notificationServices;

    private Collection<String> messages;
    private Timer messagesTimer;
    private static final int MESSAGE_BATCH_SIZE = 20;
    private static final int MESSAGE_BATCH_DELAY_MS = 60000;

    public Bot(String argBotName, String argBotLogin, String argAlterEgoName) {
        super.setName(argBotName);
        super.setLogin(argBotLogin);
        alterEgoName = argAlterEgoName;
        notificationServices = new ArrayList<NotificationService>();
        messages = new ArrayList<String>();
        messagesTimer = new Timer();
    }

    public void addNotificationService(NotificationService argService) {
        notificationServices.add(argService);
    }

    @Override
    protected void onMessage(String argChannel, String argSender, String argLogin, String argHostname, String argMessage) {
        debug("onMessage: ", "argChannel = " + argChannel, "argSender = " + argSender, "argLogin = " + argLogin, "argHostname = " + argHostname, "argMessage = " + argMessage);
        batchMessages(format(argSender, argMessage));
    }

    @Override
    protected void onPrivateMessage(String argSender, String argLogin, String argHostname, String argMessage) {
        debug("onPrivateMessage: ", "argSender = " + argSender, "argLogin = " + argLogin, "argHostname = " + argHostname, "argMessage = " + argMessage);
        sendToService("private message", format(argSender, argMessage));
        super.sendMessage(argSender, String.format("Your msg for %1$s has been recorded, feel free to send more!", alterEgoName));
    }

    @Override
    protected void onJoin(String argChannel, String argSender, String argLogin, String argHostname) {
        debug("onJoin: ", "argChannel = " + argChannel, "argSender = " + argSender, "argLogin = " + argLogin, "argHostname = " + argHostname);
        User[] users = super.getUsers(argChannel);
        switchSilentMode(users);
    }

    @Override
    protected void onPart(String argChannel, String argSender, String argLogin, String argHostname) {
        debug("onPart: ", "argChannel = " + argChannel, "argSender = " + argSender, "argLogin = " + argLogin, "argHostname = " + argHostname);
        User[] users = super.getUsers(argChannel);
        switchSilentMode(users);
    }

    @Override
    protected void onQuit(String argChannel, String argSender, String argLogin, String argHostname) {
        debug("onQuit: ", "argChannel = " + argChannel, "argSender = " + argSender, "argLogin = " + argLogin, "argHostname = " + argHostname);
        User[] users = super.getUsers(argChannel);
        switchSilentMode(users);
    }

    @Override
    protected void onUserList(String argChannel, User[] argUsers) {
        debug("onUserList: ", "argChannel = " + argChannel, "argUsers = " + Arrays.asList(argUsers).toString());
        switchSilentMode(argUsers);
    }

    private void switchSilentMode(User[] argUsers) {
        for (int i = 0; i < argUsers.length; i++) {
            User user = argUsers[i];
            if (user.getNick().equals(alterEgoName)) {
                silentMode = true;
                logger.info("Switched On Silent Mode");
                return;
            }
        }
        silentMode = false;
        logger.info("Switched Off Silent Mode");
    }

    private String format(String argSender, String argMessage) {
        return String.format("[%2$tk:%2$tM] %1$s: %3$s", argSender, new Date(), argMessage);
    }

    private void batchMessages(String argMessage) {
        if (messages.isEmpty()) {
            messages.add(argMessage);
            messagesTimer.schedule(new TimerTask() {
                public void run() {
                    processMessages();
                    messagesTimer.purge();
                }
            }, MESSAGE_BATCH_DELAY_MS);
        } else if (messages.size() < MESSAGE_BATCH_SIZE) {
            messages.add(argMessage);
        } else {
            processMessages();
            messagesTimer.purge();
        }
    }

    private void processMessages() {
        StringBuilder builder = new StringBuilder();
        for (String message : messages) {
            builder.append(message).append("\n");
        }
        sendToService("message", builder.toString());
        messages.clear();
    }

    private void sendToService(String argTitle, String argMessage) {
        for (NotificationService service : notificationServices) {
            if (!silentMode) {
                try {
                    service.notify(argTitle, argMessage);
                } catch (Exception e) {
                    // TODO retry
                    logger.severe(e.toString());
                }
            } else {
                logger.info("silent mode on, no message sent!");
            }
        }
    }

    private void debug(String... argMessage) {
        logger.info(Arrays.asList(argMessage).toString());
    }
}
