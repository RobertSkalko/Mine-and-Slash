package com.robertx22.mine_and_slash.database.item_modifications.bases;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;

public abstract class BaseGearMod extends BaseItemModification {

    @Override
    public DataItemType getDataType() {
        return DataItemType.GEAR;
    }

    @Override
    public void modify(ICommonDataItem data) {
        modifyGear((GearItemData) data);
    }

    public abstract void modifyGear(GearItemData gear);

}
