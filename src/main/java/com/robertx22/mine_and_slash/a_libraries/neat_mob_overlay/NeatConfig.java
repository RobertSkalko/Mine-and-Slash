package com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

import java.util.List;

public class NeatConfig {

    public static boolean draw = true;

    public ConfigValue<Integer> maxDistance;
    public ConfigValue<Boolean> renderInF1;
    public ConfigValue<Double> heightAbove;
    public ConfigValue<Boolean> drawBackground;
    public ConfigValue<Integer> backgroundPadding;
    public ConfigValue<Integer> backgroundHeight;
    public ConfigValue<Integer> barHeight;
    public ConfigValue<Integer> plateSize;
    public ConfigValue<Integer> plateSizeBoss;
    public ConfigValue<Boolean> colorByType;
    public ConfigValue<Integer> hpTextHeight;
    public ConfigValue<Boolean> showMaxHP;
    public ConfigValue<Boolean> showCurrentHP;
    public ConfigValue<Boolean> showPercentage;
    public ConfigValue<Boolean> showOnPlayers;
    public ConfigValue<Boolean> showOnBosses;
    public ConfigValue<Boolean> showOnlyFocused;
    public ConfigValue<Boolean> enableDebugInfo;
    public ConfigValue<List<? extends String>> blacklist;

    public NeatConfig(ForgeConfigSpec.Builder builder) {
        builder.push("NEAT_CONFIG");

        maxDistance = builder.define("Max Distance", 24);
        renderInF1 = builder.define("Render with Interface Disabled (F1)", false);
        heightAbove = builder.define("Height Above Mob", 0.6D);
        drawBackground = builder.define("Draw Background", true);
        backgroundPadding = builder.define("Background Padding", 2);
        backgroundHeight = builder.define("Background Height", 6);
        barHeight = builder.define("Health Bar Height", 4);
        plateSize = builder.define("Plate Size", 25);
        plateSizeBoss = builder.define("Plate Size (Boss)", 50);
        colorByType = builder.define("Color Health Bar by getSpellType (instead of health percentage)", false);
        hpTextHeight = builder.define("HP Text Height", 14);
        showMaxHP = builder.define("Show Max HP", true);
        showCurrentHP = builder.define("Show Current HP", true);
        showPercentage = builder.define("Show HP Percentage", true);
        showOnPlayers = builder.define("Display on Players", true);
        showOnBosses = builder.define("Display on Bosses", true);
        showOnlyFocused = builder.define("Only show the health bar for the entity looked at", true);
        enableDebugInfo = builder.define("Show Debug Info with F3", true);
        blacklist = builder.comment("Blacklist uses entity IDs, not their display names. Use F3 to see them in the Neat bar.")
                .defineList("Blacklist", ImmutableList.of("minecraft:shulker", "minecraft:armor_stand", "minecraft:cod", "minecraft:salmon", "minecraft:pufferfish", "minecraft:tropical_fish"), a -> true);

        builder.pop();
    }

}
