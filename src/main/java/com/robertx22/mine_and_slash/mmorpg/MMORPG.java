package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.a_libraries.curios.GenerateCurioDataJsons;
import com.robertx22.mine_and_slash.a_libraries.curios.RegisterCurioSlots;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.data_packs.affixes.AffixDataPackManager;
import com.robertx22.mine_and_slash.data_packs.runewords.RunewordDataPackManager;
import com.robertx22.mine_and_slash.data_packs.sets.SetDataPackManager;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.error_checks.base.ErrorChecks;
import com.robertx22.mine_and_slash.mmorpg.proxy.ClientProxy;
import com.robertx22.mine_and_slash.mmorpg.proxy.IProxy;
import com.robertx22.mine_and_slash.mmorpg.proxy.ServerProxy;
import com.robertx22.mine_and_slash.mmorpg.registers.client.ClientSetup;
import com.robertx22.mine_and_slash.mmorpg.registers.common.*;
import com.robertx22.mine_and_slash.mmorpg.registers.server.CommandRegister;
import com.robertx22.mine_and_slash.onevent.data_gen.OnGatherData;
import com.robertx22.mine_and_slash.onevent.world.OnStartResetMaps;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.tests.CountUniqueGearTypes;
import com.robertx22.mine_and_slash.uncommon.develeper.CreateLangFile;
import com.robertx22.mine_and_slash.uncommon.develeper.GenerateUniqueGearJsons;
import com.robertx22.mine_and_slash.uncommon.testing.TestManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.logging.Logger;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(Ref.MODID)
@EventBusSubscriber
public class MMORPG {

    // DISABLE WHEN PUBLIC BUILD
    public static boolean RUN_DEV_TOOLS = true;

    public static boolean statEffectDebuggingEnabled() {
        return false && RUN_DEV_TOOLS;
    }

    public static Logger LOGGER = Logger.getLogger(Ref.MOD_NAME);

    public static void devToolsLog(String string) {
        if (RUN_DEV_TOOLS) {
            System.out.println(string);
        }
    }

    public static void devToolsErrorLog(String string) {
        if (RUN_DEV_TOOLS) {
            try {
                throw new Exception(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static long MAP_WORLD_SEED = 0;

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder.named(
        new ResourceLocation(Ref.MODID, "main_channel"))
        .clientAcceptedVersions(PROTOCOL_VERSION::equals)
        .serverAcceptedVersions(PROTOCOL_VERSION::equals)
        .networkProtocolVersion(() -> PROTOCOL_VERSION)
        .simpleChannel();

    public MMORPG() {

        System.out.println("Starting Mine and Slash");

        if (MAP_WORLD_SEED == 0) {
            MAP_WORLD_SEED = org.apache.commons.lang3.RandomUtils.nextLong();
        }

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        SlashRegistry.initRegistries();

        RegisterEvents.register();

        ConfigRegister.registerForgeConfigs(); // MUST BE IN MAIN CLASS

        SlashRegistry.registerAllItems(); // after config registerAll

        OnStartResetMaps.OnStartResetMaps(); // TODO delete this after PR accepted

        SlashRegistry.checkGuidValidity();

        StructurePieceRegisters.reg();

        bus.addListener(this::commonSetupEvent);
        bus.addListener(this::interModProcessEvent);
        bus.addListener(this::interModEnqueue);
        bus.addListener(this::onloadComplete);

        bus.addListener(new OnGatherData()::onGatherData);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            bus.addListener(this::clientSetup);
        });

    }

    public static void logError(String s) {
        try {
            throw new Exception(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commonSetupEvent(FMLCommonSetupEvent event) {

        System.out.println(Ref.MODID + ":FMLCommonSetupEvent");

        PacketRegister.register();

        CapabilityRegister.register();
        CriteriaRegisters.register();

    }

    private void interModEnqueue(final InterModEnqueueEvent event) {
        System.out.println(Ref.MODID + ":InterModEnqueueEvent");
        RegisterCurioSlots.register(event);

    }

    private void interModProcessEvent(final InterModProcessEvent event) {
        System.out.println(Ref.MODID + ":InterModProcessEvent");

        ConfigRegister.registerCustomConfigs();

        RegisterProfessionRecipesFromItems.register();

    }

    public void clientSetup(final FMLClientSetupEvent event) {
        ClientSetup.setup(event);
    }

    @SubscribeEvent
    public static void onServerAboutToStart(FMLServerAboutToStartEvent event) {

        event.getServer().getResourceManager().addReloadListener(new SetDataPackManager());
        event.getServer().getResourceManager().addReloadListener(new AffixDataPackManager());
        event.getServer().getResourceManager().addReloadListener(new RunewordDataPackManager());

    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {

        CommandRegister.Register(event.getServer());

        SlashRegistry.checkGuidValidity();

        ErrorChecks.getAll().forEach(x -> x.check());

        if (RUN_DEV_TOOLS) { // CHANGE ON PUBLIC BUILDS TO FALSE
            TestManager.RunAllTests();
            CreateLangFile.create();
            GenerateCurioDataJsons.generate();
            ModelCreator.createDefaultModelsAndDirs();
            //ValidateGuids.validate();
            CountUniqueGearTypes.count();
            GenerateUniqueGearJsons.gen();
        }

    }

    @SubscribeEvent

    public static void onServerStop(FMLServerStoppedEvent event) {

    }

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent event) {
        MapManager.unregisterDims();
    }

    @SubscribeEvent
    public static void onServerStarted(FMLServerStartedEvent event) {

        if (ModConfig.INSTANCE.Server.DISABLE_VANILLA_HP_REGEN.get()) {

            GameRules.BooleanValue value = ServerLifecycleHooks.getCurrentServer()
                .getGameRules()
                .get(GameRules.NATURAL_REGENERATION);

            value.set(false, ServerLifecycleHooks.getCurrentServer());

        }

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

        PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(
            pos.getX(), pos.getY(), pos.getZ(), 50, world.getDimension().getType());

        Network.send(PacketDistributor.NEAR.with(() -> point), msg);

    }

    public static <MSG> void sendToClient(MSG msg, ServerPlayerEntity player) {

        if (player != null && msg != null) {
            Network.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static <MSG> void syncMapData(ServerPlayerEntity p) {
        if (p != null) {
            sendToClient(new SyncCapabilityToClient(p, PlayerCaps.MAP_DATA), p);
        }
    }

    public static <MSG> void sendToServer(MSG msg) {

        Network.sendToServer(msg);
    }

    public void onloadComplete(FMLLoadCompleteEvent evt) {

        //RegisterModelLayers.reg();

    }

}
