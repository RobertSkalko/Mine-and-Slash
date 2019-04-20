package com.robertx22.mmorpg.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.robertx22.database.gearitemslots.Axe;
import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Bow;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Hammer;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.RarityDropratesConfig.RarityWeights;

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

    @Config.Name("Base Player Stats")
    @Config.LangKey("mmorpg.word.base_player_stats")
    @Config.Comment("Be careful! Some Stats don't scale with levels so they shouldn't have any per level bonuses. You can still do it but you've been warned!")
    public static StatConfig BasePlayerStats = new StatConfig();

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

    @Config.Name("Dimensions Config")
    @Config.Comment("")
    @Config.LangKey("mmorpg.config.dimensions_config")
    public static DimensionsConfig Dimensions = new DimensionsConfig();

    @Config.Name("Server")
    @Config.LangKey("mmorpg.config.server")
    public static ServerContainer Server = new ServerContainer();

    @Config.Name("Droprates")
    @Config.LangKey("mmorpg.config.droprates")
    public static DropRatesContainer DropRates = new DropRatesContainer();

    static {

	// add blank slots
	for (int i = 0; i < 10; i++) {
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
	    ItemsUnderSystem.Staffs.put(str, "");
	    ItemsUnderSystem.Bows.put(str, "");

	}
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
	    syncOne(Bows, all, new Bow().GUID());
	    syncOne(Staffs, all, new Staff().GUID());

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

	@Config.Name("Bows")
	public Map<String, String> Bows = new HashMap<String, String>();

	@Config.Name("Staffs")
	public Map<String, String> Staffs = new HashMap<String, String>();

    }

    public static class DimensionsConfig {

	public DimensionsContainer getAll() {

	    ArrayList<DimensionConfigs> list = new ArrayList<DimensionConfigs>();

	    list.add(overworld);
	    list.add(nether);
	    list.add(end);
	    list.add(extra1);
	    list.add(extra2);
	    list.add(extra3);
	    list.add(extra4);
	    list.add(extra5);
	    list.add(extra6);
	    list.add(extra7);
	    list.add(extra8);
	    list.add(extra9);

	    return new DimensionsContainer(list);

	}

	@Config.Name("Overworld")
	@Config.LangKey("mmorpg.config.overworld")
	@Config.Comment("")
	public DimensionConfigs overworld = DimensionConfigs.Overworld();

	@Config.Name("Nether")
	@Config.LangKey("mmorpg.config.overworld")
	@Config.Comment("")
	public DimensionConfigs nether = DimensionConfigs.Nether();

	@Config.Name("End")
	@Config.LangKey("mmorpg.config.overworld")
	@Config.Comment("")
	public DimensionConfigs end = DimensionConfigs.End();

	@Config.Name("Default")
	@Config.LangKey("mmorpg.config.default")
	@Config.Comment("If the dimension isn't in any other config, it defaults to values inside this. The dimension ID doesn't matter in this one.")
	public DimensionConfigs default_dim = DimensionConfigs.Overworld();

	@Config.Name("Extra 1")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra1 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 2")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra2 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 3")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra3 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 4")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra4 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 5")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra5 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 6")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra6 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 7")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra7 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 8")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra8 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 9")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra9 = DimensionConfigs.DefaultExtra();

    }

    public static class MapDimensionsConfig {

	@Config.Name("Map ID Start")
	@Config.LangKey("mmorpg.config.map_id_start")
	@Config.Comment("The start of dimension IDs used for maps. It goes down from here.. to -1463.. -1464 etc")
	public int MAP_ID_START = -1462;

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
