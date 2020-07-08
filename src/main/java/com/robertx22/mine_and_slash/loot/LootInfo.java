package com.robertx22.mine_and_slash.loot;

import com.robertx22.mine_and_slash.database.stats.types.generated.LootTypeBonus;
import com.robertx22.mine_and_slash.loot.gens.BaseLootGen;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.LevelUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
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
    public WorldMapCap.IWorldMapData mapData;
    public float multi = 1;
    public int minItems = 0;
    public int maxItems = 50;
    public boolean isMapWorld = false;

    public BlockPos pos;

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

    public LootInfo(UnitData mobData, UnitData playerData, LivingEntity victim, PlayerEntity killer) {
        this.world = victim.world;
        this.mobData = mobData;
        this.playerData = playerData;
        this.victim = victim;
        this.killer = killer;
        this.pos = victim.getPosition();

        setupAllFields();
    }

    public LootInfo(World world, BlockPos pos) {

        this.world = world;
        this.pos = pos;

        setupAllFields();
    }

    public LootInfo(PlayerEntity player) {
        this.world = player.world;
        this.playerData = Load.Unit(player);
        this.pos = player.getPosition();

        setupAllFields();
    }

    private void setupAllFields() {
        // order matters
        errorIfClient();
        setWorld();
        setTier();
        setLevel();
    }

    private LootInfo setTier() {

        if (this.mobData != null) {
            this.tier = mobData.getTier();
        } else {
            if (world != null && mapData != null) {
                if (WorldUtils.isMapWorld(world)) {
                    this.tier = WorldUtils.getTier(world, mapData, pos);
                }
            }
        }
        return this;

    }

    private void errorIfClient() {
        if (world != null && world.isRemote) {
            throw new RuntimeException("Can't use Loot Info on client side!!!");
        }
    }

    private void setWorld() {
        if (world != null) {
            this.isMapWorld = WorldUtils.isMapWorld(world);
            this.mapData = Load.world(world);
        }
    }

    private void setLevel() {

        if (WorldUtils.isMapWorldClass(world)) {

            if (mapData != null) {
                level = mapData.getLevel(pos, world);
            } else {
                level = 1;
            }

        } else {
            if (mobData != null) {
                level = mobData.getLevel();
            } else {
                if (playerData != null) {
                    level = playerData.getLevel();
                } else {
                    level = LevelUtils.determineLevel(world, pos, killer);
                }
            }
        }

    }

    public void setup(BaseLootGen gen) {

        float chance = gen.baseDropChance();

        chance *= multi;

        if (victim != null && mobData != null) {
            chance *= SlashRegistry.getEntityConfig(victim, this.mobData).LOOT_MULTI;
        }

        if (this.playerData != null) {

            chance *= this.playerData.getUnit()
                .getCreateStat(new LootTypeBonus(gen.lootType()))
                .getMultiplier();

            chance *= this.playerData.getUnit()
                .getCreateStat(new LootTypeBonus(LootType.All))
                .getMultiplier();

            if (mobData != null) {
                if (playerData.getLevel() < 5 && mobData.getLevel() < 5) {
                    chance *= 2; // new player bonus droprate
                }
            }

        }

        if (killer != null) {
            chance *= Load.playerMapData(killer)
                .getLootMultiplier(killer);
        }

        if (world != null) {

            if (victim != null) {
                chance *= Load.antiMobFarm(world)
                    .getDropMultiForMob(victim);
            }

            chance *= SlashRegistry.getDimensionConfig(world).DROP_MULTIPLIER;

        }

        if (this.mapData != null && mapData.getData()
            .hasData(pos)) {
            chance *= this.mapData.getLootMultiplier(pos, world);
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
