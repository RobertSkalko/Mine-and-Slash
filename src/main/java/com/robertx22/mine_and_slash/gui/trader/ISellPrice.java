package com.robertx22.mine_and_slash.gui.trader;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

public interface ISellPrice {
    int getPriceInCommonOres();

    static String LOC = "mmorpg:sell_price";

    static int getPriceInCommonOres(ItemStack stack) {

        try {
            return stack.getTag()
                .getInt(LOC);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Integer.MAX_VALUE;

    }

    static void savePriceInCommonOres(ItemStack stack, int ores) {

        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }

        stack.getTag()
            .putInt(LOC, ores);

    }

    public static boolean hasEnoughMoney(PlayerEntity player, int needed) {

        int current = 0;

        for (ItemStack x : player.inventory.mainInventory) {
            if (x.getItem() instanceof ItemOre) {
                ItemOre ore = (ItemOre) x.getItem();
                current += rarityOresToCommons(Rarities.Gears.get(ore.rarity), x.getCount());
            }
        }

        return current >= needed;
    }

    public static void spendMoney(PlayerEntity player, int toSpend) {

        for (ItemStack x : player.inventory.mainInventory) {
            if (x.getItem() instanceof ItemOre) {
                ItemOre ore = (ItemOre) x.getItem();

                for (int i = 0; i < x.getCount(); i++) {
                    if (toSpend > 0) {
                        int val = rarityOresToCommons(Rarities.Gears.get(ore.rarity), 1);
                        toSpend -= val;
                    }
                }
            }
        }

    }

    public static ImmutablePair<Rarity, Integer> commonOresToBiggestPossibleRarity(int commons) {

        int rarity = 0;

        for (int i = IRarity.Uncommon; i < IRarity.Mythic; i++) {
            if (commons > 40) {
                commons /= 9;
                rarity++;
            }
        }

        return ImmutablePair.of(Rarities.Gears.get(rarity), commons);
    }

    public static ItemStack getHighestRarityStackFromCommons(int ores) {

        ItemStack stack = ItemStack.EMPTY;

        ImmutablePair<Rarity, Integer> pair = ISellPrice.commonOresToBiggestPossibleRarity(ores);

        if (pair.right > 0) {
            stack = new ItemStack(ItemOre.ItemOres.get(pair.left.Rank()));
            stack.setCount(pair.right);
        }

        return stack;

    }

    static void giveBackChange(PlayerEntity player, int change) {
        ItemStack stack = getHighestRarityStackFromCommons(change);
        PlayerUtils.giveItem(stack, player);
    }

    public static int rarityOresToCommons(Rarity rar, int ores) {

        return (int) (ores * Math.pow(9, MathHelper.clamp(rar.Rank() + 1, 1, 6)));
    }

}
