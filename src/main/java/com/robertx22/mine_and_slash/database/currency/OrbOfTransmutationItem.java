package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
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

public class OrbOfTransmutationItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IHasRecipe {
    @Override
    public String GUID() {
        return "currency/orb_of_transmutation";
    }

    public static final String ID = Ref.MODID + ":currency/orb_of_transmutation";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":orb_of_transmutation");
    }

    public OrbOfTransmutationItem() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint gearPrint = new GearBlueprint(gear.level);
        gearPrint.gearItemSlot.set(gear.gearTypeName);
        gearPrint.rarity.minRarity = 1;
        gearPrint.level.LevelRange = false;

        GearItemData newgear = gearPrint.createData();
        gear.WriteOverDataThatShouldStay(newgear);

        ItemStack result = ItemStack.EMPTY;

        if (gear.changesItemStack()) {
            result = GearCreationUtils.CreateStack(newgear);
        } else {
            result = stack;
            Gear.Save(result, newgear);
        }

        return result;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.IS_COMMON);
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Common;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Turn trash into treasure!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Transmutation";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform Common Item";
    }

    @Override
    public int instabilityAddAmount() {
        return 0;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
            .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 2)
            .addMaterial(Items.GOLD_INGOT, 1)
            .addMaterial(Items.COAL, 2)
            .addMaterial(Items.REDSTONE, 3)
            .buildMaterials()
            .setOutput(this)
            .levelReq(1)
            .expGained(25)
            .build();

    }

}