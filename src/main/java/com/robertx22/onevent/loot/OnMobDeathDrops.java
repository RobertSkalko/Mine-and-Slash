package com.robertx22.onevent.loot;

import com.robertx22.db_lists.Rarities;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.MasterLootGen;
import com.robertx22.mmorpg.Main;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobDeathDrops {

  @SubscribeEvent
  public static void mobOnDeathDrop(LivingDeathEvent event) {

    if (event.getEntityLiving().world.isRemote) {
      return;
    }

    try {

      EntityLivingBase entity = event.getEntityLiving();

      if (!(entity instanceof EntityPlayer)) {
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
          if (entity.hasCapability(EntityData.Data, null)) {

            float loot_multi = EntityTypeUtils.getLootMulti(entity);
            float exp_multi = EntityTypeUtils.getExpMulti(entity);

            UnitData victim = entity.getCapability(EntityData.Data, null);
            UnitData killer =
                event.getSource().getTrueSource().getCapability(EntityData.Data, null);
            
            if (victim.shouldDropLoot(entity) == false) {
                return;
            }

            if (loot_multi > 0) {

              IWorldData world = Load.World(entity.world);

              killer.onMobKill(world);

              MasterLootGen.genAndDrop(victim, killer, world, entity);

            }

            if (exp_multi > 0) {
              int exp = GiveExp((EntityLivingBase) event.getSource().getTrueSource(), killer,
                  victim, exp_multi);

              DamageNumberPackage packet = new DamageNumberPackage(entity, Elements.Nature,
                  "+" + DamageEffect.FormatNumber(exp) + " Exp!");
              packet.isExp = true;

              Main.Network.sendTo(packet, (EntityPlayerMP) event.getSource().getTrueSource());
            }
          }
        }
      }

    } catch (

    Exception e) {
      e.printStackTrace();
    }

  }



  private static int GiveExp(EntityLivingBase entity, UnitData player, UnitData mob, float multi) {

    int exp = (int) (mob.getLevel() * Rarities.Mobs.get(mob.getRarity()).ExpOnKill() * multi);

    exp = (int) LootUtils.ApplyLevelDistancePunishment(mob, player, exp);

    exp = player.PostGiveExpEvent((EntityPlayer) entity, exp);

    return exp;

  }

}
