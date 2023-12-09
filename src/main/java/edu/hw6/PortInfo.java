package edu.hw6;

import org.jetbrains.annotations.NotNull;

public record PortInfo(String protocol, int port, @NotNull String service) {
    @Override
    public String toString() {
        return String.format("%s  |  %d  |  %s", protocol, port, service);
    }
}
