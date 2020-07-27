package YABOY.RESTCurrencyConverter;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author 164645
 */
@Singleton
@Path("/conversion")
public class API{
    
    Currency baseCurrency = Currency.GBP;
    Currency fromCurrency;
    Currency toCurrency;
    
    public API(){}
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{from}/{to}")
    public Response getConversionRate(@PathParam("from") String from, @PathParam("to") String to){
        double rate;
        fromCurrency = Currency.stringToCurrency(from);
        toCurrency = Currency.stringToCurrency(to);
        
        if(toCurrency == baseCurrency){
            rate = baseCurrency.getValue()/fromCurrency.getValue();
        }else{
            rate = baseCurrency.getValue()/fromCurrency.getValue() * toCurrency.getValue();
        }
        return Response.ok(Double.toString(rate),MediaType.TEXT_PLAIN).build();
    }
}
    