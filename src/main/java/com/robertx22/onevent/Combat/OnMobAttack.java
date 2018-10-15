package com.robertx22.onevent.combat;

import java.util.Random;

import com.robertx22.capability.EntityData;
import com.robertx22.mmorpg.ModConfig;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobAttack {

	@SubscribeEvent
	public static void onMobAttack(LivingDamageEvent event) {

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
