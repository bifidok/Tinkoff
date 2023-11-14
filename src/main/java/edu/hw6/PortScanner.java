package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PortScanner {
    private final static int PORT_SCAN_RANGE_UPPER = 49151;
    private final static int PORT_SCAN_RANGE_LOWER = 0;

    @SuppressWarnings("MagicNumber")
    private Map<Integer, String> trackablePorts = Map.of(
        80, "HTTP",
        25, "SMTP",
        53, "DNS",
        5353, "mDNS",
        5355, "LLMNR",
        5672, "AMQP",
        1701, "L2F",
        1080, "SOCKS",
        5432, "PostgreSQL Database",
        3306, "MySQL Database"
    );

    public PortScanner() {
    }

    public PortScanner(Map<Integer, String> knownPorts) {
        trackablePorts = knownPorts;
    }

    @SuppressWarnings("MultipleStringLiterals")
    public List<PortInfo> scan() {
        List<PortInfo> portInfos = new ArrayList<>(trackablePorts.size());
        for (int port = PORT_SCAN_RANGE_LOWER; port <= PORT_SCAN_RANGE_UPPER; port++) {
            if (!trackablePorts.containsKey(port)) {
                continue;
            }
            try (ServerSocket socket = new ServerSocket(port)) {
                portInfos.add(new PortInfo("TCP", port, "N/A"));
            } catch (IOException exp) {
                portInfos.add(new PortInfo("TCP", port, trackablePorts.get(port)));
                continue;
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                portInfos.add(new PortInfo("UDP", port, "N/A"));
            } catch (IOException exp) {
                portInfos.add(new PortInfo("UDP", port, trackablePorts.get(port)));
            }
        }
        return portInfos;
    }
}
