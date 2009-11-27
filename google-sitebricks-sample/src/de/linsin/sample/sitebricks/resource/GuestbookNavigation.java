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

    public Entry getNext() {
        return entryDao.read(current.getId()+1);
    }

    public Entry getPrevious() {
        return entryDao.read(current.getId()-1);
    }

    public boolean isNextExists() {
        return current.getId()+1 < entryDao.readAll().size();
    }

    public boolean isPreviousExists() {
        return current.getId()-1 > 0;
    }
}
