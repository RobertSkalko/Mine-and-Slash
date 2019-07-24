package com.robertx22.mine_and_slash.dimensions;

import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class MapManager {

    @Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class EventMod {
        @SubscribeEvent
        public static void registerModDimensions(
                RegistryEvent.Register<ModDimension> event) {

            for (IWP iwp : SlashRegistry.WorldProviders().getList()) {

                ModDimension moddim = iwp.newModDimension();

                if (moddim.getRegistryName() == null) {
                    moddim.setRegistryName(iwp.getResourceLoc());
                }

                event.getRegistry().register(moddim);

                iwp.setModDimension(moddim);

            }

        }

    }

    @Mod.EventBusSubscriber
    public static class EventDim {
        @SubscribeEvent
        public static void registerAllModDims(RegisterDimensionsEvent event) {
            for (IWP iwp : SlashRegistry.WorldProviders().getList()) {

                ResourceLocation res = iwp.getResourceLoc();

                ModDimension moddim = iwp.getModDim();

                if (moddim.getRegistryName() == null) {
                    moddim.setRegistryName(iwp.getResourceLoc().toString());
                }

                if (isRegistered(iwp.getResourceLoc()) == false) {

                    DimensionType type = DimensionManager.registerDimension(res, moddim, new PacketBuffer(Unpooled
                            .buffer()), true);

                    DimensionManager.keepLoaded(type, false);

                }
            }

        }
    }

    public static DimensionType getDimensionType(ResourceLocation res) {

        return DimensionType.byName(res);

    }

    public static DimensionType getDimensionType(PlayerMapCap.IPlayerMapData data) {

        return DimensionType.byName(data.getMap().getIWP().getResourceLoc());

    }

    public static boolean isRegistered(ResourceLocation res) {

        return DimensionType.byName(res) != null;
    }

    public static DimensionType fromResource(ResourceLocation res) {
        return DimensionType.byName(res);
    }

    public static World getWorld(DimensionType type) {
        return getWorld(getResourceLocation(type).toString());
    }

    public static World getWorld(String res) {
        DimensionType type = getDimensionType(new ResourceLocation(res));

        if (type == null) {
            return null;
        }

        return getServer().getWorld(type);

    }

    public static ResourceLocation getResourceLocation(DimensionType type) {

        ResourceLocation loc = DimensionType.getKey(type);

        return loc;
    }

    public static DimensionType setupPlayerMapDimension(PlayerEntity player,
                                                        UnitData unit, MapItemData map,
                                                        BlockPos pos) {

        DimensionType type = getDimensionType(map.getIWP().getResourceLoc());

        ResourceLocation res = getResourceLocation(type);

        unit.setCurrentMapId(res.toString());

        PlayerMapCap.IPlayerMapData data = Load.playerMapData(player);

        data.init(pos, map, type, player);

        return type;

    }

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

}
