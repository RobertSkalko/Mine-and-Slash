package com.robertx22.onevent.combat;

import com.robertx22.capability.EntityData;
import com.robertx22.datasaving.Saving;
import com.robertx22.saveclasses.Unit;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobSpawn {

	@SubscribeEvent
	public static void onMobSpawn(LivingSpawnEvent.CheckSpawn event) throws Exception {

		if (Minecraft.getMinecraft().player == null) {
			return;
		}
		if (!(event.getEntityLiving() instanceof EntityMob)) {
			return;
		}
		if (event.getEntityLiving().world.isRemote) {
			return;

		}
		if (!event.getEntityLiving().hasCapability(EntityData.Data, null)) {
			return;
		}

		EntityLiving mob = (EntityLiving) event.getEntityLiving();

		Saving.Save(mob, Unit.Mob(mob));

		// System.out.println("Saved unit to mob");

		/*
		 * 
		 * if (event.getEntityLiving().hasCapability(EntityData.Data, null) &&
		 * !event.getEntityLiving() .getCapability(EntityData.Data,
		 * null).getNBT().getBoolean(Tags.ENTITY_INFO)) {
		 * 
		 * EntityLiving mob = (EntityLiving) event.getEntityLiving();
		 * 
		 * EntityData.IData data = mob.getCapability(EntityData.Data, null);
		 * 
		 * NBTTagCompound nbt = GeneralUtils.getdefaultEntityNBT();
		 * 
		 * int rarity = RandomUtils.rollArray(Chances.MOB_SPAWN_RARITY);
		 * 
		 * int lvl = Mob.getRandomLevel(event.getWorld().playerEntities.get(0));
		 * 
		 * int hp = (int) (mob.getMaxHealth() * lvl * Mob.rarityHPMulti[rarity] *
		 * ModConfig.Cheats.DIFFICULTY); int dmg = (int) (10 * lvl *
		 * Mob.rarityDMGMulti[rarity] / 4 * ModConfig.Cheats.DIFFICULTY);
		 * 
		 * nbt.setInteger(com.robertx22.Stats.HEALTH.name, hp);
		 * nbt.setInteger(Tags.LEVEL, lvl); nbt.setInteger(Tags.RARITY_NUMBER, rarity);
		 * nbt.setString(Tags.RARITY, Mob.rarityNames[rarity]);
		 * nbt.setBoolean(Tags.ENTITY_INFO, true); nbt.setInteger(Tags.DAMAGE, dmg);
		 * 
		 * mob.setCustomNameTag(Gear.rarityChatColors[rarity] + "" + " " + "LvL " + lvl
		 * + " " + Mob .rarityNames[rarity] + " " + mob.getName());
		 * 
		 * mob.setAlwaysRenderNameTag(true);
		 * 
		 * data.setNBT(nbt);
		 * 
		 * event.setResult(Event.Result.ALLOW);
		 * 
		 * }
		 * 
		 */
	}

}
