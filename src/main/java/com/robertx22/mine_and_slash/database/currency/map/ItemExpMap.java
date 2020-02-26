package com.robertx22.mine_and_slash.database.currency.map;

import com.robertx22.mine_and_slash.database.currency.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.ItemOrbOfTransmutation;
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

public class ItemExpMap extends CurrencyItem implements ICurrencyItemEffect, IHasRecipe {

    @Override
    public String GUID() {
        return "currency/exp_map";
    }

    private static final String name = Ref.MODID + ":currency/exp_map";

    public ItemExpMap() {
        super(name);
        this.itemTypesUsableOn = ItemType.MAP;
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        MapItemData map = Map.Load(stack);

        map.isExp = true;

        Map.Save(stack, map);

        return stack;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
            .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 5)
            .addMaterial(new ItemOrbOfTransmutation().getFromForgeRegistry(), 1)
            .addMaterial(Items.IRON_INGOT, 1)
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
        return IRarity.Rare;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Become enlightened.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Painful Lesson";
    }

    @Override
    public String locDescForLangFile() {
        return "Transforms loot into exp bonus, times two.";
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