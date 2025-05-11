package models;

import com.google.gson.*;
import models.resource.*;
import models.building.*;
import java.lang.reflect.Type;

public class ResourceAndBuildingAdapter implements JsonSerializer<Resource>, JsonDeserializer<Resource> {

    @Override
    public JsonElement serialize(Resource src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getSimpleName());
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    @Override
    public Resource deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement dataElement = jsonObject.get("data");

        return switch (type) {
            case "Crop" -> context.deserialize(dataElement, Crop.class);
            case "Stone" -> context.deserialize(dataElement, Stone.class);
            case "Tree" -> context.deserialize(dataElement, Tree.class);
            case "Barn" -> context.deserialize(dataElement, Barn.class);
            case "Coop" -> context.deserialize(dataElement, Coop.class);
            case "Greenhouse" -> context.deserialize(dataElement, Greenhouse.class);
            case "Shop" -> context.deserialize(dataElement, Shop.class);
            default -> throw new JsonParseException("Unknown Resource/Building type: " + type);
        };
    }
}