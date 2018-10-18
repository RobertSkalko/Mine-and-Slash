package com.robertx22.onevent.combat;

import com.robertx22.capability.EntityData;
import com.robertx22.datasaving.UnitSaving;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnEntityMeleeAttack {

	@SubscribeEvent
	public static void onEntityMeleeAttack(LivingAttackEvent event) {

		try {

			if (event.getSource().isExplosion()) {
				return;
			}
			if (event.getEntityLiving().world.isRemote) {
				return;
			}

			if (event.getSource() instanceof MyDamageSource) {
				// System.out.println("Dmg source works correctly!");
			} else {

				EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
				EntityLivingBase target = event.getEntityLiving();

				if (target.hasCapability(EntityData.Data, null) && source.hasCapability(EntityData.Data, null)) {

					Unit unit = UnitSaving.Load(source);
					if (unit != null) {
						int num = (int) unit.Stats.get("Damage").Value;
						DamageEffect dmg = new DamageEffect(source, target, num);
						dmg.Activate();

						event.setCanceled(true);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
