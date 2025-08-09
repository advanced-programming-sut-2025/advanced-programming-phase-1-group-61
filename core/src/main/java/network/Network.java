package network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import models.Game;


public class Network {
    public static final int TCP_PORT = 54555;
    public static final int UDP_PORT = 54777;

    public static class updateGame{
        private Game game;
        private int userId;

        public updateGame() {
        }

        public updateGame(Game game, int userId) {
            this.game = game;
            this.userId = userId;
        }

        public Game getGame() {
            return game;
        }

        public int getUserId() {
            return userId;
        }

    }

    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        KryoRegistrations.registerClasses(kryo);
    }
}
