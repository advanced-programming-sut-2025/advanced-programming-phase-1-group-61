package models;

import com.google.gson.*;
import models.food.Refrigerator;
import models.resource.*;
import models.building.*;
import models.shops.*;

import java.lang.reflect.Type;

public class ResourceAndBuildingAdapter implements JsonSerializer<Resource>, JsonDeserializer<Resource> {

    @Override
    public JsonElement serialize(Resource src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getCanonicalName());
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    @Override
    public Resource deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement dataElement = jsonObject.get("data");

        try {
            Class<?> clazz = Class.forName(type);
            return context.deserialize(dataElement, clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown type: " + type, e);
        }
    }
}
