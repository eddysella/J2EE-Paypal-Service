package YABOY.ThriftServer;

import javax.ejb.Singleton;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @author George Parisis
 */
@Singleton
public class Server{

    public static Handler handler;
    public static TimestampService.Processor processor;
    public static TServerTransport serverTransport;
    public static TServer server;

    public Server(){
        try {
            handler = new Handler();
            processor = new TimestampService.Processor(handler);

            Runnable simple = () -> {
                simple(processor);
            };

            new Thread(simple).start();
            server.stop();
            
        } catch (Exception x) {
            System.err.println(x);
        }
    }

    public static void simple(TimestampService.Processor processor) {
        try {
            serverTransport = new TServerSocket(20000);
            server = new TSimpleServer(new Args(serverTransport).processor(processor));
            server.serve();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
