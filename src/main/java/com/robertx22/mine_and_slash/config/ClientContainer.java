package com.robertx22.mine_and_slash.config;

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

    public BooleanValue SHOW_AFFIXED_NAME;
    public BooleanValue RENDER_FLOATING_DAMAGE;
    public BooleanValue RENDER_MOB_HEALTH_GUI;
    public BooleanValue SHOW_LOW_ENERGY_MANA_WARNING;
    public BooleanValue SHOW_FLOATING_EXP;
    public BooleanValue SHOW_VANILLA_HEARTS;

    public EnumValue<PlayerGUIs> PLAYER_GUI_TYPE;

    ClientContainer(ForgeConfigSpec.Builder builder) {
        builder.comment("Client Settings").push(NAME);

        SHOW_AFFIXED_NAME = builder.comment(".")
                .translation("mmorpg.config.show_item_affixes")
                .define("SHOW_AFFIXED_NAME", false);

        SHOW_VANILLA_HEARTS = builder.comment(".")
                .translation("mmorpg.config.show_vanilla_hearts")
                .define("SHOW_VANILLA_HEARTS", true);

        RENDER_FLOATING_DAMAGE = builder.comment(".")
                .translation("mmorpg.config.floating_damage_numbers")
                .define("RENDER_FLOATING_DAMAGE", true);

        RENDER_MOB_HEALTH_GUI = builder.comment(".")
                .translation("mmorpg.config.mob_health_bar")
                .define("RENDER_MOB_HEALTH_GUI", true);

        SHOW_LOW_ENERGY_MANA_WARNING = builder.comment(".")
                .translation("mmorpg.config.low_resource_warnings")
                .define("SHOW_LOW_ENERGY_MANA_WARNING", true);

        SHOW_FLOATING_EXP = builder.comment(".")
                .translation("mmorpg.config.floating_exp")
                .define("SHOW_FLOATING_EXP", true);

        PLAYER_GUI_TYPE = builder.comment(".")
                .translation("mmorpg.config.player_gui_overlay_type")
                .defineEnum("PLAYER_GUI_TYPE", PlayerGUIs.Bottom_Middle_Corners);

        builder.pop();
    }

}
