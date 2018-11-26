package com.robertx22.onevent.loot;

import com.robertx22.db_lists.Rarities;
import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.loot.MasterLootGen;
import com.robertx22.mmorpg.Main;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
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

					UnitData victim = entity.getCapability(EntityData.Data, null);
					UnitData killer = event.getSource().getTrueSource().getCapability(EntityData.Data, null);

					if (event.getSource().getTrueSource() instanceof EntityPlayer) {

						IWorldData world = Load.World(entity.world);

						MasterLootGen.genAndDrop(victim, killer, world, entity);

						int exp = GiveExp((EntityLivingBase) event.getSource().getTrueSource(), killer, victim);

						NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(entity.dimension,
								entity.posX, entity.posY, entity.posZ, 32);

						Main.Network.sendToAllAround(new DamageNumberPackage(entity, Elements.Nature,
								"+" + DamageEffect.FormatNumber(exp) + " Exp!"), point);
					}

				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	private static int GiveExp(EntityLivingBase entity, UnitData player, UnitData mob) {

		int exp = (int) (mob.getLevel() * Rarities.Mobs.get(mob.getRarity()).ExpOnKill());

		exp = player.GiveExp((EntityPlayer) entity, exp);

		return exp;

	}

}
