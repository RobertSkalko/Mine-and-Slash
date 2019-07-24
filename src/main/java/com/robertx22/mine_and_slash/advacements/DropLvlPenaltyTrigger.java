package com.robertx22.mine_and_slash.advacements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DropLvlPenaltyTrigger implements ICriterionTrigger<DropLvlPenaltyTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Ref.MODID, "drop_lvl_penalty_trigger");
    private final Map<PlayerAdvancements, DropLvlPenaltyTrigger.Listeners> listeners = Maps
            .newHashMap();

    public ResourceLocation getId() {
        return ID;
    }

    public void addListener(PlayerAdvancements playerAdvancementsIn,
                            ICriterionTrigger.Listener<DropLvlPenaltyTrigger.Instance> listener) {
        DropLvlPenaltyTrigger.Listeners LevelTrigger$listeners = this.listeners.get(playerAdvancementsIn);
        if (LevelTrigger$listeners == null) {
            LevelTrigger$listeners = new DropLvlPenaltyTrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, LevelTrigger$listeners);
        }

        LevelTrigger$listeners.add(listener);
    }

    public void removeListener(PlayerAdvancements playerAdvancementsIn,
                               ICriterionTrigger.Listener<DropLvlPenaltyTrigger.Instance> listener) {
        DropLvlPenaltyTrigger.Listeners LevelTrigger$listeners = this.listeners.get(playerAdvancementsIn);
        if (LevelTrigger$listeners != null) {
            LevelTrigger$listeners.remove(listener);
            if (LevelTrigger$listeners.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }

    }

    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
        this.listeners.remove(playerAdvancementsIn);
    }

    public DropLvlPenaltyTrigger.Instance deserializeInstance(JsonObject json,
                                                              JsonDeserializationContext context) {
        int level = json.get("level").getAsInt();
        return new DropLvlPenaltyTrigger.Instance(level);
    }

    public void trigger(ServerPlayerEntity player, EntityCap.UnitData data,
                        EntityCap.UnitData mob) {
        DropLvlPenaltyTrigger.Listeners LevelTrigger$listeners = this.listeners.get(player
                .getAdvancements());
        if (LevelTrigger$listeners != null) {
            LevelTrigger$listeners.trigger(data, mob);
        }

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

    static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<DropLvlPenaltyTrigger.Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void add(
                ICriterionTrigger.Listener<DropLvlPenaltyTrigger.Instance> listener) {
            this.listeners.add(listener);
        }

        public void remove(
                ICriterionTrigger.Listener<DropLvlPenaltyTrigger.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger(EntityCap.UnitData player, EntityCap.UnitData mob) {
            List<Listener<DropLvlPenaltyTrigger.Instance>> list = null;

            for (ICriterionTrigger.Listener<DropLvlPenaltyTrigger.Instance> listener : this.listeners) {
                if (listener.getCriterionInstance().conditionIsMet(player, mob)) {
                    if (list == null) {
                        list = Lists.newArrayList();
                    }

                    list.add(listener);
                }

            }

            if (list != null) {
                for (ICriterionTrigger.Listener<DropLvlPenaltyTrigger.Instance> listener1 : list) {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }

        }
    }
}
