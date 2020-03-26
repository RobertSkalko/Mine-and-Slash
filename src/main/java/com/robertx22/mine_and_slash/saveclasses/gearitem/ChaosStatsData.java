package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.chaos_stats.ChaosStat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.*;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Storable
public class ChaosStatsData implements IStatModsContainer, ICreateSpecific<StatMod>, Serializable, IGearPartTooltip, IRerollable {

    public ChaosStatsData() {

    }

    @Store
    private StatModData chaosStat;

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        int minmax = ((IAffectsOtherStats) chaosStat.getStatMod()
            .GetBaseStat()).percent();
        info.minmax = new MinMax(minmax, minmax);

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        if (info.hasShiftDown) {
            list.add(Styles.REDCOMP()
                .appendSibling(Words.Chaos_Stats.locName()
                    .appendText(":")));
        }

        for (LevelAndStats part : this.GetAllStats(info.unitdata.getLevel())) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }
        }

        return list;

    }

    @Override
    public void RerollFully(GearItemData gear) {
        if (gear.chaosStats.chaosStat == null) {

            ChaosStat stat = SlashRegistry.ChaosStats()
                .getWrapped()
                .allThatMeetRequirement(gear)
                .random();

            if (stat != null) {
                StatMod mod = new AllTraitMods(stat.getChaosTrait());
                this.create(gear, mod);
            }
        }
    }

    @Override
    public void RerollNumbers(GearItemData gear) {

    }

    @Override
    public void create(GearItemData gear, StatMod mod) {
        this.chaosStat = StatModData.Load(mod, 100);
    }

    @Override
    public Part getPart() {
        return Part.OTHER;
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
        return Arrays.asList(new LevelAndStats(this.chaosStat, level));
    }
}
