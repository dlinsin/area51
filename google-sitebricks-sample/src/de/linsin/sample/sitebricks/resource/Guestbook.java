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

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import com.google.inject.Inject;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.domain.Entry;

/**
 * Resource which represents a list of {@link Entry} instances
 *
 * @author David Linsin - linsin@synyx.de
 */
@At("/guestbook")
public class Guestbook {
    private List<Entry> entries;
    private Entry newEntry = new Entry();

    @Inject
    private EntryDao entryDao;

    public Guestbook(EntryDao argEntryDao) {
        entryDao = argEntryDao;
    }

    public Guestbook() {
    }

    public List<Entry> getEntries() {
        return entries;
    }

    @Get
    public void load() {
        entries = entryDao.readAll();
    }

    @Post
    public String save() {
        newEntry.setDate(new Date());
        Integer id = entryDao.save(newEntry);
        return String.format("/sample/guestbook/%1$s", id);
    }

    public Entry getNewEntry() {
        return newEntry;
    }

    public void setNewEntry(Entry argNewEntry) {
        newEntry = argNewEntry;
    }

    public String getTime() {
        return new SimpleDateFormat().format(new Date());
    }
}
