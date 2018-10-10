package com.robertx22.item;

import com.robertx22.constants.Gear;
import com.robertx22.constants.Stat;
import com.robertx22.constants.Stats;
import com.robertx22.constants.Tags;
import com.robertx22.utilityclasses.ItemUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.Random;

public class GearCreator {

    public static ItemStack createGear(int lvl, int rarity, String type) {

        GearCreator gearCreatorClass = new GearCreator();

        ItemStack item = gearCreatorClass.createBaseItem(lvl, rarity, type);

        gearCreatorClass.createItemStats(item, rarity);

        return item;
    }

    ItemStack createBaseItem(int lvl, int rarity, String type) {

        ItemStack item = new ItemStack(Items.AIR);

        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setBoolean(Tags.IS_GEAR, true);

        nbt.setTag(Tags.STATS, new NBTTagCompound());
        nbt.setTag(Tags.ENCHANTS, new NBTTagCompound());
        nbt.setTag(Tags.SOCKETS, new NBTTagCompound());

        Random ran = new Random();

        if (type.equals(Tags.WEAPON)) {
            switch (rarity) {

                case 0:
                    item = new ItemStack(Gear.magic_weapons[ran.nextInt(Gear.magic_weapons.length)]);
                    break;
                case 1:
                    item = new ItemStack(Gear.rare_weapons[ran.nextInt(Gear.rare_weapons.length)]);
                    break;
                case 2:
                    item = new ItemStack(Gear.epic_weapons[ran.nextInt(Gear.epic_weapons.length)]);
                    break;
                case 3:
                    item = new ItemStack(Gear.legendary_weapons[ran.nextInt(Gear.legendary_weapons.length)]);
                    break;
                case 4:
                    item = new ItemStack(Gear.mythical_weapons[ran.nextInt(Gear.mythical_weapons.length)]);
                    break;

            }

            nbt.setString(Tags.GEAR_TYPE, Tags.WEAPON);
        }
        else {

            switch (rarity) {

                case 0:
                    item = new ItemStack(Gear.magic_armors[ran.nextInt(Gear.magic_armors.length)]);
                    break;
                case 1:
                    item = new ItemStack(Gear.rare_armors[ran.nextInt(Gear.rare_armors.length)]);
                    break;
                case 2:
                    item = new ItemStack(Gear.epic_armors[ran.nextInt(Gear.epic_armors.length)]);
                    break;
                case 3:
                    item = new ItemStack(Gear.legendary_armors[ran.nextInt(Gear.legendary_armors.length)]);
                    break;
                case 4:
                    item = new ItemStack(Gear.mythical_armors[ran.nextInt(Gear.mythical_armors.length)]);
                    break;

            }

            nbt.setString(Tags.GEAR_TYPE, Tags.ARMOR);
        }

        nbt.setString(Tags.RARITY, Gear.rarityNames[rarity]);
        nbt.setInteger(Tags.RARITY_NUMBER, rarity);
        nbt.setInteger("HideFlags", 63);
        nbt.setInteger(Tags.LEVEL, lvl);
        nbt.setInteger(Tags.UPGRADE_NUMBER, 0);

        item.setTagCompound(nbt);

        item = setName(item, rarity);

        return item;
    }

    ItemStack setName(ItemStack item, int rarityNum) {

        item = item.setStackDisplayName(
                Gear.rarityChatColors[rarityNum] + "" + TextFormatting.BOLD + item.getDisplayName());

        return item;
    }

    ItemStack createItemStats(ItemStack item, int rarity) {

        List<Stat> randomStats = Stats.getAllRandomStats();

        int lvl = item.getTagCompound().getInteger(Tags.LEVEL);

        String gearType = item.getTagCompound().getString(Tags.GEAR_TYPE);
        int rarityNumber = item.getTagCompound().getInteger(Tags.RARITY_NUMBER);
        NBTTagCompound nbt = item.getTagCompound().getCompoundTag(Tags.STATS);
        int statAmount = 1;

        switch (rarity) {
            case 1:
                statAmount = 2;
                break;
            case 2:
                statAmount = 3;
                break;
            case 3:
                statAmount = 4;
                break;
            case 4:
                statAmount = 5;
                break;
        }

        Random ran = new Random();

        if (gearType == Tags.ARMOR) {

            int num = ItemUtils.randomizeBaseStat(com.robertx22.Stats.HEALTH, rarityNumber) * lvl;
            nbt.setInteger(com.robertx22.Stats.HEALTH.name, num);
        }

        if (gearType == Tags.WEAPON) {
            int min = ItemUtils.randomizeBaseStat(com.robertx22.Stats.MIN_DAMAGE, rarityNumber) * lvl;
            int max = ItemUtils.randomizeBaseStat(com.robertx22.Stats.MAX_DAMAGE, rarityNumber) * lvl;

            max = max + min;

            nbt.setInteger(com.robertx22.Stats.MIN_DAMAGE.name, min);
            nbt.setInteger(com.robertx22.Stats.MAX_DAMAGE.name, max);
        }

        while (statAmount > 0) {

            int statNum = ran.nextInt(randomStats.size());
            Stat ranStat = randomStats.get(statNum);

            if (nbt.hasKey(ranStat.name)) {
                if (nbt.getInteger(ranStat.name) != 0) {
                    continue;
                }
            }

            if (ranStat.type != gearType && ranStat.type != Tags.ALL_TYPES) {
                continue;
            }

            int num = ItemUtils.randomizeStat(ranStat);

            // if the stat scales / not a perecent, multiply with lvl
            if (!randomStats.get(statNum).isPercent) {
                num *= lvl;
            }

            nbt.setInteger(ranStat.name, num);

            statAmount--;

        }

        item.getTagCompound().setTag(Tags.STATS, nbt);

        return item;
    }

}
