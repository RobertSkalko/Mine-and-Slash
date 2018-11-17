package com.robertx22.uncommon.capability;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.datasaving.bases.Gson;
import com.robertx22.uncommon.datasaving.bases.Saving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
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

		@Override
		public NBTTagCompound getNBT() {
			return nbt;
		}

		@Override
		public void setNBT(NBTTagCompound value) {
			this.nbt = value;

		}

		@Override
		public void setAsMapWorld(boolean bool) {
			nbt.setBoolean(IS_MAP_WORLD, bool);
		}

		@Override
		public boolean isMapWorld() {
			return nbt.getBoolean(IS_MAP_WORLD);
		}

		@Override
		public void setForDelete(boolean bool) {
			nbt.setBoolean(SET_FOR_DELETE, bool);

		}

		@Override
		public boolean isSetForDelete() {

			return nbt.getBoolean(SET_FOR_DELETE);
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
			nbt.setInteger(LEVEL, lvl);

		}

		@Override
		public int getLevel() {
			return nbt.getInteger(LEVEL);
		}

		@Override
		public void setOwner(EntityPlayer player) {
			nbt.setString(OWNER, player.getUniqueID().toString());

		}

		@Override
		public String getOwner() {
			return nbt.getString(OWNER);
		}

		@Override
		public void delete(EntityPlayer player) {

			if (this.isMapWorld()) {
				if (player.getUniqueID().toString().equals(this.getOwner())) {

					this.setForDelete(true);
				}

				else {

				}
			}
		}

		@Override
		public void init(EntityPlayer player, MapItemData map) {

			Unit unit = UnitSaving.Load(player);

			this.setOwner(player);
			this.setAsMapWorld(true);
			this.setLevel(unit.GetLevel());
			this.setTier(map.tier);
			this.setMap(map);

		}

		@Override
		public void setTier(int tier) {
			nbt.setInteger(TIER, tier);

		}

		@Override
		public int getTier() {
			return nbt.getInteger(TIER);
		}

		@Override
		public void setMap(MapItemData map) {
			nbt.setString(MAP_OBJECT, Gson.instance.toJson(map));
		}

		@Override
		public MapItemData getMap() {
			return Saving.Load(nbt.getString(MAP_OBJECT), MapItemData.class);
		}

		@Override
		public void setInit() {
			nbt.setBoolean(IS_INIT, true);
		}

		@Override
		public boolean isInit() {
			return nbt.getBoolean(IS_INIT);
		}

	}

}
