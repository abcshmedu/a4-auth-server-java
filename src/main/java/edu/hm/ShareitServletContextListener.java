package edu.hm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.shareit.business.MediaService;
import edu.hm.shareit.business.MediaServiceImpl;
import edu.hm.shareit.data.MediaAccess;
import edu.hm.shareit.data.hibernate.MediaAccessPersistent;

/**
 * Context Listener to enable usage of google guice together with jersey.
 *
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 */
public class ShareitServletContextListener extends GuiceServletContextListener {

    private static final Injector injector = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(MediaService.class).to(MediaServiceImpl.class);
            bind(MediaAccess.class).to(MediaAccessPersistent.class);
        }
    });

    /**
     * This method is only required for the HK2-Guice-Bridge in the Application class.
     *
     * @return Injector instance.
     */
    static Injector getInjectorInstance() {
        return injector;
    }

    @Override
    protected Injector getInjector() {
        return injector;
    }

}
