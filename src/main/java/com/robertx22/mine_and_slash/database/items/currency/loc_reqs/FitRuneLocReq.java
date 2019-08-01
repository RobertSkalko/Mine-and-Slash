package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class FitRuneLocReq extends BaseLocRequirement {

    public static final FitRuneLocReq INSTANCE = new FitRuneLocReq();

    @Override
    public ITextComponent getText() {
        return Words.canFitRune.locName();
    }

    @Override
    public boolean isAllowed(Object object, ItemStack currency) {

        if (object instanceof GearItemData) {
            GearItemData gear = (GearItemData) object;

            if (gear.runes != null) {

                RuneItemData rune = Rune.Load(currency);
                if (rune != null) {
                    if (gear.runes.canFit(gear, rune)) {

                        return true;
                    }
                }
            }

        }

        return false;
    }
}
