package com.robertx22.mine_and_slash.potion_effects;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;

import java.util.Arrays;

public class PotionDataSaving {

    private static String LOC = Ref.MODID + ":pot_data";

    public static ExtraPotionData getData(EffectInstance instance) {

        if (instance.getCurativeItems().size() > 0) {
            ItemStack stack = instance.getCurativeItems().get(0);
            return LoadSave.Load(ExtraPotionData.class, new ExtraPotionData(), stack.getTag(), LOC);
        }
        return null;
    }

    public static void saveData(EffectInstance instance, ExtraPotionData data) {
        ItemStack stack = new ItemStack(Items.AIR);

        CompoundNBT nbt = new CompoundNBT();
        LoadSave.Save(data, nbt, LOC);

        stack.setTag(nbt);
        instance.setCurativeItems(Arrays.asList(stack));
    }

}
