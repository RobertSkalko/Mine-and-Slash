package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.a_libraries.curios.CurioClientSetup;
import com.robertx22.mine_and_slash.a_libraries.curios.GenerateCurioDataJsons;
import com.robertx22.mine_and_slash.a_libraries.curios.RegisterCurioSlots;
import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.HealthBarRenderer;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItemsSerialization;
import com.robertx22.mine_and_slash.db_lists.bases.AllPreGenMapStats;
import com.robertx22.mine_and_slash.db_lists.initializers.Stats;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.proxy.ClientProxy;
import com.robertx22.mine_and_slash.mmorpg.proxy.IProxy;
import com.robertx22.mine_and_slash.mmorpg.proxy.ServerProxy;
import com.robertx22.mine_and_slash.mmorpg.registers.client.ContainerGuiRegisters;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.client.RenderRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.client.SpecialRenderRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.*;
import com.robertx22.mine_and_slash.mmorpg.registers.server.CommandRegister;
import com.robertx22.mine_and_slash.onevent.data_gen.OnGatherData;
import com.robertx22.mine_and_slash.onevent.world.OnStartResetMaps;
import com.robertx22.mine_and_slash.uncommon.develeper.CreateLangFile;
import com.robertx22.mine_and_slash.uncommon.gui.player_overlays.BarsGUI;
import com.robertx22.mine_and_slash.uncommon.testing.TestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(Ref.MODID)
@EventBusSubscriber
public class MMORPG {

    // DISABLE WHEN PUBLIC BUILD
    public static boolean RUN_DEV_TOOLS = false;

    public static long MAP_WORLD_SEED = 0;

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Ref.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public MMORPG() {

        System.out.println("Starting Mine and Slash");

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        RegisterEvents.register();

        ConfigRegister.register(); // MUST BE IN MAIN CLASS
        ConfigRegister.load();  // MUST BE IN MAIN CLASS

        OnStartResetMaps.OnStartResetMaps();

        SlashRegistry.init(); // after config registerAll

        StructurePieceRegisters.reg();

        bus.addListener(this::commonSetupEvent);
        bus.addListener(this::interModProcessEvent);
        bus.addListener(this::interModEnqueue);
        bus.addListener(new OnGatherData()::onGatherData);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            bus.addListener(this::clientSetup);
        });

        if (MAP_WORLD_SEED == 0) {
            MAP_WORLD_SEED = org.apache.commons.lang3.RandomUtils.nextLong();
        }

    }

    public void commonSetupEvent(FMLCommonSetupEvent event) {

        System.out.println(Ref.MODID + ":FMLCommonSetupEvent");

        PacketRegister.register();
        OreGenRegister.register();
        CapabilityRegister.register();
        CriteriaRegisters.register();
        WorldGenRegisters.register();

    }

    private void interModEnqueue(final InterModEnqueueEvent event) {
        System.out.println(Ref.MODID + ":InterModEnqueueEvent");
        RegisterCurioSlots.register(event);

    }

    private void interModProcessEvent(final InterModProcessEvent event) {
        System.out.println(Ref.MODID + ":InterModProcessEvent");
        ConfigItemsSerialization.INSTANCE.generateConfigTutorials();

    }

    public void clientSetup(final FMLClientSetupEvent event) {

        SpecialRenderRegister.register(event);
        CurioClientSetup.setup(event);
        MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
        KeybindsRegister.register();
        ContainerGuiRegisters.reg();
        RenderRegister.regRenders();

    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {
        CommandRegister.Register(event.getServer());
        ConfigRegister.regAndLoadNonForgeConfigs();

        if (RUN_DEV_TOOLS) { // CHANGE ON PUBLIC BUILDS TO FALSE
            TestManager.RunAllTests();
            CreateLangFile.create();
            GenerateCurioDataJsons.generate();

        }

    }

    @SubscribeEvent
    public static void onServerStop(FMLServerStoppedEvent event) {

    }

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent event) {

    }

    @SubscribeEvent
    public static void onServerStarted(FMLServerStartedEvent event) {

        if (ModConfig.INSTANCE.Server.DISABLE_VANILLA_HP_REGEN.get()) {

            GameRules.BooleanValue value = ServerLifecycleHooks.getCurrentServer()
                    .getGameRules()
                    .get(GameRules.NATURAL_REGENERATION);

            value.set(false, ServerLifecycleHooks.getCurrentServer());

        }

        Stats.allPreGenMapStatLists = new AllPreGenMapStats(); // in case stat lists are made before all stats are added up by other mods?

    }

    public static <MSG> void sendToTracking(MSG msg, Entity entity) {

        if (msg == null || entity == null) {
            return;
        }
        if (entity.world.isRemote) {
            return;
        }

        Network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);

        if (entity instanceof PlayerEntity) {
            sendToClient(msg, (ServerPlayerEntity) entity);
        }

    }

    public static <MSG> void sendToTracking(MSG msg, BlockPos pos, World world) {

        if (msg == null || world == null) {
            return;
        }

        PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(pos.getX(), pos
                .getY(), pos.getZ(), 50, world.getDimension().getType());

        Network.send(PacketDistributor.NEAR.with(() -> point), msg);

    }

    public static <MSG> void sendToClient(MSG msg, ServerPlayerEntity player) {

        Network.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <MSG> void sendToServer(MSG msg) {

        Network.sendToServer(msg);
    }

}
