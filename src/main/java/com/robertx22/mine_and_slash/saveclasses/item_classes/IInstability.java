package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.config.ModConfig;
import net.minecraft.util.math.MathHelper;

public interface IInstability {

    int getInstability();

    default int getMaxInstability() {
        return 250;
    }

    default boolean usesInstability() {
        return ModConfig.INSTANCE.Server.ENABLE_CURRENCY_ITEMS_INSTABILITY_SYSTEM.get();
    }

    default boolean usesBreakChance() {
        return ModConfig.INSTANCE.Server.ENABLE_CURRENCY_ITEMS_BREAKING_MODIFIED_ITEMS.get();
    }

    void increaseInstability(int amount);

    default float getBreakChance() {

        if (getInstability() < (float) getMaxInstability() / 10) {
            return 0;
        }

        float breakchance = ((float) getInstability() / (float) getMaxInstability()) * 20;

        return MathHelper.clamp(breakchance, 0, 25);

    }

    default boolean isInstabilityCapReached() {
        return getInstability() >= getMaxInstability();
    }
}
