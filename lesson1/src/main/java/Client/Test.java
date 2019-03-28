package Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author gaoxiong
 * @ClassName Test
 * @Description TODO
 * @date 2019/3/28 23:35
 */
public class Test {
    public static void main ( String[] args ) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
    }
}
