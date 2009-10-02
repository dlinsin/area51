package de.linsin.sample.sitebricks.config;

import com.google.inject.Scopes;
import com.google.sitebricks.SitebricksModule;
import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.dao.SimpleEntryDao;
import de.linsin.sample.sitebricks.resource.Guestbook;

/**
 * Configures a Sitebrick Module
 *
 * @author David Linsin - linsin@synyx.de
 */
public class GuestbookSitebricksModule extends SitebricksModule {
    @Override
    protected void configureSitebricks() {
        bind(EntryDao.class).to(SimpleEntryDao.class).in(Scopes.SINGLETON);
        scan(Guestbook.class.getPackage());
    }
}
