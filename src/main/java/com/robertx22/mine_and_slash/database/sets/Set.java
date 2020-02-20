package com.robertx22.mine_and_slash.database.sets;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.sets.SerializableSet;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptySet;
import com.robertx22.mine_and_slash.saveclasses.WornSetData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class Set implements IWeighted, IGUID, IRarity, IhasRequirements, IAutoLocName,
    ISerializedRegistryEntry<Set>, ISerializable<Set> {

    public static Set EMPTY = new EmptySet();

    public Set() {
    }

    @Override
    public boolean isFromDatapack() {
        return true;
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SET;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".set." + formattedGUID();
    }

    public int StatPercent = 100;

    public MinMax statPercents() {
        return new MinMax(StatPercent, StatPercent);
    }

    @Override
    public boolean isRegistryEntryValid() {

        if (!checkStatModsValidity(new ArrayList<>(AllMods().values()))) {
            return false;
        }

        return true;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Item_Sets;
    }

    @Override
    public int Weight() {
        return this.getRarity()
            .Weight();
    }

    public abstract HashMap<Integer, StatMod> AllMods();

    public boolean meetsRequirements(GearRequestedFor requested) {

        return requirements().satisfiesAllRequirements(requested);

    }

    public List<StatMod> getObtainedMods(WornSetData data) {

        List<StatMod> mods = new ArrayList();

        if (data.setGUID == this.GUID()) {
            for (Entry<Integer, StatMod> mod : this.AllMods()
                .entrySet()) {
                if (data.count >= mod.getKey()) {
                    mods.add(mod.getValue());
                }
            }
        }

        return mods;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = getDefaultJson();

        json.add("requirements", requirements().toJson());

        JsonObject map = new JsonObject();

        AllMods().entrySet()
            .forEach(x -> {
                map.addProperty(x.getKey() + "", x.getValue()
                    .GUID());
            });

        json.add("mods", map);

        return json;
    }

    @Override
    public Set fromJson(JsonObject json) {

        String guid = getGUIDFromJson(json);
        String lang = getLangNameStringFromJson(json);
        int weight = getWeightFromJson(json);
        int rarity = getRarityFromJson(json);

        Requirements req = Requirements.EMPTY.fromJson(json.getAsJsonObject("requirements"));

        JsonObject mapJson = json.getAsJsonObject("mods");

        HashMap<Integer, StatMod> map = new HashMap();

        for (int i = 0; i < 10; i++) {
            JsonElement obj = mapJson.get(i + "");
            if (obj != null) {
                map.put(i, SlashRegistry.StatMods()
                    .get(obj.getAsString()));
            }
        }

        return new SerializableSet(rarity, weight, map, guid, lang, req);
    }

}
