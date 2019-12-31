package com.robertx22.mine_and_slash.database.items.currency.loc_reqs.item_types;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.LocReqContext;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class MapReq extends BaseLocRequirement {

    public static MapReq INSTANCE = new MapReq();

    @Override
    public ITextComponent getText() {
        return Words.MustBeMap.locName();
    }

    @Override
    public boolean isAllowed(LocReqContext context) {
        return context.data instanceof MapItemData;
    }
}

