package com.robertx22.onevent.combat;

import com.robertx22.config.ModConfig;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnHurt {


  @SubscribeEvent(priority = EventPriority.LOWEST)
  public static void onHurt(LivingHurtEvent event) {

    Entity defender = event.getEntityLiving();

    if (defender instanceof EntityPlayer == false) {

      if (event.getSource() != null) {
        Entity attacker = event.getSource().getTrueSource();

        if (attacker instanceof EntityPlayer == false) {

          UnitData data = Load.Unit(event.getEntityLiving());
          if (data != null) {
            data.onDamagedByNonPlayer(event.getAmount());
          }
        }
      } else {
        UnitData data = Load.Unit(event.getEntityLiving());
        if (data != null) {
          data.onDamagedByNonPlayer(event.getAmount());
        }
      }
    }
  }



  /**
   * If damage is from my source, leave the value, otherwise decrease it (this makes my damage
   * source the best one)
   * 
   * @param event
   */
  @SubscribeEvent
  public static void OnLivingHurt(LivingHurtEvent event) {

    if (event.getSource() instanceof MyDamageSource) {
      return;
    }

    // mobs take much less damage from any source other than my mods. This is
    // required or else there's no point in getting legendary weapons if a diamond
    // sword more damage
    if (event.getSource() != null && event.getSource().getTrueSource() instanceof EntityLivingBase
        && event.getEntityLiving().hasCapability(EntityData.Data, null)) {
      if (event.getSource().isExplosion()) {
        event.setAmount(event.getAmount() * ModConfig.Server.MOB_ENVIRONMENT_DAMAGE_MULTI);
        return;
      } else {
        event.setAmount(event.getAmount() * ModConfig.Server.NON_MOD_DAMAGE_MULTI);
        return;
      }
    }

  }
}
