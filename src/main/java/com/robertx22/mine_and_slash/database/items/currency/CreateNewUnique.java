package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.UniqueGearLootGen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CreateNewUnique extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IAutoLocMultiLore, IHasRecipe {

    private static final String GUID = Ref.MODID + ":currency/create_new_unique";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":create_new_unique");
    }

    @Override
    public String GUID() {
        return "currency/create_new_unique";
    }

    public CreateNewUnique() {

        super(GUID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        UniqueGearBlueprint gearPrint = new UniqueGearBlueprint(gear.level, gear.uniqueStats
                .getUniqueItem()
                .Tier(), false);
        gearPrint.setSpecificRarity(new UniqueGear().Rank());
        gearPrint.LevelRange = false;

        GearItemData newgear = UniqueGearLootGen.CreateData(gearPrint);

        int tries = 0; // in case there's only 1 unique in a tier
        while (newgear.gearTypeName.equals(gear.gearTypeName) && tries < 10) {
            newgear = UniqueGearLootGen.CreateData(gearPrint);
            tries++;
        }

        gear.WriteOverDataThatShouldStay(newgear);

        return UniqueGearLootGen.CreateStack(newgear);
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(SimpleGearLocReq.IS_UNIQUE);
    }

    @Override
    public int Tier() {
        return 13;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Don't want it? Transform it!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Gem Of Unique Heaven";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform unique (same tier)";
    }

    @Override
    public int instabilityAddAmount() {
        return 0;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
                .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 25)
                .addMaterial(new ItemStoneOfHope().getFromForgeRegistry(), 3)
                .addMaterial(new RerollPrefixNumbers().getFromForgeRegistry(), 2)
                .addMaterial(new RerollSuffixNumbers().getFromForgeRegistry(), 2)
                .buildMaterials()
                .setOutput(this)
                .levelReq(25)
                .expGained(50)
                .build();

    }
}