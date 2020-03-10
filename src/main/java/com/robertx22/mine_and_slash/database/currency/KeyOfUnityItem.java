package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.bases.SetPart;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SetData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class KeyOfUnityItem extends CurrencyItem implements ICurrencyItemEffect, IShapedRecipe {

    @Override
    public String GUID() {
        return "currency/add_set";
    }

    private static final String name = Ref.MODID + ":currency/add_set";

    public KeyOfUnityItem() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.set = new SetData();

        GearBlueprint blueprint = new GearBlueprint(gear.level);

        SetPart set = blueprint.getSet(gear);
        set.chance = 100;

        gear.set = set.getSetData();

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.SETS, SimpleGearLocReq.NO_SET);
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public int getRarityRank() {
        return Mythic;
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
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.KEY_OF_UNITY.get())
            .key('#', SimpleMatItem.MYTHIC_ESSENCE)
            .key('t', ModItems.CRYSTAL_OF_ASCENSION.get())
            .key('v', Items.GLISTERING_MELON_SLICE)
            .key('o', ItemOre.ItemOres.get(IRarity.Legendary))
            .patternLine("o#o")
            .patternLine("#t#")
            .patternLine("vvv")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}
