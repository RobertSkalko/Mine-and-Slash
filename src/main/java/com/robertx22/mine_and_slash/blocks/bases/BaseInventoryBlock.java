package com.robertx22.mine_and_slash.blocks.bases;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseInventoryBlock extends NonFullBlock {

    protected BaseInventoryBlock(Properties prop) {
        super(prop);

    }

    @Override
    @Deprecated
    public List<ItemStack> getDrops(BlockState blockstate, LootContext.Builder context) {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(new ItemStack(this));

        TileEntity tileentity = context.get(LootParameters.BLOCK_ENTITY);

        if (tileentity instanceof BaseTile) {

            BaseTile inv = (BaseTile) tileentity;

            for (ItemStack stack : inv.itemStacks) {
                if (stack.isEmpty() == false) {
                    items.add(stack);
                }
            }

            //inv.itemStacks = new ItemStack[inv.itemStacks.length];// destroy the stacks
            //Arrays.fill(inv.itemStacks, ItemStack.EMPTY); tterag says use the container canInteractWith method instead
            // unsure if i should use both to be extra sure

        }

        return items;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                             Hand hand, BlockRayTraceResult ray) {
        if (world.isRemote) {
            return ActionResultType.CONSUME;
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof BaseTile) {

            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, extraData -> {
                extraData.writeBlockPos(tile.getPos());
            });
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.CONSUME;
    }

}
