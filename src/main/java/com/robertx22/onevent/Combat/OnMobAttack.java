package com.robertx22.onevent.Combat;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.robertx22.utilityclasses.GeneralUtils.getHpMultiplier;
import static com.robertx22.utilityclasses.RandomUtils.roll;

import java.util.Hashtable;
import java.util.Random;

import com.robertx22.capability.EntityData;
import com.robertx22.constants.Tags;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.player.PlayerData;

public class OnMobAttack {

	@SubscribeEvent
	public void onMobAttack(LivingDamageEvent event) {

		if (event.isCanceled()) {
			return;
		}
		if (event.getSource() == null) {
			return;
		}
		if (event.getEntityLiving().world.isRemote) {
			return;
		}
		if (!(event.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		if (!(event.getSource().getTrueSource() instanceof EntityMob)) {
			return;
		}

		if (ModConfig.Cheats.GOD_MODE) {

			event.setAmount(0);
			return;

		}

		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		EntityLiving mob = (EntityLiving) event.getSource().getTrueSource();

		if (!mob.hasCapability(EntityData.Data, null)) {
			return;
		}

		Random ran = new Random();

		int mobDMG = mob.getCapability(EntityData.Data, null).getNBT().getInteger(Tags.DAMAGE);

		int DMG = mobDMG / 2 + (ran.nextInt(mobDMG + 2) + 2) / 2;

		/*
		 * Hashtable<String, Integer> stats = PlayerData.getStats(player);
		 * 
		 * DMG = reduceDMGByArmor(DMG, stats.get(Stats.ARMOR.name));
		 * 
		 * DMG = reduceDMGByBlock(DMG, stats.get(Stats.BLOCK.name));
		 * 
		 * if (roll(stats.get(Stats.DODGE.name))) {
		 * 
		 * event.setCanceled(true);
		 * 
		 * return; }
		 * 
		 * float hpmulti = getHpMultiplier(player.getMaxHealth(),
		 * stats.get(Stats.HEALTH.name));
		 * 
		 * event.setAmount(DMG * hpmulti);
		 */

	}

}
