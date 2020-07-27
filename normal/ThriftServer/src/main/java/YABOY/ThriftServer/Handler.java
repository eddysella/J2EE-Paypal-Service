package YABOY.ThriftServer;

import java.util.Date;
import org.apache.thrift.TException;

/**
 * @author 164645
 */
public class Handler implements TimestampService.Iface {

    @Override
    public long getTimestamp() throws TException {
        return new Date().getTime();
    }
}
