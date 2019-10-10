package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ItemCheapGearLvl extends CurrencyItem implements ICurrencyItemEffect, IHasRecipe {
    @Override
    public String GUID() {
        return "currency/cheap_gear_lvl";
    }

    public static final String ID = Ref.MODID + ":currency/cheap_gear_lvl";

    public ItemCheapGearLvl() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.setLevel(gear.getLevel() + 5);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(SimpleGearLocReq.LEVEL_ISNT_HIGHER_THAN_MAX);
    }

    @Override
    public int Tier() {
        return 1;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Impatience breeds failure.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Stone of Corruption";
    }

    @Override
    public String locDescForLangFile() {
        return "Increase Gears's lvl by 5";
    }

    @Override
    public int instabilityAddAmount() {
        return 250;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
                .addMaterial(new ItemOrbOfTransmutation().getFromForgeRegistry(), 3)
                .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 3)
                .addMaterial(Items.COAL, 5)
                .addMaterial(Items.GOLDEN_CARROT, 1)
                .addMaterial(Items.DIAMOND, 1)
                .buildMaterials()
                .setOutput(this)
                .levelReq(1)
                .expGained(15)
                .build();

    }

}