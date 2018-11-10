package com.robertx22.onevent.loot;

import com.robertx22.database.lists.Rarities;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.loot.LootDropsGenerator;
import com.robertx22.mmorpg.Main;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.saveclasses.DamageNumberData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.datasaving.bases.Saving;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class OnMobDeathDrops {

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

					if (event.getSource().getTrueSource() instanceof EntityPlayer) {
						LootDropsGenerator.Generate(victim, killer, entity);
						int exp = GiveExp((EntityLivingBase) event.getSource().getTrueSource(), killer, victim);
						UnitSaving.Save(event.getSource().getTrueSource(), killer);

						NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(entity.dimension,
								entity.posX, entity.posY, entity.posZ, 32);

						Main.Network.sendToAllAround(
								new DamageNumberPackage(Saving.ToString(new DamageNumberData(
										"+" + DamageEffect.FormatNumber(exp) + " Exp!", Elements.Nature, entity))),
								point);
					}

				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	private static int GiveExp(EntityLivingBase playeren, Unit player, Unit mob) {

		int exp = (int) (mob.level * Rarities.Mobs.get(mob.rarity).ExpOnKill());

		player.GiveExp((EntityPlayer) playeren, exp);

		return exp;

	}

}
