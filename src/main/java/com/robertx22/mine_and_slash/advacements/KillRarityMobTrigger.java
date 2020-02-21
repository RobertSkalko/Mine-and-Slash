package com.robertx22.mine_and_slash.advacements;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class KillRarityMobTrigger extends AbstractCriterionTrigger<KillRarityMobTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Ref.MODID, "kill_mob_rarity");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        int rarity = json.get("rarity").getAsInt();
        return new Instance(rarity);
    }

    public void trigger(ServerPlayerEntity player, EntityCap.UnitData mob) {
        this.func_227070_a_(player.getAdvancements(), (instance) -> {
            return instance.conditionIsMet(mob);
        });
    }

    public static class Instance extends CriterionInstance {

        public int rarity;

        public Instance(int rarity) {
            super(ID);
            this.rarity = rarity;
        }

        public boolean conditionIsMet(EntityCap.UnitData mobData) {
            return mobData.getRarity() == rarity;
        }

        @Override
        public JsonElement serialize() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("rarity", new JsonPrimitive(rarity));
            return jsonobject;
        }
    }

}
