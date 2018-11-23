package com.robertx22.uncommon.capability;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.dimensions.MyTeleporter;
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
import net.minecraft.util.math.BlockPos;
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

		boolean isMapWorld();

		boolean isSetForDelete();

		int getWorldID();

		int getTier();

		int getLevel();

		void setOwner(EntityPlayer player);

		String getOwner();

		void init(EntityPlayer player, MapItemData map, int dimensionId);

		void delete(EntityPlayer player, World mapworld);

		MapItemData getMap();

		boolean isInit();

		boolean didntSetBackPortal();

		void setBackPortal();

		String getSaveName();

		void setSaveName(String name);

		BlockPos getMapDevicePos();

		int getOriginalDimension();

		void teleportPlayerBack(EntityPlayer player);

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
	static final String LEVEL = "level";
	static final String OWNER = "owner";
	static final String TIER = "tier";
	static final String MAP_OBJECT = "mapObject";
	static final String IS_INIT = "isInit";
	static final String ORIGINAL_DIM = "original_dimension";
	static final String MAP_DIM = "map_dimension";
	static final String DIDNT_SET_BACK_PORTAL = "didntSetBackPortal";
	static final String SAVE_NAME = "save_name";
	static final String POS_OBJ = "POS_OBJ";

	public static class DefaultImpl implements IWorldData {
		private NBTTagCompound nbt = new NBTTagCompound();

		long pos;
		MapItemData mapdata = new MapItemData();
		int tier = 0;
		int level = 0;
		boolean isMap = false;
		boolean setForDelete = false;
		String owner = "";
		boolean isInit = false;
		int originalDimension;
		int mapDimension;
		boolean didntSetBackPortal = true;
		String saveName = "";

		@Override
		public NBTTagCompound getNBT() {
			nbt.setInteger(TIER, tier);
			nbt.setInteger(LEVEL, level);
			nbt.setBoolean(IS_MAP_WORLD, isMap);
			nbt.setBoolean(SET_FOR_DELETE, setForDelete);
			nbt.setString(OWNER, owner);
			nbt.setBoolean(IS_INIT, isInit);
			nbt.setInteger(ORIGINAL_DIM, originalDimension);
			nbt.setInteger(MAP_DIM, mapDimension);
			nbt.setBoolean(DIDNT_SET_BACK_PORTAL, didntSetBackPortal);
			nbt.setString(SAVE_NAME, saveName);

			if (mapdata != null) {
				NBTTagCompound mapnbt = new NBTTagCompound();
				Writer.write(mapnbt, mapdata);
				nbt.setTag(MAP_OBJECT, mapnbt);
			}

			nbt.setLong(POS_OBJ, pos);

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
			this.originalDimension = nbt.getInteger(ORIGINAL_DIM);
			this.mapDimension = nbt.getInteger(MAP_DIM);
			this.didntSetBackPortal = nbt.getBoolean(DIDNT_SET_BACK_PORTAL);
			this.saveName = nbt.getString(SAVE_NAME);

			NBTTagCompound mapnbt = (NBTTagCompound) this.nbt.getTag(MAP_OBJECT);
			if (mapnbt != null) {
				Reader.read(mapnbt, mapdata);
			}

			this.pos = nbt.getLong(POS_OBJ);

		}

		@Override
		public boolean isMapWorld() {
			return isMap;
		}

		@Override
		public boolean isSetForDelete() {
			return setForDelete;
		}

		@Override
		public int getWorldID() {
			return this.mapDimension;
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
		public void delete(EntityPlayer player, World mapworld) {

			if (this.isMapWorld()) {

				if (player.getUniqueID().toString().equals(this.getOwner())) {
					this.setForDelete = true;

					this.transferPlayersBack(mapworld);
				} else {

					player.sendMessage(new TextComponentString("You can't delete this world"));
				}
			}
		}

		@Override
		public void init(EntityPlayer player, MapItemData map, int dimensionId) {

			if (player.inventory.getFirstEmptyStack() > -1) {

				UnitData data = player.getCapability(EntityData.Data, null);

				this.setOwner(player);
				this.isInit = true;
				this.isMap = true;
				this.level = data.getLevel();
				this.tier = map.tier;
				this.mapdata = map;

				this.originalDimension = player.dimension;
				this.mapDimension = dimensionId;

				this.pos = player.getPosition().toLong();
			} else {
				player.sendMessage(new TextComponentString("You need a free inventory slot to enter a map"));
			}

		}

		@Override
		public int getTier() {
			return tier;
		}

		@Override
		public MapItemData getMap() {
			return mapdata;
		}

		@Override
		public boolean isInit() {
			return isInit;
		}

		@Override
		public boolean didntSetBackPortal() {
			return this.didntSetBackPortal;
		}

		@Override
		public void setBackPortal() {
			this.didntSetBackPortal = false;
		}

		@Override
		public String getSaveName() {
			return saveName;
		}

		@Override
		public void setSaveName(String name) {
			this.saveName = name;
		}

		@Override
		public BlockPos getMapDevicePos() {
			return BlockPos.fromLong(pos);
		}

		@Override
		public int getOriginalDimension() {
			return this.originalDimension;
		}

		private void transferPlayersBack(World world) {

			if (world.playerEntities != null) {

				List<EntityPlayer> players = new ArrayList<EntityPlayer>(world.playerEntities);

				for (EntityPlayer player : players) {

					teleportPlayerBack(player);
				}
			}

		}

		@Override
		public void teleportPlayerBack(EntityPlayer player) {

			if (getMapDevicePos() != null) {

				int x = getMapDevicePos().getX() - 4;

				player.setPosition(x, getMapDevicePos().getY(), getMapDevicePos().getZ());
			}
			player.changeDimension(this.originalDimension, new MyTeleporter(player, this.originalDimension));

		}

	}

}
