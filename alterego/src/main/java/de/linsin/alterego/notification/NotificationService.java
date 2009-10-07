package de.linsin.alterego.notification;

/**
 * Interface which serves as abstraction
 *
 * @author David Linsin - linsin@synyx.de
 */
public interface NotificationService {
    /**
     * Notifies a service
     *
     * @param argTitle {@link String} which serves as title of notification
     * @param argMessage {@link String} which is the actual content
     * @return true if notification was delivered, false if service reports an error
     * @throws Exception in case of a technical issue
     */
    boolean notify(String argTitle, String argMessage);
}
