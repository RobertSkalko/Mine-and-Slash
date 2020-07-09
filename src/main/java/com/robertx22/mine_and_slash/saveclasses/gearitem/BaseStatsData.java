package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.stats.tooltips.StatTooltipType;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class BaseStatsData implements IGearPartTooltip, IRerollable, IStatsContainer {

    @Store
    public List<Integer> percents = new ArrayList<Integer>();

    @Store
    public String gear_type = "";

    @Override
    public void RerollFully(GearItemData gear) {

        percents = new ArrayList<>();
        this.gear_type = gear.gear_type;

        gear.GetBaseGearType()
            .BaseStats()
            .forEach(x -> percents.add(getMinMax(gear).random()));

    }

    @Override
    public void RerollNumbers(GearItemData gear) {
        RerollFully(gear);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        info.minmax = getMinMax(gear);

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        if (info.useInDepthStats()) {
            list.add(Styles.GRAYCOMP()
                .appendSibling(Words.Primary_Stats.locName()
                    .appendText(":")));
        }

        list.add(new StringTextComponent(" "));

        info.statTooltipType = StatTooltipType.BASE_LOCAL_STATS;

        GetAllStats().forEach(x -> list.addAll(x.GetTooltipString(info)));

        info.statTooltipType = StatTooltipType.NORMAL;

        return list;

    }

    @Override
    public Part getPart() {
        return Part.PRIMARY_STATS;
    }

    @Override
    public List<ExactStatData> GetAllStats() {

        List<ExactStatData> list = new ArrayList<>();

        int i = 0;

        for (StatModifier mod : SlashRegistry.GearTypes()
            .get(gear_type)
            .BaseStats()) {
            list.add(mod.ToExactStat(percents.get(i)));
            i++;
        }

        return list;

    }
}
