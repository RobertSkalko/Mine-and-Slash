package com.robertx22.onevent.combat;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobSpawn {

	@SubscribeEvent
	public static void damn(LivingHealEvent event) {

	}

	@SubscribeEvent
	public static void onMobSpawn(EntityJoinWorldEvent event) {

		if (!(event.getEntity() instanceof EntityLivingBase)) {
			return;
		}

		EntityLivingBase entity = (EntityLivingBase) event.getEntity();

		if (Minecraft.getMinecraft().player == null) {
			return;
		}
		if (entity.world.isRemote) {
			return;
		}
		if (!entity.hasCapability(EntityData.Data, null)) {
			return;
		}

		Unit check = UnitSaving.Load(entity);

		if (!(entity instanceof EntityPlayer) && check == null) {
			Unit unit = Unit.Mob(entity);
			UnitSaving.Save(entity, unit);

		}

		entity.setHealth(200);

	}

}
