import rest.demo.rest.CurrencyResource;
import rest.demo.rest.ExchangeResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("v2")
public class App extends Application {

    /*public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CurrencyResource.class);
        classes.add(ExchangeResource.class);
        return classes;
    }*/
}
