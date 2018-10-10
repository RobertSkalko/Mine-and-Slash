package com.robertx22.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.collection.mutable.HashTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import com.robertx22.capability.EntityData;
import com.robertx22.capability.EntityData.IData;
import com.robertx22.constants.Tags;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.stats.Stat;

public class PlayerData {

	public static int getMaxXP(int lvl) {
		return lvl * 300;
	}

	public static void updateClientXPAndLvl(EntityPlayer player) {

		NBTTagCompound nbt = player.getCapability(EntityData.Data, null).getNBT();

		EntityPlayer clientPlayer = Minecraft.getMinecraft().player;

		int lvl = nbt.getInteger(Tags.LEVEL);
		int exp = nbt.getInteger(Tags.CURRENT_XP);

		int maxXP = getMaxXP(lvl);

		float percentXPFilled = (float) exp / (float) maxXP;

		clientPlayer.experienceLevel = lvl;
		clientPlayer.experience = percentXPFilled;

		player.experienceLevel = lvl;
		player.experience = percentXPFilled;
	}

	public static void setLevel(EntityPlayer player, int lvl) {

		if (Minecraft.getMinecraft().player == null) {
			return;
		}

		int currentXP = 0;

		EntityData.IData data = player.getCapability(EntityData.Data, null);

		NBTTagCompound nbt = data.getNBT();

		nbt.setInteger(Tags.LEVEL, lvl);
		nbt.setInteger(Tags.CURRENT_XP, currentXP);

		data.setNBT(nbt);

		updateClientXPAndLvl(player);

	}

	public static void giveExp(EntityPlayer player, int exp) {

		if (Minecraft.getMinecraft().player == null) {
			return;
		}
		if (ModConfig.Options.BLOCK_EXP_GAIN) {
			exp = 0;
		}

		exp = (int) (exp * ModConfig.Cheats.EXP_RATES);

		EntityData.IData data = player.getCapability(EntityData.Data, null);

		NBTTagCompound nbt = data.getNBT();

		int currentXP = nbt.getInteger(Tags.CURRENT_XP) + exp;

		int maxExp = nbt.getInteger(Tags.MAX_XP);

		if (currentXP > maxExp) {
			currentXP = maxExp;
		}

		int lvl = nbt.getInteger(Tags.LEVEL);

		EntityPlayer clientPlayer = Minecraft.getMinecraft().player;

		if (nbt.getInteger(Tags.CURRENT_XP) >= maxExp) {

			nbt.setInteger(Tags.CURRENT_XP, 0);

			nbt.setInteger(Tags.LEVEL, lvl + 1);

			int maxXp = lvl * 200;

			nbt.setInteger(Tags.MAX_XP, maxXp);

			currentXP = 0;
		}

		nbt.setInteger(Tags.CURRENT_XP, currentXP);

		data.setNBT(nbt);

		updateClientXPAndLvl(player);

	}

	public static Hashtable<String, Stat> getStats(EntityLiving entity) {

		return null;
		/*
		 * Hashtable<String,Stat> stats = new Hashtable<String, Stat>();
		 * 
		 * // here we add all possible gear pieces player uses that can add STATS
		 * List<ItemStack> gear = new ArrayList<>();
		 * 
		 * gear.addAll((Collection<? extends ItemStack>)
		 * entity.getArmorInventoryList()); gear.add(entity.getHeldItemMainhand());
		 * 
		 * int lvl = entity.getCapability(EntityData.Data,
		 * null).getNBT().getInteger(Tags.LEVEL);
		 * 
		 * List<Stat> stats = Stats.getAllStats(); // reset all STATS to 0 for (Stat
		 * stat : stats) {
		 * 
		 * stats.put(stat.name, new Stat());
		 * 
		 * }
		 * 
		 * // minimum STATS so naked combat doesn't crash the game
		 * stats.put(Stats.MIN_DAMAGE.name, 2 * lvl); stats.put(Stats.MAX_DAMAGE.name, 8
		 * * lvl); stats.put(Stats.HEALTH.name, 50 * lvl); stats.put(Stats.MANA.name, 25
		 * * lvl); stats.put(Stats.HEALTH_REGEN.name, 1);
		 * stats.put(Stats.MANA_REGEN.name, 1);
		 * 
		 * for (ItemStack item : gear) {
		 * 
		 * if (item == null || !item.hasTagCompound() ||
		 * !item.getTagCompound().hasKey(Tags.STATS) ||
		 * !item.getTagCompound().hasKey(Tags.ENCHANTS) ||
		 * !item.getTagCompound().hasKey(Tags.SOCKETS)) { continue; }
		 * 
		 * NBTTagCompound nbtStats = item.getTagCompound().getCompoundTag(Tags.STATS);
		 * NBTTagCompound nbtEnchants =
		 * item.getTagCompound().getCompoundTag(Tags.ENCHANTS); NBTTagCompound
		 * nbtSockets = item.getTagCompound().getCompoundTag(Tags.SOCKETS);
		 * 
		 * for (Stat stat : stats) {
		 * 
		 * if (nbtStats.hasKey(stat.name)) { stats.put(stat.name, stats.get(stat.name) +
		 * nbtStats.getInteger(stat.name)); } if (nbtEnchants.hasKey(stat.name)) {
		 * stats.put(stat.name, stats.get(stat.name) +
		 * nbtEnchants.getInteger(stat.name)); } if (nbtSockets.hasKey(stat.name)) {
		 * stats.put(stat.name, stats.get(stat.name) +
		 * nbtSockets.getInteger(stat.name)); } }
		 * 
		 * }
		 * 
		 * return stats;
		 */
	}

	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {

		if (!event.getEntityPlayer().world.isRemote) {

			event.getEntityPlayer().sendMessage(new TextComponentString("clone"));

			EntityPlayer player = event.getEntityPlayer();

			IData data = player.getCapability(EntityData.Data, null);

			IData oldData = event.getOriginal().getCapability(EntityData.Data, null);

			data.setNBT(oldData.getNBT());

			giveExp(player, 0);

		}

	}

}
