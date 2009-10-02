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
    public String delete(@Named("id") String argId) {
        load(argId);
        entryDao.delete(entry);
        return "/sample/guestbook";
    }
}
