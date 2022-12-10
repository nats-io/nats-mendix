package natsconnectormodule.impl;

import io.nats.client.Connection;

import java.util.HashMap;
import java.util.Map;

public class Vars {
    private static final Map<String, Connection> connections = new HashMap<>();
    private static final Map<String, Exception> lastExceptions = new HashMap<>();

    public static void put(String connectionId, Connection c) {
        connections.put(connectionId, c);
    }

    public static Connection getConn(String connectionId) {
        return connections.get(connectionId);
    }

    public static boolean closeConn(String connectionId) throws InterruptedException {
        Connection c = connections.remove(connectionId);
        if (c == null) {
            return false;
        }

        c.close();
        return true;
    }
}
