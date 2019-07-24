package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

public interface IAutoLocDesc extends IBaseAutoLoc {

    public default String getDescGroupName() {
        return locDescGroup().name()
                .toUpperCase()
                .replaceAll("_", " ") + " - DESCRIPTIONS";
    }

    public AutoLocGroup locDescGroup();

    String locDescLangFileGUID();

    String locDescForLangFile();

    public default ITextComponent locDesc() {
        return CLOC.blank(formatString(formattedLocDescLangFileGUID()));
    }

    public default String formattedLocDescLangFileGUID() {
        return getPrefix() + formatString(locDescLangFileGUID());
    }

}

