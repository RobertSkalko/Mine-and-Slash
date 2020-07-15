package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.database.stats.types.reduced_req.FlatIncreasedReq;
import com.robertx22.mine_and_slash.database.stats.types.reduced_req.ReducedAllStatReqOnItem;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

import java.util.List;

public class StatRequirement implements ISerializable<StatRequirement> {

    //the float means % of lvl up points required

    private float dex_req = 0;
    private float int_req = 0;
    private float str_req = 0;

    public int dexterity;
    public int intelligence;
    public int strength;

    public boolean hasAny() {
        return dex_req > 0 || int_req > 0 || str_req > 0;
    }

    public StatRequirement() {
    }

    public void calculate(GearItemData gear) {

        dexterity = getDex(gear);
        intelligence = getInt(gear);
        strength = getStr(gear);

        List<ExactStatData> stats = gear.GetAllStats(false, false);

        // first apply flat +x str req, then x% reduced requirements

        stats.forEach(x -> {
            if (x.getStat() instanceof FlatIncreasedReq) {
                FlatIncreasedReq reduce = (FlatIncreasedReq) x.getStat();

                dexterity = (int) reduce.getModifiedRequirement(Dexterity.INSTANCE, dexterity, x);

                intelligence = (int) reduce.getModifiedRequirement(Intelligence.INSTANCE, intelligence, x);

                strength = (int) reduce.getModifiedRequirement(Strength.INSTANCE, strength, x);

            }
        });

        stats
            .forEach(x -> {
                if (x.getStat() instanceof ReducedAllStatReqOnItem) {
                    ReducedAllStatReqOnItem reduce = (ReducedAllStatReqOnItem) x.getStat();

                    if (dexterity > 0) {
                        dexterity = (int) reduce.getModifiedRequirement(dexterity, x);
                    }
                    if (intelligence > 0) {
                        intelligence = (int) reduce.getModifiedRequirement(intelligence, x);
                    }
                    if (strength > 0) {
                        strength = (int) reduce.getModifiedRequirement(strength, x);
                    }
                }
            });
    }

    public boolean passesStatRequirements(EntityCap.UnitData data, GearItemData gear) {

        if (data.getUnit()
            .peekAtStat(Dexterity.INSTANCE)
            .getAverageValue() < dexterity) {
            return false;
        }
        if (data.getUnit()
            .peekAtStat(Intelligence.INSTANCE)
            .getAverageValue() < intelligence) {
            return false;
        }
        if (data.getUnit()
            .peekAtStat(Strength.INSTANCE)
            .getAverageValue() < strength) {
            return false;
        }

        return true;
    }

    public int getDex(GearItemData gear) {
        return (int) scale(dex_req, gear);
    }

    public int getInt(GearItemData gear) {
        return (int) scale(int_req, gear);
    }

    public int getStr(GearItemData gear) {
        return (int) scale(str_req, gear);
    }

    private float scale(float val, GearItemData gear) {
        if (val <= 0) {
            return 0;
        }

        float calc = val * gear.getRarity()
            .statReqMulti() * ModConfig.INSTANCE.Server.STAT_POINTS_PER_LVL.get()
            .floatValue();

        return (int) Dexterity.INSTANCE.scale(calc, gear.level);
    }

    public StatRequirement dexterity(float dex_req) {
        this.dex_req = dex_req;
        return this;
    }

    public StatRequirement intelligence(float int_req) {
        this.int_req = int_req;
        return this;
    }

    public StatRequirement strength(float str_req) {
        this.str_req = str_req;
        return this;
    }

    private StatRequirement(float dex_req, float int_req, float str_req) {
        this.dex_req = dex_req;
        this.int_req = int_req;
        this.str_req = str_req;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("dex_req", dex_req);
        json.addProperty("int_req", int_req);
        json.addProperty("str_req", str_req);
        return json;

    }

    @Override
    public StatRequirement fromJson(JsonObject json) {
        return new StatRequirement(json.get("dex_req")
            .getAsFloat(), json.get("int_req")
            .getAsFloat(), json.get("str_req")
            .getAsFloat());

    }
}
