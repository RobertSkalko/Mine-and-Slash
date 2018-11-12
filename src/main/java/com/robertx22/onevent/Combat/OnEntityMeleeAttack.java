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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnEntityMeleeAttack {
	/**
	 * On attack, cancel and spawn the real attack with my damage source, mobs don't
	 * use energy but players do
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public static void onEntityMeleeAttack(LivingAttackEvent event) {

		if (event.getEntityLiving().world.isRemote) {
			return;
		}

		if (event.getSource() instanceof MyDamageSource) {

			return;
		}

		try {
			if (event.getEntityLiving() == null || event.getSource().getTrueSource() == null) {
				return;
			}

			EntityLivingBase source = (EntityLivingBase) event.getSource().getTrueSource();
			EntityLivingBase target = event.getEntityLiving();

			// this seems to copy vanilla attack speed
			if ((float) target.hurtResistantTime > (float) target.maxHurtResistantTime / 2.0F) {
				event.setCanceled(true);
				return;
			}

			Unit targetUnit = UnitSaving.Load(target);
			Unit unit = UnitSaving.Load(source);

			if (unit == null || targetUnit == null) {
				return;
			}

			targetUnit.ReloadStatsAndSave(target);
			unit.ReloadStatsAndSave(source);

			if (source instanceof EntityPlayer) {

				ItemStack weapon = source.getHeldItemMainhand();

				if (weapon != null && !weapon.isEmpty() && weapon.getItem() instanceof IWeapon) {

					IWeapon iWep = (IWeapon) weapon.getItem();

					int energyCost = iWep.GetEnergyCost();

					event.setCanceled(true);

					if (unit.energyData().CurrentValue < energyCost) {

						NoEnergyMessage(source);

					} else {
						unit.SpendEnergy(energyCost);
						UnitSaving.Save(source, unit);
						weapon.setItemDamage(weapon.getItemDamage() + 1);
						iWep.Attack(source, target, unit, targetUnit);

						if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
							EntityLivingBase defender = event.getEntityLiving();
							EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
							defender.knockBack(attacker, 0.3F, attacker.posX - defender.posX,
									attacker.posZ - defender.posZ);
						}

					}

				}
			} else { // if its a mob

				unit.MobBasicAttack(source, target, unit, event.getAmount());

				if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
					EntityLivingBase defender = event.getEntityLiving();
					EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
					defender.knockBack(attacker, 0.3F, attacker.posX - defender.posX, attacker.posZ - defender.posZ);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void NoEnergyMessage(EntityLivingBase entity) {
		entity.sendMessage(new TextComponentString(TextFormatting.RED + "Not Enough Energy."));
	}

}
