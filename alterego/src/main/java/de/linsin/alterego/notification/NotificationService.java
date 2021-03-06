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
