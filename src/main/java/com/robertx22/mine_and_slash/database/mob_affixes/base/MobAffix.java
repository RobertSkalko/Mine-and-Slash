package com.robertx22.mine_and_slash.database.mob_affixes.base;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

public class MobAffix implements ISerializedRegistryEntry<MobAffix>, ISerializable<MobAffix>, IAutoLocName, IApplyableStats {

    StatModsHolder statMods;
    String id;
    String locName;
    BaseAffix.Type type;
    int weight = 1000;

    public MobAffix(String id, String locName, BaseAffix.Type type) {
        this.id = id;
        this.locName = locName;
        this.type = type;
    }

    public MobAffix setMods(StatModsHolder mods) {
        this.statMods = mods;
        return this;
    }

    public boolean isPrefix() {
        return type == BaseAffix.Type.prefix;
    }

    public boolean isSuffix() {
        return type == BaseAffix.Type.suffix;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = getDefaultJson();

        json.addProperty("type", type.name());

        if (statMods != null) {
            json.add("mods", statMods.toJson());
        }

        return json;
    }

    @Override
    public MobAffix fromJson(JsonObject json) {

        MobAffix affix = new MobAffix(
            getGUIDFromJson(json),
            getLangNameStringFromJson(json),
            BaseAffix.Type.valueOf(json.get("type")
                .getAsString()));

        try {
            affix.setMods(StatModsHolder.SERIALIZER.fromJson(json.getAsJsonObject("mods")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return affix;
    }

    public MobAffix setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.MOB_AFFIX;
    }

    @Override
    public String GUID() {
        return id;
    }

    @Override
    public int Weight() {
        return weight;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Misc;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".mob_affix." + GUID();
    }

    @Override
    public String locNameForLangFile() {
        return locName;
    }

    @Override
    public void applyStats(EntityCap.UnitData data, int level) {
        if (this.statMods != null) {
            statMods.getMods()
                .forEach(x -> StatModData.Load(x, 100)
                    .applyStats(data));
        }

    }
}
