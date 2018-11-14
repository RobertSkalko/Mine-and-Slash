package com.robertx22.mmorpg;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Ref.MODID)
@Config.LangKey("mmorpg.config.title")
@EventBusSubscriber
public class ModConfig {

	public static GUIContainer Client = new GUIContainer();

	public static ServerContainer Server = new ServerContainer();

	public static class GUIContainer {

		@Config.Name("Render Chat Combat Log")
		@Config.Comment("Show/Disable Chat Damage Numbers")
		public boolean RENDER_CHAT_COMBAT_LOG = false;

		@Config.Name("Render Floating Damage Numbers")
		@Config.Comment("Show/Disable Floating Damage Numbers when you attack mobs")
		public boolean RENDER_FLOATING_DAMAGE = true;

		@Config.Name("Render Mob Health Bar")
		@Config.Comment("Show/Disable mob health bars")
		public boolean RENDER_MOB_HEALTH_GUI = true;

		@Config.Name("Announce world boss spawn")
		@Config.Comment("plays a dragon sound when world boss spawns")
		public boolean ANNOUNCE_WORLD_BOSS_SPAWN = false;

		@Config.Name("Player Gui Overlay Type")
		@Config.Comment("Choose different Gui styles for hp mana etc overlay")
		public Player_GUIs PLAYER_GUI_TYPE = Player_GUIs.Bottom_Middle_Corners;

	}

	public static class ServerContainer {

		@Config.Name("Mob Level Per Distance")
		@Config.Comment("How fast you want mobs to level up based on distance. Higher value means slower leveling.")
		public int MOB_LEVEL_PER_DISTANCE = 12500;

		@Config.Name("Mob Level One Area")
		@Config.Comment("How big you want level 1 mob area to be. Bigger value means bigger area")
		public int MOB_LEVEL_ONE_AREA = 25000;

		@Config.Name("Level Cap")
		@Config.Comment("Select maximum level")
		public int MAXIMUM_LEVEL = 100;

		@Config.Name("Exp multiplier")
		@Config.Comment("Want to level faster or slower? 1 is normal, 0.5 half speed, 2 double")
		public float EXPERIENCE_MULTIPLIER = 1F;

	}

	@Mod.EventBusSubscriber
	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent event) {
			if (event.getModID().equals(Ref.MODID)) {
				ConfigManager.sync(Ref.MODID, Config.Type.INSTANCE);
				System.out.println("Syncing Config");
			}
		}

	}

}
