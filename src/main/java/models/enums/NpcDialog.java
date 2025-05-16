package models.enums;

import java.util.Map;

public enum NpcDialog {
    Sebastian(Map.of(
            Season.Spring, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Too many people smiling in spring. Not a fan.",
                            WeatherState.Rain, "Rain means I can stay inside without judgment.",
                            WeatherState.Storm, "Hope the power doesn't go out again...",
                            WeatherState.Snow, "Snow in spring? That’s just weird."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "The sun’s kind of nice… as long as I’m not dragged outside.",
                            WeatherState.Rain, "I get most of my coding done when it’s raining.",
                            WeatherState.Storm, "These storms are annoying, but they pass quickly.",
                            WeatherState.Snow, "Unexpected snow, huh? Guess we're not done with winter yet."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "I used to hate sunny spring days. You make them bearable.",
                            WeatherState.Rain, "Rain tapping on the window, warm coffee, and you. That’s peace.",
                            WeatherState.Storm, "The storm outside’s loud, but I don’t mind if you’re here.",
                            WeatherState.Snow, "Snow in spring isn’t so bad, if we’re snowed in together."
                    )
            ),
            Season.Summer, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Summer is the worst. Heat, bugs, noise.",
                            WeatherState.Rain, "Rain doesn’t help. Still humid and gross.",
                            WeatherState.Storm, "Figures a summer storm would ruin the WiFi.",
                            WeatherState.Snow, "Snow in summer? Okay… did I install a glitch mod?"
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Summer’s sun feels like a challenge I’m not ready for.",
                            WeatherState.Rain, "Rain cools things off, at least.",
                            WeatherState.Storm, "Storms always mess up my plans.",
                            WeatherState.Snow, "Snow in summer? I must be dreaming."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Sunshine and you make summer almost bearable.",
                            WeatherState.Rain, "I love the rain when we’re inside together.",
                            WeatherState.Storm, "Storms feel less scary with you around.",
                            WeatherState.Snow, "Snow in summer would be a miracle — like us."
                    )
            ),
            Season.Fall, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Fall’s alright, but too many leaves to rake.",
                            WeatherState.Rain, "Rain on leaves is annoying.",
                            WeatherState.Storm, "Storms scare off the wildlife.",
                            WeatherState.Snow, "Snow’s coming sooner than I’d like."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Fall days feel calmer, but still cold.",
                            WeatherState.Rain, "Rain makes the world smell earthy.",
                            WeatherState.Storm, "Storms are wild this time of year.",
                            WeatherState.Snow, "First snow is always a surprise."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Walking through fall leaves with you is nice.",
                            WeatherState.Rain, "Rain’s not so bad when we’re together.",
                            WeatherState.Storm, "Storms don’t scare me when you’re near.",
                            WeatherState.Snow, "Snow in fall feels like a promise."
                    )
            ),
            Season.Winter, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Winter sun’s cold and harsh.",
                            WeatherState.Rain, "Cold rain’s the worst.",
                            WeatherState.Storm, "Storms keep me inside all day.",
                            WeatherState.Snow, "Snow’s cold, but I guess it’s pretty."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Winter sunlight makes the snow sparkle.",
                            WeatherState.Rain, "Frozen rain makes everything slippery.",
                            WeatherState.Storm, "Storms are harsh, but I’m tougher.",
                            WeatherState.Snow, "Snow’s quiet, like the world’s holding its breath."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Winter sun and your smile warm my heart.",
                            WeatherState.Rain, "I want to hold you close during cold rains.",
                            WeatherState.Storm, "Let the storm rage outside — I’m safe with you.",
                            WeatherState.Snow, "Snowflakes remind me every moment with you is unique."
                    )
            )
    )),

    Abigail(Map.of(
            Season.Spring, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "I wish the rain would come back sooner, spring’s too bright without it.",
                            WeatherState.Rain, "The rain brings magic. I love watching it fall.",
                            WeatherState.Storm, "Storms shake the house, but they’re kind of thrilling.",
                            WeatherState.Snow, "Snow in spring feels like a spell gone wrong."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Spring sunshine makes my crystals sparkle even more.",
                            WeatherState.Rain, "Rainy days are perfect for reading my tarot cards.",
                            WeatherState.Storm, "I like storms—they’re nature’s wild magic.",
                            WeatherState.Snow, "Snow whispers secrets only I can hear."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Sunlight and you—two things I never want to lose.",
                            WeatherState.Rain, "Rain’s soft rhythm reminds me of your heartbeat.",
                            WeatherState.Storm, "In storms, I find peace… especially with you near.",
                            WeatherState.Snow, "Snowfall feels enchanting, just like us."
                    )
            ),
            Season.Summer, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Summer’s too hot. I’d rather be inside with my amethyst.",
                            WeatherState.Rain, "Rain cools the air, but it never lasts.",
                            WeatherState.Storm, "Storms make my hair crazy. Not a fan.",
                            WeatherState.Snow, "Snow in summer? That’s the stuff of nightmares."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Bright days call for bright gems and bright moods.",
                            WeatherState.Rain, "I like rainy summer nights—they feel mysterious.",
                            WeatherState.Storm, "Storms keep the energy alive, even if they’re scary.",
                            WeatherState.Snow, "Snow in summer is impossible… right?"
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Let’s find a shady spot and watch the world glow.",
                            WeatherState.Rain, "Rainy afternoons with you are my favorite kind of magic.",
                            WeatherState.Storm, "The thunder feels like a heartbeat, reminding me you’re here.",
                            WeatherState.Snow, "Snow in summer would be crazy—but I love crazy with you."
                    )
            ),
            Season.Fall, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "The fading sun suits my mood lately.",
                            WeatherState.Rain, "Rain on fall leaves smells like mystery.",
                            WeatherState.Storm, "Fall storms shake the town. I prefer the quiet.",
                            WeatherState.Snow, "Snow before winter? Seems premature."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Fall’s colors remind me of gemstones turning.",
                            WeatherState.Rain, "Rain lets me slow down and listen.",
                            WeatherState.Storm, "Storms in fall are wild, but I like a little wild.",
                            WeatherState.Snow, "Snow feels like a secret waiting to be found."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Walking through leaves with you feels magical.",
                            WeatherState.Rain, "Your presence makes even the stormiest days warm.",
                            WeatherState.Storm, "Storms don’t scare me when I’m with you.",
                            WeatherState.Snow, "Snowflakes and whispers — perfect fall magic."
                    )
            ),
            Season.Winter, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Winter’s cold light is harsh on my skin.",
                            WeatherState.Rain, "Cold rain feels like a curse.",
                            WeatherState.Storm, "Winter storms trap me inside with my books.",
                            WeatherState.Snow, "Snow’s cold, but it’s all I get this season."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Winter sun glints off frost like a thousand diamonds.",
                            WeatherState.Rain, "I love watching rain freeze on windowpanes.",
                            WeatherState.Storm, "Storms bring energy, even in winter.",
                            WeatherState.Snow, "Snow covers everything, hiding secrets beneath."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Winter sun and your smile warm my heart.",
                            WeatherState.Rain, "I want to hold you close during cold rains.",
                            WeatherState.Storm, "Let the storm rage outside — I’m safe with you.",
                            WeatherState.Snow, "Snowflakes remind me that every moment with you is unique."
                    )
            )
    )),

    Harvi(Map.of(
            Season.Spring, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Spring’s good for fishing, but I don’t trust the calm.",
                            WeatherState.Rain, "Rain makes the fish bite, but I don’t like getting wet.",
                            WeatherState.Storm, "Storms keep me indoors. Guess it’s game time.",
                            WeatherState.Snow, "Snow in spring? That’s just confusing."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "A sunny day means a good catch later.",
                            WeatherState.Rain, "Rain’s the best time to dream about fishing.",
                            WeatherState.Storm, "Storms can wait—I’ve got plans.",
                            WeatherState.Snow, "Snow’s rare in spring, but it’s kinda cool."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Fishing with you on sunny days is perfect.",
                            WeatherState.Rain, "Rainy days make for the best stories.",
                            WeatherState.Storm, "Storms are scary, but not when you’re here.",
                            WeatherState.Snow, "Snow in spring? Let’s just call it a bonus day off."
                    )
            ),
            Season.Summer, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Summer sun dries my catch fast. I like that.",
                            WeatherState.Rain, "Rain ruins fishing trips, plain and simple.",
                            WeatherState.Storm, "Storms in summer make me nervous.",
                            WeatherState.Snow, "Snow in summer? Nope."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Sunny days are made for the lake.",
                            WeatherState.Rain, "Rain cools things down a bit.",
                            WeatherState.Storm, "Storms remind me to respect nature.",
                            WeatherState.Snow, "Snow is impossible this time of year."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Nothing beats a sunny day on the water with you.",
                            WeatherState.Rain, "Rainy days are better with company.",
                            WeatherState.Storm, "Storms are fierce, but so is my heart for you.",
                            WeatherState.Snow, "Snow in summer would be a miracle—like us."
                    )
            ),
            Season.Fall, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Fall sun warms the river nicely.",
                            WeatherState.Rain, "Rain slows the fish but helps the trees.",
                            WeatherState.Storm, "Storms make me worried about the crops.",
                            WeatherState.Snow, "Snow’s coming soon, better get ready."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Fall’s calm is nice for thinking.",
                            WeatherState.Rain, "Rain makes the air smell fresh.",
                            WeatherState.Storm, "Storms remind me to appreciate calm days.",
                            WeatherState.Snow, "Snow’s first kiss on fall is special."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Fall walks with you are the best.",
                            WeatherState.Rain, "Rainy days don’t feel lonely with you.",
                            WeatherState.Storm, "Storms don’t scare me anymore.",
                            WeatherState.Snow, "Snowflakes remind me of new beginnings."
                    )
            ),
            Season.Winter, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Winter sun is weak but honest.",
                            WeatherState.Rain, "Rain in winter makes everything slippery.",
                            WeatherState.Storm, "Storms keep me by the fire.",
                            WeatherState.Snow, "Snow’s cold but peaceful."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Winter sun sparkles on the ice.",
                            WeatherState.Rain, "Frozen rain’s dangerous but beautiful.",
                            WeatherState.Storm, "Storms make me appreciate warmth.",
                            WeatherState.Snow, "Snow covers all worries."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Winter sun and your smile warm me.",
                            WeatherState.Rain, "Hold me close during cold rains.",
                            WeatherState.Storm, "Let the storm rage, I’m safe with you.",
                            WeatherState.Snow, "Every snowflake reminds me of you."
                    )
            )
    )),

    Lia(Map.of(
            Season.Spring, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Spring makes the vineyards sing, but I’m quiet.",
                            WeatherState.Rain, "Rain helps the grapes grow, even if I don’t like it.",
                            WeatherState.Storm, "Storms could ruin the harvest. I worry.",
                            WeatherState.Snow, "Snow in spring? Nature’s trick."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Spring sunshine helps my vines thrive.",
                            WeatherState.Rain, "Rain nourishes the soil and my soul.",
                            WeatherState.Storm, "Storms make me anxious but hopeful.",
                            WeatherState.Snow, "Snow feels out of place, but beautiful."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Sunny days with you grow more than grapes.",
                            WeatherState.Rain, "Rain is sweeter when shared with you.",
                            WeatherState.Storm, "Storms can’t break what we have.",
                            WeatherState.Snow, "Snowfall brings quiet moments with you."
                    )
            ),
            Season.Summer, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Summer heat stresses the vines, but I endure.",
                            WeatherState.Rain, "Rain gives relief from the scorching sun.",
                            WeatherState.Storm, "Storms might damage everything I’ve worked for.",
                            WeatherState.Snow, "Snow in summer? Not possible."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Summer days are long, and so are my worries.",
                            WeatherState.Rain, "Rain refreshes the fields and my heart.",
                            WeatherState.Storm, "Storms remind me to stay strong.",
                            WeatherState.Snow, "Snow doesn’t belong here now."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Sunshine tastes sweeter with you by my side.",
                            WeatherState.Rain, "Rainy moments are gifts with you.",
                            WeatherState.Storm, "Storms test us, but we endure together.",
                            WeatherState.Snow, "Snow in summer would be a miracle like us."
                    )
            ),
            Season.Fall, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Fall brings the best grapes but shorter days.",
                            WeatherState.Rain, "Rain slows harvest but strengthens roots.",
                            WeatherState.Storm, "Storms can ruin months of work.",
                            WeatherState.Snow, "Snow hints winter is near."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Fall’s colors inspire my best wines.",
                            WeatherState.Rain, "Rain refreshes the earth and my soul.",
                            WeatherState.Storm, "Storms remind me of nature’s power.",
                            WeatherState.Snow, "Snow’s early visit makes me nervous."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Walking in the vineyard with you feels like home.",
                            WeatherState.Rain, "Rainy days with you are cozy and sweet.",
                            WeatherState.Storm, "Storms can’t shake my heart when you’re near.",
                            WeatherState.Snow, "Snowfall brings quiet magic to us."
                    )
            ),
            Season.Winter, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Winter sun is weak but precious.",
                            WeatherState.Rain, "Cold rain chills my bones.",
                            WeatherState.Storm, "Storms keep me indoors, planning next year.",
                            WeatherState.Snow, "Snow blankets the vineyard, resting all."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Winter light reflects in frozen grapes.",
                            WeatherState.Rain, "Frozen rain is harsh but cleansing.",
                            WeatherState.Storm, "Storms remind me to prepare.",
                            WeatherState.Snow, "Snow’s quiet is a welcome rest."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Winter sun and your warmth keep me going.",
                            WeatherState.Rain, "Rainy days are better with your company.",
                            WeatherState.Storm, "Storms outside can’t touch us.",
                            WeatherState.Snow, "Snowfall makes every moment magical."
                    )
            )
    )),

    Robin(Map.of(
            Season.Spring, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Spring’s sun helps the sap flow. Time to work.",
                            WeatherState.Rain, "Rain’s good for the forest, but slows me down.",
                            WeatherState.Storm, "Storms mean repairs tomorrow.",
                            WeatherState.Snow, "Snow in spring means a slow start."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Spring’s energy makes me want to build.",
                            WeatherState.Rain, "Rain slows work but waters the trees.",
                            WeatherState.Storm, "Storms test my skills as a carpenter.",
                            WeatherState.Snow, "Snow makes for slow but quiet work."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Sunny spring days are perfect for building dreams.",
                            WeatherState.Rain, "Rain brings life to the forest and calm to my mind.",
                            WeatherState.Storm, "Storms challenge me, but you inspire me more.",
                            WeatherState.Snow, "Snowfall reminds me that patience builds strength."
                    )
            ),
            Season.Summer, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Summer sun dries the wood nicely.",
                            WeatherState.Rain, "Rain delays my projects, but the forest needs it.",
                            WeatherState.Storm, "Storms might undo all my hard work.",
                            WeatherState.Snow, "Snow in summer? Impossible."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Long summer days mean more time to build.",
                            WeatherState.Rain, "Rain’s a good excuse to rest and plan.",
                            WeatherState.Storm, "Storms are a reminder to prepare better.",
                            WeatherState.Snow, "Snow doesn’t belong here now."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Building with you on sunny days is a joy.",
                            WeatherState.Rain, "Rainy days are perfect for planning new projects.",
                            WeatherState.Storm, "Storms can’t stop us when we work together.",
                            WeatherState.Snow, "Snow in summer would be a miracle, just like us."
                    )
            ),
            Season.Fall, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Fall sun helps me finish projects before winter.",
                            WeatherState.Rain, "Rain slows the work but waters the forest.",
                            WeatherState.Storm, "Storms warn me to hurry repairs.",
                            WeatherState.Snow, "Snow’s coming soon, better finish up."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Fall’s calm lets me reflect on my work.",
                            WeatherState.Rain, "Rain refreshes the earth and my spirit.",
                            WeatherState.Storm, "Storms push me to be better prepared.",
                            WeatherState.Snow, "Snow’s first kiss on fall is quiet but certain."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Working with you in fall feels right.",
                            WeatherState.Rain, "Rainy days with you are peaceful and sweet.",
                            WeatherState.Storm, "Storms can’t shake me with you nearby.",
                            WeatherState.Snow, "Snowfall is a quiet promise of new beginnings."
                    )
            ),
            Season.Winter, Map.of(
                    FriendshipLevel.LOW, Map.of(
                            WeatherState.Sunny, "Winter sun is weak but helps dry the wood.",
                            WeatherState.Rain, "Cold rain makes work tough.",
                            WeatherState.Storm, "Storms keep me inside by the fire.",
                            WeatherState.Snow, "Snow blankets the forest, slowing everything."
                    ),
                    FriendshipLevel.MEDIUM, Map.of(
                            WeatherState.Sunny, "Winter light brightens my workshop.",
                            WeatherState.Rain, "Frozen rain is tricky to work in.",
                            WeatherState.Storm, "Storms remind me to prepare for spring.",
                            WeatherState.Snow, "Snow covers the land and my worries."
                    ),
                    FriendshipLevel.HIGH, Map.of(
                            WeatherState.Sunny, "Winter sun and your warmth keep me going.",
                            WeatherState.Rain, "Rainy days are better with your company.",
                            WeatherState.Storm, "Storms outside can’t touch us.",
                            WeatherState.Snow, "Snowfall makes every moment magical."
                    )
            )
    ));

    private Map<Season, Map<FriendshipLevel, Map<WeatherState, String>>> dialogs;

    NpcDialog(Map<Season, Map<FriendshipLevel, Map<WeatherState, String>>> dialogs) {
        this.dialogs = dialogs;
    }

    public String getDialogue(Season season, FriendshipLevel level, WeatherState weather) {
        return dialogs.getOrDefault(season, Map.of())
                .getOrDefault(level, Map.of())
                .getOrDefault(weather, "...");
    }
}
