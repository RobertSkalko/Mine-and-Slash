package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class StoneOfHopeItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/stone_of_hope";
    }

    public static final String ID = Ref.MODID + ":currency/stone_of_hope";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":stone_of_hope");
    }

    public StoneOfHopeItem() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint blueprint = new GearBlueprint();
        blueprint.gearItemSlot.set(gear.gear_type);
        blueprint.rarity.minRarity = gear.rarity + 1;

        GearItemData newgear = blueprint.createData();
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
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.IS_LOWER_THAN_LEGENDARY, SimpleGearLocReq.IS_NOT_UNIQUE);
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("When there is hope, there is a way.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Stone Of Hope";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform any rarity gear into higher rarity";
    }

    @Override
    public float additionalBreakChance() {
        return 25;
    }

    @Override
    public int instabilityAddAmount() {
        return 0;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.STONE_OF_HOPE.get())
            .key('#', SimpleMatItem.MYTHIC_ESSENCE)
            .key('t', ModItems.ORB_OF_TRANSMUTATION.get())
            .key('v', Items.DIAMOND)
            .key('o', ItemOre.ItemOres.get(IRarity.Legendary))
            .patternLine("#o#")
            .patternLine("#t#")
            .patternLine("vvv")
            .addCriterion("player_level", trigger());
    }
}