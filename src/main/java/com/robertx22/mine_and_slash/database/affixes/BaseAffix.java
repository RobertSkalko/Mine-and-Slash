package com.robertx22.mine_and_slash.database.affixes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.serialization.affixes.SerializableAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseAffix implements IWeighted, IGUID, IAutoLocName, IhasRequirements, IRarity,
        ISerializedRegistryEntry<BaseAffix>, ISerializable<BaseAffix> {

    public enum Type {
        prefix,
        suffix
    }

    public Type type;

    public BaseAffix(Requirements requirements, Type type) {
        this.requirements = requirements;
        this.type = type;
    }

    @Override
    public boolean isFromDatapack() {
        return true;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.AFFIX;
    }

    public abstract String GUID();

    Requirements requirements;

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".affix." + formattedGUID();
    }

    @Override
    public final AutoLocGroup locNameGroup() {
        return AutoLocGroup.Affixes;
    }

    @Override
    public final Requirements requirements() {
        return requirements;

    }

    @Override
    public int Weight() {
        return this.getRarity().Weight();
    }

    public abstract List<StatMod> StatMods();

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = getDefaultJson();

        json.addProperty("type", type.name());

        json.add("requirements", requirements().toJson());

        JsonArray array = new JsonArray();
        StatMods().stream().map(x -> x.toRegistryJson()).collect(Collectors.toList()).forEach(x -> array.add(x));
        json.add("mods", array);

        return json;
    }

    @Override
    public BaseAffix fromJson(JsonObject json) {

        try {
            String guid = getGUIDFromJson(json);
            String langName = getLangNameStringFromJson(json);

            Type type = Type.valueOf(json.get("type").getAsString());

            Requirements req = Requirements.EMPTY.fromJson(json.getAsJsonObject("requirements"));

            List<StatMod> mods = new ArrayList<>();
            json.getAsJsonArray("mods").forEach(x -> mods.add(StatMod.EMPTY.fromRegistryJson(x.getAsJsonObject())));

            return new SerializableAffix(req, guid, mods, langName, type);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
