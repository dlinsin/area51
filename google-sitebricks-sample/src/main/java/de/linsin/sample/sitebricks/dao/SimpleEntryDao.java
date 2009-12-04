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

package de.linsin.sample.sitebricks.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.linsin.sample.sitebricks.domain.Entry;

/**
 * Holds stuff in memory and publishs a dummy list on creation
 *
 * @author David Linsin - linsin@synyx.de
 */
public class SimpleEntryDao implements EntryDao {
    private final List<Entry> entries;

    public SimpleEntryDao() {
        List<String> sampleNames = Arrays.asList("david", "sutini", "marc", "ollie", "thomas", "achim");
        List<String> sampleEntries = Arrays.asList("Hey it's me, how are you?", "Hi, just came bye to say hello!", "What's going on, haven't heard from you!");
        long dateSeed = 1259941076191L;
        Random random = new Random();
        entries = new ArrayList<Entry>();
        for (int i = 0; i <= 10; i++) {
            entries.add(new Entry(i, new Date(dateSeed + random.nextInt()), sampleNames.get(random.nextInt(sampleNames.size()-1)), sampleEntries.get(random.nextInt(sampleEntries.size()-1))));
      }
    }

    public Integer save(Entry entry) {
        int id = calculateId();
        entry.setId(id);
        entries.add(entry);
        return id;
    }

    private int calculateId() {
        int id = 0;
        if (!entries.isEmpty()) {
            id = entries.get(entries.size()-1).getId();
            id++;
        }
        return id;
    }

    public Entry read(Integer argId) {
        for (Entry entry : entries) {
            if (entry.getId().equals(argId)) {
                return entry;
            }
        }
        return null;
    }

    public List<Entry> readAll() {
        return entries;
    }

    public void delete(Entry argEntry) {
        entries.remove(argEntry);
    }
}
