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
