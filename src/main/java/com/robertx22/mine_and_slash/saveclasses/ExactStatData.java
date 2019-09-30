package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class ExactStatData implements IApplyableStats, ITooltipList {

    public ExactStatData() {

    }

    public ExactStatData(float value, StatTypes type, String statGUID) {
        this.value = value;
        this.type = type;
        this.statGUID = statGUID;
    }

    @Store
    private float value = 0;

    @Store
    private StatTypes type = StatTypes.Flat;

    @Store
    private String statGUID = "";

    public float getValue() {
        return value;
    }

    public StatTypes getType() {
        return type;
    }

    public Stat getStat() {
        return SlashRegistry.Stats().get(statGUID);
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        data.getUnit().getStat(statGUID).addExact(type, value);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        Stat stat = getStat();
        TooltipStatInfo statInfo = new TooltipStatInfo(this, info);
        return new ArrayList<>(stat.getTooltipList(statInfo));

    }
}
