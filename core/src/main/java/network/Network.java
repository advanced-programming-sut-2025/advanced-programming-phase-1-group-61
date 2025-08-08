package network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import models.Game;
import models.User;

public class Network {
    public static final int TCP_PORT = 54555;
    public static final int UDP_PORT = 54777;

    public static class HelloMessage {
        public String text;
    }

    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(HelloMessage.class);
        kryo.register(String.class);
        kryo.register(Game.class);
        kryo.register(User.class);
    }
}
