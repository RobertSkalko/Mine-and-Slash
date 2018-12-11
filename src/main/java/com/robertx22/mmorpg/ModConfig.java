package com.robertx22.mmorpg;

import com.robertx22.uncommon.enumclasses.EntitySystemChoice;

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

    @Config.Name("Client")
    @Config.LangKey("mmorpg.config.client")
    public static ClientContainer Client = new ClientContainer();

    @Config.Name("Server")
    @Config.LangKey("mmorpg.config.server")
    public static ServerContainer Server = new ServerContainer();

    @Config.Name("Droprates")
    @Config.LangKey("mmorpg.config.droprates")
    public static DropRatesContainer DropRates = new DropRatesContainer();

    public static class ClientContainer {

	@Config.Name("Render Item On Ground Rarity Particles")
	@Config.LangKey("mmorpg.config.item_on_ground_particles")
	@Config.Comment("Might help with finding gear, but could also be laggy")
	public boolean RENDER_ITEM_ENTITY_RARITY_PARTICLES = false;

	@Config.Name("Render Chat Combat Log")
	@Config.LangKey("mmorpg.config.chat_combat_log")
	@Config.Comment("Show/Disable Chat Damage Numbers")
	public boolean RENDER_CHAT_COMBAT_LOG = false;

	@Config.Name("Render Floating Damage Numbers")
	@Config.LangKey("mmorpg.config.floating_damage_numbers")
	@Config.Comment("Show/Disable Floating Damage Numbers when you attack mobs")
	public boolean RENDER_FLOATING_DAMAGE = true;

	@Config.Name("Render Mob Health Bar")
	@Config.LangKey("mmorpg.config.mob_health_bag")
	@Config.Comment("Show/Disable mob health bars")
	public boolean RENDER_MOB_HEALTH_GUI = true;

	@Config.Name("Show Low Ene/Mana Warnings")
	@Config.LangKey("mmorpg.config.low_resource_warnings")
	@Config.Comment("Posts them in chat if you can't cast spell or attakc")
	public boolean SHOW_LOW_ENERGY_MANA_WARNING = false;

	@Config.Name("Show Floating Exp")
	@Config.LangKey("mmorpg.config.floating_exp")
	@Config.Comment("Shows how much exp you got from a mob kill. Can get annoying when you can't see your damage i assume.")
	public boolean SHOW_FLOATING_EXP = false;

	@Config.Name("Player Gui Overlay Type")
	@Config.LangKey("mmorpg.config.player_gui_overlay_type")
	@Config.Comment("Choose different Gui styles for hp mana etc overlay")
	public Player_GUIs PLAYER_GUI_TYPE = Player_GUIs.Bottom_Middle_Corners;

    }

    public static class DropRatesContainer {

	@Config.Name("Map Droprate")
	@Config.LangKey("mmorpg.config.map_droprate")
	@Config.Comment("Adventure maps, temporary items that create a temporary world to kill mobs in")
	public float MAP_DROPRATE = 1;

	@Config.Name("Gear Droprate")
	@Config.LangKey("mmorpg.config.gear_droprate")
	@Config.Comment("")
	public float GEAR_DROPRATE = 7.5F;

	@Config.Name("Unique  Droprate")
	@Config.LangKey("mmorpg.config.unique_droprate")
	@Config.Comment("Unique gear items that drop from maps only")
	public float UNIQUE_DROPRATE = 0.15F;

	@Config.Name("Spell Droprate")
	@Config.LangKey("mmorpg.config.spell_droprate")
	@Config.Comment("")
	public float SPELL_DROPRATE = 3;

	@Config.Name("Currency Droprate")
	@Config.LangKey("mmorpg.config.currency_droprate")
	@Config.Comment("Currency, or items you use to modify gear")
	public float CURRENCY_DROPRATE = 3;

    }

    public static class ServerContainer {

	@Config.Name("Mob Level Per Distance")
	@Config.LangKey("mmorpg.config.mob_lvl_per_distance")
	@Config.Comment("How fast you want mobs to level up based on distance. Higher value means slower leveling.")
	public int MOB_LEVEL_PER_DISTANCE = 14500;

	@Config.Name("Mob Level One Area")
	@Config.LangKey("mmorpg.config.mob_lvl_one_area")
	@Config.Comment("How big you want level 1 mob area to be. Bigger value means bigger area")
	public int MOB_LEVEL_ONE_AREA = 25000;

	@Config.Name("Player Level Cap")
	@Config.LangKey("mmorpg.config.player_level_cap")
	@Config.Comment("Select maximum level")
	public int MAXIMUM_PLAYER_LEVEL = 100;

	@Config.Name("Normal Worlds Mob Level Cap")
	@Config.LangKey("mmorpg.config.normal_world_max_lvl_cap")
	@Config.Comment("Select maximum level for mobs in normal worlds like vanilla surface, nether, end. This doesn't affect max level for map worlds from my mod! Note: reasoning for low level cap here is to prevent BIS gear being farmable by mob spawners, which ruin the fun")
	public int MAXIMUM_NORMAL_WORLD_MOB_LEVEL = 25;

	@Config.Name("Exp multiplier")
	@Config.LangKey("mmorpg.config.exp_multiplier")
	@Config.Comment("Want to level faster or slower? 1 is normal, 0.5 half speed, 2 double")
	public float EXPERIENCE_MULTIPLIER = 1F;

	@Config.Name("Level Ups Cost Tokens")
	@Config.LangKey("mmorpg.config.levelup_cost_token")
	@Config.Comment("If false, player levels up automatically, if true, you need to craft a token and use it. Basically hardcore mode, every level needs diamonds.")
	public boolean LEVEL_UPS_COST_TOKEN = false;

	@Config.Name("Max Players Per Map")
	@Config.LangKey("mmorpg.config.max_players_in_map")
	@Config.Comment("")
	public int MAX_PLAYERS_PER_MAP = 5;

	@Config.Name("Entities Under System")
	@Config.LangKey("mmorpg.config.entities_under_system")
	@Config.Comment("By system, it means they get my mod's health, damage stats etc. They can also use the gear (if possible). All entities means even sheep or villagers are under the system. ")
	public EntitySystemChoice ENTITIES_UNDER_SYSTEM = EntitySystemChoice.All_Entities;

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
