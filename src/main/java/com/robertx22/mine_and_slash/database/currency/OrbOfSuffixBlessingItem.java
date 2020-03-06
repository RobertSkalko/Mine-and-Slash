package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class OrbOfSuffixBlessingItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IHasRecipe {
    @Override
    public String GUID() {
        return "currency/reroll_suffix_numbers";
    }

    private static final String name = Ref.MODID + ":currency/reroll_suffix_numbers";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":reroll_suffix_numbers");
    }

    public OrbOfSuffixBlessingItem() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.suffix.RerollNumbers(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.AFFIXES, SimpleGearLocReq.HAS_SUFFIX);
    }

    @Override
    public int Tier() {
        return 14;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("I command you to change!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Suffix Blessing";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls numbers of a suffix";
    }

    @Override
    public int instabilityAddAmount() {
        return 10;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
            .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 8)
            .addMaterial(new OrbOfEverChangingSuffixItem().getFromForgeRegistry(), 2)
            .addMaterial(Items.IRON_INGOT, 3)
            .buildMaterials()
            .setOutput(this)
            .levelReq(10)
            .expGained(10)
            .build();

    }
}