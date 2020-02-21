package com.robertx22.mine_and_slash.loot;

import com.robertx22.mine_and_slash.config.lvl_penalty.LvlPenaltyContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class LootUtils {

    // prevents lvl 50 players farming lvl 1 mobs
    public static float ApplyLevelDistancePunishment(UnitData mob, UnitData player, float chance) {
        return (float) (LvlPenaltyContainer.INSTANCE.getMultiForLevelDifference(
                player.getLevel(), mob.getLevel()) * chance);
    }

    public static ItemStack RandomDamagedGear(ItemStack stack, Rarity rar, int level) {
        if (stack.isDamageable()) {

            float lvlDuraPenalty; // easier at low lvls, harder at later
            if (level < 10) {
                lvlDuraPenalty = -0.1F;
            } else if (level < 30) {
                lvlDuraPenalty = 0;
            } else {
                lvlDuraPenalty = 0.2F;
            }

            float dmgMulti = (float) RandomUtils.RandomRange(
                    rar.SpawnDurabilityHit().min, rar.SpawnDurabilityHit().max) / (float) 100;

            dmgMulti += lvlDuraPenalty;

            dmgMulti = MathHelper.clamp(dmgMulti, 0, 0.95F);

            stack.setDamage((int) (dmgMulti * stack.getMaxDamage()));

        }

        return stack;
    }

    public static float applyLootMultipliers(float chance, UnitData mob, LivingEntity entity) {

        float first = chance;

        float after_rarity = first * Rarities.Mobs.get(mob.getRarity()).LootMultiplier();

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
