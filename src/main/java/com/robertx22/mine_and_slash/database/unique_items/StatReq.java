package com.robertx22.mine_and_slash.database.unique_items;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializablePart;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatRequirementsData;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatReq implements ISerializablePart<StatReq> {

    public StatReq(LvlPointStat stat, Size size) {
        map.put(stat.statguid, size.multi);
    }

    public StatReq(StatReq... list) {
        for (StatReq req : list) {
            req.map.entrySet()
                .forEach(x -> map.put(x.getKey(), x.getValue()));
        }
    }

    public StatReq(LvlPointStat stat, Size size, LvlPointStat stat2, Size size2) {
        map.put(stat.statguid, size.multi);
        map.put(stat2.statguid, size2.multi);
    }

    private StatReq() {

    }

    public static StatReq nothing() {
        return new StatReq();
    }

    @Override
    public String getJsonID() {
        return "stat_requirements";
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        map.entrySet()
            .forEach(e -> {
                json.addProperty(e.getKey(), e.getValue());
            });

        return json;
    }

    @Override
    public StatReq fromJson(JsonObject json) {
        StatReq req = new StatReq();

        Arrays.stream(LvlPointStat.values())
            .forEach(x -> {
                if (json.has(x.statguid)) {
                    Float val = json.get(x.statguid)
                        .getAsFloat();
                    req.map.put(x.statguid, val);
                }
            });

        return req;
    }

    public enum Size {
        TINY(0.25F),
        SMALL(0.5F),
        MEDIUM(0.75F),
        NORMAL(1F),
        MAJOR(1.1F),
        HUGE(1.25F);

        public float multi;

        Size(float multi) {
            this.multi = multi;
        }
    }

    public List<Stat> getStats() {
        return this.map.keySet()
            .stream()
            .map(x -> SlashRegistry.Stats()
                .get(x))
            .collect(Collectors.toList());
    }

    private HashMap<String, Float> map = new HashMap<>();

    public HashMap<String, Integer> getRequirements(int lvl, GearRarity rar) {
        HashMap<String, Integer> hashmap = new HashMap<>();

        for (Map.Entry<String, Float> entry : map.entrySet()) {
            int val = (int) ((entry.getValue() * StatRequirementsData.getAmount(lvl)) * rar.requirementMulti());
            hashmap.put(entry.getKey(), val);
        }

        return hashmap;
    }

}
