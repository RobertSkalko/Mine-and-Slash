package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import net.minecraft.util.text.ITextComponent;

import java.util.List;

public interface ITooltipList {

    public abstract List<ITextComponent> GetTooltipString(TooltipInfo info);
}


