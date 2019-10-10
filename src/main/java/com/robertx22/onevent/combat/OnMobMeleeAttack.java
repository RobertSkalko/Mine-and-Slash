package com.robertx22.onevent.combat;

import com.robertx22.config.mod_dmg_whitelist.ModDmgWhitelistContainer;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.DamageEffect;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobMeleeAttack {

  public static void onMobMeleeAttack(EntityLivingBase source, EntityLivingBase target, float amount, LivingHurtEvent event) {

    if (event.getEntityLiving().world.isRemote) {
      return;
    }

    if (event.getSource() instanceof MyDamageSource
        || event.getSource().getDamageType().equals(DamageEffect.DmgSourceName)) {
      return;
    }
    try {
      if (event.getEntityLiving() == null || event.getSource().getTrueSource() == null
          || !(event.getSource().getTrueSource() instanceof EntityLivingBase)) {
        return;
      }

      if (target.isEntityAlive() == false) {
        return; // stops attacking dead mobs
      }

      UnitData targetData = Load.Unit(target);
      UnitData sourceData = Load.Unit(source);

      if (targetData == null || sourceData == null) {
        return;
      }

      Unit targetUnit = targetData.getUnit();
      Unit sourceUnit = sourceData.getUnit();

      if (targetUnit == null || sourceUnit == null) {
        return;
      }
      
      GearItemData weapondata = sourceData.getWeaponData(source);

      IWorldData world = Load.World(target.world);

      if (world == null) {
        return;
      }

      targetData.recalculateStats(target, world);
      sourceData.recalculateStats(source, world);

      if (source instanceof EntityPlayer) {

          if (weapondata == null) {
              ItemStack weapon = source.getHeldItemMainhand();
              ModDmgWhitelistContainer.ModDmgWhitelist mod = ModDmgWhitelistContainer
                      .getModDmgWhitelist(weapon);
              if (mod != null) {
                  return;
              }
          }

          if (sourceData.isWeapon(weapondata)) {
              if (sourceData.tryUseWeapon(weapondata, source)) {
                  sourceData.attackWithWeapon(event, source.getHeldItemMainhand(), weapondata, source, target, targetData);
              }

          } else {
              sourceData.unarmedAttack(event, source, target, targetData);
          }

      } else { // if its a mob
          sourceData.mobBasicAttack(event, source, target, sourceData, targetData, amount);
      }

    } catch (Exception e) {
      e.printStackTrace();

    }

  }

}
