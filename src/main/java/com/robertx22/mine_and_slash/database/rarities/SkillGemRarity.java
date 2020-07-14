package com.robertx22.mine_and_slash.database.rarities;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.serialization.SerializedBaseRarity;
import com.robertx22.mine_and_slash.database.rarities.serialization.SerializedSkillGemRarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface SkillGemRarity extends Rarity {

    public MinMax statPercents();

    @Override
    default JsonObject toJson() {

        JsonObject json = this.getRarityJsonObject();

        json.add("stat_percents", statPercents().toJson());

        return json;
    }

    @Override
    default SkillGemRarity fromJson(JsonObject json) {

        SerializedBaseRarity baseRarity = this.baseSerializedRarityFromJson(json);

        SerializedSkillGemRarity rar = new SerializedSkillGemRarity(baseRarity);

        rar.statPerc = MinMax.getSerializer()
            .fromJson(json.get("stat_percents")
                .getAsJsonObject());

        return rar;
    }

}
