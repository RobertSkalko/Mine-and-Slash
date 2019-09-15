package com.robertx22.mmorpg;

import com.libraries.rabbit.gui.RabbitGui;
import com.robertx22.blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.blocks.item_modify_station.StartupModify;
import com.robertx22.blocks.map_device.StartupMap;
import com.robertx22.blocks.repair_station.StartupRepair;
import com.robertx22.blocks.salvage_station.StartupSalvage;
import com.robertx22.compat.ebwizardry;
import com.robertx22.compat.fireice;
import com.robertx22.config.ModConfig;
import com.robertx22.config.non_mine_items.Serialization;
import com.robertx22.dimensions.ChestGenerator;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.items.unique_items.UniqueItemRegister;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.registers.CommandRegisters;
import com.robertx22.mmorpg.registers.GearItemRegisters;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.MessagePackage;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.MapDatas;
import com.robertx22.uncommon.capability.PlayerDeathItems;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.oregen.OreGen;
import com.robertx22.uncommon.testing.TestManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
@Mod(modid = Ref.MODID, version = Ref.VERSION, name = Ref.NAME,
    dependencies = "required-after:baubles;required-after:patchouli")
public class Main {

  @SidedProxy(clientSide = "com.robertx22.mmorpg.proxy.ClientProxy",
      serverSide = "com.robertx22.mmorpg.proxy.ServerProxy")
  public static IProxy proxy;

  @Instance(value = Ref.MODID)
  public static Main instance;

  public static final SimpleNetworkWrapper Network =
      NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MODID);

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {



    Serialization.generateConfig(event);
    Serialization.loadConfig(event);



    UniqueItemRegister.registerAll();

    GameRegistry.registerTileEntity(TileMapPortal.class,
        new ResourceLocation(Ref.MODID, "map_portal_tile"));

    proxy.preInit(event);

    proxy.registerRenders();

    GearItemRegisters.register();

    ItemOre.Register();
    StartupRepair.preInitCommon(event);
    StartupSalvage.preInitCommon(event);
    StartupModify.preInitCommon(event);
    StartupGearFactory.preInitCommon(event);
    StartupMap.preInitCommon(event);

    MinecraftForge.EVENT_BUS.register(new PlayerUnitPackage());
    MinecraftForge.EVENT_BUS.register(new EntityUnitPackage());
    MinecraftForge.EVENT_BUS.register(new DamageNumberPackage());
    MinecraftForge.EVENT_BUS.register(new ParticlePackage());
    MinecraftForge.EVENT_BUS.register(new WorldPackage());

    Network.registerMessage(PlayerUnitPackage.Handler.class, PlayerUnitPackage.class, 0,
        Side.CLIENT);
    Network.registerMessage(EntityUnitPackage.Handler.class, EntityUnitPackage.class, 1,
        Side.CLIENT);
    Network.registerMessage(DamageNumberPackage.Handler.class, DamageNumberPackage.class, 2,
        Side.CLIENT);
    Network.registerMessage(ParticlePackage.Handler.class, ParticlePackage.class, 3, Side.CLIENT);
    Network.registerMessage(WorldPackage.Handler.class, WorldPackage.class, 4, Side.CLIENT);
    Network.registerMessage(MessagePackage.Handler.class, MessagePackage.class, 5, Side.CLIENT);

    CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(),
        EntityData.DefaultImpl.class);

    CapabilityManager.INSTANCE.register(WorldData.IWorldData.class, new WorldData.Storage(),
        WorldData.DefaultImpl.class);

    CapabilityManager.INSTANCE.register(PlayerDeathItems.IPlayerDrops.class,
        new PlayerDeathItems.Storage(), PlayerDeathItems.DefaultImpl.class);

    ModMetadata modMeta = event.getModMetadata();

  }

  @EventHandler
  public void init(FMLInitializationEvent event) {

    RabbitGui.proxy.init();
    proxy.init(event);

    TestManager.RunAllTests();

    // RegisterBiomes.initBiomeManagerAndDictionary();

    if (ModConfig.Server.GENERATE_ORES) {
      int chance = 6;
      int amount = 7;

      for (int i = 0; i < ItemOre.Blocks.values().size(); i++) {
        GameRegistry.registerWorldGenerator(
            new OreGen(ItemOre.Blocks.get(i), amount - i, 10, 75, chance - i), 0);
      }

    }

    GameRegistry.registerWorldGenerator(new ChestGenerator(), 5);

  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {

    proxy.postInit(event);
    RabbitGui.proxy.postInit();
    Serialization.generateConfigTut(event);
    if (Loader.isModLoaded("iceandfire")) {
    	new fireice();
    }
    if (Loader.isModLoaded("ebwizardry")) {
    	new ebwizardry();
    }

  }

  @EventHandler
  public void start(FMLServerStartingEvent event) {

    registerDims(event);

    proxy.serverStarting(event);

    CommandRegisters.Register(event);

  }

  @EventHandler
  public void stop(FMLServerStoppedEvent event) {
    MapDatas.unregisterDimensions(); // every save game has it's own dimensions, otherwise when you
                                     // switch saves you
    // also get dimensions from your last save, which isn't nice

  }

  @EventHandler
  public void stopping(FMLServerStoppingEvent event) {

  }

  private void registerDims(FMLServerStartingEvent event) {

    String name = MapDatas.getLoc();
    MapDatas data =
        (MapDatas) event.getServer().worlds[0].getMapStorage().getOrLoadData(MapDatas.class, name);
    if (data == null) {
      event.getServer().worlds[0].getMapStorage().setData(name, new MapDatas(name));
      data = (MapDatas) event.getServer().worlds[0].getMapStorage().getOrLoadData(MapDatas.class,
          name);
    }

    data.registerDimensions();
  }

  @EventHandler
  public static void onWorldLoad(FMLServerStartedEvent event) {
    WorldServer world = DimensionManager.getWorld(0); // default world

    if (world != null) {
      if (ModConfig.Server.DISABLE_VANILLA_HP_REGEN) {
        world.getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
      }
    }

  }
}
