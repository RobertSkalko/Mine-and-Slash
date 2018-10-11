package com.robertx22.onevent.loot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.robertx22.capability.EntityData;
import com.robertx22.constants.Chances;
import com.robertx22.constants.Tags;
import com.robertx22.customitems.MyItems;
import com.robertx22.item.GearCreator;
import com.robertx22.item.SocketCreator;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.utilityclasses.RandomUtils;

public class OnMobDeathDrops {

	@SubscribeEvent
	public void mobOnDeathDrop(LivingDeathEvent event) {

		if (event.getEntityLiving().world.isRemote) {
			return;
		}
		if (event.getEntityLiving() instanceof EntityPlayer) {
			return;
		}

		if (event.getEntity() instanceof EntityMob) {

			Entity mob = event.getEntity();

			if (mob.hasCapability(EntityData.Data, null)) {

				NBTTagCompound nbt = mob.getCapability(EntityData.Data, null).getNBT();
				int lvl = nbt.getInteger(Tags.LEVEL);
				int rarity = nbt.getInteger(Tags.RARITY_NUMBER);

				List<ItemStack> items = createDropTable(mob, lvl, rarity);

				for (ItemStack item : items) {

					mob.entityDropItem(item, 0);

				}

			}
		}
	}

	List<ItemStack> createDropTable(Entity mob, int lvl, int rarity) {

		List<ItemStack> items = new ArrayList<>();

		int gearAmount = RandomUtils.rollArray(Chances.GEAR_AMOUNT, ModConfig.Cheats.DROP_RATES, rarity * 0.05F);
		int socketChance = (int) ((3 + rarity) * ModConfig.Cheats.DROP_RATES);
		int powderChance = (int) (30 * ModConfig.Cheats.DROP_RATES);

		if (rarity >= 3) {
			gearAmount++;
		}
		if (rarity == 4) {
			gearAmount++;
		}

		if (RandomUtils.roll(socketChance)) {
			int socketRarity = RandomUtils.rollArray(Chances.GEAR_RARITY, ModConfig.Cheats.DROP_RATES, rarity * 0.05F);
			String gearType = new Random().nextBoolean() ? Tags.ARMOR : Tags.WEAPON;

			items.add((SocketCreator.createSocket(lvl, socketRarity, gearType)));
		}

		if (RandomUtils.roll(powderChance)) {
			if (rarity == 0) {
				items.add(new ItemStack(MyItems.magic_powder));
			}
			if (rarity == 1) {
				items.add(new ItemStack(MyItems.rare_powder));
			}
			if (rarity == 2) {
				items.add(new ItemStack(MyItems.epic_powder));
			}
			if (rarity == 3) {
				items.add(new ItemStack(MyItems.legendary_powder));
			}
			if (rarity == 4) {
				items.add(new ItemStack(MyItems.mythical_powder));
			}
		}

		for (int i = 0; i < gearAmount; i++) {

			int gearRarity = RandomUtils.rollWhile(30, 0, 4);
			String type;

			if (RandomUtils.roll(20)) {
				type = Tags.WEAPON;
			} else {
				type = Tags.ARMOR;
			}

			ItemStack drop = GearCreator.createGear(lvl, gearRarity, type);

			items.add((drop));

		}

		return items;

	}

}
