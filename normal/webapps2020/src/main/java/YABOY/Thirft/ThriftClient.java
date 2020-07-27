package YABOY.Thirft;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * auto-generated
 */
public class ThriftClient {

    public static long getTimestamp(){
        long result = 0;
        try {
            TTransport transport;

            // instantiate a new TTransport protocol (will use a TCP socket)
            transport = new TSocket("localhost", 20000);
            transport.open();

            //instantiate a TProtocol using the TTransport instantiated above
            TProtocol protocol = new TBinaryProtocol(transport);
            //Finally, instantiate a synchronous client using the TProtocol instantiated above
            TimestampService.Client client = new TimestampService.Client(protocol);

            // use the stub method to call the RPC Server - this is a blocking call
            result = client.getTimestamp();
            
            transport.close();
        } catch (TException x) {}
        return result;
    }
}
