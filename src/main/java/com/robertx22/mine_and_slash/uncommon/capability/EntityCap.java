package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigs;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.stats.stat_types.misc.BonusExp;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.CriteriaRegisters;
import com.robertx22.mine_and_slash.network.PlayerUnitPacket;
import com.robertx22.mine_and_slash.onevent.player.OnLogin;
import com.robertx22.mine_and_slash.saveclasses.CustomStatsData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.CustomStats;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.UnitNbt;
import com.robertx22.mine_and_slash.uncommon.effectdatas.*;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.AttackUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.LevelUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;
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
    private static final String MANA = "current_mana";
    private static final String ENERGY = "current_energy";
    private static final String CURRENT_MAP_ID = "current_map_resource_loc";
    private static final String SET_MOB_STATS = "set_mob_stats";
    private static final String NEWBIE_STATUS = "is_a_newbie";
    private static final String DMG_DONE_BY_NON_PLAYERS = "DMG_DONE_BY_NON_PLAYERS";
    private static final String EQUIPS_CHANGED = "EQUIPS_CHANGED";
    private static final String TIER = "TIER";
    private static final String LAST_ATTACK_TICK = "LAST_ATTACK_TICK";

    public interface UnitData extends ICommonCapability {
        int getTicksSinceLastAttack(LivingEntity entity);

        void onAttackEntity(LivingEntity attacker, LivingEntity victim);

        boolean attackCooldownAllowsAttack(float amount, LivingEntity attacker,
                                           LivingEntity victim, GearItemData gear);

        void syncToClient(PlayerEntity player);

        GearItemData getWeaponData(LivingEntity entity);

        void setEquipsChanged(boolean bool);

        void onDamagedByNonPlayer(float dmg);

        boolean shouldDropLoot(LivingEntity entity);

        int PostGiveExpEvent(LivingEntity killed, PlayerEntity player, int exp);

        boolean isNewbie();

        void setNewbieStatus(boolean bool);

        boolean needsToBeGivenStats();

        void freelySetLevel(int lvl);

        int getLevel();

        void mobBasicAttack(LivingEntity source, LivingEntity target, UnitData sourcedata,
                            UnitData targetdata, float event_damage);

        void setLevel(int lvl, LivingEntity entity);

        boolean increaseRarity(LivingEntity entity);

        int getExp();

        void setExp(int exp);

        int GiveExp(PlayerEntity player, int i);

        int GetExpRequiredForLevelUp();

        boolean CheckIfCanLevelUp();

        boolean LevelUp(PlayerEntity player);

        boolean CheckLevelCap();

        void SetMobLevelAtSpawn(LivingEntity entity);

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

        void attackWithWeapon(ItemStack weapon, GearItemData gear, LivingEntity source,
                              LivingEntity target, UnitData targetdata);

        void onLogin(PlayerEntity player);

        float getCurrentMana();

        float getCurrentEnergy();

        void setCurrentEnergy(float i);

        void setCurrentMana(float i);

        boolean hasEnoughMana(float i);

        boolean hasEnoughEnergy(float i);

        void restoreMana(float i);

        void restoreEnergy(float i);

        void consumeMana(float i);

        void consumeEnergy(float i);

        void heal(HealData data);

        String getCurrentMapId();

        void setCurrentMapId(String res);

        boolean hasCurrentMapId();

        void clearCurrentMapId();

        void unarmedAttack(LivingEntity source, LivingEntity target, UnitData targetdata);

        boolean decreaseRarity(LivingEntity entity);

        boolean isWeapon(GearItemData gear);

        void setTier(int tier);

        int getTier();

        float getStatMultiplierIncreaseByTier();

        float getDMGMultiplierIncreaseByTier();

        CustomStatsData getCustomStats();
    }

    @EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

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
        int lastAttackTick = 0;

        float dmgByNonPlayers = 0;

        float energy;
        float mana;

        CustomStatsData customStats = new CustomStatsData();

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();

            nbt.putFloat(MANA, mana);
            nbt.putFloat(ENERGY, energy);
            nbt.putFloat(DMG_DONE_BY_NON_PLAYERS, dmgByNonPlayers);
            nbt.putInt(LEVEL, level);
            nbt.putInt(EXP, exp);
            nbt.putInt(RARITY, rarity);
            nbt.putInt(TIER, tier);
            nbt.putInt(LAST_ATTACK_TICK, lastAttackTick);
            nbt.putString(UUID, uuid);
            nbt.putBoolean(MOB_SAVED_ONCE, true);
            nbt.putString(CURRENT_MAP_ID, currentMapResourceLoc);
            nbt.putBoolean(SET_MOB_STATS, setMobStats);
            nbt.putBoolean(NEWBIE_STATUS, this.isNewbie);
            nbt.putBoolean(EQUIPS_CHANGED, equipsChanged);

            if (customStats != null) {
                CustomStats.Save(nbt, customStats);
            }

            if (unit != null) {
                UnitNbt.Save(nbt, unit);
            }

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {

            this.level = nbt.getInt(LEVEL);
            this.exp = nbt.getInt(EXP);
            this.rarity = nbt.getInt(RARITY);
            this.tier = nbt.getInt(TIER);
            this.lastAttackTick = nbt.getInt(LAST_ATTACK_TICK);
            this.uuid = nbt.getString(UUID);
            this.energy = nbt.getFloat(ENERGY);
            this.dmgByNonPlayers = nbt.getFloat(DMG_DONE_BY_NON_PLAYERS);
            this.mana = nbt.getFloat(MANA);
            this.currentMapResourceLoc = nbt.getString(CURRENT_MAP_ID);
            this.setMobStats = nbt.getBoolean(SET_MOB_STATS);
            this.isNewbie = nbt.getBoolean(NEWBIE_STATUS);
            this.equipsChanged = nbt.getBoolean(EQUIPS_CHANGED);

            CustomStatsData newstats = CustomStats.Load(nbt);
            if (newstats != null) {
                this.customStats = newstats;
            } else {
                this.customStats = new CustomStatsData();
            }

            Unit newunit = UnitNbt.Load(nbt);
            if (newunit != null) {
                this.unit = newunit;
            } else {
                this.unit = new Unit();
            }

        }

        @Override
        public void mobBasicAttack(LivingEntity source, LivingEntity target,
                                   UnitData sourcedata, UnitData targetdata,
                                   float event_damage) {

            MobRarity rar = Rarities.Mobs.get(sourcedata.getRarity());

            float vanilla = event_damage * sourcedata.getLevel() * sourcedata.getDMGMultiplierIncreaseByTier();

            float num = 1.1F * vanilla * rar.DamageMultiplier();

            num *= ModEntityConfigs.INSTANCE.getConfig(source).DMG_MULTI;

            DamageEffect dmg = new DamageEffect(source, target, (int) num, sourcedata, targetdata, EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.None);

            dmg.Activate();

        }

        public static float nullifyVanillaArmor(LivingEntity target, float damage) {

            float armorValue = (float) target.getTotalArmorValue();
            float armorToughness = (float) target.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS)
                    .getValue();

            float lvt_3_1_ = 2.0F + armorToughness / 4.0F;
            float lvt_4_1_ = MathHelper.clamp(armorValue - damage / lvt_3_1_, armorValue * 0.2F, 20.0F);
            return damage / (1.0F - lvt_4_1_ / 25.0F);
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
        public void SetMobLevelAtSpawn(LivingEntity entity) {

            this.setMobStats = true;
            ModEntityConfig config = ModEntityConfigs.INSTANCE.getConfig(entity);

            int lvl = LevelUtils.determineLevel(entity.world, entity.getPosition()) + config.LEVEL_MODIFIER;

            this.level = MathHelper.clamp(lvl, config.MIN_LEVEL, config.MAX_LEVEL);

        }

        @Override
        public void setEquipsChanged(boolean bool) {
            this.equipsChanged = bool;
        }

        @Override
        public void onDamagedByNonPlayer(float dmg) {

            this.dmgByNonPlayers += dmg;

        }

        @Override
        public boolean shouldDropLoot(LivingEntity entity) {

            if (entity.getMaxHealth() * ModConfig.INSTANCE.Server.STOP_DROPS_IF_NON_PLAYER_DOES_DMG_PERCENT
                    .get() >= this.dmgByNonPlayers) {
                return true;
            }

            return false;
        }

        @Override
        public int PostGiveExpEvent(LivingEntity killed, PlayerEntity player, int i) {

            i *= ModConfig.INSTANCE.Server.EXPERIENCE_MULTIPLIER.get();

            i *= (double) this.getUnit().getStat(new BonusExp()).Value / 100 + 1;

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
                player.sendMessage(Chats.You_have_leveled_up.locName());
                CriteriaRegisters.PLAYER_LEVEL_TRIGGER.trigger((ServerPlayerEntity) player, this);

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

        @Override
        public int getLevel() {

            return level;

        }

        @Override
        public void setLevel(int lvl, LivingEntity entity) {

            level = MathHelper.clamp(lvl, 1, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                    .get());

            this.equipsChanged = true;

            CriteriaRegisters.PLAYER_LEVEL_TRIGGER.trigger((ServerPlayerEntity) entity, this);

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
            this.lastAttackTick = attacker.ticksExisted;
        }

        @Override
        public int getTicksSinceLastAttack(LivingEntity entity) {
            if (this.lastAttackTick == 0) {
                return 1000;
            }

            return MathHelper.clamp(entity.ticksExisted - this.lastAttackTick, 0, 1000);

        }

        public static float getCooldownPeriod(LivingEntity entity) {

            double atkSpeed = entity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED)
                    .getValue();

            return (float) (1.0D / atkSpeed * 20.0D);
        }

        /**
         * Returns the percentage of attack power available based on the cooldown (zero to one).
         */
        public static float getCooledAttackStrength(boolean useTicks, UnitData data,
                                                    LivingEntity entity,
                                                    float adjustTicks) {

            int lastAtk = 0;

            if (useTicks) {
                lastAtk = data.getTicksSinceLastAttack(entity);
            }

            float cooldownPeriod = getCooldownPeriod(entity);

            return MathHelper.clamp(((float) lastAtk + adjustTicks) / cooldownPeriod, 0.0F, 1.0F);
        }

        // makes sure hammers the aoe weapons arent machine guns
        @Override
        public boolean attackCooldownAllowsAttack(float amount, LivingEntity attacker,
                                                  LivingEntity victim,
                                                  GearItemData gear) {

            if (attacker instanceof PlayerEntity) {
                float cooled = getCooledAttackStrength(true, this, attacker, 0.5F);
                float base = getCooledAttackStrength(false, this, attacker, 0.5F);

                boolean canAtk = false;

                if (cooled >= 1 || Float.compare(cooled, base) == 0) {
                    canAtk = true;
                }

                if (canAtk) {
                    if ((float) victim.hurtResistantTime > 10.0F) {
                        if (amount <= attacker.lastDamage) {
                            return false;
                        }
                    }

                } else {
                    return false;
                }
            } else {
                if ((float) victim.hurtResistantTime > 10.0F) {
                    if (amount <= attacker.lastDamage) {
                        return false;
                    }
                }
            }

            return true;
        }

        @Override
        public void syncToClient(PlayerEntity player) {
            if (unit != null) {
                PlayerUnitPacket packet = new PlayerUnitPacket(this.getNBT());
                ServerPlayerEntity mp = (ServerPlayerEntity) player;
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

            try {

                if (weaponData != null && weaponData.GetBaseGearType() instanceof IWeapon) {

                    IWeapon iwep = (IWeapon) weaponData.GetBaseGearType();

                    float energyCost = iwep.mechanic().GetEnergyCost();

                    if (hasEnoughEnergy(energyCost) == false) {
                        AttackUtils.NoEnergyMessage(source);
                        return false;

                    } else {
                        consumeEnergy(energyCost);
                        //weapon.damageItem(1, source);

                        return true;

                    }

                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void attackWithWeapon(ItemStack weapon, GearItemData weaponData,
                                     LivingEntity source, LivingEntity target,
                                     UnitData targetdata) {

            if (weaponData.GetBaseGearType() instanceof IWeapon) {

                if (weapon != null) {
                    weapon.attemptDamageItem(1, new Random(), null);
                }

                IWeapon iwep = (IWeapon) weaponData.GetBaseGearType();
                WeaponMechanic iWep = iwep.mechanic();
                iWep.Attack(source, target, this, targetdata);

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
        public float getCurrentMana() {
            return mana;
        }

        @Override
        public float getCurrentEnergy() {
            return energy;
        }

        @Override
        public void setCurrentEnergy(float i) {
            energy = i;

        }

        @Override
        public void setCurrentMana(float i) {
            mana = i;

        }

        @Override
        public boolean hasEnoughMana(float i) {
            return mana >= i;
        }

        @Override
        public boolean hasEnoughEnergy(float i) {
            return energy >= i;
        }

        @Override
        public void restoreMana(float i) {
            float max = unit.manaData().Value;

            mana += i;
            if (mana > max) {
                mana = (int) max;
            }

        }

        @Override
        public void restoreEnergy(float i) {
            float max = unit.energyData().Value;

            energy += i;
            if (energy > max) {
                energy = (int) max;
            }

        }

        @Override
        public void consumeMana(float i) {
            mana -= i;
            if (mana < 0) {
                mana = 0;
            }

        }

        @Override
        public void consumeEnergy(float i) {
            energy -= i;
            if (energy < 0) {
                energy = 0;
            }

        }

        @Override
        public void heal(HealData data) {
            if (data.target.isAlive()) {

                if (data.spell != null) {
                    SpellHealEffect effect = new SpellHealEffect(data);
                    effect.Activate();

                } else {
                    HealEffect effect = new HealEffect(data);
                    effect.Activate();
                }
            }
        }

        @Override
        public boolean increaseRarity(LivingEntity entity) {

            if (rarity == 5) {
                return false;
            } else {
                rarity = rarity + 1;

                return true;

            }
        }

        @Override
        public boolean decreaseRarity(LivingEntity entity) {

            if (rarity - 1 < 0) {
                return false;
            } else {
                rarity = rarity - 1;

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
        public void unarmedAttack(LivingEntity source, LivingEntity target,
                                  UnitData targetdata) {

            float cost = ModConfig.INSTANCE.Server.UNARMED_ENERGY_COST.get().floatValue();

            if (this.hasEnoughEnergy(cost)) {

                this.consumeEnergy(cost);
                int num = (int) unit.getStat(PhysicalDamage.GUID).Value;
                DamageEffect dmg = new DamageEffect(source, target, num, this, targetdata, EffectData.EffectTypes.NORMAL, WeaponTypes.None);

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
