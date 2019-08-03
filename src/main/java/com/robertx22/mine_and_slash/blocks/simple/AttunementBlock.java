package com.robertx22.mine_and_slash.blocks.simple;

import com.robertx22.mine_and_slash.blocks.bases.NonFullBlock;
import com.robertx22.mine_and_slash.items.level_incentives.Hearthstone;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.loot.LootContext;

import java.util.Arrays;
import java.util.List;

public class AttunementBlock extends NonFullBlock {

    public AttunementBlock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3F));
    }

    @Deprecated
    public List<ItemStack> getDrops(BlockState p_220076_1_,
                                    LootContext.Builder p_220076_2_) {
        return Arrays.asList(new ItemStack(this));
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos,
                                    PlayerEntity player, Hand hand,
                                    BlockRayTraceResult ray) {
        try {
            if (world.isRemote) {
                return true;
            }

            ItemStack stack = player.getHeldItem(hand);

            if (stack.getItem() instanceof Hearthstone) {
                Hearthstone item = (Hearthstone) stack.getItem();

                DimensionType type = player.world.getDimension().getType();

                item.setLoc(stack, pos, type);
                player.sendMessage(Chats.You_have_attuned_to_this_Altar.locName());

            } else {
                player.sendMessage(Chats.Youneedheartstone.locName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
