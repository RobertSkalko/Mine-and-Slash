package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class RuneNoDuplicateReq extends BaseLocRequirement {

    public static final RuneNoDuplicateReq INSTANCE = new RuneNoDuplicateReq();

    @Override
    public ITextComponent getText() {
        return Words.NoDuplicateRunes.locName();
    }

    @Override
    public boolean isAllowed(LocReqContext context) {

        if (context.data instanceof GearItemData) {
            GearItemData gear = (GearItemData) context.data;

            if (gear.runes != null) {
                RuneItemData rune = Rune.Load(context.Currency);
                if (rune != null) {
                    if (gear.runes.alreadyContains(rune) == false) {

                        return true;
                    }
                }
            }

        }

        return false;
    }
}

