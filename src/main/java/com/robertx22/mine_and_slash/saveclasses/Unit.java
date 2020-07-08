package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionConfig;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.mob_affixes.base.MobAffix;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.IAfterStatCalc;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.UnknownStat;
import com.robertx22.mine_and_slash.database.stats.types.resources.BonusMaximumHealth;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.packets.EfficientMobUnitPacket;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.stat_calculation.CommonStatUtils;
import com.robertx22.mine_and_slash.uncommon.stat_calculation.MobStatUtils;
import com.robertx22.mine_and_slash.uncommon.stat_calculation.PlayerStatUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

// this stores data that can be lost without issue, stats that are recalculated all the time
// and mob status effects.
@Storable
public class Unit {

    @Store
    private StatContainer stats = new StatContainer();

    @Store
    public String GUID = UUID.randomUUID()
        .toString();

    @Store
    public String prefix;
    @Store
    public String suffix;

    public MobAffix getPrefix() {
        if (prefix == null) {
            return null;
        } else {
            return SlashRegistry.MobAffixes()
                .get(prefix);
        }
    }

    public MobAffix getSuffix() {
        if (suffix == null) {
            return null;
        } else {
            return SlashRegistry.MobAffixes()
                .get(suffix);
        }
    }

    @Nonnull
    public HashMap<String, StatData> getStats() {

        if (stats.stats == null) {
            this.initStats();
        }

        return stats.stats;
    }

    @Nonnull
    public StatData getCreateStat(Stat stat) {
        return getCreateStat(stat.GUID());
    }

    public boolean hasStat(Stat stat) {
        return hasStat(stat.GUID());
    }

    public boolean hasStat(String guid) {
        return stats.stats.containsKey(guid);
    }

    public StatData peekAtStat(Stat stat) {
        return peekAtStat(stat.GUID());
    }

    @Nonnull
    public StatData peekAtStat(String guid) {

        if (stats.stats == null) {
            this.initStats();
        }

        return stats.stats.getOrDefault(guid, StatData.empty());

    }

    public void setRandomMobAffixes(MobRarity rarity) {

        if (RandomUtils.roll(rarity.bothAffixesChance())) {
            randomizePrefix();
            randomizeSuffix();
        } else if (RandomUtils.roll(rarity.oneAffixChance())) {

            if (RandomUtils.roll(50)) {
                randomizePrefix();
            } else {
                randomizeSuffix();
            }
        }

    }

    public void randomizePrefix() {
        this.prefix = SlashRegistry.MobAffixes()
            .getFilterWrapped(x -> x.isPrefix())
            .random()
            .GUID();
    }

    public void randomizeSuffix() {
        this.suffix = SlashRegistry.MobAffixes()
            .getFilterWrapped(x -> x.isSuffix())
            .random()
            .GUID();
    }

