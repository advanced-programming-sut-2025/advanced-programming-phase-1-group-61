package models;

import com.google.gson.*;
import models.tool.*;

import java.lang.reflect.Type;

public class ToolAdapter implements JsonSerializer<Tool>, JsonDeserializer<Tool> {

    @Override
    public JsonElement serialize(Tool src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getSimpleName());
        // Serialize the Tool object as it is
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    @Override
    public Tool deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement dataElement = jsonObject.get("data");

        switch (type) {
            case "Axe":
                return context.deserialize(dataElement, Axe.class);
            case "Hoe":
                return context.deserialize(dataElement, Hoe.class);
            case "Shear":
                return context.deserialize(dataElement, Shear.class);
            case "Scythe":
                return context.deserialize(dataElement, Scythe.class);
            case "FishingPole":
                return context.deserialize(dataElement, FishingPole.class);
            case "WateringCan":
                return context.deserialize(dataElement, WateringCan.class);
            case "Pickaxe":
                return context.deserialize(dataElement, Pickaxe.class);
            case "MilkPail":
                return context.deserialize(dataElement, MilkPail.class);
            default:
                throw new JsonParseException("Unknown Tool type: " + type);
        }
    }
}
