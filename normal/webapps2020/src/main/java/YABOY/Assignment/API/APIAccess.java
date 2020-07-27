package YABOY.Assignment.API;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author 164645
 */
public class APIAccess {
    
    public APIAccess(){}
    
    synchronized public Double getRate(String from, String to){
        Client client = ClientBuilder.newClient();
            Response response = client
                .target("http://localhost:10000/RESTAPI/conversion/")
                .path(from)
                .path(to)
                .request(MediaType.TEXT_PLAIN)
                .get(Response.class);
        return Double.parseDouble(response.readEntity(String.class));
    }
}
