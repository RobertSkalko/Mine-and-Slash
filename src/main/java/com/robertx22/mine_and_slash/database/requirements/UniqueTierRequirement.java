package com.robertx22.mine_and_slash.database.requirements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import com.robertx22.mine_and_slash.database.requirements.bases.UniqueItemRequirement;

public class UniqueTierRequirement extends UniqueItemRequirement<UniqueTierRequirement> {

    int minTier = 0;
    int maxTier = Integer.MAX_VALUE;

    public UniqueTierRequirement(int minTier) {
        this.minTier = minTier;
    }

    public UniqueTierRequirement() {

    }

    public UniqueTierRequirement(int minTier, int maxTier) {
        this.minTier = minTier;
        this.maxTier = maxTier;
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        Boolean superreq = super.meetsRequierment(requested);

        if (superreq == false) {
            return false;
        }

        int tier = requested.gearData.uniqueStats.getUniqueItem().Tier();

        if (tier < minTier || tier > maxTier) {
            return false;
        }

        return true;

    }

    @Override
    public String getJsonID() {
        return "unique_tier";
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("min_tier", new JsonPrimitive(minTier));
        json.add("max_tier", new JsonPrimitive(maxTier));
        return json;
    }

    @Override
    public UniqueTierRequirement fromJson(JsonObject json) {
        ExactUniquesRequierement newobj = null;
        try {
            return new UniqueTierRequirement(json.get("min_tier").getAsInt(), json.get("max_tier").getAsInt());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
