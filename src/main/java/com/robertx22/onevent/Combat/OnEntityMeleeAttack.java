package com.robertx22.onevent.combat;

import com.robertx22.customitems.bases.IWeapon;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnEntityMeleeAttack {

	@SubscribeEvent
	public static void osdsdnEntityMeleeAttack(LivingHurtEvent event) {
		if (event.getEntityLiving().world.isRemote) {
			return;
		}
		if (event.getSource().isExplosion()) {
			event.setAmount(event.getAmount() / 5);
			return;
		}
	}

	@SubscribeEvent
	public static void onEntityMeleeAttack(LivingAttackEvent event) {
		if (event.getEntityLiving().world.isRemote) {
			return;
		}

		if (event.getSource() instanceof MyDamageSource) {
			System.out.println("Dmg source works correctly!");
			return;
		}

		try {

			if (event.getEntityLiving() == null || event.getSource().getTrueSource() == null) {
				return;
			}
			if (event.getEntityLiving().world.isRemote) {
				return;
			}

			EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
			EntityLivingBase target = event.getEntityLiving();

			if (target.hurtResistantTime > 0) {
				event.setCanceled(true);
				return;

			}

			Unit targetUnit = UnitSaving.Load(target);
			Unit unit = UnitSaving.Load(source);

			if (unit == null || targetUnit == null) {
				return;
			}

			unit.RecalculateStats(source);
			// targetUnit.RecalculateStats(target);

			if (source instanceof EntityPlayer) {
				ItemStack weapon = source.getHeldItemMainhand();

				if (weapon != null && !weapon.isEmpty() && weapon.getItem() instanceof IWeapon) {

					IWeapon iWep = (IWeapon) weapon.getItem();

					int energyCost = iWep.GetEnergyCost();

					if (unit != null && targetUnit != null) {

						event.setCanceled(true);
						// event.setAmount(0);

						if (unit.energy().GetCurrentValue() < energyCost) {
							// event.setCanceled(true);
							NoEnergyMessage(source);

						} else {
							unit.SpendEnergy(energyCost);
							UnitSaving.Save(source, unit);

							unit.BasicAttack(source, target, unit);

						}
					}
				}
			} else {
				unit.BasicAttack(source, target, unit);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private static void NoEnergyMessage(EntityLivingBase entity) {
		entity.sendMessage(new TextComponentString(TextFormatting.RED + "Not Enough Energy."));
	}

}
