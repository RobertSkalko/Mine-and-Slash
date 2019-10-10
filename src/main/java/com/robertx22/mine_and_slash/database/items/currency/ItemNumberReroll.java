package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ItemNumberReroll extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IHasRecipe {
    @Override
    public String GUID() {
        return "currency/number_reroll";
    }

    private static final String name = Ref.MODID + ":currency/number_reroll";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":number_reroll");
    }

    public ItemNumberReroll() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        for (IRerollable rel : gear.GetAllRerollable()) {
            rel.RerollNumbers(gear);
        }
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearEnumLocReq.REROLL_NUMBERS);
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Hopefully works better than the last time.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Blessing";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls all numbers on a gear";
    }

    @Override
    public int instabilityAddAmount() {
        return 10;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
                .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 5)
                .addMaterial(new ItemAddSecondaryStat().getFromForgeRegistry(), 2)
                .addMaterial(Items.GOLD_INGOT, 3)
                .buildMaterials()
                .setOutput(this)
                .levelReq(1)
                .expGained(25)
                .build();

    }
}