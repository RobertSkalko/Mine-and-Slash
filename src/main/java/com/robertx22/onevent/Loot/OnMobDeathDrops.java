package com.robertx22.onevent.loot;

import com.robertx22.database.lists.Rarities;
import com.robertx22.loot.LootDropsGenerator;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobDeathDrops {

	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void mobOnDeathDrop(LivingDeathEvent event) {

		if (event.getEntityLiving().world.isRemote) {
			return;
		}

		try {

			EntityLivingBase entity = event.getEntityLiving();

			if (!(entity instanceof EntityPlayer)) {
				if (entity instanceof IMob || entity instanceof EntityMob) {

					if (entity instanceof EntitySlime && ((EntitySlime) entity).isSmallSlime()) {
						return;
					}

					Unit victim = UnitSaving.Load(entity);
					Unit killer = UnitSaving.Load(event.getSource().getTrueSource());

					LootDropsGenerator.Generate(victim, killer, entity);

					if (event.getSource().getTrueSource() instanceof EntityPlayer) {
						GiveExp((EntityLivingBase) event.getSource().getTrueSource(), killer, victim);
						UnitSaving.Save(event.getSource().getTrueSource(), killer);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void GiveExp(EntityLivingBase playeren, Unit player, Unit mob) {

		int exp = (int) (3 + mob.level * Rarities.Mobs.get(mob.rarity).LootMultiplier());

		player.GiveExp((EntityPlayer) playeren, exp);

	}

}
