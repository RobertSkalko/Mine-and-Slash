package com.robertx22.uncommon.capability;

import java.util.UUID;

import com.robertx22.database.rarities.MobRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.UnitPackage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.capability.bases.ICommonCapability;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
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
    private static final String UNIT_OBJECT = "unit_object";

    public interface UnitData extends ICommonCapability {

	int getLevel();

	void setLevel(int lvl);

	int getExp();

	void setExp(int exp);

	int GiveExp(EntityPlayer player, int i);

	int GetExpRequiredForLevelUp();

	boolean CheckIfCanLevelUp();

	boolean LevelUp(EntityPlayer player);

	boolean CheckLevelCap();

	void SetMobLevel(IWorldData data, int lvl);

	Unit getUnit();

	void setUnit(Unit unit, EntityLivingBase entity);

	void setRarity(int rarity);

	int getRarity();

	String getUUID();

	void setUUID(UUID id);

	void setName(EntityLivingBase entity);

	String getName();

	void HandleCloneEvent(UnitData old);

	void recalculateStats(EntityLivingBase entity);

	void forceRecalculateStats(EntityLivingBase entity);

	void forceSetUnit(Unit unit);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
	@SubscribeEvent
	public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

	    if (event.getObject() instanceof EntityLivingBase || event.getObject() instanceof EntityLiving) {

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
	int level = 1;
	int exp = 0;
	int rarity = 0;
	String uuid = "";
	String name = "";
	boolean mobSavedOnce = false;

	@Override
	public NBTTagCompound getNBT() {
	    nbt.setInteger(LEVEL, level);
	    nbt.setInteger(EXP, exp);
	    nbt.setInteger(RARITY, rarity);
	    nbt.setString(UUID, uuid);
	    nbt.setString(NAME, name);
	    nbt.setBoolean(MOB_SAVED_ONCE, true);

	    if (unit != null) {
		NBTTagCompound unitnbt = new NBTTagCompound();
		Writer.write(unitnbt, unit);
		nbt.setTag(UNIT_OBJECT, unitnbt);
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
	    this.mobSavedOnce = value.getBoolean(MOB_SAVED_ONCE);

	    NBTTagCompound object_nbt = (NBTTagCompound) this.nbt.getTag(UNIT_OBJECT);
	    if (object_nbt != null) {
		unit = new Unit();
		Reader.read(object_nbt, unit);
	    }
	}

	@Override
	public int GetExpRequiredForLevelUp() {

	    int lvl = getLevel();

	    int tens = lvl / 10;

	    if (lvl < 5) {
		return 250 * lvl;
	    }

	    return lvl * 1000 + (tens * 5000);

	}

	@Override
	public void SetMobLevel(IWorldData data, int lvl) {

	    if (lvl < 1) {
		lvl = 1;

	    }
	    if (data != null && !data.isMapWorld()) {
		if (lvl > ModConfig.Server.MAXIMUM_NORMAL_WORLD_MOB_LEVEL) {
		    lvl = ModConfig.Server.MAXIMUM_NORMAL_WORLD_MOB_LEVEL;
		}
	    }

	    if (lvl > ModConfig.Server.MAXIMUM_PLAYER_LEVEL) {
		lvl = ModConfig.Server.MAXIMUM_PLAYER_LEVEL;
	    }

	    setLevel(lvl);
	}

	@Override
	public int GiveExp(EntityPlayer player, int i) {

	    i *= ModConfig.Server.EXPERIENCE_MULTIPLIER;

	    setExp(exp + i);

	    if (exp > this.GetExpRequiredForLevelUp()) {

		setExp(this.GetExpRequiredForLevelUp());

		if (this.CheckIfCanLevelUp() && this.CheckLevelCap()) {
		    this.LevelUp(player);
		}

		return i;
	    }

	    return i;

	}

	@Override
	public boolean CheckIfCanLevelUp() {

	    return getExp() >= GetExpRequiredForLevelUp();

	}

	@Override
	public boolean CheckLevelCap() {
	    return getLevel() + 1 <= ModConfig.Server.MAXIMUM_PLAYER_LEVEL;
	}

	@Override
	public boolean LevelUp(EntityPlayer player) {

	    if (!CheckIfCanLevelUp()) {
		player.sendMessage(
			new TextComponentString(TextFormatting.RED + "You don't have enough experience to Level Up."));
	    } else if (!CheckLevelCap()) {
		player.sendMessage(
			new TextComponentString(TextFormatting.RED + "You have already reached maximum level."));
	    }

	    if (CheckIfCanLevelUp() && CheckLevelCap()) {

		this.setLevel(level + 1);
		setExp(0);

		player.sendMessage(new TextComponentString(
			TextFormatting.GREEN + "You have Leveled up! Current lvl: " + getLevel()));

		return true;
	    }
	    return false;
	}

	@Override
	public int getLevel() {

	    return level;

	}

	@Override
	public void setLevel(int lvl) {
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
		UnitPackage packet = new UnitPackage(this.getNBT());
		Main.Network.sendTo(packet, (EntityPlayerMP) player);
	    }
	}

	@Override
	public Unit getUnit() {
	    return unit;

	}

	@Override
	public void setUnit(Unit unit, EntityLivingBase entity) {

	    if (entity instanceof EntityPlayer) {
		this.unit = unit;
	    } else {
		if (this.mobSavedOnce == false) {
		    this.mobSavedOnce = true;
		    this.unit = unit;
		}
	    }
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
	public void setUUID(UUID id) {
	    uuid = id.toString();
	}

	@Override
	public void setName(EntityLivingBase entity) {
	    String name = "";
	    if (entity instanceof EntityPlayer) {
		name = ((EntityPlayer) entity).getDisplayNameString();
	    } else {
		MobRarity rarity = Rarities.Mobs.get(getRarity());

		name = TextFormatting.YELLOW + "[Lv:" + this.getLevel() + "] " + rarity.Color() + rarity.Name() + " "
			+ entity.getName();
	    }
	    this.name = name;

	}

	@Override
	public String getName() {
	    return name;
	}

	@Override
	public void HandleCloneEvent(UnitData old) {
	    this.setNBT(old.getNBT());
	}

	@Override
	public void recalculateStats(EntityLivingBase entity) {
	    if (entity instanceof EntityPlayer) {
		unit.RecalculateStats(entity, level);
	    }
	}

	@Override
	public void forceRecalculateStats(EntityLivingBase entity) {
	    unit.RecalculateStats(entity, level);
	}

	@Override
	public void forceSetUnit(Unit unit) {
	    this.unit = unit;
	}

    }

}
