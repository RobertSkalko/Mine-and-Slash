package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RepairUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

public interface MyForgeItem extends IForgeItem {

    @Override
    default void setDamage(ItemStack stack, int damage) {
        RepairUtils.setDamageOverride(stack, damage);
    }

    @Override
    default boolean isRepairable(ItemStack stack) {
        return !ModConfig.INSTANCE.Server.ONLY_REPAIR_IN_STATION.get();
    }

    @Override
    default boolean isDamaged(ItemStack stack) {
        if (ModConfig.INSTANCE.Server.ONLY_REPAIR_IN_STATION.get()) {
            return false; // say its fine so mending doesnt spend exp
        } else {
            return stack.getDamage() > 0;
        }
    }

    @Override
    default boolean showDurabilityBar(ItemStack stack) {
        return stack.getDamage() > 0;
    }

}
