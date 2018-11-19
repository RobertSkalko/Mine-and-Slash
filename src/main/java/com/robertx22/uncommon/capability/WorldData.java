package com.robertx22.uncommon.capability;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class WorldData {

	@CapabilityInject(IWorldData.class)
	public static final Capability<IWorldData> Data = null;

	public interface IWorldData {
		NBTTagCompound getNBT();

		void setNBT(NBTTagCompound value);

		void setAsMapWorld(boolean bool);

		boolean isMapWorld();

		void setForDelete(boolean bool);

		boolean isSetForDelete();

		void setID(int id);

		int getID();

		void setTier(int tier);

		int getTier();

		void setLevel(int lvl);

		int getLevel();

		void setOwner(EntityPlayer player);

		String getOwner();

		void init(EntityPlayer player, MapItemData map);

		void delete(EntityPlayer player);

		void setMap(MapItemData map);

		MapItemData getMap();

		void setInit();

		boolean isInit();

	}

	@Mod.EventBusSubscriber
	public static class EventHandler {
		@SubscribeEvent
		public static void onWorldConstuct(AttachCapabilitiesEvent<World> event) {

			event.addCapability(new ResourceLocation(Ref.MODID, "WorldData"),
					new ICapabilitySerializable<NBTTagCompound>() {
						IWorldData inst = new DefaultImpl();

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

	public static class Storage implements IStorage<IWorldData> {
		@Override
		public NBTBase writeNBT(Capability<IWorldData> capability, IWorldData instance, EnumFacing side) {

			return instance.getNBT();
		}

		@Override
		public void readNBT(Capability<IWorldData> capability, IWorldData instance, EnumFacing side, NBTBase nbt) {

			instance.setNBT((NBTTagCompound) nbt);

		}
	}

	static final String SET_FOR_DELETE = "setForDelete";
	static final String IS_MAP_WORLD = "isMap";
	static final String ID = "id";
	static final String LEVEL = "level";
	static final String OWNER = "owner";
	static final String TIER = "tier";
	static final String MAP_OBJECT = "mapObject";
	static final String IS_INIT = "isInit";

	public static class DefaultImpl implements IWorldData {
		private NBTTagCompound nbt = new NBTTagCompound();

		MapItemData mapdata = new MapItemData();
		int tier = 0;
		int level = 0;
		boolean isMap = false;
		boolean setForDelete = false;
		int worldID;
		String owner = "";
		boolean isInit = false;

		@Override
		public NBTTagCompound getNBT() {
			nbt.setInteger(TIER, tier);
			nbt.setInteger(LEVEL, level);
			nbt.setBoolean(IS_MAP_WORLD, isMap);
			nbt.setBoolean(SET_FOR_DELETE, setForDelete);
			nbt.setString(OWNER, owner);
			nbt.setBoolean(IS_INIT, isInit);

			if (mapdata != null) {
				NBTTagCompound mapnbt = new NBTTagCompound();
				Writer.write(mapnbt, mapdata);
				nbt.setTag(MAP_OBJECT, mapnbt);
			}

			return nbt;

		}

		@Override
		public void setNBT(NBTTagCompound value) {
			this.nbt = value;
			tier = nbt.getInteger(TIER);
			level = nbt.getInteger(LEVEL);
			isMap = nbt.getBoolean(IS_MAP_WORLD);
			setForDelete = nbt.getBoolean(SET_FOR_DELETE);
			owner = nbt.getString(OWNER);
			isInit = nbt.getBoolean(IS_INIT);

			NBTTagCompound nbt = (NBTTagCompound) this.nbt.getTag(MAP_OBJECT);
			if (nbt != null) {
				Reader.read(((NBTTagCompound) nbt).getCompoundTag(MAP_OBJECT), mapdata);
			}

		}

		@Override
		public void setAsMapWorld(boolean bool) {
			isMap = bool;
		}

		@Override
		public boolean isMapWorld() {
			return isMap;
		}

		@Override
		public void setForDelete(boolean bool) {
			this.setForDelete = bool;

		}

		@Override
		public boolean isSetForDelete() {
			return setForDelete;
		}

		@Override
		public void setID(int id) {
			nbt.setInteger(ID, id);

		}

		@Override
		public int getID() {
			return nbt.getInteger(ID);
		}

		@Override
		public void setLevel(int lvl) {
			this.level = lvl;
		}

		@Override
		public int getLevel() {
			return level;
		}

		@Override
		public void setOwner(EntityPlayer player) {
			this.owner = player.getUniqueID().toString();

		}

		@Override
		public String getOwner() {
			return owner;
		}

		@Override
		public void delete(EntityPlayer player) {

			if (this.isMapWorld()) {

				if (player.getUniqueID().toString().equals(this.getOwner())) {
					this.setForDelete(true);
				} else {

					player.sendMessage(new TextComponentString("You can't delete this world"));
				}
			}
		}

		@Override
		public void init(EntityPlayer player, MapItemData map) {

			UnitData data = player.getCapability(EntityData.Data, null);

			this.setOwner(player);
			this.setAsMapWorld(true);
			this.setLevel(data.getLevel());
			this.setTier(map.tier);
			this.setMap(map);

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
		public void setMap(MapItemData map) {
			this.mapdata = map;
		}

		@Override
		public MapItemData getMap() {
			return mapdata;
		}

		@Override
		public void setInit() {
			this.isInit = true;
		}

		@Override
		public boolean isInit() {
			return isInit;
		}

	}

}
