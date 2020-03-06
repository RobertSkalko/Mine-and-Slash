package com.robertx22.mine_and_slash.database.currency.map;

import com.robertx22.mine_and_slash.database.currency.OrbOfTransmutationItem;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.MapReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class EndlessSkiesItem extends CurrencyItem implements ICurrencyItemEffect, IHasRecipe {

    @Override
    public String GUID() {
        return "currency/add_map_level";
    }

    private static final String name = Ref.MODID + ":currency/add_map_level";

    public EndlessSkiesItem() {
        super(name);
        this.itemTypesUsableOn = ItemType.MAP;
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        MapItemData map = Map.Load(stack);

        map.level += 5;

        Map.Save(stack, map);

        return stack;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
            .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 40)
            .addMaterial(new OrbOfTransmutationItem().getFromForgeRegistry(), 1)
            .addMaterial(Items.GOLD_INGOT, 1)
            .buildMaterials()
            .setOutput(this)
            .levelReq(5)
            .expGained(50)
            .build();

    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(MapReq.INSTANCE);
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Venture further.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Endless Skies";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases map level by 5";
    }

    @Override
    public boolean addsInstability() {
        return false;
    }

    @Override
    public int instabilityAddAmount() {
        return 0;
    }

}