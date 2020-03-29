package com.robertx22.mine_and_slash.uncommon.capability.entity;

import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.commands.open_gui.OpenHub;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.types.misc.BonusExp;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.tiers.base.Tier;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.CriteriaRegisters;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.onevent.player.OnLogin;
import com.robertx22.mine_and_slash.packets.EntityUnitPacket;
import com.robertx22.mine_and_slash.packets.NoEnergyPacket;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.*;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.capability.bases.INeededForClient;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.*;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.LevelUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Random;
import java.util.UUID;

@EventBusSubscriber
public class EntityCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "entitydata");

    @CapabilityInject(UnitData.class)
    public static final Capability<UnitData> Data = null;

    private static final String LEVEL = "level";
    private static final String RARITY = "rarity";
    private static final String EXP = "exp";
    private static final String UUID = "uuid";
    private static final String MOB_SAVED_ONCE = "mob_saved_once";
    private static final String CURRENT_MAP_ID = "current_map_resource_loc";
    private static final String SET_MOB_STATS = "set_mob_stats";
    private static final String NEWBIE_STATUS = "is_a_newbie";
    private static final String DMG_STATS = "dmg_stats";
    private static final String EQUIPS_CHANGED = "EQUIPS_CHANGED";
    private static final String TIER = "TIER";
    private static final String PREVENT_LOOT = "PREVENT_LOOT";
    private static final String SHOULD_SYNC = "SHOULD_SYNC";
    private static final String ENTITY_TYPE = "ENTITY_TYPE";
    private static final String RESOURCES_LOC = "RESOURCES_LOC";
    private static final String AVG_GEAR_LVL = "AVG_GEAR_LVL";

    public interface UnitData extends ICommonPlayerCap, INeededForClient {

        void modifyResource(ResourcesData.Context ctx);

        void onDeath(LivingEntity en);

        void setType(LivingEntity en);

        EntityTypeUtils.EntityType getType();

        void trySync(LivingEntity entity);

        void onAttackEntity(LivingEntity attacker, LivingEntity victim);

        GearItemData setupWeaponData(LivingEntity entity);

        int getLastHitTicksExisted();

        void setAttackCooldown(PlayerEntity player);

        float getAttackCooldown();

        boolean isAttackCooldownInSweepRange();

        void setEquipsChanged(boolean bool);

        void onDamagedBy(LivingEntity entity, float dmg, LivingEntity self);

        Entity getHighestDamageEntity(Entity entity);

        int PostGiveExpEvent(LivingEntity killed, PlayerEntity player, int exp);

        boolean isNewbie();

        void setNewbieStatus(boolean bool);

        boolean needsToBeGivenStats();

        void freelySetLevel(int lvl);

        int getLevel();

        void setLevel(int lvl, LivingEntity entity);

        boolean increaseRarity(LivingEntity entity);

        int getExp();

        void setExp(int exp);

        int GiveExp(PlayerEntity player, int i);

        int getExpRequiredForLevelUp();

        boolean CheckIfCanLevelUp();

        boolean LevelUp(PlayerEntity player);

        boolean CheckLevelCap();

        void SetMobLevelAtSpawn(WorldMapCap.IWorldMapData data, LivingEntity entity, PlayerEntity player);

        Unit getUnit();

        void setUnit(Unit unit, LivingEntity entity);

        void setRarity(int rarity);

        int getRarity();

        String getUUID();

        void setUUID(UUID id);

        ITextComponent getName(LivingEntity entity);

        void tryRecalculateStats(LivingEntity entity);

        void forceRecalculateStats(LivingEntity entity, DamageEventData data);

        void forceRecalculateStats(LivingEntity entity);

        void forceSetUnit(Unit unit);

        boolean tryUseWeapon(GearItemData gear, LivingEntity entity);

        boolean tryUseWeapon(GearItemData gear, LivingEntity entity, float multi);

        void onLogin(PlayerEntity player);

        String getCurrentMapId();

        void setCurrentMapId(String res);

        boolean hasCurrentMapId();

        void clearCurrentMapId();

        boolean decreaseRarity(LivingEntity entity);

        boolean isWeapon(GearItemData gear);

        void setTier(int tier);

        int getTier();

        Tier getMapTier();

        CustomStatsData getCustomStats();

        CustomExactStatsData getCustomExactStats();

        ResourcesData getResources();

        float getCurrentEnergy();

        float getCurrentMana();

        int getLvlForResourceCosts();

        void attackWithWeapon(DamageEventData data);

        void unarmedAttack(DamageEventData data);

        void mobBasicAttack(DamageEventData data);
    }

    @EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

            if (event.getObject() instanceof ArmorStandEntity) {
                return;
            }

            if (event.getObject() instanceof LivingEntity) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<UnitData> {

        @Override
        public UnitData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<UnitData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements UnitData {

        // sync these for mobs
        Unit unit = null;
        int level = 1;
        int rarity = 0;

        EntityTypeUtils.EntityType type = EntityTypeUtils.EntityType.PLAYER;
        // sync these for mobs

        int exp = 0;
        boolean setMobStats = false;
        String uuid = "";
        String currentMapResourceLoc = "";
        boolean isNewbie = true;
        boolean equipsChanged = true;
        int tier = 0;
        boolean shouldSync = false;

        float lastHitCooldownStr = 1;
        int lastHitTicks = 0;

        EntityDmgStatsData dmgStats = new EntityDmgStatsData();
        ResourcesData resources = new ResourcesData();
        CustomStatsData customStats = new CustomStatsData();
        CustomExactStatsData customExactStats = new CustomExactStatsData();

        @Override
        public CompoundNBT getClientNBT() {

            CompoundNBT nbt = new CompoundNBT();

            nbt.putInt(LEVEL, level);
            nbt.putInt(RARITY, rarity);
            nbt.putString(ENTITY_TYPE, this.type.toString());

            if (unit != null) {
                // System.out.println(unit.getStats().size()); for testing if mobs get all stats or only ones they need

                UnitNbt.Save(nbt, unit);
            }

            return nbt;

        }

        @Override
        public void setClientNBT(CompoundNBT nbt) {

            this.level = nbt.getInt(LEVEL);
            this.rarity = nbt.getInt(RARITY);

            try {
                String typestring = nbt.getString(ENTITY_TYPE);
                this.type = EntityTypeUtils.EntityType.valueOf(typestring);
            } catch (Exception e) {
                this.type = EntityTypeUtils.EntityType.OTHER;
                //if no nbt, set to default. Then at spawn, set correctly
            }

            this.unit = UnitNbt.Load(nbt);
            if (this.unit == null) {
                this.unit = new Unit();
            }
        }

        @Override
        public CompoundNBT saveToNBT() {
            CompoundNBT nbt = getClientNBT();

            nbt.putInt(EXP, exp);
            nbt.putInt(TIER, tier);
            nbt.putString(UUID, uuid);
            nbt.putBoolean(MOB_SAVED_ONCE, true);
            nbt.putString(CURRENT_MAP_ID, currentMapResourceLoc);
            nbt.putBoolean(SET_MOB_STATS, setMobStats);
            nbt.putBoolean(NEWBIE_STATUS, this.isNewbie);
            nbt.putBoolean(EQUIPS_CHANGED, equipsChanged);
            nbt.putBoolean(SHOULD_SYNC, shouldSync);

            nbt.putFloat("cdr", lastHitCooldownStr);
            nbt.putInt("lht", lastHitTicks);

            if (customStats != null) {
                CustomStats.Save(nbt, customStats);
            }

            if (customExactStats != null) {
                CustomExactStats.Save(nbt, customExactStats);
            }

            if (resources != null) {
                LoadSave.Save(resources, nbt, RESOURCES_LOC);
            }

            if (dmgStats != null) {
                LoadSave.Save(dmgStats, nbt, DMG_STATS);
            }

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {

            setClientNBT(nbt);

            this.exp = nbt.getInt(EXP);
            this.tier = nbt.getInt(TIER);
            this.uuid = nbt.getString(UUID);
            this.currentMapResourceLoc = nbt.getString(CURRENT_MAP_ID);
            this.setMobStats = nbt.getBoolean(SET_MOB_STATS);
            this.isNewbie = nbt.getBoolean(NEWBIE_STATUS);
            this.equipsChanged = nbt.getBoolean(EQUIPS_CHANGED);
            this.shouldSync = nbt.getBoolean(SHOULD_SYNC);

            this.lastHitCooldownStr = nbt.getFloat("cdr");
            this.lastHitTicks = nbt.getInt("lht");

            try {
                this.resources = LoadSave.Load(ResourcesData.class, new ResourcesData(), nbt, RESOURCES_LOC);
                if (resources == null) {
                    resources = new ResourcesData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                this.dmgStats = LoadSave.Load(EntityDmgStatsData.class, new EntityDmgStatsData(), nbt, DMG_STATS);
                if (dmgStats == null) {
                    dmgStats = new EntityDmgStatsData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.customStats = CustomStats.Load(nbt);
            if (this.customStats == null) {
                this.customStats = new CustomStatsData();
            }

            this.customExactStats = CustomExactStats.Load(nbt);
            if (this.customExactStats == null) {
                this.customExactStats = new CustomExactStatsData();
            }

        }

        @Override
        public int getExpRequiredForLevelUp() {
            return levelToExp(this.getLevel() + 1);
        }

        public static void testEXPLevelingCurve() {

            System.out.println("Old Formula");
            for (int i = 1; i < 101; i++) {
                //System.out.println("level: " + i + " exp: " + oldlevelToExp(i));

            }

            System.out.println("New Formula");
            for (int i = 1; i < 101; i++) {
                System.out.println("level: " + i + " exp: " + levelToExp(i));
            }

        }

        public static int equateXp(double lvl) {
            return (int) Math.floor(lvl + 340 * Math.pow(2, lvl / 9));
        }

        public static int levelToExp(int level) {
            if (true) {
                return level * level * level * 10;
            }

            double xp = 0;

            for (int i = 1; i < level; i++)
                xp += equateXp(i);

            return (int) Math.floor(xp / 4);
        }

        @Override
        public void SetMobLevelAtSpawn(WorldMapCap.IWorldMapData data, LivingEntity entity,
                                       PlayerEntity nearestPlayer) {
            this.setMobStats = true;

            if (WorldUtils.isMapWorldClass(entity.world)) {
                this.level = data.getLevel(entity.getPosition());

            } else {
                setMobLvlNormally(entity, nearestPlayer);
            }
        }

        private void setMobLvlNormally(LivingEntity entity, PlayerEntity nearestPlayer) {
            ModEntityConfig entityConfig = SlashRegistry.getEntityConfig(entity, this);
            int lvl = LevelUtils.determineLevel(entity.world, entity.getPosition(),
                nearestPlayer
            ) + entityConfig.LEVEL_MODIFIER;
            if (entityConfig.LEVEL_TO_NEAREST_PLAYER) {
                int playerLvl = Load.Unit(nearestPlayer)
                    .getLevel();
                this.level = MathHelper.clamp(lvl, playerLvl, playerLvl);
            } else {
                this.level = MathHelper.clamp(lvl, entityConfig.MIN_LEVEL, entityConfig.MAX_LEVEL);
            }
        }

        @Override
        public void setEquipsChanged(boolean bool) {
            this.equipsChanged = bool;
        }

        @Override
        public void onDamagedBy(LivingEntity entity, float dmg, LivingEntity self) {
            try {
                this.dmgStats.onDamage(entity, dmg);

                if (entity == null && self != null) {
                    float msDamage = this.getUnit()
                        .magicShieldData()
                        .getAverageValue() * (dmg / self.getMaxHealth());
                    ResourcesData.Context ms = new ResourcesData.Context(this, self,
                        ResourcesData.Type.MAGIC_SHIELD,
                        msDamage,
                        ResourcesData.Use.SPEND
                    );
                    getResources()
                        .modify(ms);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public Entity getHighestDamageEntity(Entity entity) {
            return dmgStats.getHighestDamageEntity((ServerWorld) entity.world);
        }

        @Override
        public int PostGiveExpEvent(LivingEntity killed, PlayerEntity player, int i) {

            i *= ModConfig.INSTANCE.Server.EXPERIENCE_MULTIPLIER.get();

            i *= (double) this.getUnit()
                .peekAtStat(BonusExp.GUID)
                .getAverageValue() / 100 + 1;

            MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.GiveExpEvent(killed, player, this, i));

            return i;
        }

        @Override
        public int GiveExp(PlayerEntity player, int i) {

            setExp(exp + i);

            if (exp > this.getExpRequiredForLevelUp()) {

                if (this.CheckIfCanLevelUp() && this.CheckLevelCap()) {
                    this.LevelUp(player);
                }

                return i;
            }
            return i;
        }

        @Override
        public boolean CheckIfCanLevelUp() {

            return getExp() >= getExpRequiredForLevelUp();

        }

        public int getRemainingExp() {
            int num = getExp() - getExpRequiredForLevelUp();

            if (num < 0) {
                num = 0;
            }
            return num;
        }

        @Override
        public boolean CheckLevelCap() {
            return getLevel() + 1 <= ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get();
        }

        @Override
        public boolean LevelUp(PlayerEntity player) {

            if (!CheckIfCanLevelUp()) {
                player.sendMessage(Chats.Not_enough_experience.locName());
            } else if (!CheckLevelCap()) {
                player.sendMessage(Chats.Can_not_go_over_maximum_level.locName());
            }

            if (CheckIfCanLevelUp() && CheckLevelCap()) {

                this.setLevel(level + 1, player);
                setExp(getRemainingExp());
                player.sendMessage(
                    new StringTextComponent(TextFormatting.YELLOW + "" + TextFormatting.BOLD).appendSibling(
                        Chats.You_have_leveled_up.locName())
                        .appendText("!"));
                CriteriaRegisters.PLAYER_LEVEL_TRIGGER.trigger((ServerPlayerEntity) player, this);

                onLvlPostMsg(player);

                try {
                    Load.playersCapBackup(MapManager.getWorld(DimensionType.OVERWORLD))
                        .getBackup()
                        .backup((ServerPlayerEntity) player, this);
                } catch (Exception e) {
                    //  e.printStackTrace();
                }

                return true;
            }
            return false;
        }

        public void onLvlPostMsg(LivingEntity en) {

            int talents = Load.talents((PlayerEntity) en)
                .getFreePoints(this);
            int spells = Load.spells((PlayerEntity) en)
                .getFreePoints(this);
            int stats = Load.statPoints((PlayerEntity) en)
                .getAvailablePoints(this);

            int total = talents + spells + stats;

            if (total > 0) {
                ITextComponent msg = new StringTextComponent(
                    TextFormatting.BLUE + "You have " + total + " Unspent Level up points." + TextFormatting.ITALIC + " Click to Open Main Hub [Default key: (H)]");
                msg.setStyle(msg.getStyle()
                    .setClickEvent(
                        new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + OpenHub.COMMAND)));
                en.sendMessage(msg);

            }

        }

        @Override
        public int getLevel() {

            return level;

        }

        @Override
        public void setLevel(int lvl, LivingEntity entity) {

            level = MathHelper.clamp(lvl, 1, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get());

            this.equipsChanged = true;
            this.shouldSync = true;

            if (entity instanceof ServerPlayerEntity) {
                CriteriaRegisters.PLAYER_LEVEL_TRIGGER.trigger((ServerPlayerEntity) entity, this);
            }
        }

        @Override
        public int getExp() {
            return exp;
        }

        @Override
        public void setExp(int exp) {
            this.exp = exp;
        }

        @Override
        public void onAttackEntity(LivingEntity attacker, LivingEntity victim) {

        }

        @Override
        public void modifyResource(ResourcesData.Context ctx) {
            this.resources.modify(ctx);
        }

        @Override
        public void onDeath(LivingEntity en) {

            int expLoss = (int) (exp * ModConfig.INSTANCE.Server.XP_LOSS_ON_DEATH.get());

            if (expLoss > 0) {
                this.exp = MathHelper.clamp(exp - expLoss, 0, Integer.MAX_VALUE);
            }
        }

        @Override
        public void setType(LivingEntity en) {
            this.type = EntityTypeUtils.getType(en);
        }

        @Override
        public EntityTypeUtils.EntityType getType() {
            return this.type;
        }

        @Override
        public void trySync(LivingEntity entity) {
            if (this.shouldSync) {
                this.shouldSync = false;
                MMORPG.sendToTracking(new EntityUnitPacket(entity), entity);
            }

        }

        @Override
        public PlayerCaps getCapType() {
            return PlayerCaps.ENTITY_DATA;
        }

        @Override
        public Unit getUnit() {
            return unit;
        }

        @Override
        public void setUnit(Unit unit, LivingEntity entity) {
            this.unit = unit;
        }

        @Override
        public void setRarity(int rarity) {
            this.rarity = rarity;
            this.equipsChanged = true;

        }

        @Override
        public int getRarity() {
            return MathHelper.clamp(rarity, -2, IRarity.Highest);
        }

        @Override
        public String getUUID() {
            return uuid;
        }

        @Override
        public void setUUID(UUID id) {
            uuid = id.toString();
        }

        @Override
        public ITextComponent getName(LivingEntity entity) {

            if (entity instanceof PlayerEntity) {
                return new StringTextComponent("[Lv:").appendText(this.getLevel() + "] " + " ")
                    .appendSibling(entity.getDisplayName());

            } else {
                MobRarity rarity = Rarities.Mobs.get(getRarity());
                ITextComponent rarityprefix = rarity.locName();

                BossCap.IBossData boss = Load.boss(entity);

                ITextComponent name = boss.isBoss() ? boss.getBoss()
                    .getNameFor(entity) : entity.getDisplayName();

                ITextComponent lvlcomp = Styles.YELLOWCOMP()
                    .appendSibling(new StringTextComponent("[Lv:" + this.getLevel() + "] "));

                ITextComponent suffix = new StringTextComponent(rarity.textFormatting() + "").appendSibling(
                    rarityprefix.appendText(" ")
                        .appendSibling(name));

                return lvlcomp.appendSibling(suffix);

            }
        }

        @Override
        public void tryRecalculateStats(LivingEntity entity) {

            if (unit == null) {
                unit = new Unit();
            }

            if (needsToRecalcStats()) {
                unit.recalculateStats(entity, this, level, null);
            }

        }

        @Override
        public void forceRecalculateStats(LivingEntity entity, DamageEventData data) {
            unit.recalculateStats(entity, this, level, data);
        }

        @Override
        public void forceRecalculateStats(LivingEntity entity) {

            if (unit == null) {
                unit = new Unit();
            }
            unit.recalculateStats(entity, this, level, null);
        }

        // This reduces stat calculation by about 4 TIMES!
        private boolean needsToRecalcStats() {

            return this.equipsChanged;
        }

        @Override
        public void forceSetUnit(Unit unit) {
            this.unit = unit;
        }

        @Override
        public GearItemData setupWeaponData(LivingEntity entity) {
            return Gear.Load(entity.getHeldItemMainhand());
        }

        @Override
        public int getLastHitTicksExisted() {
            return this.lastHitTicks;
        }

        @Override
        public void setAttackCooldown(PlayerEntity player) {
            this.lastHitCooldownStr = player.getCooledAttackStrength(0.5F);
            this.lastHitTicks = player.ticksExisted;
        }

        @Override
        public float getAttackCooldown() {
            return this.lastHitCooldownStr;
        }

        @Override
        public boolean isAttackCooldownInSweepRange() {
            return getAttackCooldown() > 0.9F;
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, LivingEntity source) {
            return tryUseWeapon(weaponData, source, 1);
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, LivingEntity source, float multi) {

            try {

                if (weaponData != null) {

                    GearItemSlot slot = weaponData.GetBaseGearType();

                    float energyCost = slot.getSwingCosts()
                        .GetEnergyCost(getLvlForResourceCosts()) * multi;

                    float manaCost = slot.getSwingCosts()
                        .GetManaCost(getLvlForResourceCosts()) * multi;

                    float cooldown = getAttackCooldown();

                    energyCost *= cooldown;
                    manaCost *= cooldown;

                    // if player is using attack cooldown nicely, dont add any penalty
                    if (!isAttackCooldownInSweepRange()) {
                        float penalty = 1.2F;
                        energyCost *= penalty;
                        manaCost *= penalty;
                    }

                    ResourcesData.Context ene = new ResourcesData.Context(
                        this, source, ResourcesData.Type.ENERGY, energyCost, ResourcesData.Use.SPEND);
                    ResourcesData.Context mana = new ResourcesData.Context(
                        this, source, ResourcesData.Type.MANA, manaCost, ResourcesData.Use.SPEND);

                    if (getResources().hasEnough(ene) == false) {
                        if (source instanceof ServerPlayerEntity) {
                            MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) source);
                        }
                        return false;

                    } else {

                        if (getResources().hasEnough(mana) == false) {
                            if (source instanceof ServerPlayerEntity) {
                                MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) source);
                            }
                            return false;
                        }

                        getResources().modify(ene);
                        getResources().modify(mana);

                        return true;

                    }

                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void onLogin(PlayerEntity player) {

            try {

                if (unit == null) {
                    unit = new Unit();
                }
                unit.removeUnregisteredStats();

                // check if newbie
                if (isNewbie()) {
                    setNewbieStatus(false);
                    if (ModConfig.INSTANCE.Server.GET_STARTER_ITEMS.get()) {
                        OnLogin.GiveStarterItems(player);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean increaseRarity(LivingEntity entity) {

            if (rarity == IRarity.Highest) {
                return false;
            } else {
                rarity = rarity + 1;
                this.shouldSync = true;
                return true;

            }
        }

        @Override
        public boolean decreaseRarity(LivingEntity entity) {

            if (rarity - 1 < 0) {
                return false;
            } else {
                rarity = rarity - 1;
                this.shouldSync = true;
                return true;

            }
        }

        @Override
        public String getCurrentMapId() {
            return this.currentMapResourceLoc;
        }

        @Override
        public void setCurrentMapId(String id) {
            this.currentMapResourceLoc = id;
        }

        @Override
        public boolean hasCurrentMapId() {
            return this.currentMapResourceLoc.isEmpty() == false;
        }

        @Override
        public void clearCurrentMapId() {
            this.currentMapResourceLoc = "";
        }

        @Override
        public boolean isWeapon(GearItemData gear) {
            try {

                if (gear == null) {
                    return false;
                }
                if (gear.GetBaseGearType()
                    .slotType()
                    .equals(GearItemSlot.GearSlotType.Weapon)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void setTier(int tier) {
            this.tier = tier;
        }

        @Override
        public int getTier() {
            return tier;
        }

        @Override
        public Tier getMapTier() {
            return SlashRegistry.Tiers()
                .get(this.tier + "");
        }

        @Override
        public CustomStatsData getCustomStats() {
            return this.customStats;
        }

        @Override
        public CustomExactStatsData getCustomExactStats() {
            return this.customExactStats;
        }

        @Override
        public ResourcesData getResources() {
            return this.resources;
        }

        @Override
        public float getCurrentEnergy() {
            return this.resources.getEnergy();
        }

        @Override
        public float getCurrentMana() {
            return this.resources.getMana();
        }

        @Override
        public int getLvlForResourceCosts() {

            return this.getLevel();

        }

        @Override
        public void attackWithWeapon(DamageEventData data) {
            if (data.weaponData.GetBaseGearType()
                .getWeaponMechanic() != null) {

                if (data.weapon != null) {
                    data.weapon.attemptDamageItem(1, new Random(), null);
                }

                data.weaponData.GetBaseGearType()
                    .getWeaponMechanic()
                    .attack(data);

            }
        }

        @Override
        public void unarmedAttack(DamageEventData data) {
            float cost = ModConfig.INSTANCE.Server.UNARMED_ENERGY_COST.get()
                .floatValue();

            cost = Energy.getInstance()
                .calculateScalingStatGrowth(cost, getLvlForResourceCosts());

            ResourcesData.Context energy = new ResourcesData.Context(
                this, data.source, ResourcesData.Type.ENERGY, cost, ResourcesData.Use.SPEND);

            if (this.getResources()
                .hasEnough(energy)) {
                this.getResources()
                    .modify(energy);
                int num = (int) unit.getCreateStat(PhysicalDamage.GUID)
                    .getRandomRangeValue();
                DamageEffect dmg = new DamageEffect(
                    data, num, EffectData.EffectTypes.NORMAL, WeaponTypes.None);

                dmg.Activate();
            }
        }

        @Override
        public void mobBasicAttack(DamageEventData data) {
            MobRarity rar = Rarities.Mobs.get(data.sourceData.getRarity());

            float vanilla = PhysicalDamage.getInstance()
                .getScaling()
                .scale(data.getEventDamage(), data.sourceData.getLevel());

            float num = vanilla * rar.DamageMultiplier() * getMapTier().mob_damage_multi;

            num *= SlashRegistry.getEntityConfig(data.source, data.sourceData).DMG_MULTI;

            DamageEffect dmg = new DamageEffect(
                data, (int) num, EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.None
            );

            dmg.Activate();

        }

        @Override
        public void freelySetLevel(int lvl) {
            this.level = lvl;
        }

        @Override
        public boolean isNewbie() {
            return isNewbie;
        }

        @Override
        public void setNewbieStatus(boolean bool) {
            isNewbie = bool;
        }

        @Override
        public boolean needsToBeGivenStats() {
            return this.setMobStats == false;
        }

    }

    public static class Storage extends BaseStorage<UnitData> {

    }

}
