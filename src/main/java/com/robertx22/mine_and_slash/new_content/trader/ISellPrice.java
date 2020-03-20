package com.robertx22.mine_and_slash.new_content.trader;

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

import java.util.ArrayList;
import java.util.HashMap;

public interface ISellPrice {
    int getSavedPriceInCommonOres();

    static String LOC = "mmorpg:sell_price";

    static void removePrice(ItemStack stack) {
        try {
            stack.getTag()
                .remove(LOC);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static int getSavedPriceInCommonOres(ItemStack stack) {

        try {
            return stack.getTag()
                .getInt(LOC);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Integer.MAX_VALUE;

    }

    static boolean hasPrice(ItemStack stack) {
        return stack.hasTag() && stack.getTag()
            .contains(LOC);
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

        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack.getItem() instanceof ItemOre) {
                ItemOre ore = (ItemOre) stack.getItem();
                current += stack.getCount() * ore.getValueInCommonOres();
            }
        }

        return current >= needed;
    }

    public static void spendMoney(PlayerEntity player, int toSpend) {

        for (ItemStack stack : new ArrayList<>(player.inventory.mainInventory)) {
            if (stack.getItem() instanceof ItemOre) {
                ItemOre ore = (ItemOre) stack.getItem();

                int c = stack.getCount();

                for (int i = 0; i < c; i++) {
                    if (toSpend > 0 && stack.getCount() > 0) {
                        int val = ore.getValueInCommonOres();
                        stack.shrink(1);
                        toSpend -= val;
                        player.inventory.markDirty();
                    }
                }
            }
        }

        if (toSpend > 0) {
            try {
                throw new RuntimeException("Tried to spend money when player didn't have enough!");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        if (toSpend < 0) {
            giveBackChange(player, Math.abs(toSpend));
        }

    }

    public static ImmutablePair<Rarity, Integer> commonOresToBiggestPossibleRarity(int ores) {

        int rarity = 0;

        for (int i = 0; i < 6; i++) {

            if (rarity < IRarity.Highest) {

                if (ores >= 9) {
                    ores /= 9;
                    rarity++;
                }
            }
        }

        return ImmutablePair.of(Rarities.Gears.get(rarity), ores);
    }

    public static HashMap<Rarity, Integer> commonOresToExactListOfHigherRarities(int ores) {
        HashMap<Rarity, Integer> map = new HashMap<>();

        int rarity = 0;

        for (int i = 0; i < 6; i++) {

            if (rarity < IRarity.Highest) {

                if (ores >= 9) {

                    int rem = ores % 9;

                    if (rem > 0) {
                        Rarity rar = Rarities.Gears.get(rarity);
                        map.put(rar, map.getOrDefault(rar, 0) + rem);
                    }
                    ores /= 9;
                    rarity++;

                }

            }
        }

        Rarity rar = Rarities.Gears.get(rarity);
        map.put(rar, map.getOrDefault(rar, 0) + ores);

        return map;
    }

    public static ItemStack getHighestRarityStackFromCommons(int ores) {

        ItemStack stack = ItemStack.EMPTY;

        ImmutablePair<Rarity, Integer> pair = commonOresToBiggestPossibleRarity(ores);

        Rarity rar = pair.left;
        int amount = pair.right;

        if (amount > 0) {
            stack = new ItemStack(ItemOre.ItemOres.get(rar.Rank()));
            stack.setCount(amount);
        }

        return stack;

    }

    static void giveBackChange(PlayerEntity player, int change) {
        ItemStack stack = getHighestRarityStackFromCommons(change);
        PlayerUtils.giveItem(stack, player);
    }

    public static int rarityOresToCommons(Rarity rar, int ores) {

        return (int) (ores * Math.pow(9, MathHelper.clamp(rar.Rank(), 1, 6)));
    }

}
