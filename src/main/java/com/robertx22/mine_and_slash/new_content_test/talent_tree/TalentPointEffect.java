package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;
import java.util.List;

public class TalentPointEffect implements ITooltipList, IApplyableStats {

    public PerkType type = PerkType.SMALL;
    public List<ExactStatData> exactStats;

    public TalentPointEffect(List<ExactStatData> exactStats) {
        this.exactStats = exactStats;
    }

    public TalentPointEffect(ExactStatData exactStat) {
        this.exactStats = Arrays.asList(exactStat);
    }

    public TalentPointEffect type(PerkType type) {
        this.type = type;
        return this;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        return null;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        exactStats.forEach(x -> x.applyStats(data));
    }
}
