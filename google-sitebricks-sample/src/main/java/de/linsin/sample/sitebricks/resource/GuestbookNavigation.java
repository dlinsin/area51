/*
 * Copyright 2009 David Linsin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.linsin.sample.sitebricks.resource;

import com.google.inject.Inject;
import com.google.sitebricks.rendering.EmbedAs;
import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.domain.Entry;

/**
 * Used as a teaser element in the entry site of a guestbook
 *
 * @author David Linsin - linsin@synyx.de
 */
@EmbedAs("navigation")
public class GuestbookNavigation {
    private Entry current;
    @Inject
    private EntryDao entryDao;

    public Entry getCurrent() {
        return current;
    }

    public void setCurrent(Entry argCurrent) {
        current = argCurrent;
    }

    /**
     * @return a {@link Entry} instance which is the next in line, null if there is no next
     */
    public Entry getNext() {
        for (Entry entry : entryDao.readAll()) {
            if (entry.getId() > current.getId()) {
                return entry;
            }
        }
        return null;
    }

    /**
     * @return a {@link Entry} instance which is the previous in line, null if there is no previous
     */
    public Entry getPrevious() {
        Entry previous = null;
        for (Entry entry : entryDao.readAll()) {
            if (entry.getId().equals(current.getId()) && previous != null) {
                return previous;
            }
            previous = entry;
        }
        return null;
    }

    /**
     * @return true is there is a next {@link Entry} in line, else false
     */
    public boolean isNextExists() {
        return getNext() != null;
    }

    /**
     * @return true if there is a previous {@link Entry} in line, else false
     */
    public boolean isPreviousExists() {
        return getPrevious() != null;
    }

    public EntryDao getEntryDao() {
        return entryDao;
    }

    public void setEntryDao(EntryDao argEntryDao) {
        entryDao = argEntryDao;
    }
}
