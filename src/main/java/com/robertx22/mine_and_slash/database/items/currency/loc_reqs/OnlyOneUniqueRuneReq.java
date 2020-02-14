package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class OnlyOneUniqueRuneReq extends BaseLocRequirement {

    private OnlyOneUniqueRuneReq() {
    }

    public static OnlyOneUniqueRuneReq getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public ITextComponent getText() {
        return Words.hasMatchingRunes.locName();
    }

    @Override
    public boolean isAllowed(LocReqContext context) {

        if (context.data instanceof GearItemData) {

            GearItemData gear = (GearItemData) context.data;

            if (gear.runes != null) {
                if (gear.runes.hasUniqueRune()) {
                    return false;
                }
            }

            return true;

        }

        return false;

    }

    private static class SingletonHolder {
        private static final OnlyOneUniqueRuneReq INSTANCE = new OnlyOneUniqueRuneReq();
    }
}

