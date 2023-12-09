package edu.hw6;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PortScannerTest {
    private final Map<Integer, String> portsToScan = Map.of(
        167, "NAMP",
        169, "SEND",
        750, "RFILE",
        13231, "WireGuard"
    );

    @Test
    @DisplayName("Сканнер заданных портов")
    public void scan() {
        PortScanner scanner = new PortScanner(portsToScan);

        List<PortInfo> ports = scanner.scan();

        for (PortInfo portInfo : ports) {
            assertThat(portsToScan.containsKey(portInfo.port())).isTrue();
            assertThat(portInfo.service()).isNotEmpty();
        }
    }
}
