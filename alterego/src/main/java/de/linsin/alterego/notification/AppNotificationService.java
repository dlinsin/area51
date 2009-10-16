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


import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Notifies AppNotification (appnotifications.com) by Http
 *
 * @author David Linsin - linsin@synyx.de
 */
public class AppNotificationService implements NotificationService {
    protected final Logger logger = Logger.getLogger(AppNotificationService.class.getName());
    private final String credentials;
    public static final String USER_CREDENTIALS = "user_credentials";
    public static final String NOTIFICATION_LONG_MESSAGE = "notification[long_message]";
    public static final String NOTIFICATION_TITLE = "notification[title]";
    public static final String MESSAGE_LEVEL = "message_level";
    public static final String URL = "https://www.appnotifications.com/account/notifications.xml";
    public static final String HTTP_USERAGENT = "http.useragent";

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
        client.getParams().setParameter(HTTP_USERAGENT, "IRC Bot");
        return client;
    }

    PostMethod setUp(String argTitle, String argMessage) {
        PostMethod method = new PostMethod(URL);
        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        method.addParameter(USER_CREDENTIALS, credentials);
        method.addParameter(NOTIFICATION_LONG_MESSAGE, argMessage);
        method.addParameter(NOTIFICATION_TITLE, argTitle);
        method.addParameter(MESSAGE_LEVEL, "2");
        return method;
    }
}
