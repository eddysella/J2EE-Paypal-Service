package YABOY.RESTCurrencyConverter;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * auto-generated
 */
@ApplicationPath("/")
public class JAXRSConfiguration extends Application {
     @Override    
     public Set<Class<?>> getClasses() {        
         final Set<Class<?>> classes = new HashSet<>();
          classes.add(API.class);        
          return classes;    
     }       
}
