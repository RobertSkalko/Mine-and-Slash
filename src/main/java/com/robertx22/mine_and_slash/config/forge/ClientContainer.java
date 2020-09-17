package com.robertx22.mine_and_slash.config.forge;

import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.NeatConfig;
import com.robertx22.mine_and_slash.config.forge.parts.DmgParticleConfig;
import com.robertx22.mine_and_slash.uncommon.enumclasses.PlayerGUIs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

    public ForgeConfigSpec.IntValue AZURE_X_POS_ADJUST;
    public ForgeConfigSpec.IntValue AZURE_Y_POS_ADJUST;

    public ForgeConfigSpec.IntValue BMC_LEFT_X_POS_ADJUST;
    public ForgeConfigSpec.IntValue BMC_LEFT_Y_POS_ADJUST;
    public ForgeConfigSpec.IntValue BMC_RIGHT_X_POS_ADJUST;
    public ForgeConfigSpec.IntValue BMC_RIGHT_Y_POS_ADJUST;
    
    public ForgeConfigSpec.IntValue MIDDLE_Y_POS_ADJUST;
    
    public ForgeConfigSpec.IntValue TOPLEFT_X_POS_ADJUST;
    public ForgeConfigSpec.IntValue TOPLEFT_Y_POS_ADJUST;

    public EnumValue<PlayerGUIs> PLAYER_GUI_TYPE;

    public static int AzureXAdjust;
    public static int AzureYAdjust;

    public static int BMCLeftXAdjust;
    public static int BMCLeftYAdjust;
    public static int BMCRightXAdjust;
    public static int BMCRightYAdjust;

    public static int MiddleYAdjust;

    public static int TopLeftXAdjust;
    public static int TopLeftYAdjust;

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == ClientContainer.spec) {
			bakeConfig();
		}
	}

	public static void bakeConfig() {
        AzureXAdjust = INSTANCE.AZURE_X_POS_ADJUST.get();
        AzureYAdjust = INSTANCE.AZURE_Y_POS_ADJUST.get();

        BMCLeftXAdjust = INSTANCE.BMC_LEFT_X_POS_ADJUST.get();
        BMCLeftYAdjust = INSTANCE.BMC_LEFT_Y_POS_ADJUST.get();
        BMCRightXAdjust = INSTANCE.BMC_RIGHT_X_POS_ADJUST.get();
        BMCRightYAdjust = INSTANCE.BMC_RIGHT_Y_POS_ADJUST.get();

        MiddleYAdjust = INSTANCE.MIDDLE_Y_POS_ADJUST.get();

        TopLeftXAdjust = INSTANCE.TOPLEFT_X_POS_ADJUST.get();
        TopLeftYAdjust = INSTANCE.TOPLEFT_Y_POS_ADJUST.get();
	}

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

        AZURE_Y_POS_ADJUST = builder.comment("Adjusts All Bars Downwards")
            .defineInRange("AZURE_Y_POS_ADJUST", 0, 0, 1000);
        AZURE_X_POS_ADJUST = builder.comment("Adjusts All Bars Rightwards")
            .defineInRange("AZURE_X_POS_ADJUST", 0, 0, 1000);

        builder.pop();

        builder.comment("Bottom Middle Corner Bar Settings")
            .push("Bottom Middle Corner Bars");

        BMC_LEFT_X_POS_ADJUST = builder.comment("Adjusts HP/XP Leftwards")
            .defineInRange("BMC_LEFT_X_POS_ADJUST", 0, 0, 1000);
        BMC_LEFT_Y_POS_ADJUST = builder.comment("Adjusts HP/XP Upwards")
            .defineInRange("BMC_LEFT_Y_POS_ADJUST", 0, 0, 1000);
        BMC_RIGHT_X_POS_ADJUST = builder.comment("Adjusts Mana/Energy Rightwards")
            .defineInRange("BMC_RIGHT_X_POS_ADJUST", 0, 0, 1000);
        BMC_RIGHT_Y_POS_ADJUST = builder.comment("Adjusts Mana/Energy Upwards")
            .defineInRange("BMC_RIGHT_Y_POS_ADJUST", 0, 0, 1000);

        builder.pop();

        builder.comment("Middle Bar Settings")
            .push("Middle Bars");

        MIDDLE_Y_POS_ADJUST = builder.comment("Adjusts All Bars Upwards")
            .defineInRange("MIDDLE_Y_POS_ADJUST", 0, 0, 1000);

        builder.pop();

        builder.comment("Top Left Settings")
            .push("Top Left Bars");

        TOPLEFT_Y_POS_ADJUST = builder.comment("Adjusts All Bars Downwards")
            .defineInRange("TOPLEFT_Y_POS_ADJUST", 0, 0, 1000);
        TOPLEFT_X_POS_ADJUST = builder.comment("Adjusts All Bars Rightwards")
            .defineInRange("TOPLEFT_X_POS_ADJUST", 0, 0, 1000);

        builder.pop();

        builder.comment("Vanilla Bar Settings")
            .push("Vanilla Bars");

        LEFT_VANILLA_LIKE_BARS_Y__POS_ADJUST = builder.comment(".")
            .defineInRange("LEFT_VANILLA_LIKE_BARS_Y__POS_ADJUST", 0, 0, 1000);
        RIGHT_VANILLA_LIKE_BARS_Y__POS_ADJUST = builder.comment(".")
            .defineInRange("RIGHT_VANILLA_LIKE_BARS_Y__POS_ADJUST", 0, 0, 1000);

        builder.pop();
    }

}
