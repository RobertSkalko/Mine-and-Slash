package com.robertx22.mine_and_slash.advacements;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class KillBossTrigger extends AbstractCriterionTrigger<KillBossTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Ref.MODID, "kill_boss");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        String boss = json.get("boss").getAsString();
        return new Instance(boss);
    }

    public void trigger(ServerPlayerEntity player, BossCap.IBossData boss) {
        this.func_227070_a_(player.getAdvancements(), (instance) -> {
            return instance.conditionIsMet(boss);
        });
    }

    public static class Instance extends CriterionInstance {

        public String boss;

        public Instance(String bossID) {
            super(ID);
            this.boss = bossID;
        }

        public boolean conditionIsMet(BossCap.IBossData boss) {
            if (boss == null || !boss.isBoss() || boss.getBoss() == null) {
                return false;
            }
            return this.boss.equals(boss.getBoss().GUID());
        }

        @Override
        public JsonElement serialize() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("boss", new JsonPrimitive(boss));
            return jsonobject;
        }
    }

}
