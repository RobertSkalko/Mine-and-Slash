package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.ItemStack;

public class RepairUtils {

    static final String IS_BEING_REPAIRED = Ref.MODID + "is_being_repaired";

    public static void setDamageOverride(ItemStack stack, int damage) {

        if (damage < stack.getDamage() && ModConfig.INSTANCE.Server.ONLY_REPAIR_IN_STATION
                .get()) {
            if (stack.getOrCreateTag().getBoolean(IS_BEING_REPAIRED)) {
                stack.getOrCreateTag().putInt("Damage", Math.max(0, damage));
            }
        } else {
            stack.getOrCreateTag()
                    .putInt("Damage", Math.max(1, damage)); // dont ever break, just make them useless in combat
        }
    }

    public static void allowRepair(ItemStack stack) {
        stack.getOrCreateTag().putBoolean(IS_BEING_REPAIRED, true);
    }

    public static void disableRepair(ItemStack stack) {
        stack.getOrCreateTag().putBoolean(IS_BEING_REPAIRED, false);
    }

    public static void setDamage(ItemStack stack, int damage) {
        allowRepair(stack);
        setDamageOverride(stack, damage);
        disableRepair(stack);
    }

}
