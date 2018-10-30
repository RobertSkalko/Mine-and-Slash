package com.robertx22.onevent.loot;

import com.robertx22.database.lists.Rarities;
import com.robertx22.loot.LootDropsGenerator;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;
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

		if (event.getEntity() instanceof EntityMob && event.getSource().getTrueSource() instanceof EntityPlayer) {

			try {
				EntityLivingBase mobEntity = event.getEntityLiving();

				Unit mob = UnitSaving.Load(mobEntity);
				Unit player = UnitSaving.Load(event.getSource().getTrueSource());

				LootDropsGenerator.Generate(mob, player, mobEntity);

				GiveExp((EntityLivingBase) event.getSource().getTrueSource(), player, mob);

				UnitSaving.Save(event.getSource().getTrueSource(), player);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static void GiveExp(EntityLivingBase playeren, Unit player, Unit mob) {

		int exp = (int) (3 + mob.level * Rarities.Mobs.get(mob.rarity).LootMultiplier());

		player.GiveExp((EntityPlayer) playeren, exp);

	}

}
