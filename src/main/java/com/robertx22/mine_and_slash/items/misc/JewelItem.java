package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.data_generation.models.IAutoModel;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.JewelReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.JewelData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Jewel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class JewelItem extends Item implements IAutoModel, ICurrencyItemEffect {

    public JewelItem() {
        super(new Item.Properties().maxStackSize(1)
            .maxDamage(0)
            .group(CreativeTabs.MyModTab));
    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {

        GearItemData gear = Gear.Load(stack);

        JewelData jewel = Jewel.Load(currency);

        jewel.insertIntoGear(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, new JewelReq());
    }

}
