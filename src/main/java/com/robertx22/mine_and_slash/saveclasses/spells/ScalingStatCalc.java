package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

@Storable
public class ScalingStatCalc implements ITooltipList {

    @Store
    public String statID;

    @Store
    public float multi;

    public Stat getStat() {
        return SlashRegistry.Stats()
            .get(statID);
    }

    public ScalingStatCalc(Stat stat, float multi) {
        super();
        this.statID = stat.GUID();
        this.multi = multi;
    }

    public int getCalculatedValue(EntityCap.UnitData data) {
        return (int) (data.getUnit()
            .peekAtStat(statID).val * multi);
    }

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

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        return getTooltipFor(multi, getCalculatedValue(info.unitdata), getStat().locName(), getStat().getElement());
    }
}
