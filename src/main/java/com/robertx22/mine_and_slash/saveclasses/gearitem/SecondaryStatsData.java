package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Storable
public class SecondaryStatsData extends StatGroupData implements Serializable, IGearPartTooltip, IRerollable {

    private static final long serialVersionUID = 6149243047165372987L;

    public SecondaryStatsData() {

    }

    @Store
    public boolean AddedStat = false;

    @Override
    public void RerollFully(GearItemData gear) {

        this.Mods = new ArrayList<StatModData>();

        int Stats = gear.getRarity()
            .secondaryStatAmount()
            .random();

        while (Stats > 0) {
            StatMod mod = RandomUtils.weightedRandom(gear.GetBaseGearType()
                .getPossibleSecondaryStats()
                .getMods());
            this.Mods.add(StatModData.NewRandom(getMinMax(gear), mod));
            Stats--;

        }

    }

    public void AddStat(GearItemData gear) {
        StatMod mod = RandomUtils.weightedRandom(gear.GetBaseGearType()
            .getPossibleSecondaryStats()
            .getMods());

        gear.secondaryStats.Mods.add(StatModData.NewRandom(gear.getRarity(), mod));

        this.AddedStat = true;

    }

    @Override
    public void RerollNumbers(GearItemData gear) {

        for (StatModData data : this.Mods) {
            data.setPercent(getMinMax(gear)
                .random());
        }

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {
        info.minmax = getMinMax(gear);

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        if (!this.Mods.isEmpty()) {

            list.add(Styles.GRAYCOMP()
                .appendSibling(Words.Secondary_Stats.locName()
                    .appendText(":")));

            for (LevelAndStats part : this.GetAllStats(info.level)) {
                for (StatModData data : part.mods) {
                    list.addAll(data.GetTooltipString(info));
                }
            }
        }

        return list;

    }

    @Override
    public Part getPart() {
        return Part.SECONDARY_STATS;
    }
}
