Level 1 Mods
------------
1. Start with an archetype or better go with a template directory
     1. onEnable will type a log message in game console
1. New command: spawn NN flaming pigs where you are, on fire for 30 secs, die afterwards
1. Event handler: When an entity dies by fire, lightning will strike
1. Weather API: need to figure out what would be an interesting use case here
1. Weather sequence: Makes it rain for 30 secs, thunder for 30 secs, clear for 30 secs ??? (can be easily done already)
1. Place TNT, put leaves on it, replaces it with a creeper @BlockPlaceEvent

Level 2 Mods
------------
1. Multiple events: PlayerInteractEvent
1. Zombies drop bones and rotten flesh when killed
1. Add a new crafting recipe
1. If a sponge is powered then it absorbs water
1. Hit the ground with a golden shovel, it creates a little house (using code)
1. Throw eggs, where it lands a chance of spawning something else (use random and switch statements) control how the egg is thrown and what hatches, including number of hatches
1. Right click zombie with a stick then there is 1/8 chance of getting a diamond
     1. add a command to enable/disable it
     1.send a message to all players that the player got a diamond

Level 3 Mods
------------
1. If you get bit by a zombie then you continuously get damaged until you eat cake. If you don’t eat cake and die then a zombie is spawned.
