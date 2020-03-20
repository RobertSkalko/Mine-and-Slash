package com.robertx22.mine_and_slash.database.rarities;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.serialization.SerializedBaseRarity;
import com.robertx22.mine_and_slash.database.rarities.serialization.SerializedGearRarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface GearRarity extends Rarity, SalvagableItem, IStatPercents {

    @Override
    default JsonObject toJson() {

        JsonObject json = this.getRarityJsonObject();

        json.addProperty("affix_chance", AffixChance());
        json.addProperty("set_chance", SetChance());
        json.addProperty("rune_slots", runeSlots());
        json.addProperty("requirements_multi", requirementMulti());
        json.addProperty("unidentified_chance", unidentifiedChance());

        json.add("secondary_stat_amount", secondaryStatAmount().toJson());
        json.add("stat_percents", StatPercents().toJson());

        return json;
    }

    @Override
    default GearRarity fromJson(JsonObject json) {

        SerializedBaseRarity baseRarity = this.baseSerializedRarityFromJson(json);

        SerializedGearRarity rar = new SerializedGearRarity(baseRarity);

        rar.affixChance = json.get("affix_chance")
            .getAsInt();
        rar.setChance = json.get("set_chance")
            .getAsInt();
        rar.runeSlots = json.get("rune_slots")
            .getAsInt();
        rar.unidentifiedChance = json.get("unidentified_chance")
            .getAsInt();
        rar.requirementMulti = json.get("requirements_multi")
            .getAsFloat();

        rar.statPercents = MinMax.getSerializer()
            .fromJson(json.getAsJsonObject("stat_percents"));
        rar.secondaryStatsAmount = MinMax.getSerializer()
            .fromJson(json.getAsJsonObject("secondary_stat_amount"));

        return rar;
    }

    int AffixChance();

    int SetChance();

    int runeSlots();

    MinMax secondaryStatAmount();

    float itemTierPower();

    float requirementMulti();

    float unidentifiedChance();
}
