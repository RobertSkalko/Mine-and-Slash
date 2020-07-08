package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.tooltips.StatTooltipType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class BaseStatsData extends StatGroupData implements IGearPartTooltip, IRerollable {

    public BaseStatsData() {

    }

    @Override
    public void RerollFully(GearItemData gear) {

        this.Mods = new ArrayList<StatModData>();

        PosStats pos = RandomUtils.weightedRandom(gear.GetBaseGearType()
            .getPossiblePrimaryStats());

        int statsAmount = pos.mods.size();

        pos.mods.forEach(mod -> {
            StatModData moddata = StatModData.NewRandom(getMinMax(gear), mod);
            this.Mods.add(moddata);

        });

    }

}

    @Override
    public void RerollNumbers(GearItemData gear) {

        for (StatModData data : this.Mods) {
            data.setPercent(getMinMax(gear).random());
        }

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

        info.statTooltipType = StatTooltipType.PRIMARY_STATS;

        for (LevelAndStats part : this.GetAllStats(info.level)) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }

        }

        info.statTooltipType = StatTooltipType.NORMAL;

        return list;

    }

    @Override
    public Part getPart() {
        return Part.PRIMARY_STATS;
    }
}
