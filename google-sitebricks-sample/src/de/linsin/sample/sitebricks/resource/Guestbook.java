package de.linsin.sample.sitebricks.resource;

import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import com.google.inject.Inject;
import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.domain.Entry;

import java.util.List;
import java.util.Date;

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
}
