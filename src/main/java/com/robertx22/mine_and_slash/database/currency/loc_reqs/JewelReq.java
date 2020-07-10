package com.robertx22.mine_and_slash.database.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.JewelData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Jewel;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.text.ITextComponent;

public class JewelReq extends BaseLocRequirement {
    @Override
    public ITextComponent getText() {
        return new SText("Gear can't accept more than 1 affix.");
    }

    @Override
    public boolean isAllowed(LocReqContext context) {

        JewelData jewel = Jewel.Load(context.Currency);
        GearItemData gear = (GearItemData) context.data;

        return jewel.canInsertInto(gear);

    }
}
