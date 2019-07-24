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

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayerLevelTrigger implements ICriterionTrigger<PlayerLevelTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Ref.MODID, "player_level");
    private final Map<PlayerAdvancements, PlayerLevelTrigger.Listeners> listeners = Maps.newHashMap();

    public ResourceLocation getId() {
        return ID;
    }

    public void addListener(PlayerAdvancements playerAdvancementsIn,
                            ICriterionTrigger.Listener<PlayerLevelTrigger.Instance> listener) {
        PlayerLevelTrigger.Listeners LevelTrigger$listeners = this.listeners.get(playerAdvancementsIn);
        if (LevelTrigger$listeners == null) {
            LevelTrigger$listeners = new PlayerLevelTrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, LevelTrigger$listeners);
        }

        LevelTrigger$listeners.add(listener);
    }

    public void removeListener(PlayerAdvancements playerAdvancementsIn,
                               ICriterionTrigger.Listener<PlayerLevelTrigger.Instance> listener) {
        PlayerLevelTrigger.Listeners LevelTrigger$listeners = this.listeners.get(playerAdvancementsIn);
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

    public PlayerLevelTrigger.Instance deserializeInstance(JsonObject json,
                                                           JsonDeserializationContext context) {
        int level = json.get("level").getAsInt();
        return new PlayerLevelTrigger.Instance(level);
    }

    public void trigger(ServerPlayerEntity player, EntityCap.UnitData data) {
        PlayerLevelTrigger.Listeners LevelTrigger$listeners = this.listeners.get(player.getAdvancements());
        if (LevelTrigger$listeners != null) {
            LevelTrigger$listeners.trigger(data);
        }

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

        public JsonElement serialize() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("level", new JsonPrimitive(level));
            return jsonobject;
        }
    }

    static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<PlayerLevelTrigger.Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void add(
                ICriterionTrigger.Listener<PlayerLevelTrigger.Instance> listener) {
            this.listeners.add(listener);
        }

        public void remove(
                ICriterionTrigger.Listener<PlayerLevelTrigger.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger(EntityCap.UnitData player) {
            List<Listener<PlayerLevelTrigger.Instance>> list = null;

            for (ICriterionTrigger.Listener<PlayerLevelTrigger.Instance> listener : this.listeners) {
                if (listener.getCriterionInstance().conditionIsMet(player)) {
                    if (list == null) {
                        list = Lists.newArrayList();
                    }

                    list.add(listener);
                }

            }

            if (list != null) {
                for (ICriterionTrigger.Listener<PlayerLevelTrigger.Instance> listener1 : list) {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }

        }
    }
}
