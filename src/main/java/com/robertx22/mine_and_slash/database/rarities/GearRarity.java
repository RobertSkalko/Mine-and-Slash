package com.robertx22.mine_and_slash.database.rarities;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.serialization.SerializedBaseRarity;
import com.robertx22.mine_and_slash.database.rarities.serialization.SerializedGearRarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

import static com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPart.Part;

public interface GearRarity extends Rarity, SalvagableItem, IStatPercents {

    @Override
    default JsonObject toJson() {

        JsonObject json = this.getRarityJsonObject();

        json.addProperty("affix_chance", AffixChance());
        json.addProperty("unidentified_chance", unidentifiedChance());
        json.addProperty("salvage_lottery_chance", salvageLotteryWinChance());
        json.addProperty("max_affixes", maxAffixes());

        json.add("unique_stat_percents", uniqueStatPercents().toJson());
        json.add("affix_stat_percents", affixStatPercents().toJson());

        return json;
    }

    @Override
    default GearRarity fromJson(JsonObject json) {

        SerializedBaseRarity baseRarity = this.baseSerializedRarityFromJson(json);

        SerializedGearRarity rar = new SerializedGearRarity(baseRarity);

        rar.maxAffixes = json.get("max_affixes")
            .getAsInt();
        rar.affixChance = json.get("affix_chance")
            .getAsInt();
        rar.salvageLotteryChance = json.get("salvage_lottery_chance")
            .getAsInt();
        rar.unidentifiedChance = json.get("unidentified_chance")
            .getAsInt();
        rar.requirementMulti = json.get("requirements_multi")
            .getAsFloat();

        rar.statPercents = MinMax.getSerializer()
            .fromJson(json.getAsJsonObject("stat_percents"));

        rar.affixStatPercents = MinMax.getSerializer()
            .fromJson(json.getAsJsonObject("affix_stat_percents"));

        rar.secondaryStatPercents = MinMax.getSerializer()
            .fromJson(json.getAsJsonObject("secondary_stat_percents"));

        rar.uniqueStatPercents = MinMax.getSerializer()
            .fromJson(json.getAsJsonObject("unique_stat_percents"));

        rar.primaryStatPercents = MinMax.getSerializer()
            .fromJson(json.getAsJsonObject("primary_stat_percents"));

        Preconditions.checkArgument(!StatPercents().isEmpty());

        return rar;
    }

    default MinMax getStatPercentsFor(Part part) {

        if (part == Part.AFFIX) {
            return affixStatPercents();
        } else if (part == Part.UNIQUE_STATS) {
            return uniqueStatPercents();
        } else {
            return StatPercents();
        }
    }

    MinMax affixStatPercents();

    MinMax uniqueStatPercents();

    int AffixChance();

    int maxAffixes();

    default int maximumOfOneAffixType() {
        return maxAffixes() / 2;
    }

    float itemTierPower();

    float unidentifiedChance();
}
