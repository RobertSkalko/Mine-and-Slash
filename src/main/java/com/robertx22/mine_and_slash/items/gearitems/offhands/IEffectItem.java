package com.robertx22.mine_and_slash.items.gearitems.offhands;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public interface IEffectItem {

    public default TextFormatting color() {
        return TextFormatting.AQUA;
    }

    List<ITextComponent> getEffectTooltip(boolean moreInfo);

}
