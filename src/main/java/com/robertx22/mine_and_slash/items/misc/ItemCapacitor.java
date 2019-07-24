package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemCapacitor extends Item {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemCapacitor(int rarity) {

        super(new Properties().group(CreativeTabs.MyModTab));

        this.rarity = rarity;

        RegisterItemUtils.RegisterItemName(this, "capacitor" + rarity);
    }

    int rarity;

    public List<Float> RepairValues = Arrays.asList(0.95F, 0.9F, 0.8F, 0.7F, 0.6F, 0.5F);

    public List<Float> bonusSalvageChance = Arrays.asList(1F, 2.5F, 5F, 10F, 15F, 25F);

    public Float getSalvageBonusChance() {

        return bonusSalvageChance.get(rarity);

    }

    public Float GetFuelMultiplier() {

        return RepairValues.get(rarity);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        Tooltip.add(CLOC.tooltip("capacitor"), tooltip);

        Tooltip.add(CLOC.tooltip("capacitor2")
                .appendText(": " + this.GetFuelMultiplier() + "x"), tooltip);

        Tooltip.add(Words.Bonus_Salvage_Chance.locName()
                .appendText(": " + this.getSalvageBonusChance() + "%"), tooltip);

    }

}
