package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerkEffect implements ITooltipList, IApplyableStats {

    public PerkType type = PerkType.SMALL;
    public List<ExactStatData> exactStats;

    public PerkEffect(List<ExactStatData> exactStats) {
        this.exactStats = exactStats;
    }

    public PerkEffect(ExactStatData exactStat) {
        this.exactStats = Arrays.asList(exactStat);
    }

    public PerkEffect type(PerkType type) {
        this.type = type;
        return this;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();
        this.exactStats.stream().forEach(x -> list.addAll(x.GetTooltipString(info)));
        return list;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        exactStats.forEach(x -> x.applyStats(data));
    }
}
