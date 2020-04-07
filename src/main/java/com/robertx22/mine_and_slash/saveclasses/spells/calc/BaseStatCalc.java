package com.robertx22.mine_and_slash.saveclasses.spells.calc;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import info.loenwind.autosave.annotations.Storable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

@Storable
public abstract class BaseStatCalc implements ITooltipList {

    public List<ITextComponent> getTooltipFor(float multi, float value, ITextComponent statname, Elements el) {
        List<ITextComponent> list = new ArrayList<>();
        String eleStr = "";

        if (el != null) {
            eleStr = el.format + el.icon;
        }

        list.add(new StringTextComponent(
            TextFormatting.RED + "Scales with " + (int) (multi * 100F) + "% " + eleStr + " ").appendSibling(
            statname)
            .appendText(" (" + value + ")"));

        return list;
    }

    public abstract float getMulti();

    public abstract int getCalculatedValue(EntityCap.UnitData data);
}
