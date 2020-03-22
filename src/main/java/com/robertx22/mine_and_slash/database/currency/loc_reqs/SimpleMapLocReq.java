package com.robertx22.mine_and_slash.database.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Predicate;

public class SimpleMapLocReq extends BaseLocRequirement {

    public static final SimpleMapLocReq LOWER_THAN_MAX_TIER = new SimpleMapLocReq(
        x -> x.tier < ITiered.MAX_TIER, Words.TierLessThanMax.locName());

    private SimpleMapLocReq(Predicate<MapItemData> pred, ITextComponent text) {
        this.text = text;
        this.mapThatCanDoThis = pred;
    }

    Predicate<MapItemData> mapThatCanDoThis;
    ITextComponent text;

    @Override
    public ITextComponent getText() {

        return text;
    }

    @Override
    public boolean isAllowed(LocReqContext context) {

        if (context.data instanceof MapItemData) {
            MapItemData gear = (MapItemData) context.data;
            return mapThatCanDoThis.test(gear);

        }

        return false;
    }

}

