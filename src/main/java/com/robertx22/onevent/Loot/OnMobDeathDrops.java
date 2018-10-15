package com.robertx22.onevent.loot;

import com.robertx22.capability.EntityData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
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
		if (event.getEntityLiving() instanceof EntityPlayer) {
			return;
		}

		if (event.getEntity() instanceof EntityMob) {

			Entity mob = event.getEntity();

			if (mob.hasCapability(EntityData.Data, null)) {

				/*
				 * List<ItemStack> items = createDropTable(mob, lvl, rarity);
				 * 
				 * for (ItemStack item : items) {
				 * 
				 * mob.entityDropItem(item, 0);
				 * 
				 * }
				 * 
				 */
			}
		}
	}

}
