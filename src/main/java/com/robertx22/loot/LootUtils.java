package com.robertx22.loot;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.ItemStack;

public class LootUtils {
  static final int LEVEL_DISTANCE_PUNISHMENT_ACTIVATION = ModConfig.Server.LEVEL_DISTANCE_PUNISHMENT_ACTIVATION;

  // prevents lvl 50 players farming lvl 1 mobs
  public static float ApplyLevelDistancePunishment(UnitData mob, UnitData player, float chance) {

    int difference = Math.abs(player.getLevel() - mob.getLevel());
    int maxlvl = ModConfig.Server.MAXIMUM_PLAYER_LEVEL;

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
      float damage = (float) RandomUtils.RandomRange(rar.SpawnDurabilityHit().Min,
          rar.SpawnDurabilityHit().Max) / (float) 100;
      stack.setItemDamage((int) (damage * stack.getMaxDamage()));
    }

    return stack;
  }

  public static float applyLootMultipliers(float chance, UnitData player, UnitData mob,
      EntityLivingBase entity, IWorldData world) {

    float first = chance;

    float after_rarity = first * Rarities.Mobs.get(mob.getRarity()).LootMultiplier();

    float after_mob_health = after_rarity * (1 + entity.getMaxHealth() / 20);

    float after_world = after_mob_health;

    if (world.isMapWorld()) {
      after_world = after_mob_health * 1 + world.getMap().getBonusLootAmount() / 100;
    }
    if (entity instanceof EntitySlime) {
      after_world /= 15;
    }

    float perkillsbonus = player.getLootBonusPerAffixKills(world.getMap());

    float after_peraffixkill = after_world * (1 + perkillsbonus / 100);

    return after_peraffixkill;
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
