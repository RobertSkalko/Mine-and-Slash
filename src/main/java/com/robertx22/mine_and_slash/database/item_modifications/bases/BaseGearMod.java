package com.robertx22.mine_and_slash.database.item_modifications.bases;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;

public abstract class BaseGearMod extends BaseItemModification {

    @Override
    public DataItemType getDataType() {
        return DataItemType.GEAR;
    }

}
