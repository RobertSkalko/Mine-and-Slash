package com.robertx22.mmorpg;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Ref.MODID)
@Config.LangKey("mmorpg.config.title")
public class ModConfig {

    public static OptionsContainer Options = new OptionsContainer();
    public static CheatsContainer Cheats = new CheatsContainer();

    public static class OptionsContainer {

        @Config.RangeInt(min = 0, max = 4)
        @Config.Comment("Set to 1 if you only want to pickup rare gear, 2 if only epic items etc")
        public int PICKUP_ONLY_RARITIES_GEAR = 0;

        @Config.RangeInt(min = 0, max = 4)
        @Config.Comment("Set to 1 if you only want to pickup rare sockets, 2 if only epic sockets etc")
        public int PICKUP_ONLY_RARITIES_SOCKETS = 0;

        @Config.Comment("Blocks EXP GAIN, so you can grind better gear")
        public boolean BLOCK_EXP_GAIN = false;

        @Config.Comment("Disables showing item names on the ground")
        public boolean DISABLE_ITEM_NAME_RENDER = false;

        @Config.Comment("Disables showing powder item names on the ground")
        public boolean HIDE_POWDER_NAME_RENDER = false;

        @Config.RangeInt(min = 0, max = 4)
        @Config.Comment("Set to 1 if you only want to render names of rare sockets, 2 if only epic sockets etc")
        public int RENDER_ONLY_RARITIES_SOCKETS = 0;

        @Config.RangeInt(min = 0, max = 4)
        @Config.Comment("Set to 1 if you only want to render names of rare gear, 2 if only epic gear etc")
        public int RENDER_ONLY_RARITIES_GEAR = 0;
    }

    public static class CheatsContainer {

        @Config.RangeDouble(min = 0.3, max = 10)
        @Config.Comment("Increase or decrease Difficulty, 1 is normal, 2 is double, 0.5 is halved")
        public float DIFFICULTY = 1F;

        @Config.RangeDouble(min = 0.1, max = 20)
        @Config.Comment("Increase or decrease GEAR drop rates, 1 is normal, 2 is double, 0.5 is halved")
        public float DROP_RATES = 1F;

        @Config.RangeDouble(min = 0.1, max = 3)
        @Config.Comment("Increase or decrease EXPERIENCE rates, 1 is normal, 2 is double, 0.5 is halved")
        public float EXP_RATES = 1F;

        @Config.RangeDouble(min = 0.1, max = 3)
        @Config.Comment("Increase or decrease CURRENCY drop rates, 1 is normal, 2 is double, 0.5 is halved")
        public float CURRENCY_RATES = 1F;

        @Config.Comment("Kill anything in 1 shot, mobs deal 0 damage, for testing purposes")
        public boolean GOD_MODE = false;

        @Config.Comment("Enables commands, this is meant for testing and not for normal gameplay")
        public boolean CHEAT_MODE = false;
        
    }

    @Mod.EventBusSubscriber
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent event) {
            if (event.getModID().equals(Ref.MODID)) {
                ConfigManager.sync(Ref.MODID, Config.Type.INSTANCE);
            }
        }

    }

}
