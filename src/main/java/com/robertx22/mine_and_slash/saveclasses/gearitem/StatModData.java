package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipString;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Storable
public class StatModData implements ITooltipString {

    public StatModData() {

    }

    public static StatModData NewRandom(GearRarity rar, StatMod mod) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.randomize(rar);
        data.multiplier = mod.multiplier;

        return data;
    }

    public static StatModData Load(StatMod mod, int percent) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.type = mod.Type();
        data.percent = percent;
        data.multiplier = mod.multiplier;

        return data;
    }

    public void useOnPlayer(UnitData unit) {
        Add(unit.getUnit().getStat(this.getStatMod().GetBaseStat()), unit.getLevel());
    }

    public void useOnPlayer(UnitData unit, int level) {
        Add(unit.getUnit().getStat(this.getStatMod().GetBaseStat()), level);
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int perc) {
        this.percent = perc;
    }

    public void setPercentClamp(MinMax minmax, int perc) {
        this.percent = MathHelper.clamp(perc, minmax.Min, minmax.Max);
    }

    @Store
    private float multiplier = 1F;

    @Store
    private StatTypes type;

    @Store
    private int percent;

    @Store
    private String baseModName;

    public StatMod getStatMod() {

        return SlashRegistry.StatMods().get(baseModName).multi(multiplier);

    }

    public float GetActualVal(int level) {

        StatMod mod = getStatMod();

        Stat stat = mod.GetBaseStat();

        float val = mod.GetFloatByPercent(percent);

        if (stat.ScalesToLevel() && mod.Type().equals(StatTypes.Flat)) {
            val = calculateStatGrowth(val, level);
        }

        return val;
    }

    public static float calculateStatGrowth(float stat, int lvl) {
        return stat * (float) Math.pow(lvl, getMultiplier(lvl));
    }

    private static float getMultiplier(int lvl) {
        return MathHelper.clamp(0.5F + (float) lvl / 100, 0.5F, 1.5F);
    }

    public String printValue(int level) {

        float val = GetActualVal(level);

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        try {
            return getStatMod().GetBaseStat().getTooltipList(info, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(new StringTextComponent(""));
    }

    public void randomize(GearRarity rarity) {
        this.randomize(rarity.StatPercents());
    }

    public void randomize(MinMax minmax) {
        this.percent = minmax.genPercent();
    }

    public void Add(StatData data, int level) {

        if (type == StatTypes.Flat) {
            data.Flat += GetActualVal(level);
        } else if (type == StatTypes.Percent) {
            data.Percent += GetActualVal(level);
        } else if (type == StatTypes.Multi) {
            data.Multi += GetActualVal(level);

        }

    }
}