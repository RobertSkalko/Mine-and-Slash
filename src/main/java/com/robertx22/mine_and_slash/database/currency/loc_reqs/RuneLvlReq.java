package com.robertx22.mine_and_slash.database.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class RuneLvlReq extends BaseLocRequirement {

    public static final RuneLvlReq INSTANCE = new RuneLvlReq();

    @Override
    public ITextComponent getText() {
        return Words.Runelvlnothigherthanitemlvl.locName();
    }

    @Override
    public boolean isAllowed(LocReqContext context) {

        if (context.data instanceof GearItemData) {
            GearItemData gear = (GearItemData) context.data;

            RuneItemData rune = Rune.Load(context.Currency);
            if (rune != null) {
                if (gear.level >= rune.level) {
                    return true;
                }
            }

        }

        return false;
    }
}

