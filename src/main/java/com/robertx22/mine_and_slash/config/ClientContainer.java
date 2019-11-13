package com.robertx22.mine_and_slash.config;

import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.NeatConfig;
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
        final Pair<ClientContainer, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
                .configure(ClientContainer::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    public NeatConfig neatConfig;
    public DmgParticleConfig dmgParticleConfig;

    public BooleanValue SHOW_AFFIXED_NAME;
    public BooleanValue RENDER_MOB_HEALTH_GUI;
    public BooleanValue SHOW_LOW_ENERGY_MANA_WARNING;
    public BooleanValue SHOW_VANILLA_HEARTS;
    public BooleanValue SHOW_UNMET_GEAR_REQUIREMENTS_GUI;
    public ForgeConfigSpec.IntValue REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES;

    public EnumValue<PlayerGUIs> PLAYER_GUI_TYPE;

    ClientContainer(ForgeConfigSpec.Builder builder) {
        builder.comment("Client Settings").push(NAME);

        neatConfig = builder.configure(NeatConfig::new).getLeft();
        dmgParticleConfig = builder.configure(DmgParticleConfig::new).getLeft();

        REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES = builder.comment(".")
                .defineInRange("REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES", 35, 0, 1000);

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
    }

}
