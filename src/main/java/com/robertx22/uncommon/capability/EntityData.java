package com.robertx22.uncommon.capability;

import java.util.Random;
import java.util.UUID;
import com.robertx22.api.MineAndSlashEvents;
import com.robertx22.config.DimensionConfigs;
import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stat_types.misc.BonusExp;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.onevent.player.OnLogin;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.PlayerMapKillsData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.AttackUtils;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.capability.bases.ICommonCapability;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.EntitySystemChoice;
import com.robertx22.uncommon.utilityclasses.HealthUtils;
import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EntityData {

	@CapabilityInject(UnitData.class)
	public static final Capability<UnitData> Data = null;

	private static final String LEVEL = "level";
	private static final String RARITY = "rarity";
	private static final String EXP = "exp";
	private static final String UUID = "uuid";
	private static final String NAME = "name";
	private static final String MOB_SAVED_ONCE = "mob_saved_once";
	private static final String TIER = "TIER";
	private static final String UNIT_OBJECT = "unit_object";
	private static final String KILLS_OBJECT = "kils_object";
	private static final String MANA = "current_mana";
	private static final String ENERGY = "current_energy";
	private static final String CURRENT_MAP_ID = "current_map_id";
	private static final String DMG_DONE_BY_NON_PLAYERS = "DMG_DONE_BY_NON_PLAYERS";

	public interface UnitData extends ICommonCapability {

		void onDamagedByNonPlayer(float dmg);

		boolean shouldDropLoot(EntityLivingBase entity);

		void freelySetLevel(int lvl);

		int getLevel();

		void setLevel(int lvl, EntityLivingBase entity);

		boolean increaseRarity(EntityLivingBase entity);

		int getExp();

		int PostGiveExpEvent(EntityPlayer player, int exp);

		void setExp(int exp);

		int GiveExp(EntityPlayer player, int i);

		int GetExpRequiredForLevelUp();

		boolean CheckIfCanLevelUp();

		boolean LevelUp(EntityPlayer player);

		boolean CheckLevelCap();

		void SetMobLevelAtSpawn(IWorldData data, EntityLivingBase entity);

		Unit getUnit();

		void setUnit(Unit unit, EntityLivingBase entity);

		void setRarity(int rarity);

		int getRarity();

		String getUUID();

		void setUUID(UUID id);

		String getName(EntityLivingBase entity);

		void HandleCloneEvent(UnitData old);

		void recalculateStats(EntityLivingBase entity, IWorldData world);

		void forceSetUnit(Unit unit);
		
		boolean tryUseWeapon(GearItemData gear, EntityLivingBase entity);

        boolean tryUseWeapon(GearItemData gear, EntityLivingBase entity, float multi);

		void onMobKill(IWorldData world);

		int getLootBonusPerAffixKills(MapItemData map);

		void onLogin(EntityPlayer player);

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

		void heal(EntityLivingBase entity, int healthrestored);

		int getCurrentMapId();

		void setCurrentMapId(int id);

		boolean hasCurrentMapId();

		void clearCurrentMapId();
		
		void unarmedAttack(LivingHurtEvent event, EntityLivingBase source,
				EntityLivingBase target, UnitData targetdata);
		
		void setTier(int tier);

		boolean decreaseRarity(EntityLivingBase entity);

		boolean isWeapon(GearItemData gear);

		void onAttackEntity(EntityLivingBase attacker, EntityLivingBase victim);

		float getDMGMultiplierIncreaseByTier();

		GearItemData getWeaponData(EntityLivingBase entity);

		void attackWithWeapon(LivingHurtEvent event, ItemStack heldItemMainhand, GearItemData weapondata,
				EntityLivingBase source, EntityLivingBase target, UnitData targetData);

		void mobBasicAttack(LivingHurtEvent event, EntityLivingBase source, EntityLivingBase target, UnitData sourceData,
				UnitData targetData, float amount);
	}

	@Mod.EventBusSubscriber
	public static class EventHandler {
		@SubscribeEvent
		public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

			boolean can = false;

			if (ModConfig.Server.ENTITIES_UNDER_SYSTEM.equals(EntitySystemChoice.All_Entities)
					&& event.getObject() instanceof EntityLivingBase) {
				can = true;
			}

			if (ModConfig.Server.ENTITIES_UNDER_SYSTEM.equals(EntitySystemChoice.Mobs_And_Players)) {
				if (event.getObject() instanceof IMob || event.getObject() instanceof EntityPlayer) {
					can = true;
				}
			}

			if (can) {

				event.addCapability(new ResourceLocation(Ref.MODID, "EntityData"),
						new ICapabilitySerializable<NBTTagCompound>() {
							UnitData inst = new DefaultImpl();

							@Override
							public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
								return capability == Data;

							}

							@Override
							public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
								return capability == Data ? Data.<T>cast(inst) : null;
							}

							@Override
							public NBTTagCompound serializeNBT() {
								return (NBTTagCompound) Data.getStorage().writeNBT(Data, inst, null);
							}

							@Override
							public void deserializeNBT(NBTTagCompound nbt) {
								Data.getStorage().readNBT(Data, inst, null, nbt);
							}

						});

			}
		}

	}

	public static class Storage implements IStorage<UnitData> {
		@Override
		public NBTBase writeNBT(Capability<UnitData> capability, UnitData instance, EnumFacing side) {

			return instance.getNBT();
		}

		@Override
		public void readNBT(Capability<UnitData> capability, UnitData instance, EnumFacing side, NBTBase nbt) {

			instance.setNBT((NBTTagCompound) nbt);

		}
	}

	public static class DefaultImpl implements UnitData {
		private NBTTagCompound nbt = new NBTTagCompound();

		Unit unit = null;
		PlayerMapKillsData kills = null;
		int level = 1;
		int exp = 0;
		int rarity = 0;
		String uuid = "";
		String name = "";
		int currentMapId = 0;

		float dmgByNonPlayers = 0;

		float energy;
		float mana;

		int tier = 0;

		@Override
		public NBTTagCompound getNBT() {
			nbt.setFloat(MANA, mana);
			nbt.setFloat(ENERGY, energy);
			nbt.setInteger(LEVEL, level);
			nbt.setInteger(EXP, exp);
			nbt.setInteger(RARITY, rarity);
			nbt.setFloat(DMG_DONE_BY_NON_PLAYERS, dmgByNonPlayers);
			nbt.setString(UUID, uuid);
			nbt.setString(NAME, name);
			nbt.setBoolean(MOB_SAVED_ONCE, true);
			nbt.setInteger(CURRENT_MAP_ID, currentMapId);

			if (unit != null) {
				NBTTagCompound unitnbt = new NBTTagCompound();
				Writer.write(unitnbt, unit);
				nbt.setTag(UNIT_OBJECT, unitnbt);
			}
			if (kills != null) {
				NBTTagCompound killsnbt = new NBTTagCompound();
				Writer.write(killsnbt, kills);
				nbt.setTag(KILLS_OBJECT, killsnbt);
			}

			return nbt;

		}

		@Override
		public void setNBT(NBTTagCompound value) {
			this.nbt = value;
			this.level = value.getInteger(LEVEL);
			this.exp = value.getInteger(EXP);
			this.rarity = value.getInteger(RARITY);
			this.uuid = value.getString(UUID);
			this.name = value.getString(NAME);
			this.energy = value.getFloat(ENERGY);
			this.dmgByNonPlayers = value.getFloat(DMG_DONE_BY_NON_PLAYERS);
			this.mana = value.getFloat(MANA);
			this.currentMapId = value.getInteger(CURRENT_MAP_ID);

			NBTTagCompound object_nbt = (NBTTagCompound) this.nbt.getTag(UNIT_OBJECT);
			if (object_nbt != null) {
				unit = new Unit();
				Reader.read(object_nbt, unit);
			}

			NBTTagCompound kills_nbt = (NBTTagCompound) this.nbt.getTag(KILLS_OBJECT);
			if (kills_nbt != null) {
				kills = new PlayerMapKillsData();
				Reader.read(kills_nbt, kills);
			}

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
		public void SetMobLevelAtSpawn(IWorldData data, EntityLivingBase entity) {

			DimensionConfigs config = ModConfig.Dimensions.getAll().getConfig(entity.dimension);

			int lvl = 1;

			if (data != null && data.isMapWorld()) {
				lvl = data.getLevel();
			} else {
				if (config.SINGLEPLAYER_MOB_SCALING) {

					EntityPlayer player = entity.world.getClosestPlayerToEntity(entity, 4999);

					if (player != null) {
						lvl = player.getCapability(EntityData.Data, null).getLevel();

					}

				} else {

					lvl = GetMobLevelByDistanceFromSpawn(entity, config) + config.MINIMUM_MOB_LEVEL - 1;

				}

			}
			lvl = MathHelper.clamp(lvl, config.MINIMUM_MOB_LEVEL, config.MAXIMUM_MOB_LEVEL);

			this.level = lvl;
		}

		public static int GetMobLevelByDistanceFromSpawn(Entity entity, DimensionConfigs config) {

			double distance = entity.world.getSpawnPoint().getDistance((int) entity.posX, (int) entity.posY,
					(int) entity.posZ);

			int lvl = 1;

			if (distance < config.MOB_LEVEL_ONE_AREA) {
				lvl = 1;
			} else {

				lvl = (int) (1 + (distance / config.MOB_LEVEL_PER_DISTANCE));
			}

			return lvl;

		}

		@Override
		public int PostGiveExpEvent(EntityPlayer player, int i) {

			i *= ModConfig.Server.EXPERIENCE_MULTIPLIER;
			;

			i *= (double) this.getUnit().MyStats.get(new BonusExp().GUID()).Value / 100 + 1;

			MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.GiveExpEvent(player, this, i));

			return i;
		}

		@Override
		public int GiveExp(EntityPlayer player, int i) {

			setExp(exp + i);

			if (exp > this.GetExpRequiredForLevelUp()) {

				if (ModConfig.Server.LEVEL_UPS_COST_TOKEN == false) {

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
			return getLevel() + 1 <= ModConfig.Server.MAXIMUM_PLAYER_LEVEL;
		}

		@Override
		public boolean LevelUp(EntityPlayer player) {

			if (!CheckIfCanLevelUp()) {
				player.sendMessage(SLOC.chat("not_enough_experience"));
			} else if (!CheckLevelCap()) {
				player.sendMessage(SLOC.chat("cannot_over_maximum_level"));
			}

			if (CheckIfCanLevelUp() && CheckLevelCap()) {

				this.setLevel(level + 1, player);

				setExp(getRemainingExp());

				player.sendMessage(SLOC.chat("levelup_success"));

				return true;
			}
			return false;
		}

		@Override
		public int getLevel() {

			return level;

		}

		@Override
		public void setLevel(int lvl, EntityLivingBase entity) {
			if (lvl > ModConfig.Server.MAXIMUM_PLAYER_LEVEL) {
				lvl = ModConfig.Server.MAXIMUM_PLAYER_LEVEL;
			}

			level = lvl;

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
		public void syncToClient(EntityPlayer player) {
			if (unit != null) {
				PlayerUnitPackage packet = new PlayerUnitPackage(this.getNBT());
				Main.Network.sendTo(packet, (EntityPlayerMP) player);
			}
		}

		@Override
		public Unit getUnit() {
			return unit;

		}

		@Override
		public void setUnit(Unit unit, EntityLivingBase entity) {

			this.unit = unit;

		}

		@Override
		public void setRarity(int rarity) {
			this.rarity = rarity;

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
        public float getDMGMultiplierIncreaseByTier() {
            return 1 + tier * 0.2F;
        }

		@Override
		public void setUUID(UUID id) {
			uuid = id.toString();
		}

		@Override
		public String getName(EntityLivingBase entity) {

			if (entity instanceof EntityPlayer) {

				return TextFormatting.YELLOW + "[Lv:" + this.getLevel() + "] " + " "
						+ entity.getDisplayName().getFormattedText();

			} else {
				MobRarity rarity = Rarities.Mobs.get(getRarity());
				String rarityprefix = "";
				String name = "";

				name = entity.getDisplayName().getFormattedText();
				rarityprefix = rarity.locName();

				return TextFormatting.YELLOW + "[Lv:" + this.getLevel() + "] " + rarity.Color() + rarityprefix + " "
						+ name;

			}
		}

		@Override
		public void HandleCloneEvent(UnitData old) {
			this.setNBT(old.getNBT());
		}

		@Override
		public void recalculateStats(EntityLivingBase entity, IWorldData world) {

			unit.RecalculateStats(entity, this, level, world);

		}

		@Override
		public void forceSetUnit(Unit unit) {
			this.unit = unit;
		}

		@Override
        public boolean tryUseWeapon(GearItemData weaponData, EntityLivingBase source) {
            return tryUseWeapon(weaponData, source, 1);
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, EntityLivingBase source,
                                    float multi) {

            try {

                if (weaponData != null && weaponData.GetBaseGearType() instanceof IWeapon) {

                    IWeapon iwep = (IWeapon) weaponData.GetBaseGearType();

                    float energyCost = iwep.mechanic().GetEnergyCost() * multi;
                    float manaCost = iwep.mechanic().GetManaCost() * multi;

                    if (hasEnoughEnergy(energyCost) == false) {
                        AttackUtils.NoEnergyMessage(source);
                        return false;

                    } else {

                        if (hasEnoughMana(manaCost) == false) {
                            AttackUtils.NoEnergyMessage(source);
                            return false;
                        }

                        consumeEnergy(energyCost);
                        consumeMana(manaCost);

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
                                     GearItemData weaponData, EntityLivingBase source,
                                     EntityLivingBase target, UnitData targetdata) {

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
		public void onMobKill(IWorldData world) {

			Runnable noteThread = new Runnable() {
				@Override
				public void run() {
					try {

						if (kills == null) {
							kills = new PlayerMapKillsData();
						}

						kills.onKill(world.getMap());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			};
			FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(noteThread);

		}

		@Override
		public int getLootBonusPerAffixKills(MapItemData map) {

			if (kills == null) {
				return 0;
			} else {
				return kills.getLootMulti(map);
			}
		}

		@Override
		public void onLogin(EntityPlayer player) {

			try {

				// check if newbie
				if (unit == null) {
					unit = new Unit();
					unit.InitPlayerStats();
					if (ModConfig.Server.GENERATE_GEAR) {
						OnLogin.GiveStarterItems(player);
					} else {
					}
				} else {
					getUnit().InitPlayerStats();
					recalculateStats(player, Load.World(player));
				}

				if (kills == null) {
					kills = new PlayerMapKillsData();
				}

				kills.init();

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
		public void heal(EntityLivingBase entity, int healthrestored) {
			entity.heal(
					HealthUtils.DamageToMinecraftHealth(healthrestored / ModConfig.Server.NON_MOD_HEAL_MULTI, entity, null));
		}

		@Override
		public boolean increaseRarity(EntityLivingBase entity) {

			if (rarity == 5) {
				return false;
			} else {
				rarity = rarity + 1;

				return true;

			}
		}

		@Override
		public boolean decreaseRarity(EntityLivingBase entity) {

			if (rarity - 1 < 0) {
				return false;
			} else {
				rarity = rarity - 1;

				return true;

			}
		}

		@Override
		public int getCurrentMapId() {
			return this.currentMapId;
		}

		@Override
		public void setCurrentMapId(int id) {
			this.currentMapId = id;
		}

		@Override
		public boolean hasCurrentMapId() {
			return this.currentMapId != 0;
		}

		@Override
		public void clearCurrentMapId() {
			this.currentMapId = 0;
		}

		@Override
		public void unarmedAttack(LivingHurtEvent event, EntityLivingBase source, EntityLivingBase target, UnitData targetdata) {

			float cost = ModConfig.Server.UNARMED_ENERGY_COST;

			if (this.hasEnoughEnergy(cost)) {

				this.consumeEnergy(cost);
				int num = (int) unit.MyStats.get(PhysicalDamage.GUID).Value;
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
		public void freelySetLevel(int lvl) {
			this.level = lvl;
		}

		@Override
		public void onDamagedByNonPlayer(float dmg) {

			this.dmgByNonPlayers += dmg;

		}

		@Override
		public boolean shouldDropLoot(EntityLivingBase entity) {

			if (entity.getMaxHealth()
					* ModConfig.Server.STOP_DROPS_IF_NON_PLAYER_DOES_DMG_PERCENT > this.dmgByNonPlayers) {
				return true;
			}

			return false;
		}

		@Override
        public void onAttackEntity(EntityLivingBase attacker, EntityLivingBase victim) {

        }
		
		@Override
        public void setTier(int tier) {
            this.tier = tier;
        }

		@Override
		public GearItemData getWeaponData(EntityLivingBase entity) {
			return Gear.Load(entity.getHeldItemMainhand());
		}

		@Override
        public void mobBasicAttack(LivingHurtEvent event, EntityLivingBase source,
        		EntityLivingBase target, UnitData sourcedata,
                                   UnitData targetdata, float event_damage) {

            MobRarity rar = Rarities.Mobs.get(sourcedata.getRarity());

            float vanilla = event_damage * sourcedata.getLevel() * sourcedata.getDMGMultiplierIncreaseByTier();

            float num = 1.1F * vanilla * rar.DamageMultiplier();

            DamageEffect dmg = new DamageEffect(event, source, target, (int) num, sourcedata, targetdata, EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.None);

            dmg.Activate();

        }
	}

}
