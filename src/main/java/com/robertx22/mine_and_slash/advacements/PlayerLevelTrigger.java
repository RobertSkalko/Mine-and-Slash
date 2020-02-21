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

public class PlayerLevelTrigger extends AbstractCriterionTrigger<PlayerLevelTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Ref.MODID, "player_level");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public PlayerLevelTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        int level = json.get("level").getAsInt();
        return new PlayerLevelTrigger.Instance(level);
    }

    public void trigger(ServerPlayerEntity player, EntityCap.UnitData data) {
        this.func_227070_a_(player.getAdvancements(), (instance) -> {
            return instance.conditionIsMet(data);
        });
    }

    public static class Instance extends CriterionInstance {

        public int level;

        public Instance(int level) {
            super(PlayerLevelTrigger.ID);
            this.level = level;
        }

        public boolean conditionIsMet(EntityCap.UnitData player) {
            return player.getLevel() >= level;
        }

        @Override
        public JsonElement serialize() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("level", new JsonPrimitive(level));
            return jsonobject;
        }
    }

}
