package com.robertx22.onevent.combat;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnEntityMeleeAttack {

	static int energyCost = 3;

	// i think ill replace health compltely and just cancel all damage events and
	// just set hp display or somehting
	@SubscribeEvent
	public static void onEntityMeleeAttack(LivingAttackEvent event) {

		try {

			if (event.getEntityLiving() == null || event.getSource().getTrueSource() == null) {
				return;
			}
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

						if (source instanceof EntityPlayer) {
							if (unit.energy().GetCurrentValue() < energyCost) {
								((EntityPlayer) source).sendMessage(
										new TextComponentString(TextFormatting.RED + "Not Enough Energy."));

							} else {
								unit.SpendEnergy(energyCost);
								UnitSaving.Save(source, unit);
								int num = (int) unit.Stats.get("Damage").Value;
								DamageEffect dmg = new DamageEffect(source, target, num);
								dmg.Activate();
							}

						} else {

							int num = (int) unit.Stats.get("Damage").Value;
							DamageEffect dmg = new DamageEffect(source, target, num);
							dmg.Activate();
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
