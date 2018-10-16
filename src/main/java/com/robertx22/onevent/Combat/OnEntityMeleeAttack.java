package com.robertx22.onevent.combat;

import com.robertx22.capability.EntityData;
import com.robertx22.datasaving.Saving;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;

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

			if (event.getSource().getDamageType().equals(DamageEffect.DmgSourceName)) {
				// System.out.println("Dmg source works correctly!");
			} else {

				EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
				EntityLivingBase target = event.getEntityLiving();

				if (target.hasCapability(EntityData.Data, null) && source.hasCapability(EntityData.Data, null)) {

					Unit unit = Saving.Load(source);
					if (unit != null) {
						int num = (int) unit.Stats().get("Damage").Value;
						DamageEffect dmg = new DamageEffect(source, target, num);
						dmg.Activate();
					}

				}
			}
		} catch (Exception e) {
			// e.printStackTrace();

		}

	}

}
