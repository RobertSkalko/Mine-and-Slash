package com.robertx22.mine_and_slash.dimensions.blocks;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModTileEntities;
import com.robertx22.mine_and_slash.new_content.building.DungeonUtils;
import com.robertx22.mine_and_slash.onevent.world.OnShutdownResetMaps;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonDimensionData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileMapPortal extends TileEntity {

    public TileMapPortal() {
        super(ModTileEntities.MAP_PORTAL.get());
    }

    int ticks = 0;
    public String dungeonID = "";
    public MapItemData map = new MapItemData();
    public BlockPos mapDevicePos = new BlockPos(0, 0, 0);

    public void onTick() {
        ticks++;
    }

    public void onMapSacrificed(BlockPos mapDevicePos, MapItemData map, String dungeonID) {
        this.mapDevicePos = mapDevicePos;
        this.map = map;
        this.dungeonID = dungeonID;
    }

    public boolean readyToTeleport() {

        if (ticks > 70) {
            ticks = 0;
            return true;
        }
        return false;

    }

    public void onDone(PlayerEntity player) {
        if (WorldUtils.isMapWorldClass(player.world)) {
            Load.playerMapData(player)
                .teleportPlayerBack(player);
        } else {
            ChunkPos cpos = DungeonDimensionData.getChunkFromId(dungeonID);

            if (cpos != null) {

                PlayerMapCap.IPlayerMapData data = Load.playerMapData(player);

                data.init(mapDevicePos, map, MapManager.getDungeonDimensionType(), player);

                if (data.isMapActive()) {
                    World mapworld = MapManager.getWorld(MapManager.getDungeonDimensionType());

                    try {
                        OnShutdownResetMaps.shouldDelete = Load.world(mapworld)
                            .shouldDeleteFolderOnServerShutdown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (mapworld == null) {
                        return;
                    }
                    if (WorldUtils.isMapWorld(mapworld)) {
                        player.sendMessage(Chats.Teleport_started.locName());

                        BlockPos p = DungeonUtils.getDungeonStartTeleportPos(cpos);

                        Entity tped = PlayerUtils.changeDimension((ServerPlayerEntity) player, MapManager.getDungeonDimensionType(), p);

                        MMORPG.devToolsLog("tp to map succeeded");

                    }
                } else {
                    player.sendMessage(Chats.Not_enough_time.locName());
                }

            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderFace(Direction face) {
        return face == Direction.UP;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        try {
            ticks = nbt.getInt("ticks");
            dungeonID = nbt.getString("dungeon_id");

            mapDevicePos = new BlockPos(nbt.getInt("xmap"), nbt.getInt("ymap"), nbt.getInt("zmap"));

            if (mapDevicePos == new BlockPos(0, 0, 0)) {
                mapDevicePos = this.getPos()
                    .up()
                    .south();
            }

            map = Map.Load(nbt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt); // The super call is required to save and load the tile loc

        try {
            nbt.putInt("ticks", ticks);
            nbt.putString("dungeon_id", dungeonID);

            Map.Save(nbt, map);

            nbt.putInt("xmap", mapDevicePos.getX());
            nbt.putInt("ymap", mapDevicePos.getY());
            nbt.putInt("zmap", mapDevicePos.getZ());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nbt;
    }

}