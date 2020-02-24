package com.robertx22.mine_and_slash.dimensions;

import com.robertx22.mine_and_slash.database.world_providers.DungeonIWP;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class MapManager {

    public static final String DUNGEON_ID = "mmorpg:resettable_dungeon";

    public static DimensionType getDungeonDimensionType() {
        return getDimensionType(new ResourceLocation(DUNGEON_ID));
    }

    @Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class EventMod {

        @SubscribeEvent
        public static void registerModDimensions(RegistryEvent.Register<ModDimension> event) {

            for (IWP iwp : SlashRegistry.WorldProviders()
                .getList()) {

                ModDimension moddim = iwp.newModDimension();

                if (moddim.getRegistryName() == null) {
                    moddim.setRegistryName(iwp.getResourceLoc());
                }

                event.getRegistry()
                    .register(moddim);

                iwp.setModDimension(moddim);

            }

            ModDimension moddim = SlashRegistry.WorldProviders()
                .get(new DungeonIWP(null, null).GUID()).moddim;
            DimensionType type =
                DimensionManager.registerDimension(new ResourceLocation(DUNGEON_ID), moddim, new PacketBuffer(Unpooled.buffer()), true);
            DimensionManager.keepLoaded(type, false);

        }

        @SubscribeEvent
        public static void regTypes(RegistryEvent.Register<DimensionType> event) {

        }

    }

    public static DimensionType getDimensionType(ResourceLocation res) {

        return DimensionType.byName(res);

    }

    public static DimensionType getDimensionType(PlayerMapCap.IPlayerMapData data) {

        return DimensionType.byName(data.getMap()
            .getIWP()
            .getResourceLoc());

    }

    public static boolean isRegistered(ResourceLocation res) {

        return DimensionType.byName(res) != null;
    }

    public static DimensionType fromResource(ResourceLocation res) {
        return DimensionType.byName(res);
    }

    public static String getId(IWorld world) {

        if (world.getDimension() instanceof DungeonIWP) {
            return DUNGEON_ID; // TODO UNSURE WHY NULLP
        }

        try {
            return getResourceLocation(world.getDimension()
                .getType()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static World getWorld(DimensionType type) {
        return getServer().getWorld(type);
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

    public static DimensionType setupPlayerMapDimension(PlayerEntity player, UnitData unit, MapItemData map,
                                                        BlockPos pos) {

        DimensionType type = getDungeonDimensionType();

        unit.setCurrentMapId(map.mapUUID);

        PlayerMapCap.IPlayerMapData data = Load.playerMapData(player);

        data.init(pos, map, type, player);

        return type;

    }

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

}
