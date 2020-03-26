package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Storable
public class SetData implements IGearPartTooltip {

    @Store
    public String baseSet;

    public Set GetSet() {

        return SlashRegistry.Sets()
            .get(baseSet);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        List<ITextComponent> list = new ArrayList<>();

        if (baseSet == null || info.unitdata == null) {
            return list;
        }

        info.isSet = true;

        Set set = GetSet();

        if (set != null) {
            info.minmax = set.statPercents();

            list.add(Styles.GREENCOMP()
                .appendSibling(new StringTextComponent("[Set]: ").appendSibling(set.locName())));

            for (Map.Entry<Integer, StatMod> entry : GetSet().AllMods()
                .entrySet()) {

                boolean has = false;

                TextFormatting color = null;
                if (info.unitdata.getUnit().wornSets.get(baseSet).count >= entry.getKey()) {
                    color = TextFormatting.GREEN;
                    has = true;
                } else {
                    color = TextFormatting.DARK_GREEN;
                }

                int avgLvl = info.unitdata.getUnit().wornSets.get(baseSet)
                    .getAverageLevel();

                TooltipInfo setInfo = info.withLevel(avgLvl)
                    .setIsSet();
                setInfo.minmax = set.statPercents();

                for (ITextComponent str : StatModData.Load(entry.getValue(), GetSet().StatPercent)
                    .GetTooltipString(setInfo)) {

                    ITextComponent comp = new StringTextComponent(color + "").appendSibling(
                        new StringTextComponent("(" + entry.getKey() + ")"));

                    if (has) {
                        comp.appendText(": ")
                            .appendSibling(str);
                    } else {
                        comp.appendText(": ")
                            .appendSibling(Words.Locked.locName());
                    }

                    list.add(comp);
                }

            }
            list.add(new StringTextComponent(""));
        }

        info.isSet = false;

        return list;

    }

    public SetData generate(Set set) {

        SetData setdata = null;

        if (set != null) {
            setdata = new SetData();
            setdata.baseSet = set.GUID();
        }

        return setdata;

    }

    @Override
    public Part getPart() {
        return Part.OTHER;
    }
}
