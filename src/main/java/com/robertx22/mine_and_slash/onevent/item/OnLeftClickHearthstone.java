package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.items.level_incentives.Hearthstone;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnLeftClickHearthstone {

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock evt) {

        ServerPlayerEntity player = (ServerPlayerEntity) evt.getEntityPlayer();
        BlockState block = player.world.getBlockState(evt.getPos());

        ItemStack stack = evt.getItemStack();

        if (stack.getItem() instanceof Hearthstone == false) {
            return;
        }

        Hearthstone item = (Hearthstone) evt.getItemStack().getItem();

        if (block.getBlock().equals(BlockRegister.ATTUNEMENT_ALTAR_BLOCK)) {

            DimensionType type = evt.getWorld().getDimension().getType();

            item.setLoc(stack, new BlockPos(evt.getPos()), type);
            player.sendMessage(Chats.You_have_attuned_to_this_Altar.locName());

        } else {
            player.sendMessage(Chats.This_is_not_an_Attunement_Altar.locName());
        }
    }

}