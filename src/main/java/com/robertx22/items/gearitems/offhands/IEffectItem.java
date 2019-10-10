package com.robertx22.items.gearitems.offhands;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public interface IEffectItem {

    public default TextFormatting color() {
        return TextFormatting.AQUA;
    }

    List<ITextComponent> getEffectTooltip(boolean moreInfo);

}