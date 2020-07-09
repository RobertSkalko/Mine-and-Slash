package com.robertx22.mine_and_slash.loot;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class LootUtils {

    public static ItemStack RandomDamagedGear(ItemStack stack, Rarity rar) {
        if (stack.isDamageable()) {

            float dmgMulti = (float) RandomUtils.RandomRange(
                rar.SpawnDurabilityHit().min, rar.SpawnDurabilityHit().max) / (float) 100;

            dmgMulti = MathHelper.clamp(dmgMulti, 0, 0.95F);

            stack.setDamage((int) (dmgMulti * stack.getMaxDamage()));

        }

        return stack;
    }

    public static float applyLootMultipliers(float chance, UnitData mob, LivingEntity entity) {

        float first = chance;

        float after_rarity = first * Rarities.Mobs.get(mob.getRarity())
            .LootMultiplier();

        float after_mob_health = after_rarity * (1 + entity.getMaxHealth() / 20);

        if (entity instanceof SlimeEntity) {
            after_mob_health /= 15;
        }

        return after_mob_health;
    }

    public static int WhileRoll(float chance) {
        int amount = 0;

        while (chance > 0) {

            float maxChance = 75F;

            float currentChance = chance;

            if (currentChance > maxChance) {
                currentChance = maxChance;
            }

            chance -= currentChance;

            if (RandomUtils.roll(currentChance)) {
                amount++;
            }

        }
        return amount;

    }

}
