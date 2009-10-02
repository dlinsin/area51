package de.linsin.sample.sitebricks.dao;

import de.linsin.sample.sitebricks.domain.Entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Holds stuff in memory and publishs a dummy list on creation
 *
 * @author David Linsin - linsin@synyx.de
 */
public class SimpleEntryDao implements EntryDao {
    private final List<Entry> entries;

    public SimpleEntryDao() {
        entries = new ArrayList<Entry>();
        for (int i = 0; i <= 10; i++) {
          entries.add(new Entry(i, new Date(), "dlinsin" + i, "This is sample # " + i));
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
