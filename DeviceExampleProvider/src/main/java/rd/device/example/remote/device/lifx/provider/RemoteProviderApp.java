package rd.device.example.remote.device.lifx.provider;

import com.stuntguy3000.lifxlansdk.handler.PacketHandler;
import com.stuntguy3000.lifxlansdk.helper.DeviceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rd.device.framework.api.handler.ErrorHandler;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * Asynchronous command pattern implementation applied to a light device example
 */
@SpringBootApplication
@EnableAutoConfiguration
public class RemoteProviderApp {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);


    static List<InetAddress> listAllBroadcastAddresses() throws SocketException {
        List<InetAddress> broadcastList = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces
                = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();

            if (networkInterface.isLoopback() || !networkInterface.isUp() || networkInterface.isVirtual()) {
                continue;
            }

            networkInterface.getInterfaceAddresses().stream()
                    .map(InterfaceAddress::getBroadcast)
                    .filter(Objects::nonNull)
                    .forEach(broadcastList::add);
        }

        return broadcastList;
    }

    public static void main(String[] args) throws SocketException {
        List<InetAddress> broadcastList = listAllBroadcastAddresses();
        if (broadcastList.size()>0) {
            logger.info("Running Local API to connect to LIFX bulbs on UDP broadcast address " + broadcastList.get(0).getHostAddress());
            PacketHandler.setBroadcastAddress(broadcastList.get(0));
            SpringApplication.run(RemoteProviderApp.class, args);
        } else {
            logger.error("No multicast address is available");
        }
    }
}
