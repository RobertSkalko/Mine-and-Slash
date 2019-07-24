package com.robertx22.mine_and_slash.loot;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.item.ItemStack;

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

    public static ItemStack RandomDamagedGear(ItemStack stack, ItemRarity rar) {
        if (stack.getMaxDamage() > 0) {
            float damage = (float) RandomUtils.RandomRange(rar.SpawnDurabilityHit().Min, rar
                    .SpawnDurabilityHit().Max) / (float) 100;
            stack.setDamage((int) (damage * stack.getMaxDamage()));
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
