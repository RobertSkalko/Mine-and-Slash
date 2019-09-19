package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.text.DecimalFormat;

@Storable
public class StatData {

    public StatData() {

    }

    public StatData(Stat stat) {
        this.Name = stat.GUID();
    }

    public Stat GetStat() {
        return SlashRegistry.Stats().get(Name); // testing if this is better
    }

    @Store
    public String Name;

    public float Flat = 0;

    public float Percent = 0;

    public float Multi = 0;

    @Store
    public float Value = 0;

    public void add(StatData data) {
        if (data.Name.equals(this.Name)) {
            this.Flat += data.Flat;
            this.Percent += data.Percent;
            this.Multi += data.Multi;
        }

    }

    public void addFlat(float val, int lvl) {
        if (this.GetStat().ScalesToLevel()) {
            this.Flat += this.GetStat().calculateScalingStatGrowth(val, lvl);
        } else {
            this.Flat += val;
        }
    }

    public void Clear() {
        Flat = 0;
        Percent = 0;
        Multi = 0;

    }

    public String formattedValue() {

        float val = Value;

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
        return 1 + Value / 100;
    }

}
