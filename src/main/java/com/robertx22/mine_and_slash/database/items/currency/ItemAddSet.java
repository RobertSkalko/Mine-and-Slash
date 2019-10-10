package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SetData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ItemAddSet extends CurrencyItem implements ICurrencyItemEffect, IHasRecipe {

    @Override
    public String GUID() {
        return "currency/add_set";
    }

    private static final String name = Ref.MODID + ":currency/add_set";

    public ItemAddSet() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.set = new SetData();

        GearBlueprint blueprint = new GearBlueprint(gear.level);
        blueprint.SetCustomSetChance(100);

        if (blueprint.canGetSet(gear)) {
            gear.set = new SetData();
            gear.set = gear.set.generate(gear);
        }

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearEnumLocReq.SETS, SimpleGearLocReq.NO_SET);
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Become one.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Key of Unity";
    }

    @Override
    public String locDescForLangFile() {
        return "Adds a set to an item";
    }

    @Override
    public int instabilityAddAmount() {
        return 40;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
                .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 10)
                .addMaterial(new ItemLevelUpGear().getFromForgeRegistry(), 3)
                .addMaterial(new ItemOrbOfTransmutation().getFromForgeRegistry(), 5)
                .addMaterial(Items.ENDER_EYE, 1)
                .buildMaterials()
                .setOutput(this)
                .levelReq(75)
                .expGained(50)
                .build();

    }
}
