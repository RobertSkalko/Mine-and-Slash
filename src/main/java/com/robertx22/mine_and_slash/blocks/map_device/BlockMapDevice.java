package com.robertx22.mine_and_slash.blocks.map_device;

import com.robertx22.mine_and_slash.blocks.bases.BaseInventoryBlock;
import com.robertx22.mine_and_slash.items.misc.UniqueDungeonKeyItem;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockMapDevice extends BaseInventoryBlock {

    public BlockMapDevice() {
        super(Properties.create(Material.ROCK)
            .hardnessAndResistance(5F));
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return new TileMapDevice();

    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                             Hand hand, BlockRayTraceResult ray) {

        if (world.isRemote) {
            return ActionResultType.CONSUME;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileMapDevice) {
            TileMapDevice map = (TileMapDevice) tile;

            ItemStack stack = player.getHeldItemMainhand();

            MapItemData mapdata = Map.Load(stack);

            if (mapdata != null) {
                map.sacrificeMap(player, mapdata, stack);
                return ActionResultType.SUCCESS;
            } else {

                if (stack.getItem() instanceof UniqueDungeonKeyItem) {
                    UniqueDungeonKeyItem key = (UniqueDungeonKeyItem) stack.getItem();

                    return ActionResultType.SUCCESS;
                }

            }

        }

        return super.onBlockActivated(state, world, pos, player, hand, ray);
    }

}