package network;

import com.esotericsoftware.kryo.Kryo;
import models.Game;
import models.User;
import models.character.*;
import models.character.Character;
import models.date.Date;
import models.enums.BackpackType;
import models.enums.Gender;
import models.enums.SecurityQuestion;
import models.enums.TrashcanType;
import models.map.Map;
import models.map.Tile;
import models.map.Weather;

import java.util.ArrayList;
import java.util.List;

public class KryoRegistrations {
    public static void registerClasses(Kryo kryo) {
        kryo.register(Game.class);
        kryo.register(User.class);
        kryo.register(Gender.class);
        kryo.register(Question.class);
        kryo.register(SecurityQuestion.class);
        kryo.register(Map.class);
        kryo.register(Tile.class);
        kryo.register(Date.class);
        kryo.register(Weather.class);
        kryo.register(Character.class);
        kryo.register(Inventory.class);
        kryo.register(InventorySlot.class);
        kryo.register(BackpackType.class);
        kryo.register(Trashcan.class);
        kryo.register(TrashcanType.class);
        kryo.register(Integer.class);






        kryo.register(network.Requsets.class);
        kryo.register(Network.updateGame.class);
        kryo.register(List.class);
        kryo.register(ArrayList.class);
        kryo.register(network.NetworkRequest.class);


    }
}
