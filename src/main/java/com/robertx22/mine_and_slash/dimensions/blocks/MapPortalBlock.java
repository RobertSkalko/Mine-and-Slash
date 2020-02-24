package com.robertx22.mine_and_slash.dimensions.blocks;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.new_content.building.DungeonUtils;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonDimensionData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MapPortalBlock extends EndPortalBlock {

    public MapPortalBlock() {

        super(Block.Properties.create(Material.PORTAL, MaterialColor.BLACK)
            .doesNotBlockMovement()
            .lightValue(15)
            .hardnessAndResistance(2, 2));

    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        try {
            if (world.isRemote == false && entity instanceof PlayerEntity) {
                if (!entity.isBeingRidden() && entity.isNonBoss()) {

                    TileEntity en = world.getTileEntity(pos);

                    if (en instanceof TileMapPortal) {
                        TileMapPortal portal = (TileMapPortal) en;

                        portal.ontick();

                        if (portal.readyToTeleport()) {

                            ChunkPos cpos = DungeonDimensionData.getChunkFromId(portal.dungeonID);

                            if (cpos != null) {

                                PlayerEntity player = (PlayerEntity) entity;

                                PlayerMapCap.IPlayerMapData data = Load.playerMapData(player);

                                if (data.hasTimeForMap()) {

                                    World mapworld = MapManager.getWorld(MapManager.DUNGEON);

                                    if (mapworld == null) {
                                        return;
                                    }

                                    if (WorldUtils.isMapWorld(mapworld)) {

                                        entity.sendMessage(Chats.Teleport_started.locName());

                                        BlockPos p = DungeonUtils.getStartChunk(cpos)
                                            .asBlockPos();

                                        p = new BlockPos(p.getX() + 8, 55, p.getZ() + 8);

                                        Entity tped = PlayerUtils.changeDimension((ServerPlayerEntity) player, MapManager.DUNGEON, p);

                                        MMORPG.devToolsLog("tp to map succeeded");

                                    }
                                } else {
                                    entity.sendMessage(Chats.Not_enough_time.locName());
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileMapPortal();
    }

}
