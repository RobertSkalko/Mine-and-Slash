package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.commands.OpenPickStatsGui;
import com.robertx22.mine_and_slash.commands.OpenTalentsGui;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.stat_types.misc.BonusExp;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.CriteriaRegisters;
import com.robertx22.mine_and_slash.network.EntityUnitPacket;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.network.sync_cap.SyncCapabilityToClient;
import com.robertx22.mine_and_slash.onevent.player.OnLogin;
import com.robertx22.mine_and_slash.saveclasses.CustomExactStatsData;
import com.robertx22.mine_and_slash.saveclasses.CustomStatsData;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.*;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.AttackUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.LevelUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
    private static final String DMG_DONE_BY_NON_PLAYERS = "DMG_DONE_BY_NON_PLAYERS";
    private static final String EQUIPS_CHANGED = "EQUIPS_CHANGED";
    private static final String TIER = "TIER";
    private static final String PREVENT_LOOT = "PREVENT_LOOT";
    private static final String SHOULD_SYNC = "SHOULD_SYNC";
    private static final String ENTITY_TYPE = "ENTITY_TYPE";
    private static final String RESOURCES_LOC = "RESOURCES_LOC";

    public interface UnitData extends ICommonCapability {

        void modifyResource(ResourcesData.Context ctx);

        void onDeath(LivingEntity en);

        void setType(LivingEntity en);

        EntityTypeUtils.EntityType getType();

        void trySync(LivingEntity entity);

        void onAttackEntity(LivingEntity attacker, LivingEntity victim);

        void syncToClient(PlayerEntity player);

        GearItemData getWeaponData(LivingEntity entity);

        void setEquipsChanged(boolean bool);

        void onDamagedByNonPlayer(LivingEntity entity, float dmg);

        boolean shouldDropLoot();

        int PostGiveExpEvent(LivingEntity killed, PlayerEntity player, int exp);

        boolean isNewbie();

        void setNewbieStatus(boolean bool);

        boolean needsToBeGivenStats();

        void freelySetLevel(int lvl);

        int getLevel();

        void mobBasicAttack(LivingHurtEvent event, LivingEntity source,
                            LivingEntity target, UnitData sourcedata, UnitData targetdata,
                            float event_damage);

        void setLevel(int lvl, LivingEntity entity);

        boolean increaseRarity(LivingEntity entity);

        int getExp();

        void setExp(int exp);

        int GiveExp(PlayerEntity player, int i);

        int GetExpRequiredForLevelUp();

        boolean CheckIfCanLevelUp();

        boolean LevelUp(PlayerEntity player);

        boolean CheckLevelCap();

        void SetMobLevelAtSpawn(LivingEntity entity, PlayerEntity player);

        Unit getUnit();

        void setUnit(Unit unit, LivingEntity entity);

        void setRarity(int rarity);

        int getRarity();

        String getUUID();

        void setUUID(UUID id);

        ITextComponent getName(LivingEntity entity);

        void HandleCloneEvent(UnitData old);

        void recalculateStats(LivingEntity entity);

        void forceRecalculateStats(LivingEntity entity);

        void forceSetUnit(Unit unit);

        boolean tryUseWeapon(GearItemData gear, LivingEntity entity);

        boolean tryUseWeapon(GearItemData gear, LivingEntity entity, float multi);

        void attackWithWeapon(LivingHurtEvent event, ItemStack weapon, GearItemData gear,
                              LivingEntity source, LivingEntity target,
                              UnitData targetdata);

        void onLogin(PlayerEntity player);

        String getCurrentMapId();

        void setCurrentMapId(String res);

        boolean hasCurrentMapId();

        void clearCurrentMapId();

        void unarmedAttack(LivingHurtEvent event, LivingEntity source,
                           LivingEntity target, UnitData targetdata);

        boolean decreaseRarity(LivingEntity entity);

        boolean isWeapon(GearItemData gear);

        void setTier(int tier);

        int getTier();

        float getStatMultiplierIncreaseByTier();

        float getDMGMultiplierIncreaseByTier();

        CustomStatsData getCustomStats();

        CustomExactStatsData getCustomExactStats();

        ResourcesData getResources();

        float getCurrentEnergy();

        float getCurrentMana();
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

        boolean setMobStats = false;
        Unit unit = null;
        int level = 1;
        int exp = 0;
        int rarity = 0;
        String uuid = "";
        String currentMapResourceLoc = "";
        boolean isNewbie = true;
        boolean equipsChanged = true;
        int tier = 0;
        boolean preventLoot = false;
        boolean shouldSync = false;
        EntityTypeUtils.EntityType type = EntityTypeUtils.EntityType.PLAYER;

        float dmgByNonPlayers = 0;

        ResourcesData resources = new ResourcesData();

        CustomStatsData customStats = new CustomStatsData();
        CustomExactStatsData customExactStats = new CustomExactStatsData();

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();

            nbt.putFloat(DMG_DONE_BY_NON_PLAYERS, dmgByNonPlayers);
            nbt.putInt(LEVEL, level);
            nbt.putInt(EXP, exp);
            nbt.putInt(RARITY, rarity);
            nbt.putInt(TIER, tier);
            nbt.putString(UUID, uuid);
            nbt.putBoolean(MOB_SAVED_ONCE, true);
            nbt.putString(CURRENT_MAP_ID, currentMapResourceLoc);
            nbt.putBoolean(SET_MOB_STATS, setMobStats);
            nbt.putBoolean(NEWBIE_STATUS, this.isNewbie);
            nbt.putBoolean(EQUIPS_CHANGED, equipsChanged);
            nbt.putBoolean(PREVENT_LOOT, preventLoot);
            nbt.putBoolean(SHOULD_SYNC, shouldSync);
            nbt.putString(ENTITY_TYPE, this.type.toString());

            if (customStats != null) {
                CustomStats.Save(nbt, customStats);
            }

            if (customExactStats != null) {
                CustomExactStats.Save(nbt, customExactStats);
            }

            if (unit != null) {
                UnitNbt.Save(nbt, unit);
            }

            if (resources != null) {
                LoadSave.Save(resources, nbt, RESOURCES_LOC);
            }
            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {

            this.level = nbt.getInt(LEVEL);
            this.exp = nbt.getInt(EXP);
            this.rarity = nbt.getInt(RARITY);
            this.tier = nbt.getInt(TIER);
            this.uuid = nbt.getString(UUID);
            this.dmgByNonPlayers = nbt.getFloat(DMG_DONE_BY_NON_PLAYERS);
            this.currentMapResourceLoc = nbt.getString(CURRENT_MAP_ID);
            this.setMobStats = nbt.getBoolean(SET_MOB_STATS);
            this.isNewbie = nbt.getBoolean(NEWBIE_STATUS);
            this.equipsChanged = nbt.getBoolean(EQUIPS_CHANGED);
            this.preventLoot = nbt.getBoolean(PREVENT_LOOT);
            this.shouldSync = nbt.getBoolean(SHOULD_SYNC);

            try {
                this.resources = LoadSave.Load(ResourcesData.class, new ResourcesData(), nbt, RESOURCES_LOC);
                if (resources == null) {
                    resources = new ResourcesData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String typestring = nbt.getString(ENTITY_TYPE);
                this.type = EntityTypeUtils.EntityType.valueOf(typestring);
            } catch (Exception e) {
                this.type = EntityTypeUtils.EntityType.OTHER;
                //if no nbt, set to default. Then at spawn, set correctly
            }

            this.customStats = CustomStats.Load(nbt);
            if (this.customStats == null) {
                this.customStats = new CustomStatsData();
            }

            this.customExactStats = CustomExactStats.Load(nbt);
            if (this.customExactStats == null) {
                this.customExactStats = new CustomExactStatsData();
            }

            this.unit = UnitNbt.Load(nbt);
            if (this.unit == null) {
                this.unit = new Unit();
            }

        }

        @Override
        public void mobBasicAttack(LivingHurtEvent event, LivingEntity source,
                                   LivingEntity target, UnitData sourcedata,
                                   UnitData targetdata, float event_damage) {

            MobRarity rar = Rarities.Mobs.get(sourcedata.getRarity());

            float vanilla = event_damage * sourcedata.getLevel() * sourcedata.getDMGMultiplierIncreaseByTier();

            float num = 1.1F * vanilla * rar.DamageMultiplier();

            num *= SlashRegistry.getEntityConfig(source, sourcedata).DMG_MULTI;

            DamageEffect dmg = new DamageEffect(event, source, target, (int) num, sourcedata, targetdata, EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.None);

            dmg.Activate();

        }

        @Override
        public int GetExpRequiredForLevelUp() {
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

        public static int equateXp(double xp) {
            return (int) Math.floor(xp + 340 * Math.pow(2, xp / 9));
        }

        public static int levelToExp(int level) {
            double xp = 0;

            for (int i = 1; i < level; i++)
                xp += equateXp(i);

            return (int) Math.floor(xp / 4);
        }

        @Override
        public void SetMobLevelAtSpawn(LivingEntity entity, PlayerEntity player) {
            this.setMobStats = true;

            if (WorldUtils.isMapWorldClass(entity.world)) {
                if (player != null) {
                    this.level = Load.playerMapData(player).getLevel();
                } else {
                    setMobLvlNormally(entity);
                }

            } else {
                setMobLvlNormally(entity);
            }
        }

        private void setMobLvlNormally(LivingEntity entity) {
            ModEntityConfig entityConfig = SlashRegistry.getEntityConfig(entity, this);
            int lvl = LevelUtils.determineLevel(entity.world, entity.getPosition()) + entityConfig.LEVEL_MODIFIER;
            this.level = MathHelper.clamp(lvl, entityConfig.MIN_LEVEL, entityConfig.MAX_LEVEL);
        }

        @Override
        public void setEquipsChanged(boolean bool) {
            this.equipsChanged = bool;
        }

        @Override
        public void onDamagedByNonPlayer(LivingEntity entity, float dmg) {

            this.dmgByNonPlayers += dmg;

            if (this.preventLoot == false && this.shouldDropLoot(entity) == false) {
                this.preventLoot = true;
                this.shouldSync = true;
            }

        }

        @Override
        public boolean shouldDropLoot() {
            return this.preventLoot == false;
        }

        private boolean shouldDropLoot(LivingEntity entity) {

            if (entity.getMaxHealth() * ModConfig.INSTANCE.Server.STOP_DROPS_IF_NON_PLAYER_DOES_DMG_PERCENT
                    .get() >= this.dmgByNonPlayers) {
                return true;
            }

            return false;
        }

        @Override
        public int PostGiveExpEvent(LivingEntity killed, PlayerEntity player, int i) {

            i *= ModConfig.INSTANCE.Server.EXPERIENCE_MULTIPLIER.get();

            i *= (double) this.getUnit().getStat(BonusExp.GUID).Value / 100 + 1;

            MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.GiveExpEvent(killed, player, this, i));

            return i;
        }

        @Override
        public int GiveExp(PlayerEntity player, int i) {

            setExp(exp + i);

            if (exp > this.GetExpRequiredForLevelUp()) {

                if (ModConfig.INSTANCE.Server.LEVEL_UPS_COST_TOKEN.get() == false) {

                    if (this.CheckIfCanLevelUp() && this.CheckLevelCap()) {
                        this.LevelUp(player);
                    }
                }

                return i;
            }
            return i;
        }

        @Override
        public boolean CheckIfCanLevelUp() {

            return getExp() >= GetExpRequiredForLevelUp();

        }

        public int getRemainingExp() {
            int num = getExp() - GetExpRequiredForLevelUp();

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
                player.sendMessage(new StringTextComponent(TextFormatting.YELLOW + "" + TextFormatting.BOLD)
                        .appendSibling(Chats.You_have_leveled_up.locName())
                        .appendText("!"));
                CriteriaRegisters.PLAYER_LEVEL_TRIGGER.trigger((ServerPlayerEntity) player, this);
                onLvlPostStatPickMsg(player);
                onLvlPostTalentsMsg(player);

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

        public void onLvlPostTalentsMsg(LivingEntity en) {

            int points = Load.talents((PlayerEntity) en).getFreePoints(this);

            if (points > 0) {
                ITextComponent msg = new StringTextComponent(TextFormatting.BLUE + "You have " + points + " Unspent Talent points." + TextFormatting.ITALIC + " Click to Open Talents");
                msg.setStyle(msg.getStyle()
                        .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + OpenTalentsGui.COMMAND)));
                en.sendMessage(msg);
            }
        }

        public void onLvlPostStatPickMsg(LivingEntity en) {

            int points = Load.statPoints((PlayerEntity) en).getAvailablePoints(this);

            if (points > 0) {
                ITextComponent msg = new StringTextComponent(TextFormatting.GREEN + "You have " + points + " Unspent Stat points." + TextFormatting.ITALIC + " Click to Open Gui");
                msg.setStyle(msg.getStyle()
                        .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + OpenPickStatsGui.COMMAND)));
                en.sendMessage(msg);
            }
        }

        @Override
        public int getLevel() {

            return level;

        }

        @Override
        public void setLevel(int lvl, LivingEntity entity) {

            level = MathHelper.clamp(lvl, 1, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                    .get());

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
        public void syncToClient(PlayerEntity player) {
            if (unit != null) {
                ServerPlayerEntity mp = (ServerPlayerEntity) player;
                SyncCapabilityToClient packet = new SyncCapabilityToClient(mp, CapTypes.ENTITY_DATA);
                MMORPG.sendToClient(packet, mp);
            }
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
            return rarity;
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
                ITextComponent name = entity.getDisplayName();

                ITextComponent lvlcomp = Styles.YELLOWCOMP()
                        .appendSibling(new StringTextComponent("[Lv:" + this.getLevel() + "] "));

                ITextComponent suffix = new StringTextComponent(rarity.textFormatColor() + "")
                        .appendSibling(rarityprefix.appendText(" ").appendSibling(name));

                return lvlcomp.appendSibling(suffix);

            }
        }

        @Override
        public void HandleCloneEvent(UnitData old) {
            this.setNBT(old.getNBT());
        }

        @Override
        public void recalculateStats(LivingEntity entity) {

            if (unit == null) {
                unit = new Unit();
            }

            if (needsToRecalcStats()) {
                unit.RecalculateStats(entity, this, level);
            }

        }

        @Override
        public void forceRecalculateStats(LivingEntity entity) {

            if (unit == null) {
                unit = new Unit();
            }
            unit.RecalculateStats(entity, this, level);
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
        public GearItemData getWeaponData(LivingEntity entity) {
            return Gear.Load(entity.getHeldItemMainhand());
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, LivingEntity source) {
            return tryUseWeapon(weaponData, source, 1);
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, LivingEntity source,
                                    float multi) {

            try {

                if (weaponData != null && weaponData.GetBaseGearType() instanceof IWeapon) {

                    IWeapon iwep = (IWeapon) weaponData.GetBaseGearType();

                    float energyCost = iwep.mechanic().GetEnergyCost() * multi;
                    float manaCost = iwep.mechanic().GetManaCost(getLevel()) * multi;

                    ResourcesData.Context ene = new ResourcesData.Context(this, source, ResourcesData.Type.ENERGY, energyCost, ResourcesData.Use.SPEND);
                    ResourcesData.Context mana = new ResourcesData.Context(this, source, ResourcesData.Type.MANA, manaCost, ResourcesData.Use.SPEND);

                    if (getResources().hasEnough(ene) == false) {
                        AttackUtils.NoEnergyMessage(source);
                        return false;

                    } else {

                        if (getResources().hasEnough(mana) == false) {
                            AttackUtils.NoEnergyMessage(source);
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
        public void attackWithWeapon(LivingHurtEvent event, ItemStack weapon,
                                     GearItemData weaponData, LivingEntity source,
                                     LivingEntity target, UnitData targetdata) {

            if (weaponData.GetBaseGearType() instanceof IWeapon) {

                if (weapon != null) {
                    weapon.attemptDamageItem(1, new Random(), null);
                }

                IWeapon iwep = (IWeapon) weaponData.GetBaseGearType();
                WeaponMechanic iWep = iwep.mechanic();
                iWep.Attack(event, source, target, this, targetdata);

            }
        }

        @Override
        public void onLogin(PlayerEntity player) {

            try {

                if (unit == null) {
                    unit = new Unit();
                }
                unit.InitPlayerStats();

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

            if (rarity == 5) {
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
        public void unarmedAttack(LivingHurtEvent event, LivingEntity source,
                                  LivingEntity target, UnitData targetdata) {

            float cost = ModConfig.INSTANCE.Server.UNARMED_ENERGY_COST.get().floatValue();

            ResourcesData.Context energy = new ResourcesData.Context(this, source, ResourcesData.Type.ENERGY, cost, ResourcesData.Use.SPEND);

            if (this.getResources().hasEnough(energy)) {
                this.getResources().modify(energy);
                int num = (int) unit.getStat(PhysicalDamage.GUID).Value;
                DamageEffect dmg = new DamageEffect(event, source, target, num, this, targetdata, EffectData.EffectTypes.NORMAL, WeaponTypes.None);

                dmg.Activate();
            }
        }

        @Override
        public boolean isWeapon(GearItemData gear) {
            try {

                if (gear == null) {
                    return false;
                }
                if (gear.GetBaseGearType() instanceof IWeapon) {
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
        public float getStatMultiplierIncreaseByTier() {
            return 1 + tier * 0.13F;
        }

        @Override
        public float getDMGMultiplierIncreaseByTier() {
            return 1 + tier * 0.2F;
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
