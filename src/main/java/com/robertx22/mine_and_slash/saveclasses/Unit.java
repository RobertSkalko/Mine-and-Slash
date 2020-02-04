package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionConfig;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.UnknownStat;
import com.robertx22.mine_and_slash.database.stats.types.game_changers.BloodMage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.EntityUnitPacket;
import com.robertx22.mine_and_slash.saveclasses.effects.StatusEffectData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.BossCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.stat_calculation.CommonStatUtils;
import com.robertx22.mine_and_slash.uncommon.stat_calculation.MobStatUtils;
import com.robertx22.mine_and_slash.uncommon.stat_calculation.PlayerStatUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import java.util.*;

// this stores data that can be lost without issue, stats that are recalculated all the time
// and mob status effects.
@Storable
public class Unit {

    @Store
    private HashMap<String, StatData> MyStats = null;

    @Store
    public WornSetsContainerData wornSets = new WornSetsContainerData();

    @Store
    public HashMap<String, StatusEffectData> statusEffects = new HashMap<String, StatusEffectData>();

    @Store
    public String GUID = UUID.randomUUID().toString();

    @Nonnull
    public HashMap<String, StatData> getStats() {

        if (MyStats == null) {
            this.initStats();
        }

        return MyStats;
    }

    @Nonnull
    public StatData getCreateStat(Stat stat) {
        return getCreateStat(stat.GUID());
    }

    public boolean hasStat(Stat stat) {
        return hasStat(stat.GUID());
    }

    public boolean hasStat(String guid) {
        return MyStats.containsKey(guid);
    }

    public StatData peekAtStat(Stat stat) {
        return peekAtStat(stat.GUID());
    }

    @Nonnull
    public StatData peekAtStat(String guid) {

        if (MyStats == null) {
            this.initStats();
        }

        return MyStats.getOrDefault(guid, StatData.empty());

    }

    @Nonnull
    public StatData getCreateStat(String guid) {

        if (MyStats == null) {
            this.initStats();
        }

        StatData data = MyStats.get(guid);

        if (data == null) {
            Stat stat = SlashRegistry.Stats().get(guid);
            if (stat != null) {
                MyStats.put(stat.GUID(), new StatData(stat));

                return MyStats.get(stat.GUID());
            } else {
                return new StatData(new UnknownStat());
            }
        } else {
            return data;
        }
    }

    public Unit() {

    }

    public void initStats() {
        MyStats = new HashMap<String, StatData>();
    }

    private void removeEmptyStats() {

        for (StatData data : new ArrayList<>(MyStats.values())) {
            if (data.val <= 0 || data.getId().isEmpty()) {
                //System.out.println(data.Name);
                MyStats.remove(data.getId());
            }
        }

    }

