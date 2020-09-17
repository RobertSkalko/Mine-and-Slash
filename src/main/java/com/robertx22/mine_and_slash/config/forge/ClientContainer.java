package com.robertx22.mine_and_slash.config.forge;

import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.NeatConfig;
import com.robertx22.mine_and_slash.config.forge.parts.DmgParticleConfig;
import com.robertx22.mine_and_slash.uncommon.enumclasses.PlayerGUIs;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import org.apache.commons.lang3.tuple.Pair;

public class ClientContainer {

    public static final String NAME = "CLIENT";
    public static final ForgeConfigSpec spec;
    public static final ClientContainer INSTANCE;

    static {
        final Pair<ClientContainer, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(
            ClientContainer::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    public NeatConfig neatConfig;
    public DmgParticleConfig dmgParticleConfig;

    public BooleanValue SHOW_AFFIXED_NAME;
    public BooleanValue RENDER_ITEM_NAMES_ON_GROUND;
    public BooleanValue RENDER_MOB_HEALTH_GUI;
    public BooleanValue SHOW_LOW_ENERGY_MANA_WARNING;
    public BooleanValue SHOW_VANILLA_HEARTS;
    public BooleanValue SHOW_UNMET_GEAR_REQUIREMENTS_GUI;
    public BooleanValue RENDER_SIMPLE_MOB_BAR;
    public ForgeConfigSpec.IntValue REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES;

    public ForgeConfigSpec.IntValue LEFT_VANILLA_LIKE_BARS_Y__POS_ADJUST;
    public ForgeConfigSpec.IntValue RIGHT_VANILLA_LIKE_BARS_Y__POS_ADJUST;

    public EnumValue<PlayerGUIs> PLAYER_GUI_TYPE;

    ClientContainer(ForgeConfigSpec.Builder builder) {
        builder.comment("Client Settings")
            .push(NAME);

        neatConfig = builder.configure(NeatConfig::new)
            .getLeft();
        dmgParticleConfig = builder.configure(DmgParticleConfig::new)
            .getLeft();

        REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES = builder.comment(".")
            .defineInRange("REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES", 35, 0, 1000);

        RENDER_SIMPLE_MOB_BAR = builder.comment(".")
            .translation("mmorpg.config.")
            .define("RENDER_SIMPLE_MOB_BAR", true);

        RENDER_ITEM_NAMES_ON_GROUND = builder.comment(".")
            .define("RENDER_ITEM_NAMES_ON_GROUND", true);

        SHOW_AFFIXED_NAME = builder.comment(".")
            .translation("mmorpg.config.show_item_affixes")
            .define("SHOW_AFFIXED_NAME", true);

        SHOW_UNMET_GEAR_REQUIREMENTS_GUI = builder.comment(".")
            .translation("mmorpg.config.")
            .define("SHOW_UNMET_GEAR_REQUIREMENTS_GUI", true);

        SHOW_VANILLA_HEARTS = builder.comment(".")
            .translation("mmorpg.config.show_vanilla_hearts")
            .define("SHOW_VANILLA_HEARTS", true);

        RENDER_MOB_HEALTH_GUI = builder.comment(".")
            .translation("mmorpg.config.mob_health_bar")
            .define("RENDER_MOB_HEALTH_GUI", true);

        SHOW_LOW_ENERGY_MANA_WARNING = builder.comment(".")
            .translation("mmorpg.config.low_resource_warnings")
            .define("SHOW_LOW_ENERGY_MANA_WARNING", true);

        PLAYER_GUI_TYPE = builder.comment(".")
            .translation("mmorpg.config.player_gui_overlay_type")
            .defineEnum("PLAYER_GUI_TYPE", PlayerGUIs.Bottom_Middle_Corners);

        builder.pop();

        builder.comment("Azure Top Left Settings")
            .push("Azure Bars");

        AZURE_Y_POS_ADJUST = builder.comment(".")
            .defineInRange("AZURE_Y_POS_ADJUST", 0, -10000, 10000);
        AZURE_X_POS_ADJUST = builder.comment(".")
            .defineInRange("AZURE_X_POS_ADJUST", 0, -10000, 10000);

        builder.pop();

        builder.comment("Bottom Middle Corner Bar Settings")
            .push("Bottom Middle Corner Bars");

        BMC_LEFT_Y_POS_ADJUST = builder.comment(".")
            .defineInRange("BMC_LEFT_Y_POS_ADJUST", 0, -10000, 10000);
        BMC_LEFT_X_POS_ADJUST = builder.comment(".")
            .defineInRange("BMC_LEFT_X_POS_ADJUST", 0, -10000, 10000);
        BMC_RIGHT_Y_POS_ADJUST = builder.comment(".")
            .defineInRange("BMC_RIGHT_Y_POS_ADJUST", 0, -10000, 10000);
        BMC_RIGHT_X_POS_ADJUST = builder.comment(".")
            .defineInRange("BMC_RIGHT_X_POS_ADJUST", 0, -10000, 10000);

        builder.pop();

        builder.comment("Bottom Middle Bar Settings")
            .push("Bottom Middle Bars");

        BM_LEFT_Y_POS_ADJUST = builder.comment(".")
            .defineInRange("BM_LEFT_Y_POS_ADJUST", 0, -10000, 10000);
        BM_LEFT_X_POS_ADJUST = builder.comment(".")
            .defineInRange("BM_LEFT_X_POS_ADJUST", 0, -10000, 10000);
        BM_RIGHT_Y_POS_ADJUST = builder.comment(".")
            .defineInRange("BM_RIGHT_Y_POS_ADJUST", 0, -10000, 10000);
        BM_RIGHT_X_POS_ADJUST = builder.comment(".")
            .defineInRange("BM_RIGHT_X_POS_ADJUST", 0, -10000, 10000);

        builder.pop();

        builder.comment("Middle Bar Settings")
            .push("Middle Bars");

        MIDDLE_Y_POS_ADJUST = builder.comment(".")
            .defineInRange("MIDDLE_Y_POS_ADJUST", 0, -10000, 10000);
        MIDDLE_X_POS_ADJUST = builder.comment(".")
            .defineInRange("MIDDLE_X_POS_ADJUST", 0, -10000, 10000);

        builder.pop();

        builder.comment("Top Left Settings")
            .push("Top Left Bars");

        TOP_LEFT_Y_POS_ADJUST = builder.comment(".")
            .defineInRange("TOP_LEFT_Y_POS_ADJUST", 0, -10000, 10000);
        TOP_LEFT_X_POS_ADJUST = builder.comment(".")
            .defineInRange("TOP_LEFT_X_POS_ADJUST", 0, -10000, 10000);

        builder.pop();

        builder.comment("Vanilla Bar Settings")
            .push("Vanilla Bars");

        LEFT_VANILLA_LIKE_BARS_Y__POS_ADJUST = builder.comment(".")
            .defineInRange("LEFT_VANILLA_LIKE_BARS_Y__POS_ADJUST", 0, -10000, 10000);
        RIGHT_VANILLA_LIKE_BARS_Y__POS_ADJUST = builder.comment(".")
            .defineInRange("RIGHT_VANILLA_LIKE_BARS_Y__POS_ADJUST", 0, -10000, 10000);

        builder.pop();
    }

}
