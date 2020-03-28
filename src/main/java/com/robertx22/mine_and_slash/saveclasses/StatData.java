package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;

import java.text.DecimalFormat;

@Storable
public class StatData {

    private static StatData empty = new StatData();

    public static StatData empty() {
        return empty;
    }

    public StatData() {

    }

    public StatData(Stat stat) {
        this.id = stat.GUID();
    }

    public Stat GetStat() {
        return SlashRegistry.Stats()
            .get(id);
    }

    @Store// guid
    private String id = "";

    public float Flat = 0;

    public float Percent = 0;

    public float Multi = 0;

    @Store
    public float val = 0;

    public void CalcVal(EntityCap.UnitData Source) {

        Stat stat = this.GetStat();

        if (stat.isTrait()) {
            if (Flat > 0) {
                val = 1;
            } else {
                val = 0;
            }
            return;
        } else {

            float finalValue = stat.BaseFlat;

            finalValue = stat.getScaling()
                .scale(stat.BaseFlat, Source.getLevel());

            finalValue += Flat;

            finalValue *= 1 + Percent / 100;

            finalValue *= 1 + Multi / 100;

            val = MathHelper.clamp(finalValue, stat.minimumValue, stat.maximumValue);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(float val) {
        Stat stat = GetStat();

        this.val = MathHelper.clamp(val, stat.minimumValue, stat.maximumValue);

    }

    public float getValue() {
        return val;
    }

    public void addExact(StatModTypes type, float value) {
        if (type == StatModTypes.Flat) {
            this.Flat += value;
        } else if (type == StatModTypes.Percent) {
            this.Percent += value;
        } else {
            this.Multi += value;
        }
    }

    public void addExact(StatData data) {
        if (data.id.equals(this.id)) {
            this.Flat += data.Flat;
            this.Percent += data.Percent;
            this.Multi += data.Multi;
        }

    }

    public void addFlat(float val, int lvl) {
        this.Flat += this.GetStat()
            .calculateScalingStatGrowth(val, lvl);
    }

    public void Clear() {
        Flat = 0;
        Percent = 0;
        Multi = 0;

    }

    public String formattedValue() {

        float val = this.val;

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

    }

    public float getMultiplier() {
        return 1 + val / 100;
    }

    public boolean isNotEmpty() {
        return Flat != 0 || val != 0 || Percent != 0 || Multi != 0;
    }
}
