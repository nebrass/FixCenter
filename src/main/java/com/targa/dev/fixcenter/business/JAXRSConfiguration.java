package com.targa.dev.fixcenter.business;

import com.targa.dev.fixcenter.business.boundary.CustomersResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

/**
 *
 * @author Nebrass Lamouchi <lnibrass at gmail.com>
 */
@ApplicationPath("rest")
public class JAXRSConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>(2);
        set.add(MOXyJsonProvider.class);
        set.add(CustomersResource.class);
        set.add(RESTCorsDemoRequestFilter.class);
        set.add(RESTCorsDemoResponseFilter.class);
        return set;
    }

}
