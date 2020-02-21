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
import net.minecraft.util.math.MathHelper;

public class DropLvlPenaltyTrigger extends AbstractCriterionTrigger<DropLvlPenaltyTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Ref.MODID, "drop_lvl_penalty_trigger");

    public ResourceLocation getId() {
        return ID;
    }

    public DropLvlPenaltyTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        int level = json.get("level").getAsInt();
        return new DropLvlPenaltyTrigger.Instance(level);
    }

    public void trigger(ServerPlayerEntity player, EntityCap.UnitData data, EntityCap.UnitData mob) {
        this.func_227070_a_(player.getAdvancements(), (instance) -> {
            return instance.conditionIsMet(data, mob);
        });
    }

    public static class Instance extends CriterionInstance {

        public int level;

        public Instance(int level) {
            super(DropLvlPenaltyTrigger.ID);
            this.level = level;
        }

        public boolean conditionIsMet(EntityCap.UnitData player, EntityCap.UnitData mob) {
            int diff = MathHelper.abs(player.getLevel() - mob.getLevel());
            return diff >= this.level;
        }

        public JsonElement serialize() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("level", new JsonPrimitive(level));
            return jsonobject;
        }
    }

}
