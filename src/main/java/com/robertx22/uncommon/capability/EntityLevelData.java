package com.robertx22.uncommon.capability;

import com.robertx22.mmorpg.Ref;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EntityLevelData {

	@CapabilityInject(IEntityLevelData.class)
	public static final Capability<IEntityLevelData> Data = null;

	public interface IEntityLevelData {
		NBTTagCompound getNBT();

		void setNBT(NBTTagCompound value);
	}

	@Mod.EventBusSubscriber
	public static class EventHandler {
		@SubscribeEvent
		public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

			if (event.getObject() instanceof EntityLivingBase || event.getObject() instanceof EntityLiving) {

				event.addCapability(new ResourceLocation(Ref.MODID, "EntityLevelData"),
						new ICapabilitySerializable<NBTTagCompound>() {
							IEntityLevelData inst = new DefaultImpl();

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

	public static class Storage implements IStorage<IEntityLevelData> {
		@Override
		public NBTBase writeNBT(Capability<IEntityLevelData> capability, IEntityLevelData instance, EnumFacing side) {

			return instance.getNBT();
		}

		@Override
		public void readNBT(Capability<IEntityLevelData> capability, IEntityLevelData instance, EnumFacing side,
				NBTBase nbt) {

			instance.setNBT((NBTTagCompound) nbt);

		}
	}

	public static class DefaultImpl implements IEntityLevelData {
		private NBTTagCompound nbt = new NBTTagCompound();

		@Override
		public NBTTagCompound getNBT() {
			return nbt;
		}

		@Override
		public void setNBT(NBTTagCompound value) {
			this.nbt = value;
		}

	}

}
