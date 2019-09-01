package com.robertx22.mine_and_slash.loot;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class LootUtils {

    static final int LEVEL_DISTANCE_PUNISHMENT_ACTIVATION = 5;

    // prevents lvl 50 players farming lvl 1 mobs
    public static float ApplyLevelDistancePunishment(UnitData mob, UnitData player,
                                                     float chance) {

        int difference = Math.abs(player.getLevel() - mob.getLevel());
        int maxlvl = ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get();

        if (difference > LEVEL_DISTANCE_PUNISHMENT_ACTIVATION) {

            // if a high lvl player is killing higher than max lvl mobs
            if (player.getLevel() == maxlvl && mob.getLevel() > maxlvl) {
                return chance;
            }

            float levelDiff = 1;

            if (player.getLevel() > mob.getLevel()) {
                levelDiff = (float) mob.getLevel() / (float) (player.getLevel());
            } else {
                levelDiff = (float) player.getLevel() / (float) (mob.getLevel());
            }

            if (levelDiff > 1) {
                levelDiff = 1;
            }

            return chance * levelDiff;

        }

        return chance;

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

            float dmgMulti = (float) RandomUtils.RandomRange(rar.SpawnDurabilityHit().Min, rar
                    .SpawnDurabilityHit().Max) / (float) 100;

            dmgMulti += lvlDuraPenalty;

            dmgMulti = MathHelper.clamp(dmgMulti, 0, 0.95F);

            stack.setDamage((int) (dmgMulti * stack.getMaxDamage()));
        }

        return stack;
    }

    public static float applyLootMultipliers(float chance, UnitData mob,
                                             LivingEntity entity) {

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
