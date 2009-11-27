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
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.domain.Entry;

/**
 * Resource which represents a single Entry
 *
 * @author David Linsin - linsin@synyx.de
 */
@At("/guestbook/:id")
public class GuestbookEntry {
    private Entry entry;
    @Inject
    private EntryDao entryDao;

    @Get
    public void load(@Named("id") String argId) {
        entry = entryDao.read(Integer.valueOf(argId));
    }

    public Entry getEntry() {
        return entry;
    }

    @Post
    public Guestbook delete(@Named("id") String argId) {
        load(argId);
        entryDao.delete(entry);
        return new Guestbook(entryDao);
    }
}
