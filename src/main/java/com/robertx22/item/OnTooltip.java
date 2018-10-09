package com.robertx22.item;

import com.robertx22.constants.Gear;
import com.robertx22.constants.Stat;
import com.robertx22.constants.Stats;
import com.robertx22.constants.Tag;
import com.robertx22.utilityclasses.ItemUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class OnTooltip {

    ItemStack item;
    NBTTagCompound nbt;
    NBTTagCompound statsNBT;
    NBTTagCompound enchantsNBT;
    NBTTagCompound socketNBT;
    List<Stat> allRandomStats;

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {

        if (event.getEntityPlayer() != null && event.getEntityPlayer().world != null
                && !event.getEntityPlayer().world.isRemote) {
            return;
        }

        item = event.getItemStack();

        if (item == null) {
            return;
        }
        if (!item.hasTagCompound()) {
            return;
        }

        if (ItemUtils.isGear(item)) {

            nbt = item.getTagCompound();

            // log(nbt.toString());
            allRandomStats = Stats.getAllRandomStats();

            statsNBT = nbt.getCompoundTag(Tag.STATS);
            enchantsNBT = nbt.getCompoundTag(Tag.ENCHANTS);
            socketNBT = nbt.getCompoundTag(Tag.SOCKETS);

            event.getToolTip().add("Stats:");

            addStats(event);

            event.getToolTip().add(" ");

            event.getToolTip().add("Sockets:");

            addSockets(event);

            event.getToolTip().add(" ");

            event.getToolTip().add("Upgrade LVL:" + nbt.getInteger(Tag.UPGRADE_NUMBER));

            addRarity(event);

            addLevel(event);

        }

        if (ItemUtils.isSocket(item.getItem())) {

            allRandomStats = Stats.getAllRandomStats();

            NBTTagCompound nbt = item.getTagCompound();

            addRandomStats(event, nbt);

        }

    }

    void addStats(ItemTooltipEvent event) {

        if (statsNBT.hasKey(Stats.HEALTH.name)) {
            if (statsNBT.getInteger(Stats.HEALTH.name) != 0) {
                event.getToolTip().add(
                        TextFormatting.RED + Format(Stats.HEALTH.name) + ": " + statsNBT.getInteger(Stats.HEALTH.name));
            }
        }

        if (statsNBT.hasKey(Stats.MIN_DAMAGE.name)) {
            if (statsNBT.getInteger(Stats.MIN_DAMAGE.name) != 0) {

                int min = statsNBT.getInteger(Stats.MIN_DAMAGE.name);
                int max = statsNBT.getInteger(Stats.MAX_DAMAGE.name);

                event.getToolTip().add(TextFormatting.RED + "Damage" + ": " + min + " - " + max);
            }
        }

        addRandomStats(event, statsNBT);
    }

    void addRandomStats(ItemTooltipEvent event, NBTTagCompound currentTag) {

        for (Stat stat : allRandomStats) {

            if (currentTag.hasKey(stat.name)) {
                if (currentTag.getInteger(stat.name) != 0) {

                    String lore = TextFormatting.RED + Format(stat.name) + ": " + currentTag.getInteger(stat.name);

                    if (stat.isPercent) {
                        lore += "%";
                    }

                    event.getToolTip().add(lore);

                }
            }

        }

    }

    void addEnchants(ItemTooltipEvent event) {

        addRandomStats(event, enchantsNBT);

    }

    void addSockets(ItemTooltipEvent event) {

        addRandomStats(event, socketNBT);

    }

    void addRarity(ItemTooltipEvent event) {

        String rarity = nbt.getString(Tag.RARITY);

        int num = nbt.getInteger(Tag.RARITY_NUMBER);

        event.getToolTip().add(Gear.rarityChatColors[num] + rarity);

    }

    void addLevel(ItemTooltipEvent event) {
        event.getToolTip().add(TextFormatting.YELLOW + "" + "Level: " + nbt.getInteger(Tag.LEVEL));
    }

    String Format(String lore) {

        List<String> list = Arrays.asList(lore.split("_"));

        String formatedLore = "";

        for (String s : list) {

            s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();

            formatedLore += s + " ";

        }

        return formatedLore;
    }

}
