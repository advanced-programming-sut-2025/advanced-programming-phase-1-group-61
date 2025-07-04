package views;

import controllers.GameMenuController;
import models.App;
import models.RandomNumber;
import models.Result;
import models.enums.Commands.CheatCodes;
import models.enums.Commands.GameMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();
    private boolean inGame = false;

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();

        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);


        if (!inGame) {
            Matcher start = GameMenuCommands.START_GAME.getMatcher(input);
            Matcher loadGame = GameMenuCommands.LOAD_GAME.getMatcher(input);
            if (showCurrentMenu != null) {
                System.out.println("you are in game menu start a game to enter game");
            }
            if (start != null) {
                List<String> usernames = new ArrayList<>();
                usernames.add(App.getLoggedInUser().getUsername());
                String user1 = start.group(1);
                if (user1 == null) {
                    System.out.println("you need at least one more player to start game");
                    return;
                }
                usernames.add(user1);
                for (int i = 2; i <= 3; i++) {
                    String user = start.group(i);
                    if (user != null) {
                        usernames.add(user);
                    }
                }
                Result res = controller.startGameErrors(usernames);
                if (!res.isSuccessful()) {
                    System.out.println(res.message());
                    return;
                }

                Result userValidation = controller.userListIsValid(usernames);
                if (userValidation.isSuccessful()) {
                    int[] mapNumber = new int[4];
                    int i = 0;
                    while (i < usernames.size()) {
                        System.out.println("choosing map for " + usernames.get(i));
                        String mapChoosing = scanner.nextLine();
                        Matcher chooseMap = GameMenuCommands.CHOOSE_MAP.getMatcher(mapChoosing);
                        if (chooseMap != null) {
                            int number = Integer.parseInt(chooseMap.group("number"));
                            if (number > 2 || number <= 0) {
                                System.out.println("unavailable map number");
                            } else {
                                mapNumber[i] = number;
                                i++;
                            }
                        } else {
                            System.out.println("you have to choose map using command: game map <number>");
                        }
                    }

                    while (i < 4) {
                        mapNumber[i++] = RandomNumber.getRandomNumberWithBoundaries(1, 3);
                    }
                    Result result = controller.startGame(usernames, mapNumber);
                    System.out.println(result.message());
                    if (result.isSuccessful()) {
                        inGame = true;
                    }
                }
            } else if (loadGame != null) {
                Result result = controller.loadGame();
                System.out.println(result.message());
                if (result.isSuccessful()) inGame = true;
            } else {
                System.out.println("invalid command");
            }
        } else {
            Matcher equipTool = GameMenuCommands.EQUIP_TOOL.getMatcher(input);
            Matcher showCurrentTool = GameMenuCommands.SHOW_CURRENT_TOOL.getMatcher(input);
            Matcher toolsShowAvailable = GameMenuCommands.TOOLS_SHOW_AVAILABLE.getMatcher(input);
            Matcher toolsUpgrade = GameMenuCommands.TOOLS_UPGRADE.getMatcher(input);
            Matcher exitGame = RegisterMenuCommands.LEAVE_GAME.getMatcher(input);
            Matcher changePlayerTurn = GameMenuCommands.NEXT_TURN.getMatcher(input);
            Matcher showHour = GameMenuCommands.SHOW_HOUR.getMatcher(input);
            Matcher showDate = GameMenuCommands.SHOW_DATE.getMatcher(input);
            Matcher showDateAndTime = GameMenuCommands.SHOW_DATE_AND_TIME.getMatcher(input);
            Matcher showWeekDay = GameMenuCommands.SHOW_WEEKDAY.getMatcher(input);
            Matcher cheatHour = CheatCodes.CHEAT_ADVANCE_TIME.getMatcher(input);
            Matcher cheatDay = CheatCodes.CHEAT_ADVANCE_DATE.getMatcher(input);
            Matcher cheatThor = CheatCodes.CHEAT_THOR.getMatcher(input);
            Matcher showWeather = GameMenuCommands.SHOW_WEATHER.getMatcher(input);
            Matcher weatherForeCast = GameMenuCommands.FORECAST_WEATHER.getMatcher(input);
            Matcher cheatWeather = CheatCodes.CHEAT_WEATHER_SET.getMatcher(input);
            Matcher walk = GameMenuCommands.WALK.getMatcher(input);
            Matcher energySet = CheatCodes.ENERGY_SET.getMatcher(input);
            Matcher unlimitedEnergy = CheatCodes.ENERGY_UNLIMITED.getMatcher(input);
            Matcher cheatAddItem = CheatCodes.CHEAT_ADD_ITEM.getMatcher(input);
            Matcher inventoryShow = GameMenuCommands.INVENTORY_SHOW.getMatcher(input);
            Matcher inventoryTrash = GameMenuCommands.INVENTORY_TRASH.getMatcher(input);
            Matcher helpReadingMap = GameMenuCommands.HELP_READING_MAP.getMatcher(input);
            Matcher printMap = GameMenuCommands.PRINT_MAP.getMatcher(input);
            Matcher showEnergy = GameMenuCommands.SHOW_ENERGY.getMatcher(input);
            Matcher toolsUse = GameMenuCommands.TOOLS_USE.getMatcher(input);
            Matcher cheatAddTool = CheatCodes.CHEAT_ADD_TOOL.getMatcher(input);
            Matcher craftInfo = GameMenuCommands.CRAFT_INFO.getMatcher(input);
            Matcher useAxeForSyrup = GameMenuCommands.USE_AXE_FOR_SYRUP.getMatcher(input);
            Matcher buyAnimal = GameMenuCommands.BUY_ANIMAL.getMatcher(input);
            Matcher pet = GameMenuCommands.PET.getMatcher(input);
            Matcher cheatFriendship = GameMenuCommands.CHEAT_SET_FRIENDSHIP.getMatcher(input);
            Matcher Animals = GameMenuCommands.ANIMALS.getMatcher(input);
            Matcher Sheperd = GameMenuCommands.SHEPERD_ANIMALS.getMatcher(input);
            Matcher FeedAnimal = GameMenuCommands.FEED_HEY.getMatcher(input);
            Matcher Produces = GameMenuCommands.PRODUCES.getMatcher(input);
            Matcher collectProduces = GameMenuCommands.COLLECT_PRODUCES.getMatcher(input);
            Matcher sellAnimal = GameMenuCommands.SELL_ANIMAL.getMatcher(input);
            Matcher showAllProducts = GameMenuCommands.SHOW_ALL_PRODUCTS.getMatcher(input);
            Matcher showAllAvailableProducts = GameMenuCommands.SHOW_ALL_AVAILABLE_PRODUCTS.getMatcher(input);
            Matcher purchaseProduct = GameMenuCommands.PURCHASE.getMatcher(input);
            Matcher artisanuse = GameMenuCommands.ARTISAN_USE.getMatcher(input);
            Matcher artisancollect = GameMenuCommands.ARTISAN_GET.getMatcher(input);
            Matcher plant = GameMenuCommands.PLANT.getMatcher(input);
            Matcher showPlant = GameMenuCommands.SHOW_PLANT.getMatcher(input);
            Matcher fertilize = GameMenuCommands.FERTILIZE.getMatcher(input);
            Matcher showWaterInBucket = GameMenuCommands.WATER_IN_BUCKET.getMatcher(input);
            Matcher putOrPickItemInRefrigerator = GameMenuCommands.Refrigerator.getMatcher(input);
            Matcher showCookingRecipe = GameMenuCommands.ShowCookingRecipes.getMatcher(input);
            Matcher cooking = GameMenuCommands.CookingPrepare.getMatcher(input);
            Matcher eatFood = GameMenuCommands.EatFood.getMatcher(input);
            Matcher meetNpc = GameMenuCommands.MEET_NPC.getMatcher(input);
            Matcher friendshipNPCList = GameMenuCommands.FRIENDSHIP_NPC_LIST.getMatcher(input);
            Matcher cheatSetNpcFriendship = CheatCodes.CHEAT_SET_NPC_FRIENDSHIP.getMatcher(input);
            Matcher npcQuestsList = GameMenuCommands.NPC_QUESTS_LIST.getMatcher(input);
            Matcher npcQuestFinish = GameMenuCommands.NPC_QUEST_FINISH.getMatcher(input);
            Matcher giftNpc = GameMenuCommands.GIFT_NPC.getMatcher(input);
            Matcher buildCage = GameMenuCommands.BuildCage.getMatcher(input);
            Matcher showCraftingRecipes = GameMenuCommands.ShowCraftingRecipes.getMatcher(input);
            Matcher craft = GameMenuCommands.CRAFT_INFO.getMatcher(input);
            Matcher placeItem = GameMenuCommands.placeItem.getMatcher(input);
            Matcher repairGreenHouse = GameMenuCommands.RepairGreenHouse.getMatcher(input);
            Matcher Friendships = GameMenuCommands.Friendships.getMatcher(input);
            Matcher Talk = GameMenuCommands.Talk.getMatcher(input);
            Matcher TalkHistory = GameMenuCommands.TalkHistory.getMatcher(input);
            Matcher Gift = GameMenuCommands.Gift.getMatcher(input);
            Matcher GiftList = GameMenuCommands.GiftList.getMatcher(input);
            Matcher GiftRate = GameMenuCommands.GiftRate.getMatcher(input);
            Matcher GiftHistory = GameMenuCommands.GiftHistory.getMatcher(input);
            Matcher Hug = GameMenuCommands.Hug.getMatcher(input);
            Matcher Flower = GameMenuCommands.Flower.getMatcher(input);
            Matcher AskMarriage = GameMenuCommands.AskMarriage.getMatcher(input);
            Matcher RespondMarriage = GameMenuCommands.RespondMarriage.getMatcher(input);
            Matcher StartTrade = GameMenuCommands.StartTrade.getMatcher(input);
            Matcher Trade = GameMenuCommands.Trade.getMatcher(input);
            Matcher TradeList = GameMenuCommands.TradeList.getMatcher(input);
            Matcher TradeResponse = GameMenuCommands.TradeResponse.getMatcher(input);
            Matcher TradeHistory = GameMenuCommands.TradeHistory.getMatcher(input);
            Matcher cheatAddMoney = CheatCodes.CHEAT_ADD_MONEY.getMatcher(input);
            Matcher sellItem = GameMenuCommands.SellItem.getMatcher(input);


            if (showCurrentMenu != null) {
                System.out.println("you are in game");
            } else if (AskMarriage != null) {
                Result result = controller.AskMarriage(AskMarriage);
                System.out.println(result.message());
            } else if (RespondMarriage != null) {
                Result result = controller.RespondMarriage(RespondMarriage);
                System.out.println(result.message());
            } else if (StartTrade != null) {
                Result result = controller.StartTrade(StartTrade);
                System.out.println(result.message());
            } else if (Trade != null) {
                Result result = controller.Trade(Trade);
                System.out.println(result.message());
            } else if (TradeList != null) {
                Result result = controller.TradeList(TradeList);
                System.out.println(result.message());
            } else if (TradeResponse != null) {
                Result result = controller.TradeResponse(TradeResponse);
                System.out.println(result.message());
            } else if (cheatFriendship != null) {
                Result result =controller.cheatFriendship(cheatFriendship);
                System.out.println(result.message());
            } else if (TradeHistory != null) {
                Result result = controller.TradeHistory(TradeHistory);
                System.out.println(result.message());
            } else if (Flower != null) {
                Result result = controller.Flower(Flower);
                System.out.println(result.message());
            } else if (Friendships != null) {
                Result result = controller.Friendships(Friendships);
                System.out.println(result.message());
            } else if (GiftRate != null) {
                Result result = controller.GiftRate(GiftRate);
                System.out.println(result.message());
            } else if (GiftHistory != null) {
                Result result = controller.GiftHistory(GiftHistory);
                System.out.println(result.message());
            } else if (Hug != null) {
                Result result = controller.Hug(Hug);
                System.out.println(result.message());
            } else if (Talk != null) {
                Result result = controller.Talk(Talk);
                System.out.println(result.message());
            } else if (TalkHistory != null) {
                Result result = controller.TalkHistory(TalkHistory);
                System.out.println(result.message());
            } else if (Gift != null) {
                Result result = controller.Gift(Gift);
                System.out.println(result.message());
            } else if (GiftList != null) {
                Result result = controller.repairGreenHouse(GiftList);
                System.out.println(result.message());
            } else if (sellItem != null) {
                Result result = controller.sellItem(sellItem);
                System.out.println(result.message());
            } else if (cheatAddMoney != null) {
                Result result = controller.cheatAddMoney(cheatAddMoney);
                System.out.println(result.message());
            } else if (repairGreenHouse != null) {
                Result result = controller.repairGreenHouse(repairGreenHouse);
                System.out.println(result.message());
            } else if (artisanuse != null) {
                Result result = controller.artisanUse(artisanuse);
                System.out.println(result.message());
            } else if (artisancollect != null) {
                Result result = controller.artisancollect(artisancollect);
                System.out.println(result.message());
            } else if (placeItem != null) {
                Result result = controller.placeItem(placeItem);
                System.out.println(result.message());
            } else if (craft != null) {
                Result result = controller.craft(craft);
                System.out.println(result.message());
            } else if (showCraftingRecipes != null) {
                Result result = controller.showRecipes();
                System.out.println(result.message());
            } else if (buildCage != null) {
                Result result = controller.buildCage(buildCage);
                System.out.println(result.message());
            } else if (eatFood != null) {
                Result result = controller.eatFood(eatFood);
                System.out.println(result.message());
            } else if (cooking != null) {
                Result result = controller.cooking(cooking);
                System.out.println(result.message());
            } else if (showCookingRecipe != null) {
                Result result = controller.showCookingRecipes();
                System.out.println(result.message());
            } else if (putOrPickItemInRefrigerator != null) {
                Result result = controller.putOrPickItemInRefrigerator(putOrPickItemInRefrigerator);
                System.out.println(result.message());
            } else if (showWaterInBucket != null) {
                Result result = controller.showWaterInBucket();
                System.out.println(result.message());
            } else if (fertilize != null) {
                Result result = controller.fertilize(fertilize);
                System.out.println(result.message());
            } else if (showPlant != null) {
                Result result = controller.showPlant(showPlant);
                System.out.println(result.message());
            } else if (plant != null) {
                Result result = controller.plant(plant);
                System.out.println(result.message());
            } else if (sellAnimal != null) {
                Result result = controller.sellAnimal(sellAnimal);
                System.out.println(result.message());
            } else if (collectProduces != null) {
                Result result = controller.getAnimalProduct(collectProduces);
                System.out.println(result.message());
            } else if (Produces != null) {
                Result result = controller.animalsProducts();
                System.out.println(result.message());
            } else if (FeedAnimal != null) {
                Result result = controller.feedHay(FeedAnimal);
                System.out.println(result.message());
            } else if (Sheperd != null) {
                Result result = controller.shepherd(Sheperd);
                System.out.println(result.message());
            } else if (Animals != null) {
                Result result = controller.showAnimals();
                System.out.println(result.message());
            } else if (cheatFriendship != null) {
                Result result = controller.cheatFriendship(cheatFriendship);
                System.out.println(result.message());
            } else if (pet != null) {
                Result result = controller.pet(pet);
                System.out.println(result.message());
            } else if (buyAnimal != null) {
                Result result = controller.buyAnimal(buyAnimal);
                System.out.println(result.message());
            } else if (useAxeForSyrup != null) {
                Result result = controller.useAxeForSyrup(useAxeForSyrup);
                System.out.println(result.message());
            } else if (showEnergy != null) {
                Result result = controller.showEnergy();
                System.out.println(result.message());
            } else if (printMap != null) {
                Result result = controller.printMap(printMap);
                System.out.println(result.message());
            } else if (helpReadingMap != null) {
                Result result = controller.helpRead();
                System.out.println(result.message());
            } else if (cheatWeather != null) {
                Result result = controller.cheatWeatherState(cheatWeather);
                System.out.println(result.message());
            } else if (weatherForeCast != null) {
                Result result = controller.foreCastWeather();
                System.out.println(result.message());
            } else if (showWeather != null) {
                String state = App.getCurrentGame().getMap().getWeather().getState().getDisplayName();
                System.out.println("weather is now " + state);
            } else if (cheatThor != null) {
                Result result = controller.cheatThor(cheatThor);
                System.out.println(result.message());
            } else if (cheatDay != null) {
                Result result = controller.cheatDay(cheatDay);
                System.out.println(result.message());
            } else if (showHour != null) {
                Result result = controller.showHour();
                System.out.println(result.message());
            } else if (showDate != null) {
                Result result = controller.showDate();
                System.out.println(result.message());
            } else if (showDateAndTime != null) {
                Result result = controller.showDateAndTime();
                System.out.println(result.message());
            } else if (showWeekDay != null) {
                Result result = controller.showWeekDay();
                System.out.println(result.message());
            } else if (changePlayerTurn != null) {
                Result result = controller.changeTurn();
                System.out.println(result.message());
            } else if (cheatHour != null) {
                Result result = controller.cheatHour(cheatHour);
                System.out.println(result.message());
            } else if (energySet != null) {
                Result result = controller.energySet(energySet);
                System.out.println(result.message());
            } else if (unlimitedEnergy != null) {
                Result result = controller.unlimitedEnergySet();
                System.out.println(result.message());
            } else if (cheatAddItem != null) {
                Result result = controller.cheatAddItem(cheatAddItem);
                System.out.println(result.message());
            } else if (cheatAddTool != null) {
                Result result = controller.cheatAddTool(cheatAddTool);
                System.out.println(result.message());
            } else if (walk != null) {
                Result res = controller.energyResult(walk);
                System.out.println(res.message());
                if (res.isSuccessful()) {
                    String confirmation = scanner.nextLine();
                    Result result = controller.walk(confirmation);
                    System.out.println(result.message());
                }
            } else if (equipTool != null) {
                Result result = controller.equipTool(equipTool);
                System.out.println(result.message());
            } else if (showCurrentTool != null) {
                Result result = controller.showCurrentTool();
                System.out.println(result.message());
            } else if (toolsUpgrade != null) {
                Result result = controller.upgradeTool(toolsUpgrade);
                System.out.println(result.message());
            } else if (toolsShowAvailable != null) {
                Result result = controller.showAvailableTools();
                System.out.println(result.message());
            } else if (toolsUse != null) {
                Result result = controller.useTool(toolsUse);
                System.out.println(result.message());
            } else if (inventoryShow != null) {
                Result result = controller.inventoryShow();
                System.out.println(result.message());
            } else if (inventoryTrash != null) {
                Result result = controller.inventoryTrash(inventoryTrash);
                System.out.println(result.message());
            } else if (exitGame != null) {
                try {
                    App.saveApp();
                } catch (IOException e) {
                    System.out.println("failed to save app");
                }
                App.setCurrentMenu(Menu.EXIT_MENU);
            } else if (craftInfo != null) {
                Result result = controller.craftInfo(craftInfo);
                System.out.println(result.message());
            } else if (showAllProducts != null) {
                Result result = controller.showShopProducts();
                System.out.println(result.message());
            } else if (showAllAvailableProducts != null) {
                Result result = controller.showShopAvailableProducts();
                System.out.println(result.message());
            } else if (purchaseProduct != null) {
                Result result = controller.purchaseProduct(purchaseProduct);
                System.out.println(result.message());
            } else if (meetNpc != null) {
                Result result = controller.meetNpc(meetNpc);
                System.out.println(result.message());
            } else if (friendshipNPCList != null) {
                Result result = controller.friendshipNPCList();
                System.out.println(result.message());
            } else if (cheatSetNpcFriendship != null) {
                Result result = controller.cheatSetNpcFriendship(cheatSetNpcFriendship);
                System.out.println(result.message());
            } else if (npcQuestsList != null) {
                Result result = controller.questsList();
                System.out.println(result.message());
            } else if (npcQuestFinish != null) {
                Result result = controller.questsFinish(npcQuestFinish);
                System.out.println(result.message());
            } else if (giftNpc != null) {
                Result result = controller.giftNPC(giftNpc);
                System.out.println(result.message());
            } else {
                System.out.println("invalid command");
            }
        }
    }

}