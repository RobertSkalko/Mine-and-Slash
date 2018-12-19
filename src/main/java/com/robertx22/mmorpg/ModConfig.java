package com.robertx22.mmorpg;

import java.util.HashMap;
import java.util.Map;

import com.robertx22.database.gearitemslots.Axe;
import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.uncommon.enumclasses.EntitySystemChoice;

import net.minecraft.item.Item;
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

    @Config.Name("Entity Config")
    @Config.LangKey("mmorpg.word.entities")
    public static EntityConfigs EntityTypeConfig = new EntityConfigs();

    @Config.Name("Rarity Weights")
    @Config.LangKey("mmorpg.config.rarity_weights")
    public static RarityWeights RarityWeightConfig = new RarityWeights();

    @Config.Name("Items Under System")
    @Config.Comment("When items are crafted, they get stats like items from my mod. Put IDs here, like minecraft:diamond_sword. This is not my intented way to play the mod but it should be good for modpack makers or some who want an easier way to gear up.")
    @Config.LangKey("mmorpg.config.items_under_system")
    public static ItemsUnderSystem ItemsUnderSystem = new ItemsUnderSystem();

    @Config.Name("Map Dimensions Config")
    @Config.Comment("")
    @Config.LangKey("mmorpg.config.map_dimensions_config")
    public static MapDimensionsConfig MapDimensions = new MapDimensionsConfig();

    static {

	// add blank slots
	for (int i = 0; i < 100; i++) {
	    String str = String.format("%07d", i);

	    ItemsUnderSystem.Swords.put(str, "");
	    ItemsUnderSystem.Axes.put(str, "");
	    ItemsUnderSystem.Hammers.put(str, "");
	    ItemsUnderSystem.Chests.put(str, "");
	    ItemsUnderSystem.Pants.put(str, "");
	    ItemsUnderSystem.Helmets.put(str, "");
	    ItemsUnderSystem.Boots.put(str, "");
	    ItemsUnderSystem.Rings.put(str, "");
	    ItemsUnderSystem.Pants.put(str, "");
	    ItemsUnderSystem.Necklaces.put(str, "");
	    ItemsUnderSystem.Charms.put(str, "");
	    ItemsUnderSystem.Bracelets.put(str, "");

	}
    }

    @Config.Name("Server")
    @Config.LangKey("mmorpg.config.server")
    public static ServerContainer Server = new ServerContainer();

    @Config.Name("Droprates")
    @Config.LangKey("mmorpg.config.droprates")
    public static DropRatesContainer DropRates = new DropRatesContainer();

    public static class RarityWeights {

	@Config.Name("Item Weights")
	@Config.LangKey("mmorpg.word.item")
	@Config.Comment("")
	public RarityWeight ITEMS = new RarityWeight();

	@Config.Name("Mob Weights")
	@Config.LangKey("mmorpg.word.mob")
	@Config.Comment("")
	public RarityWeight MOBS = new RarityWeight();

	@Config.Name("Map Weights")
	@Config.LangKey("mmorpg.word.map")
	@Config.Comment("")
	public RarityWeight MAPS = new RarityWeight();

	@Config.Name("Currency Weights")
	@Config.LangKey("mmorpg.word.currency")
	@Config.Comment("")
	public RarityWeight CURRENCY = new RarityWeight();

	@Config.Name("Spells Weights")
	@Config.LangKey("mmorpg.word.spell")
	@Config.Comment("")
	public RarityWeight SPELLS = new RarityWeight();

    }

    public static class RarityWeight {

	@Config.Name("Common")
	@Config.LangKey("mmorpg.rarity.common")
	@Config.Comment("")
	public int COMMON_WEIGHT = 25000;

	@Config.Name("Uncommon")
	@Config.LangKey("mmorpg.rarity.uncommon")
	@Config.Comment("")
	public int UNCOMMON_WEIGHT = 20000;

	@Config.Name("Rare")
	@Config.LangKey("mmorpg.rarity.rare")
	@Config.Comment("")
	public int RARE_WEIGHT = 5000;

	@Config.Name("Epic")
	@Config.LangKey("mmorpg.rarity.epic")
	@Config.Comment("")
	public int EPIC_WEIGHT = 3000;

	@Config.Name("Legendary")
	@Config.LangKey("mmorpg.rarity.legendary")
	@Config.Comment("")
	public int LEGENDARY_WEIGHT = 1250;

	@Config.Name("Mythical")
	@Config.LangKey("mmorpg.rarity.mythical")
	@Config.Comment("")
	public int MYTHICAL_WEIGHT = 300;

    }

    public static class EntityConfigs {

	@Config.Name("NPC Config")
	@Config.LangKey("mmorpg.word.npc")
	@Config.Comment("")
	public NPCConfig NPC_CONFIG = new NPCConfig();

	@Config.Name("Mob Config")
	@Config.LangKey("mmorpg.word.mob")
	@Config.Comment("")
	public MobConfig MOB_CONFIG = new MobConfig();

	@Config.Name("Animal Config")
	@Config.LangKey("mmorpg.word.animal")
	@Config.Comment("")
	public AnimalConfig ANIMAL_CONFIG = new AnimalConfig();

	@Config.Name("Other Entities Config")
	@Config.LangKey("mmorpg.word.other_entities")
	@Config.Comment("")
	public OtherEntityConfig OTHER_CONFIG = new OtherEntityConfig();

    }

    public static class OtherEntityConfig {

	@Config.Name("Loot Multi")
	@Config.LangKey("mmorpg.config.loot_multi")
	@Config.Comment("")
	public float LOOT_MULTI = 0;

	@Config.Name("Exp Multi")
	@Config.LangKey("mmorpg.config.exp_multi")
	@Config.Comment("")
	public float EXP_MULTI = 0;

    }

    public static class MobConfig {

	@Config.Name("Loot Multi")
	@Config.LangKey("mmorpg.config.loot_multi")
	@Config.Comment("")
	public float LOOT_MULTI = 1F;

	@Config.Name("Exp Multi")
	@Config.LangKey("mmorpg.config.exp_multi")
	@Config.Comment("")
	public float EXP_MULTI = 1F;

    }

    public static class AnimalConfig {

	@Config.Name("Loot Multi")
	@Config.LangKey("mmorpg.config.loot_multi")
	@Config.Comment("")
	public float LOOT_MULTI = 0.01F;

	@Config.Name("Exp Multi")
	@Config.LangKey("mmorpg.config.exp_multi")
	@Config.Comment("")
	public float EXP_MULTI = 0.05F;

    }

    public static class NPCConfig {

	@Config.Name("Loot Multi")
	@Config.LangKey("mmorpg.config.loot_multi")
	@Config.Comment("")
	public float LOOT_MULTI = 0.3F;

	@Config.Name("Exp Multi")
	@Config.LangKey("mmorpg.config.exp_multi")
	@Config.Comment("")
	public float EXP_MULTI = 0.4F;

    }

    public static class ItemsUnderSystem {

	@Config.Name("Crafted Items are Under System")
	@Config.LangKey("mmorpg.config.crafted_items_are_under_system")
	@Config.Comment("This enables crafted items to get stats, if their ID matches. So if you put minecraft:iron_axe in Swords, the iron axe gets sword stats and sword attack functionality.")
	public boolean CRAFTED_ITEMS_UNDER_SYSTEM = false;

	@Config.Ignore
	public Map<String, String> all = new HashMap<String, String>();

	public void sync() {

	    all.clear();

	    syncOne(Swords, all, new Sword().GUID());
	    syncOne(Chests, all, new Chest().GUID());
	    syncOne(Pants, all, new Pants().GUID());
	    syncOne(Helmets, all, new Helmet().GUID());
	    syncOne(Boots, all, new Boots().GUID());
	    syncOne(Rings, all, new Ring().GUID());
	    syncOne(Necklaces, all, new Necklace().GUID());
	    syncOne(Charms, all, new Charm().GUID());
	    syncOne(Bracelets, all, new Bracelet().GUID());
	    syncOne(Hammers, all, new Hammer().GUID());
	    syncOne(Axes, all, new Axe().GUID());

	}

	public GearItemSlot getType(Item item) {

	    try {
		sync();

		String id = item.getRegistryName().toString();

		if (all.containsKey(id)) {
		    String type = all.get(id);

		    return GearTypes.All.get(type);
		} else {
		    return null;
		}
	    } catch (Exception e) {
		e.printStackTrace();
		return null;
	    }
	}

	private void syncOne(Map<String, String> items, Map<String, String> all, String gearType) {

	    for (String id : items.values()) {

		if (all.containsKey(id) && !id.isEmpty()) {
		    System.out
			    .println("Error, item under system is contained under more than 1 gear type, please check "
				    + Ref.MODID + " config. Id: " + id);

		} else {
		    all.put(id, gearType);
		}
	    }
	}

	@Config.Name("Swords")
	public Map<String, String> Swords = new HashMap<String, String>();

	@Config.Name("Chests")
	public Map<String, String> Chests = new HashMap<String, String>();

	@Config.Name("Pants")
	public Map<String, String> Pants = new HashMap<String, String>();

	@Config.Name("Boots")
	public Map<String, String> Boots = new HashMap<String, String>();

	@Config.Name("Helmets")
	public Map<String, String> Helmets = new HashMap<String, String>();

	@Config.Name("Rings")
	public Map<String, String> Rings = new HashMap<String, String>();

	@Config.Name("Necklaces")
	public Map<String, String> Necklaces = new HashMap<String, String>();

	@Config.Name("Charms")
	public Map<String, String> Charms = new HashMap<String, String>();

	@Config.Name("Bracelets")
	public Map<String, String> Bracelets = new HashMap<String, String>();

	@Config.Name("Hammers")
	public Map<String, String> Hammers = new HashMap<String, String>();

	@Config.Name("Axes")
	public Map<String, String> Axes = new HashMap<String, String>();

    }

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

	@Config.Name("Non Mod Damage Multiplier")
	@Config.LangKey("mmorpg.config.non_mod_damage_multiplier")
	@Config.Comment("0 to 1. 0 means other types of damage (not from my mod) are nullified. 1 means they are the same. Please leave at default value unless required to change it. Too high value means you do 2 different types of damage, vanilla damage (sword that does half a mob's health) plus my mod's damage.. Which will lead to you both one shotting mobs and mobs one shotting you too.")
	public float NON_MOD_DAMAGE_MULTI = 0.03F;

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

    public static class MapDimensionsConfig {

	@Config.Name("Map ID Start")
	@Config.LangKey("mmorpg.config.map_id_start")
	@Config.Comment("The start of dimension IDs used for maps. It goes down from here.. to -1463.. -1464 etc")
	public int MAP_ID_START = -1462;

    }

    public static class DimensionConfigs {

	public DimensionConfigs() {

	}

	public DimensionConfigs(int id, int min, int max) {
	    this.DIMENSION_id = id;
	    this.MINIMUM_MOB_LEVEL = min;
	    this.MAXIMUM_MOB_LEVEL = max;
	}

	public DimensionConfigs(int id, int distance, int area, int min, int max) {
	    this.DIMENSION_id = id;
	    this.MOB_LEVEL_PER_DISTANCE = distance;
	    this.MOB_LEVEL_ONE_AREA = area;
	    this.MINIMUM_MOB_LEVEL = min;
	    this.MAXIMUM_MOB_LEVEL = max;
	}

	@Config.Name("Dimension ID")
	@Config.LangKey("mmorpg.config.dimension_id")
	@Config.Comment("0 is overwold for example")
	public int DIMENSION_id = 0;

	@Config.Name("Mob Level Per Distance")
	@Config.LangKey("mmorpg.config.mob_lvl_per_distance")
	@Config.Comment("How fast you want mobs to level up based on distance. Higher value means slower leveling.")
	public int MOB_LEVEL_PER_DISTANCE = 14500;

	@Config.Name("Mob Level One Area")
	@Config.LangKey("mmorpg.config.mob_lvl_one_area")
	@Config.Comment("How big you want level 1 mob area to be. Bigger value means bigger area")
	public int MOB_LEVEL_ONE_AREA = 25000;

	@Config.Name("Maximum mob level")
	@Config.LangKey("mmorpg.config.maximum_mob_level")
	@Config.Comment("Select maximum level")
	public int MAXIMUM_MOB_LEVEL = 100;

	@Config.Name("Minimum mob level")
	@Config.LangKey("mmorpg.config.minimum_mob_level")
	@Config.Comment("Select maximum level for mobs in normal worlds like vanilla surface, nether, end. This doesn't affect max level for map worlds from my mod! Note: reasoning for low level cap here is to prevent BIS gear being farmable by mob spawners, which ruin the fun")
	public int MINIMUM_MOB_LEVEL = 25;

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
