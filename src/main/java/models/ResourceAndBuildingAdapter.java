package models;

import com.google.gson.*;
import models.resource.*;
import models.building.*;
import models.shops.*;

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
            case "BlackSmith" -> context.deserialize(dataElement , BlackSmith.class);
            case "Carpenter" -> context.deserialize(dataElement , Carpenter.class);
            case "FishSHop" -> context.deserialize(dataElement , FishShop.class);
            case "JojaMart" -> context.deserialize(dataElement , JojaMart.class);
            case "Marnie" -> context.deserialize(dataElement , Marnie.class);
            case "Pierre" -> context.deserialize(dataElement , Pierre.class);
            case "ShopAnimals" -> context.deserialize(dataElement , ShopAnimals.class);
            case "ShopCages" -> context.deserialize(dataElement,ShopCages.class);
            case "ShopFoods" -> context.deserialize(dataElement,ShopFoods.class);
            case "ShopItem" -> context.deserialize(dataElement,ShopItem.class);
            case "ShopTool" -> context.deserialize(dataElement , ShopTool.class);
            case "StarDrop" -> context.deserialize(dataElement , StarDrop.class);
            case "BuildingReference" -> context.deserialize(dataElement , BuildingReference.class);
            default -> throw new JsonParseException("Unknown Resource/Building type: " + type);
        };
    }
}