    @Nonnull
    public StatData getCreateStat(String guid) {

        if (stats.stats == null) {
            this.initStats();
        }

        StatData data = stats.stats.get(guid);

        if (data == null) {
            Stat stat = SlashRegistry.Stats()
                .get(guid);
            if (stat != null) {
                stats.stats.put(stat.GUID(), new StatData(stat));

                return stats.stats.get(stat.GUID());
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
        stats.stats = new HashMap<String, StatData>();
    }

    private void removeEmptyStats() {

        for (StatData data : new ArrayList<>(stats.stats.values())) {
            if (!data.isNotZero() || data.getId()
                .isEmpty()) {
                //System.out.println(data.Name);
                stats.stats.remove(data.getId());
            }
        }

    }

    public void removeUnregisteredStats() {

        if (stats.stats == null) {
            stats.stats = new HashMap<String, StatData>();
        }

        removeEmptyStats();

        for (Map.Entry<String, StatData> entry : new ArrayList<>(stats.stats.entrySet())) {

            StatData data = entry.getValue();

            if (!SlashRegistry.Stats()
                .isRegistered(data.getId())) {
                stats.stats.remove(entry.getKey());
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
    public BonusMaximumHealth health() {
        return BonusMaximumHealth.getInstance();
    }

    public Mana mana() {
        return Mana.getInstance();
    }

    public Energy energy() {
        return Energy.getInstance();
    }

    public float getCurrentEffectiveHealth(LivingEntity entity, UnitData data) {
        float curhp = health().CurrentValue(entity, this);
        if (data.getResources() != null) {
            curhp += data.getResources()
                .getMagicShield();
        }
        return curhp;

    }

    public float getMaxEffectiveHealth() {
        float hp = healthData().getAverageValue();
        hp += magicShieldData().getAverageValue();

        return hp;

    }

    public StatData healthData() {
        try {
            return getCreateStat(BonusMaximumHealth.GUID);
        } catch (Exception e) {
        }
        return StatData.empty();
    }

    public StatData magicShieldData() {
        try {
            return getCreateStat(MagicShield.GUID);
        } catch (Exception e) {

        }
        return StatData.empty();
    }

    public StatData manaData() {
        try {
            return getCreateStat(Mana.GUID);
        } catch (Exception e) {

        }
        return StatData.empty();
    }

    public StatData energyData() {
        try {
            return getCreateStat(Energy.GUID);
        } catch (Exception e) {

        }
        return StatData.empty();
    }

    public int randomRarity(LivingEntity entity, UnitData data) {

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

        MobRarity finalRarity = RandomUtils.weightedRandom(rarities);

        ModEntityConfig entityConfig = SlashRegistry.getEntityConfig(entity, Load.Unit(entity));

        return MathHelper.clamp(finalRarity.Rank(), entityConfig.MIN_RARITY, entityConfig.MAX_RARITY);

    }

    protected void ClearStats() {

        if (stats.stats == null) {
            this.initStats();
        }

        for (StatData stat : stats.stats.values()) {
            stat.Clear();
        }
    }

    protected void CalcStats(UnitData data) {
        stats.stats.values()
            .forEach((StatData stat) -> stat.CalcVal(data));
    }

    public float getMissingHealth(LivingEntity en) {
        return healthData().getAverageValue() - health().CurrentValue(en, this);
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

        if (stats.stats == null || stats.stats.isEmpty()) {
            this.initStats();
        }

        DirtyCheck check = new DirtyCheck();

        check.hp = (int) getCreateStat(BonusMaximumHealth.GUID).getAverageValue();

        return check;
    }

    public void recalculateStats(LivingEntity entity, UnitData data, @Nullable DamageEventData dmgData) {

        data.setEquipsChanged(false);

        if (data.getUnit() == null) {
            data.setUnit(this, entity);
        }

        DirtyCheck old = getDirtyCheck();

        List<GearItemData> gears = new ArrayList<>();

        MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.CollectGearStacksEvent(entity, gears, dmgData));

        ClearStats();

        MobRarity rar = Rarities.Mobs.get(data.getRarity());

        Boolean isMapWorld = WorldUtils.isMapWorld(entity.world);

        CommonStatUtils.addPotionStats(entity, data);
        CommonStatUtils.addCustomStats(data, this);
        CommonStatUtils.addExactCustomStats(data);

        if (entity instanceof PlayerEntity) {
            PlayerStatUtils.AddPlayerBaseStats(data, this);

        } else {
            MobStatUtils.AddMobcStats(data);
            MobStatUtils.addAffixStats(data);
            MobStatUtils.worldMultiplierStats(entity.world, this);

            if (isMapWorld) {
                MobStatUtils.increaseMobStatsPerTier(data, this);
            }

            MobStatUtils.modifyMobStatsByConfig(entity, data);

        }

        PlayerStatUtils.AddAllGearStats(entity, gears, data);

        Unit copy = this.Clone();

        CommonStatUtils.CalcStatConversionsAndTransfers(copy, this);

        CommonStatUtils.CalcTraitsAndCoreStats(
            data); // has to be at end for the conditionals like if crit higher than x

        MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.OnStatCalculation(entity, data));

        CalcStats(data);

        removeEmptyStats();

        if (entity instanceof PlayerEntity) {
            PlayerStatUtils.applyRequirementsUnmetPenalty(entity, data, gears);

            PlayerSpellCap.ISpellsCap spells = Load.spells(entity);

            spells.getAbilitiesData()
                .clearBonusLevels();

            try {
                this.stats.stats.entrySet()
                    .forEach(x -> {
                        Stat stat = x.getValue()
                            .GetStat();

                        if (stat instanceof IAfterStatCalc) {
                            IAfterStatCalc after = (IAfterStatCalc) stat;

                            ((IAfterStatCalc) stat).doAfterStatCalc(x.getValue(), data, spells);

                        }

                    });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        DirtyCheck newcheck = getDirtyCheck();

        if (old.isDirty(newcheck)) {
            if (!Unit.shouldSendUpdatePackets((LivingEntity) entity)) {
                return;
            }
            MMORPG.sendToTracking(getUpdatePacketFor(entity, data), entity);
        }

    }

    private static List<EntityType> IGNORED_ENTITIES = null;

    public static List<EntityType> getIgnoredEntities() {

        if (IGNORED_ENTITIES == null) {
            IGNORED_ENTITIES = ModConfig.INSTANCE.Server.IGNORED_ENTITIES.get()
                .stream()
                .filter(x -> ForgeRegistries.ENTITIES.containsKey(new ResourceLocation(x)))
                .map(x -> ForgeRegistries.ENTITIES.getValue(new ResourceLocation(x)))
                .collect(Collectors.toList());
        }

        return IGNORED_ENTITIES;

    }

    public static boolean shouldSendUpdatePackets(LivingEntity en) {
        return getIgnoredEntities().contains(en.getType()) == false;
    }

    public static Object getUpdatePacketFor(LivingEntity en, UnitData data) {
        return new EfficientMobUnitPacket(en, data); // todo maybe players will need extra data later on? maybe
    }

    private Unit Clone() {

        Unit clone = new Unit();
        if (this.stats.stats != null) {
            clone.stats.stats = new HashMap<String, StatData>(stats.stats);
        } else {
            clone.stats.stats = new HashMap<String, StatData>();
        }

        return clone;

    }

}