    public void removeUnregisteredStats() {

        if (MyStats == null) {
            MyStats = new HashMap<String, StatData>();
        }

        removeEmptyStats();

        for (Map.Entry<String, StatData> entry : new ArrayList<>(MyStats.entrySet())) {

            StatData data = entry.getValue();

            if (!SlashRegistry.Stats().isRegistered(data.getId())) {
                MyStats.remove(entry.getKey());
            }
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Unit) {
            return ((Unit) obj).GUID == this.GUID;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return GUID.hashCode();
    }

    // Stat shortcuts
    public Health health() {
        return Health.INSTANCE;
    }

    public Mana mana() {
        return Mana.INSTANCE;
    }

    public Energy energy() {
        return Energy.INSTANCE;
    }

    public float getCurrentEffectiveHealth(LivingEntity entity, UnitData data) {
        float curhp = health().CurrentValue(entity, this);
        curhp += data.getResources().getMagicShield();
        return curhp;

    }

    public float getMaxEffectiveHealth() {
        float hp = healthData().val;
        hp += magicShieldData().val;

        return hp;

    }

    public boolean isBloodMage() {
        return hasStat(BloodMage.INSTANCE) && this.getCreateStat(BloodMage.INSTANCE).val > 0;
    }

    public float getMaximumBlood() {
        if (this.getCreateStat(BloodMage.INSTANCE).val > 0) {
            return healthData().val / 2;
        }
        return 0;
    }

    public StatData healthData() {
        try {
            return getCreateStat(Health.GUID);
        } catch (Exception e) {
        }
        return null;
    }

    public StatData magicShieldData() {
        try {
            return getCreateStat(MagicShield.GUID);
        } catch (Exception e) {

        }
        return null;
    }

    public StatData manaData() {
        try {
            return getCreateStat(Mana.GUID);
        } catch (Exception e) {

        }
        return null;
    }

    public StatData energyData() {
        try {
            return getCreateStat(Energy.GUID);
        } catch (Exception e) {

        }
        return null;
    }

    public int randomRarity(LivingEntity entity, UnitData data, BossCap.IBossData boss) {

        if (boss != null && boss.isBoss()) {
            return IRarity.Boss;
        }

        int level = data.getLevel();

        double y = entity.posY;

        List<MobRarity> rarities = Rarities.Mobs.getNormalRarities();

        if (entity.world.rand.nextBoolean()) {
            if (entity.dimension.equals(DimensionType.OVERWORLD)) {
                if (y < 50) {
                    rarities.removeIf(x -> x.Rank() == IRarity.Common);
                }
                if (y < 30) {
                    rarities.removeIf(x -> x.Rank() == IRarity.Uncommon);
                }
            }
        }

        DimensionConfig config = SlashRegistry.getDimensionConfig(entity.world);

        if (config.LEVEL_FOR_MOBS_TO_BE_LEGENDARY > level) {
            rarities.removeIf(x -> x.Rank() == IRarity.Legendary);
        }
        if (config.LEVEL_FOR_MOBS_TO_BE_MYTHICAL > level) {
            rarities.removeIf(x -> x.Rank() == IRarity.Mythic);
        }

        MobRarity finalRarity = RandomUtils.weightedRandom(rarities);

        ModEntityConfig entityConfig = SlashRegistry.getEntityConfig(entity, Load.Unit(entity));

        return MathHelper.clamp(finalRarity.Rank(), entityConfig.MIN_RARITY, entityConfig.MAX_RARITY);

    }

    protected void ClearStats() {

        if (MyStats == null) {
            this.initStats();
        }

        for (StatData stat : MyStats.values()) {
            stat.Clear();
        }
    }

    protected void CalcStats(UnitData data) {

        MyStats.values().forEach((StatData stat) -> stat.GetStat().CalcVal(stat, data));
    }

    class DirtyCheck {
        int hp;

        public boolean isDirty(DirtyCheck newcheck) {

            if (newcheck.hp != hp) {
                return true;
            }

            return false;

        }

    }

    /**
     * @return checks if it should be synced to clients. Clients currently only see
     * health and status effects
     */
    private DirtyCheck getDirtyCheck() {

        if (MyStats == null || MyStats.isEmpty()) {
            this.initStats();
        }

        DirtyCheck check = new DirtyCheck();

        check.hp = (int) getCreateStat(Health.GUID).val;

        return check;
    }

    private float getHpAdded(LivingEntity entity, MobRarity rar, UnitData data) {

        float hpadded = Health.INSTANCE.calculateScalingStatGrowth(entity.getMaxHealth(), data.getLevel());

        if (entity instanceof PlayerEntity) {
            hpadded *= ModConfig.INSTANCE.Server.PLAYER_HEART_TO_HEALTH_CONVERSION.get();

        } else {
            hpadded *= 2F * rar.HealthMultiplier();
        }

        return hpadded;
    }

    public void recalculateStats(LivingEntity entity, UnitData data, int level) {

        data.setEquipsChanged(false);

        if (data.getUnit() == null) {
            data.setUnit(this, entity);
        }

        DirtyCheck old = getDirtyCheck();

        List<GearItemData> gears = new ArrayList<>();

        MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.CollectGearStacksEvent(entity, gears));

        boolean gearIsValid = this.isGearCombinationValid(gears, entity);

        Unit copy = this.Clone();

        ClearStats();

        MobRarity rar = Rarities.Mobs.get(data.getRarity());

        float hpadded = getHpAdded(entity, rar, data);

        MyStats.get(Health.GUID).Flat += hpadded;

        Boolean isMapWorld = WorldUtils.isMapWorld(entity.world);

        WorldMapCap.IWorldMapData mapData = Load.world(entity.world);

        CommonStatUtils.addPotionStats(entity, data);
        CommonStatUtils.addCustomStats(data, this, level);
        CommonStatUtils.addExactCustomStats(data);

        if (entity instanceof PlayerEntity) {
            PlayerStatUtils.AddPlayerBaseStats(data, this);
            PlayerStatUtils.addTalentStats(data, (PlayerEntity) entity);

            Load.statPoints((PlayerEntity) entity).getData().getAllStatDatas().forEach(x -> x.applyStats(data));

        } else {
            MobStatUtils.AddMobcStats(data, data.getLevel());
            MobStatUtils.worldMultiplierStats(entity.world, this);

            if (isMapWorld) {
                MobStatUtils.increaseMobStatsPerTier(data, this);
            }

            MobStatUtils.modifyMobStatsByConfig(entity, data);

        }

        if (gearIsValid) {
            PlayerStatUtils.countWornSets(entity, gears, this);
            PlayerStatUtils.AddAllGearStats(entity, gears, data, level);
            PlayerStatUtils.AddAllSetStats(entity, data, this, level);
        }

        CommonStatUtils.AddStatusEffectStats(this, level);

        if (isMapWorld) {
            CommonStatUtils.AddMapAffixStats(mapData, this, level, entity);
        }

        CommonStatUtils.CalcStatConversionsAndTransfers(copy, this);

        CommonStatUtils.CalcTraitsAndCoreStats(
                data); // has to be at end for the conditionals like if crit higher than x

        MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.OnStatCalculation(entity, data));

