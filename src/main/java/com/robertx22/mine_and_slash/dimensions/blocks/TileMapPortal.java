package com.robertx22.mine_and_slash.dimensions.blocks;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import com.robertx22.mine_and_slash.new_content.building.DungeonUtils;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonDimensionData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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
        super(TileEntityRegister.MAP_PORTAL.get());
    }

    int ticks = 0;
    public String dungeonID = "";
    public boolean isBackPortal = false;

    public void onTick() {
        ticks++;
    }

    public boolean readyToTeleport() {

        if (ticks > 70) {
            ticks = 0;
            return true;
        }
        return false;

    }

    public void onDone(PlayerEntity player) {
        if (isBackPortal) {
            Load.playerMapData(player)
                .teleportPlayerBack(player);
        } else {
            ChunkPos cpos = DungeonDimensionData.getChunkFromId(dungeonID);

            if (cpos != null) {

                PlayerMapCap.IPlayerMapData data = Load.playerMapData(player);

                if (data.isMapActive()) {
                    World mapworld = MapManager.getWorld(MapManager.getDungeonDimensionType());
                    if (mapworld == null) {
                        return;
                    }
                    if (WorldUtils.isMapWorld(mapworld)) {
                        player.sendMessage(Chats.Teleport_started.locName());

                        BlockPos p = DungeonUtils.getStartChunk(cpos)
                            .asBlockPos();

                        p = new BlockPos(p.getX() + 8, 55, p.getZ() + 8);

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

        ticks = nbt.getInt("ticks");
        dungeonID = nbt.getString("dungeon_id");
        isBackPortal = nbt.getBoolean("is_back_portal");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt); // The super call is required to save and load the tile loc

        nbt.putInt("ticks", ticks);
        nbt.putString("dungeon_id", dungeonID);
        nbt.putBoolean("is_back_portal", isBackPortal);

        return nbt;
    }

}