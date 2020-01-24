package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
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
public class StatCalc implements ITooltipList {

    @Store
    public String statID;

    @Store
    public float multi;

    public Stat getStat() {
        return SlashRegistry.Stats().get(statID);
    }

    public StatCalc(Stat stat, float multi) {
        super();
        this.statID = stat.GUID();
        this.multi = multi;
    }

    public int getCalculatedValue(EntityCap.UnitData data) {
        return (int) (data.getUnit().getStat(statID).val * multi);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        Stat stat = getStat();

        Elements el = stat.getElement();

        String eleStr = "";

        if (el != null) {
            eleStr = el.format + el.icon;
        }

        list.add(new StringTextComponent(
                TextFormatting.RED + "Scales with " + (int) (multi * 100F) + "% " + eleStr + " ").appendSibling(
                stat.locName()).appendText(" (" + getCalculatedValue(info.unitdata) + ")"));

        return list;
    }
}
