package de.linsin.alterego;

import de.linsin.alterego.notification.AppNotificationService;
import org.jibble.pircbot.IrcException;

import java.io.IOException;

/**
 * TODO document
 *
 * @author David Linsin - linsin@synyx.de
 */
public class StartUp {

    public static void main(String[] args) throws IrcException, IOException {
        Bot bot = new Bot("alterego1", "dlinsin", "dlinsin");
//        bot.connect("midletspiele.de");
//        bot.joinChannel("#synyx");
        bot.connect("irc.freenode.net");
        bot.joinChannel("#dlinsin");

        bot.addNotificationService(new AppNotificationService(System.getProperty("credentials")));
        
    }
}
