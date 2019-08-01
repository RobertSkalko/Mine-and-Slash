package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SetData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemRerollSet extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
        return "reroll_set";
    }

    private static final String name = Ref.MODID + ":currency/reroll_set";

    public ItemRerollSet() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.set = new SetData();

        GearBlueprint blueprint = new GearBlueprint(gear.level);
        blueprint.SetCustomSetChance(100);

        gear.set = new SetData();
        gear.set = gear.set.generate(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearEnumLocReq.SETS, SimpleGearLocReq.HAS_SET);
    }

    @Override
    public int Tier() {
        return 17;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Become one again.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Key of new Dawn";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls an item's set.";
    }
}
