package com.robertx22.mine_and_slash.database.item_modifications.gear_items;

import com.robertx22.mine_and_slash.database.item_modifications.bases.BaseGearMod;
import com.robertx22.mine_and_slash.database.item_modifications.bases.ItemModType;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SetData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class AddSetMod extends BaseGearMod {

    @Override
    public ItemModType getItemModType() {
        return ItemModType.SET;
    }

    @Override
    public ITextComponent locName() {
        return Words.AddSet.locName();
    }

    @Override
    protected boolean canModifyPRIVATE(ICommonDataItem data) {
        GearItemData gear = (GearItemData) data;
        return gear.set == null;
    }

    @Override
    public String GUID() {
        return "add_set";
    }

    @Override
    public void modifyGear(GearItemData gear) {
        gear.set = new SetData();
        gear.set = gear.set.generate(SlashRegistry.Sets()
                                             .getWrapped()
                                             .of(x -> x.requirements()
                                                     .satisfiesAllRequirements(new GearRequestedFor(gear)))
                                             .random());

    }
}
