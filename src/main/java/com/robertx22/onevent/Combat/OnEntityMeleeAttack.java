package com.robertx22.onevent.combat;

import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

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

	    targetData.recalculateStats(target);
	    sourceData.recalculateStats(source);

	    if (source instanceof EntityPlayer) {

		ItemStack weapon = source.getHeldItemMainhand();

		if (weapon != null && !weapon.isEmpty() && weapon.getItem() instanceof IWeapon) {

		    WeaponMechanic iWep = ((IWeapon) weapon.getItem()).mechanic();

		    int energyCost = iWep.GetEnergyCost();

		    if (sourceData.getUnit().hasEnoughEnergy(energyCost) == false) {
			NoEnergyMessage(source);
			event.setCanceled(true);
			return;

		    } else {
			sourceData.getUnit().SpendEnergy(energyCost);
			weapon.damageItem(1, source);
			iWep.Attack(source, target, sourceData, targetData);

		    }

		}
	    } else { // if its a mob

		sourceData.getUnit().MobBasicAttack(source, target, sourceData.getUnit(), event.getAmount());

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