        CalcStats(data);

        removeEmptyStats();

        if (entity instanceof PlayerEntity) {
            PlayerStatUtils.applyRequirementsUnmetPenalty(entity, data, gears);
        }

        DirtyCheck newcheck = getDirtyCheck();

        if (old.isDirty(newcheck)) {
            MMORPG.sendToTracking(new EntityUnitPacket(entity, data), entity);
        }

    }

    // gear check works on everything but the weapon.
    public boolean isGearCombinationValid(List<GearItemData> gears, Entity en) {

        int unique_items = countUniqueItems(gears);

        if (unique_items > ModConfig.INSTANCE.Server.MAXIMUM_WORN_UNIQUE_ITEMS.get()) {
            if (en instanceof ServerPlayerEntity) {
                en.sendMessage(new StringTextComponent(
                        "Gear Stats Not Added, reason: you are wearing too many unique items! Maximum Possible " +
                                "Unique" + " items (excluding weapon): " + ModConfig.INSTANCE.Server.MAXIMUM_WORN_UNIQUE_ITEMS
                                .get()));
            }
            return false;
        }

        int runed_items = countRunedItems(gears);

        if (runed_items > ModConfig.INSTANCE.Server.MAXIMUM_WORN_RUNED_ITEMS.get()) {
            if (en instanceof ServerPlayerEntity) {
                en.sendMessage(new StringTextComponent(
                        "Gear Stats Not Added, reason: you are wearing too many runed items! Maximum Possible Unique "
                                + "items (excluding weapon): " + ModConfig.INSTANCE.Server.MAXIMUM_WORN_RUNED_ITEMS
                                .get()));
            }
            return false;
        }

        // here i can go through all the items and then runewords and check if there is
        // more than

        return true;

    }

    private int countRunedItems(List<GearItemData> gears) {

        int amount = 0;

        for (GearItemData gear : gears) {
            if (gear.isRuned()) {
                amount++;
            }
        }

        return amount;

    }

    private int countUniqueItems(List<GearItemData> gears) {

        int amount = 0;

        for (GearItemData gear : gears) {
            if (gear.isUnique) {
                amount++;
            }
        }

        return amount;

    }

    private Unit Clone() {

        Unit clone = new Unit();
        if (this.MyStats != null) {
            clone.MyStats = new HashMap<String, StatData>(this.MyStats);
        } else {
            clone.MyStats = new HashMap<String, StatData>();
        }

        return clone;

    }

}
