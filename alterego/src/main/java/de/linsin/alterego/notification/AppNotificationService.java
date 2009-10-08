/*
 * Copyright (C) 2009  <David Linsin>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.linsin.alterego.notification;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.logging.Logger;

/**
 * Notifies AppNotification (appnotifications.com) by Http
 *
 * @author David Linsin - linsin@synyx.de
 */
public class AppNotificationService implements NotificationService {
    protected final Logger logger = Logger.getLogger(AppNotificationService.class.getName());
    private final String credentials;
    private static final String USER_CREDENTIALS = "user_credentials";
    private static final String NOTIFICATION_MESSAGE = "notification[message]";
    private static final String NOTIFICATION_TITLE = "notification[title]";
    private static final String MESSAGE_LEVEL = "message_level";
    private static final String URL = "https://www.appnotifications.com/account/notifications.xml";

    public AppNotificationService(String argCredentials) {
        credentials = argCredentials;
    }

    /**
     * @see NotificationService#notify(String, String)
     */
    public boolean notify(String argTitle, String argMessage) {
        HttpClient client;
        PostMethod method = null;
        try {
            client = setUp();
            method = setUp(argTitle, argMessage);
            int returnCode = client.executeMethod(method);

            if (returnCode > HttpStatus.SC_OK) {
                logger.warning("Couldn't send notification! Return code: " + returnCode);
                return false;
            } else {
                logger.info("Sent notification!");
                return true;
            }
        } catch (Exception e) {
            logger.severe(e.toString());
            throw new AppNotificationException();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
    }

    HttpClient setUp() {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.useragent", "IRC Bot");
        return client;
    }

    private PostMethod setUp(String argTitle, String argMessage) {
        PostMethod method = new PostMethod(URL);

        method.addParameter(USER_CREDENTIALS, credentials);
        method.addParameter(NOTIFICATION_MESSAGE, argMessage);
        method.addParameter(NOTIFICATION_TITLE, argTitle);
        method.addParameter(MESSAGE_LEVEL, "2");
        return method;
    }

}
