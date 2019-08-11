package com.robertx22.mine_and_slash.database.items.currency.loc_reqs;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.misc.ItemAwakenRuneWord;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;

public class AwakenRuneWordLocReq extends BaseLocRequirement {

    public static final AwakenRuneWordLocReq INSTANCE = new AwakenRuneWordLocReq();

    @Override
    public ITextComponent getText() {
        return Words.hasMatchingRunes.locName();
    }

    @Override
    public boolean isAllowed(LocReqContext context) {

        if (context.data instanceof GearItemData) {

            if (context.Currency.getItem() instanceof ItemAwakenRuneWord) {

                ItemAwakenRuneWord awaken = (ItemAwakenRuneWord) context.Currency.getItem();

                GearItemData gear = (GearItemData) context.data;

                if (gear != null) {

                    String wordtext = awaken.getWord(context.Currency);

                    if (SlashRegistry.RuneWords().isRegistered(wordtext)) {
                        if (gear.isRuned() && gear.runes.canAwakenRuneWord(SlashRegistry.RuneWords()
                                .get(wordtext))) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }
}

