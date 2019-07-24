package com.robertx22.mine_and_slash.loot;

import com.robertx22.mine_and_slash.config.dimension_configs.DimensionsContainer;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.loot.gens.BaseLootGen;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class LootInfo {

    public int amount = 0;
    public int tier = 0;
    public int level = 1;

    public UnitData mobData;
    public UnitData playerData;
    public LivingEntity victim;
    public PlayerEntity killer;
    public World world;
    public float multi = 1;
    public int minItems = 0;
    public int maxItems = 50;

    public LootInfo setMaximum(int max) {
        this.maxItems = max;
        return this;
    }

    public LootInfo setMinimum(int min) {
        this.minItems = min;
        return this;
    }

    public LootInfo setLevel(int level) {
        this.level = level;
        return this;
    }

    public LootInfo setMulti(float multi) {
        this.multi = multi;
        return this;
    }

    public LootInfo setTier() {

        if (this.mobData != null) {
            this.tier = mobData.getTier();
        } else {
            if (killer != null) {
                if (WorldUtils.isMapWorld(killer.world)) {

                    this.tier = WorldUtils.getTier(killer.world, killer);

                }
            }
        }
        return this;

    }

    public LootInfo(UnitData mob, UnitData player, LivingEntity victim,
                    PlayerEntity killer) {
        this.world = victim.world;
        this.mobData = mob;
        this.playerData = player;
        this.victim = victim;
        this.killer = killer;
        this.level = mob.getLevel();
        setTier();

    }

    public LootInfo(World theworld) {
        this.world = theworld;
    }

    public LootInfo(PlayerEntity player) {
        this.world = player.world;
        this.playerData = Load.Unit(player);
        this.level = Load.playerMapData(player).getLevel();
        setTier();

    }

    public void setup(BaseLootGen gen) {

        float chance = gen.BaseChance();

        chance *= multi;

        if (victim != null) {
            chance *= EntityTypeUtils.getLootMulti(victim);
        }

        if (this.playerData != null) {

            chance *= this.playerData.getUnit()
                    .getStat(new LootTypeBonusFlat(gen.lootType()))
                    .getMultiplier();

            chance *= this.playerData.getUnit()
                    .getStat(new LootTypeBonusFlat(LootType.All))
                    .getMultiplier();

        }

        if (killer != null) {
            chance *= Load.playerMapData(killer).getLootMultiplier(killer);

        }

        if (world != null) {
            chance *= DimensionsContainer.INSTANCE.getConfig(world).DROP_MULTIPLIER;

            if (world.getDimension() instanceof IWP) {
                IWP iwp = (IWP) world.getDimension();
                chance *= 1 + iwp.getBonusLootMulti();
            }
        }

        if (mobData != null && victim != null) {
            chance = LootUtils.applyLootMultipliers(chance, mobData, victim);
        }

        if (mobData != null && playerData != null) {
            if (gen.hasLevelDistancePunishment()) {
                chance = LootUtils.ApplyLevelDistancePunishment(mobData, playerData, chance);
            }
        }

        amount = LootUtils.WhileRoll(chance);
    }

}
