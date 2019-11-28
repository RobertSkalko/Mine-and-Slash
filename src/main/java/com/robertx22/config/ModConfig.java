package com.robertx22.config;

import java.util.ArrayList;

import com.robertx22.config.RarityDropratesConfig.RarityWeights;
import com.robertx22.mmorpg.Ref;

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
	    list.add(extra10);
	    list.add(extra10);
	    list.add(extra12);
	    list.add(extra13);
	    list.add(extra14);
	    list.add(extra15);
	    list.add(extra16);
	    list.add(extra17);
	    list.add(extra18);
	    list.add(extra19);
	    list.add(extra20);
	    list.add(extra21);
	    list.add(extra22);
	    list.add(extra23);
	    list.add(extra24);
	    list.add(extra25);
	    list.add(extra26);
	    list.add(extra27);
	    list.add(extra28);
	    list.add(extra29);
	    list.add(extra30);
	    list.add(extra31);
	    list.add(extra32);
	    list.add(extra33);
	    list.add(extra34);
	    list.add(extra35);
	    list.add(extra36);

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
	
	@Config.Name("Extra 10")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra10 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 11")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra11 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 12")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra12 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 13")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra13 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 14")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra14 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 15")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra15 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 16")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra16 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 17")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra17 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 18")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra18 = DimensionConfigs.DefaultExtra();
	
	@Config.Name("Extra 19")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra19 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 20")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra20 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 21")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra21 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 22")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra22 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 23")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra23 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 24")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra24 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 25")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra25 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 26")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra26 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 27")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra27 = DimensionConfigs.DefaultExtra();
	
	@Config.Name("Extra 28")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra28 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 29")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra29 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 30")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra30 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 31")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra31 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 32")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra32 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 33")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra33 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 34")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra34 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 35")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra35 = DimensionConfigs.DefaultExtra();

	@Config.Name("Extra 36")
	@Config.LangKey("")
	@Config.Comment("Here you can add extra mod dimensions to the config too! Say you want aether to have mobs start at lvl 50.")
	public DimensionConfigs extra36 = DimensionConfigs.DefaultExtra();

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
