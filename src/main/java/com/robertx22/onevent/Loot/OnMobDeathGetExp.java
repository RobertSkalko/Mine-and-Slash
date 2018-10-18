package com.robertx22.onevent.loot;

import com.robertx22.capability.EntityData;
import com.robertx22.capability.EntityData.IEntityData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobDeathGetExp {

	@SubscribeEvent
	public static void mobOnDeathgetExp(LivingDeathEvent event) {

		if (event.getEntityLiving().world.isRemote) {
			return;
		}
		if (!(event.getEntityLiving() instanceof EntityMob)) {
			return;
		}
		if (event.getSource() == null) {
			return;
		}
		if (event.getSource().getTrueSource() == null) {
			return;
		}

		if (event.getSource().getTrueSource() instanceof EntityPlayer) {

			Entity mob = event.getEntity();

			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

			EntityData.IEntityData mobdata = (IEntityData) mob.getCapability(EntityData.Data, null);

			NBTTagCompound mobnbt = mobdata.getNBT();

			// int exp = mobnbt.getInteger(Tags.LEVEL) *
			// (Mob.rarityXPMulti[mobnbt.getInteger(Tags.RARITY_NUMBER)]);

			// Unit unit = Saving.Load(player, Unit.class);

			// unit.GiveExp(player, exp);

		}

	}
}