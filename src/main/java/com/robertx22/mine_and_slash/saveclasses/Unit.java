package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionConfig;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionsContainer;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigs;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.UnknownStat;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Mana;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.EntityUnitPacket;
import com.robertx22.mine_and_slash.saveclasses.effects.StatusEffectData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

// this stores data that can be lost without issue, stats that are recalculated all the time
// and mob status effects.
@Storable
public class Unit {

    @Store
    private HashMap<String, StatData> MyStats = null;

    @Nonnull
    public HashMap<String, StatData> getStats() {

        if (MyStats == null) {
            this.InitMobStats();
        }

        return MyStats;
    }

    @Nonnull
    public StatData getStat(Stat stat) {
        return getStat(stat.GUID());
    }

    @Nonnull
    public StatData getStat(String guid) {

        if (MyStats == null) {
            this.InitMobStats();
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

    @Store
    public WornSetsContainerData wornSets = new WornSetsContainerData();

    @Store
    public HashMap<String, StatusEffectData> statusEffects = new HashMap<String, StatusEffectData>();

    @Store
    public String GUID = UUID.randomUUID().toString();

    public Unit() {

    }

    public void InitMobStats() {

        MyStats = new HashMap<String, StatData>();
        // adds all stats
        for (Stat stat : SlashRegistry.Stats().getAll().values()) {
            MyStats.put(stat.GUID(), new StatData(stat));
        }

    }

    public void InitPlayerStats() {

        if (MyStats == null) {
            MyStats = new HashMap<String, StatData>();

            // adds all stats
            for (Stat stat : SlashRegistry.Stats().getAll().values()) {
                MyStats.put(stat.GUID(), new StatData(stat));
            }

        } else {
            // adds new stats
            for (Stat stat : SlashRegistry.Stats().getAll().values()) {
                if (!MyStats.containsKey(stat.GUID())) {
                    MyStats.put(stat.GUID(), new StatData(stat));
                }
            }
            // removes stats that were deleted or renamed
            HashMap<String, StatData> stats = new HashMap<String, StatData>(MyStats);
            for (Entry<String, StatData> entry : stats.entrySet()) {
                if (!SlashRegistry.Stats().isRegistered(entry.getKey())) {
                    MyStats.remove(entry.getKey());
                }
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
        return (Health) getStat(new Health()).GetStat();
    }

    public Mana mana() {
        return (Mana) getStat(new Mana()).GetStat();
    }

    public Energy energy() {
        return (Energy) getStat(new Energy()).GetStat();
    }

    public StatData healthData() {
        try {
            return getStat(new Health());
        } catch (Exception e) {
        }
        return null;
    }

    public StatData manaData() {
        try {
            return getStat(new Mana());
        } catch (Exception e) {

        }
        return null;
    }

    public StatData energyData() {
        try {
            return getStat(new Energy());
        } catch (Exception e) {

        }
        return null;
    }

    public static Unit Mob(LivingEntity entity, @Nullable PlayerEntity nearestPlayer) {

        Unit mob = new Unit();
        mob.InitMobStats();

        UnitData endata = Load.Unit(entity);

        if (nearestPlayer != null) {
            if (WorldUtils.isMapWorld(entity.world)) {
                PlayerMapCap.IPlayerMapData mapdata = Load.playerMapData(nearestPlayer);
                endata.setTier(mapdata.getTier());
            }
        }

        endata.SetMobLevelAtSpawn(entity);
        endata.setRarity(randomRarity(entity, endata.getLevel()));

        MobStatUtils.AddRandomMobStatusEffects(entity, mob, Rarities.Mobs.get(endata.getRarity()));

        endata.setUnit(mob, entity);

        mob.RecalculateStats(entity, endata, endata.getLevel());

        return mob;

    }

    private static int randomRarity(LivingEntity entity, int level) {

        double y = entity.posY;

        int minRarity = 0;

        if (entity.dimension.equals(DimensionType.OVERWORLD)) {

            if (y < 50) {
                minRarity = 1;
            }
            if (y < 30) {
                minRarity = 2;
            }
        }

        List<MobRarity> rarities = Rarities.Mobs.rarities();
        List<MobRarity> after = new ArrayList<MobRarity>();

        DimensionConfig config = DimensionsContainer.INSTANCE.getConfig(entity.world);

        for (MobRarity rar : rarities) {
            if (rar.Rank() >= minRarity) {
                if (rar.Rank() == 4 && config.LEVEL_FOR_MOBS_TO_BE_LEGENDARY > level) {

                } else if (rar.Rank() == 5 && config.LEVEL_FOR_MOBS_TO_BE_MYTHICAL > level) {

                } else {
                    after.add(rar);
                }
            }
        }

        MobRarity finalRarity = RandomUtils.weightedRandom(after);

        ModEntityConfig entityconfig = ModEntityConfigs.INSTANCE.getConfig(entity);

        return MathHelper.clamp(finalRarity.Rank(), entityconfig.MIN_RARITY, entityconfig.MAX_RARITY);

    }

    protected void ClearStats() {

        if (MyStats == null) {
            this.InitPlayerStats();
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
            this.InitPlayerStats();
        }

        DirtyCheck check = new DirtyCheck();

        check.hp = (int) MyStats.get(Health.GUID).Value;

        return check;
    }

    private float getHpAdded(LivingEntity entity, MobRarity rar, UnitData data) {

        float hpadded = entity.getMaxHealth() * data.getLevel();

        if (entity instanceof PlayerEntity) {
            hpadded *= ModConfig.INSTANCE.Server.PLAYER_HEART_TO_HEALTH_CONVERSION.get();

        } else {
            hpadded *= 2F * rar.HealthMultiplier();
        }

        return hpadded;
    }

    public void RecalculateStats(LivingEntity entity, UnitData data, int level) {

        entity.world.getProfiler().startSection(Ref.MODID + ":stat_calculation");

        data.setEquipsChanged(false);

        if (data.getUnit() == null) {
            data.setUnit(this, entity);
        }

        DirtyCheck old = getDirtyCheck();

        List<GearItemData> gears = PlayerStatUtils.getEquipsExcludingWeapon(entity); // slow but required

        boolean gearIsValid = this.isGearCombinationValid(gears, entity);

        ItemStack weapon = entity.getHeldItemMainhand();
        if (weapon != null) {
            GearItemData wep = Gear.Load(weapon);
            if (wep != null && wep.GetBaseGearType() != null && wep.GetBaseGearType()
                    .slotType()
                    .equals(GearItemSlot.GearSlotType.Weapon)) {
                gears.add(wep);
            }

        }

        ItemStack offhand = entity.getHeldItemOffhand();
        if (offhand != null) {
            GearItemData off = Gear.Load(offhand);
            if (off != null && off.GetBaseGearType() != null && off.GetBaseGearType()
                    .slotType()
                    .equals(GearItemSlot.GearSlotType.OffHand)) {
                gears.add(off);
            } else if (off != null && off.GetBaseGearType()
                    .slotType()
                    .equals(GearItemSlot.GearSlotType.Weapon)) {
                entity.sendMessage(new StringTextComponent("You can't wear a weapon in offhand."));
            }

        }

        Unit copy = this.Clone();

        PlayerMapCap.IPlayerMapData mapdata = null;

        if (entity instanceof PlayerEntity) {
            mapdata = Load.playerMapData((PlayerEntity) entity);
        }

        ClearStats();

        MobRarity rar = Rarities.Mobs.get(data.getRarity());

        float hpadded = getHpAdded(entity, rar, data);

        MyStats.get(Health.GUID).Flat += hpadded;

        Boolean isMapWorld = WorldUtils.isMapWorld(entity.world);

        CommonStatUtils.addPotionStats(entity, data);
        CommonStatUtils.addCustomStats(data, this, level);

        if (entity instanceof PlayerEntity) {
            PlayerStatUtils.AddPlayerBaseStats(data, this);

        } else {
            MobStatUtils.AddMobcStats(data, data.getLevel());
            MobStatUtils.worldMultiplierStats(entity.world, this);

            if (isMapWorld) {
                MobStatUtils.increaseMobStatsPerTier(data, this);
            }

            MobStatUtils.modifyMobStatsByConfig(entity, data, level);

        }

        if (gearIsValid) {
            PlayerStatUtils.CountWornSets(entity, gears, this);
            PlayerStatUtils.AddAllGearStats(entity, gears, this, level);
            PlayerStatUtils.AddAllSetStats(entity, data, this, level);

        }

        CommonStatUtils.AddStatusEffectStats(this, level);

        if (isMapWorld) {
            CommonStatUtils.AddMapAffixStats(mapdata, this, level, entity);
        }

        CommonStatUtils.CalcStatConversionsAndTransfers(copy, this);
        CommonStatUtils.CalcTraitsAndCoreStats(data); // has to be at end for the conditionals like if crit higher than x

        CalcStats(data);

        DirtyCheck newcheck = getDirtyCheck();

        if (old.isDirty(newcheck)) {
            MMORPG.sendToTracking(new EntityUnitPacket(entity, data), entity);
        }

        entity.world.getProfiler().endSection();
    }

    // gear check works on everything but the weapon.
    public boolean isGearCombinationValid(List<GearItemData> gears, Entity en) {

        int unique_items = countUniqueItems(gears);

        if (unique_items > ModConfig.INSTANCE.Server.MAXIMUM_WORN_UNIQUE_ITEMS.get()) {
            if (en instanceof PlayerEntity) {
                en.sendMessage(new StringTextComponent("Gear Stats Not Added, reason: you are wearing too many unique items! Maximum Possible Unique items (excluding weapon): " + ModConfig.INSTANCE.Server.MAXIMUM_WORN_UNIQUE_ITEMS
                        .get()));
            }
            return false;
        }

        int runed_items = countRunedItems(gears);

        if (runed_items > ModConfig.INSTANCE.Server.MAXIMUM_WORN_RUNED_ITEMS.get()) {
            if (en instanceof PlayerEntity) {
                en.sendMessage(new StringTextComponent("Gear Stats Not Added, reason: you are wearing too many runed items! Maximum Possible Unique items (excluding weapon): " + ModConfig.INSTANCE.Server.MAXIMUM_WORN_RUNED_ITEMS
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
