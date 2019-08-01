package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Predicate;

public class SimpleGearLocReq extends BaseLocRequirement {

    public static final SimpleGearLocReq DOESNT_HAVE_AFFIX = new SimpleGearLocReq(x -> x.set == null, Words.Doesnthaveset
            .locName());

    private SimpleGearLocReq(Predicate<GearItemData> pred, ITextComponent text) {
        this.text = text;
        this.gearsThatCanDoThis = pred;
    }

    Predicate<GearItemData> gearsThatCanDoThis;
    ITextComponent text;

    @Override
    public ITextComponent getText() {

        return text;
    }

    @Override
    public boolean isAllowed(Object object) {

        if (object instanceof GearItemData) {
            GearItemData gear = (GearItemData) object;
            return gearsThatCanDoThis.test(gear);

        }

        return false;
    }

}
