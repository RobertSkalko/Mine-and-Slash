package com.robertx22.mine_and_slash.new_content_test.requests;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import net.minecraft.item.ItemStack;

public abstract class BaseBlueprintRequest implements ITooltipList {

    public int UNUSED_INT;

    public abstract int getDifficultyValue();

    public abstract boolean matches(ItemStack stack);

    public abstract void random(ItemRarity rar);

    public static float getRarityDifficultyMulti(int rar) {

        return Rarities.Items.get(rar).powerMultiplier();
    }

    public static float getTierDifficultyMulti(int num) {
        return num * 0.1F;
    }

}

