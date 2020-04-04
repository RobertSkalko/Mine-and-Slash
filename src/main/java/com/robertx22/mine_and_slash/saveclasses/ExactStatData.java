package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.BaseStatContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class ExactStatData extends BaseStatContainer {

    public ExactStatData() {

    }

    public ExactStatData scaleToLvl(int lvl) {
        if (type.isFlat()) {
            Stat stat = SlashRegistry.Stats()
                .get(statGUID);
            value = stat.calculateScalingStatGrowth(value, lvl);
        }
        this.alreadyScale = true;
        return this;
    }

    public ExactStatData(ExactStatData other) {
        this.value = other.value;
        this.type = other.type;
        this.statGUID = other.statGUID;
    }

    public ExactStatData(float value, StatModTypes type, String statGUID) {
        this.value = value;
        this.type = type;
        this.statGUID = statGUID;
    }

    public ExactStatData(float value, StatModTypes type, Stat stat) {
        this.value = value;
        this.type = type;
        this.statGUID = stat.GUID();
    }

    public ExactStatData(float value, Stat stat) {
        this.value = value;
        this.type = StatModTypes.Flat;
        this.statGUID = stat.GUID();
    }

    public void setValue(float val) {
        this.value = val;
    }

    @Store
    private float value = 0;

    @Store
    private StatModTypes type = StatModTypes.Flat;

    @Store
    private String statGUID = "";

    private boolean alreadyScale = false;

    public float getValue() {
        return value;
    }

    public StatModTypes getType() {
        return type;
    }

    public Stat getStat() {
        return SlashRegistry.Stats()
            .get(statGUID);
    }

    @Override
    public void applyStats(EntityCap.UnitData data, int level) {
        if (!alreadyScale) {
            this.scaleToLvl(level);
        }
        data.getUnit()
            .getCreateStat(statGUID)
            .addExact(type, value);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        if (value == 0) {
            return new ArrayList<>();
        }

        Stat stat = getStat();
        TooltipStatInfo statInfo = new TooltipStatInfo(this, info);
        return new ArrayList<>(stat.getTooltipList(statInfo));

    }
}
