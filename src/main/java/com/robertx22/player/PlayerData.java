package com.robertx22.player;

import com.robertx22.capability.EntityData;
import com.robertx22.capability.EntityData.IData;
import com.robertx22.constants.Stat;
import com.robertx22.constants.Stats;
import com.robertx22.constants.Tag;
import com.robertx22.mmorpg.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class PlayerData {

    public static int getMaxXP(int lvl) {
        return lvl * 300;
    }

    public static void updateClientXPAndLvl(EntityPlayer player) {

        NBTTagCompound nbt = player.getCapability(EntityData.Data, null).getNBT();

        EntityPlayer clientPlayer = Minecraft.getMinecraft().player;

        int lvl = nbt.getInteger(Tag.LEVEL);
        int exp = nbt.getInteger(Tag.CURRENT_XP);

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

        nbt.setInteger(Tag.LEVEL, lvl);
        nbt.setInteger(Tag.CURRENT_XP, currentXP);

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

        int currentXP = nbt.getInteger(Tag.CURRENT_XP) + exp;

        int maxExp = nbt.getInteger(Tag.MAX_XP);

        if (currentXP > maxExp) {
            currentXP = maxExp;
        }

        int lvl = nbt.getInteger(Tag.LEVEL);

        EntityPlayer clientPlayer = Minecraft.getMinecraft().player;

        if (nbt.getInteger(Tag.CURRENT_XP) >= maxExp) {

            nbt.setInteger(Tag.CURRENT_XP, 0);

            nbt.setInteger(Tag.LEVEL, lvl + 1);

            int maxXp = lvl * 200;

            nbt.setInteger(Tag.MAX_XP, maxXp);

            currentXP = 0;
        }

        nbt.setInteger(Tag.CURRENT_XP, currentXP);

        data.setNBT(nbt);

        updateClientXPAndLvl(player);

    }

    public static Hashtable<String, Integer> getStats(EntityPlayer player) {

        Hashtable<String, Integer> playerStats = new Hashtable<>();

        // here we add all possible gear pieces player uses that can add STATS
        List<ItemStack> gear = new ArrayList<>();

        gear.addAll(player.inventory.armorInventory);
        gear.add(player.getHeldItemMainhand());

        int lvl = player.getCapability(EntityData.Data, null).getNBT().getInteger(Tag.LEVEL);

        List<Stat> stats = Stats.getAllStats();
        // reset all STATS to 0
        for (Stat stat : stats) {

            playerStats.put(stat.name, 0);

        }

        // minimum STATS so naked combat doesn't crash the game
        playerStats.put(Stats.MIN_DAMAGE.name, 2 * lvl);
        playerStats.put(Stats.MAX_DAMAGE.name, 8 * lvl);
        playerStats.put(Stats.HEALTH.name, 50 * lvl);
        playerStats.put(Stats.MANA.name, 25 * lvl);
        playerStats.put(Stats.HEALTH_REGEN.name, 1);
        playerStats.put(Stats.MANA_REGEN.name, 1);

        for (ItemStack piece : gear) {

            if (piece == null) {
                continue;
            }
            if (!piece.hasTagCompound()) {
                continue;
            }
            if (!piece.getTagCompound().hasKey(Tag.STATS)) {
                continue;
            }
            if (!piece.getTagCompound().hasKey(Tag.ENCHANTS)) {
                continue;
            }
            if (!piece.getTagCompound().hasKey(Tag.SOCKETS)) {
                continue;
            }

            NBTTagCompound nbtStats = piece.getTagCompound().getCompoundTag(Tag.STATS);
            NBTTagCompound nbtEnchants = piece.getTagCompound().getCompoundTag(Tag.ENCHANTS);
            NBTTagCompound nbtSockets = piece.getTagCompound().getCompoundTag(Tag.SOCKETS);

            for (Stat stat : stats) {

                if (nbtStats.hasKey(stat.name)) {
                    playerStats.put(stat.name, playerStats.get(stat.name) + nbtStats.getInteger(stat.name));
                }
                if (nbtEnchants.hasKey(stat.name)) {
                    playerStats.put(stat.name, playerStats.get(stat.name) + nbtEnchants.getInteger(stat.name));
                }
                if (nbtSockets.hasKey(stat.name)) {
                    playerStats.put(stat.name, playerStats.get(stat.name) + nbtSockets.getInteger(stat.name));
                }
            }

        }

        return playerStats;

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
