package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

public class StatRequirement implements ISerializable<StatRequirement> {

    //the float means % of lvl up points required

    private float dex_req = 0;
    private float int_req = 0;
    private float str_req = 0;

    public StatRequirement() {
    }

    public boolean passesStatRequirements(EntityCap.UnitData data, GearItemData gear) {

        if (data.getUnit()
            .peekAtStat(Dexterity.INSTANCE)
            .getAverageValue() < getDex(gear)) {
            return false;
        }
        if (data.getUnit()
            .peekAtStat(Intelligence.INSTANCE)
            .getAverageValue() < getInt(gear)) {
            return false;
        }
        if (data.getUnit()
            .peekAtStat(Strength.INSTANCE)
            .getAverageValue() < getStr(gear)) {
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
