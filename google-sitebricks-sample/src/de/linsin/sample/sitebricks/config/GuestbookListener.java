package de.linsin.sample.sitebricks.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Configures Guice injector
 *
 * @author David Linsin - linsin@synyx.de
 */
public class GuestbookListener extends GuiceServletContextListener {
    protected Injector getInjector() {
        return Guice.createInjector(new GuestbookSitebricksModule());
    }
}
