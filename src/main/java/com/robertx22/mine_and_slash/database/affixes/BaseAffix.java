package com.robertx22.mine_and_slash.database.affixes;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.data_generation.JsonUtils;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseAffix implements IWeighted, IGUID, IAutoLocName, IhasRequirements, IRarity,
    ISerializedRegistryEntry<BaseAffix>, ISerializable<BaseAffix> {

    public enum Type {
        prefix,
        suffix,
        implicit
    }

    String guid;
    List<StatModifier> mods;
    String langName;
    int weight = 1000;
    Requirements requirements;
    public List<AffixTag> tags = new ArrayList<>();
    public Type type;

    @Override
    public boolean isRegistryEntryValid() {
        if (guid == null || langName == null || mods == null || requirements == null || type == null || weight < 0) {
            return false;
        }

        return true;
    }

    @Override
    public final String datapackFolder() {
        return type.name() + "/";
    }

    @Override
    public boolean isFromDatapack() {
        return true;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.AFFIX;
    }

    public String GUID() {
        return guid;
    }

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

    public LevelRequirement getLevelRequirement() {

        Optional<BaseRequirement> opt = requirements.requirements.stream()
            .filter(x -> x instanceof LevelRequirement)
            .findAny();

        if (opt.isPresent()) {
            return (LevelRequirement) opt.get();
        }

        return LevelRequirement.none();
    }

    @Override
    public int Weight() {
        return weight;
    }

    public List<StatModifier> StatMods() {
        return mods;
    }

    @Override
    public String locNameForLangFile() {
        return this.langName;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(0);
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = getDefaultJson();

        json.addProperty("type", type.name());
        json.add("requirements", requirements().toJson());

        JsonUtils.addStats(StatMods(), json, "stats");

        return json;
    }

    @Override
    public BaseAffix fromJson(JsonObject json) {

        try {
            String guid = getGUIDFromJson(json);
            String langName = getLangNameStringFromJson(json);
            int weight = getWeightFromJson(json);

            Type type = Type.valueOf(json.get("type")
                .getAsString());

            Requirements req = Requirements.EMPTY.fromJson(json.getAsJsonObject("requirements"));

            List<StatModifier> mods = JsonUtils.getStats(json, "stats");

            BaseAffix affix = new BaseAffix();
            affix.weight = weight;
            affix.requirements = req;
            affix.guid = guid;
            affix.mods = mods;
            affix.langName = langName;
            affix.type = type;
            return affix;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
