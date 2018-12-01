package com.robertx22.uncommon.capability;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.ItemListData;
import com.robertx22.uncommon.capability.bases.ICommonCapability;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
public class PlayerDeathItems {

    @CapabilityInject(IPlayerDrops.class)
    public static final Capability<IPlayerDrops> Data = null;

    public interface IPlayerDrops extends ICommonCapability {

	void saveItems(List<EntityItem> items);

	List<ItemStack> getItems();

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
	@SubscribeEvent
	public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

	    if (event.getObject() instanceof EntityPlayer) {

		event.addCapability(new ResourceLocation(Ref.MODID, "PlayerDeathItems"),
			new ICapabilitySerializable<NBTTagCompound>() {
			    IPlayerDrops inst = new DefaultImpl();

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

    public static class Storage implements IStorage<IPlayerDrops> {
	@Override
	public NBTBase writeNBT(Capability<IPlayerDrops> capability, IPlayerDrops instance, EnumFacing side) {

	    return instance.getNBT();
	}

	@Override
	public void readNBT(Capability<IPlayerDrops> capability, IPlayerDrops instance, EnumFacing side, NBTBase nbt) {

	    instance.setNBT((NBTTagCompound) nbt);

	}
    }

    public static final String ITEM_LIST_OBJ = "items_list";

    public static class DefaultImpl implements IPlayerDrops {
	private NBTTagCompound nbt = new NBTTagCompound();

	ItemListData item_list = new ItemListData();

	@Override
	public NBTTagCompound getNBT() {

	    if (item_list != null) {
		NBTTagCompound itemsnbt = new NBTTagCompound();
		Writer.write(itemsnbt, item_list);
		nbt.setTag(ITEM_LIST_OBJ, itemsnbt);
	    }

	    return nbt;

	}

	@Override
	public void setNBT(NBTTagCompound value) {

	    NBTTagCompound object_nbt = (NBTTagCompound) this.nbt.getTag(ITEM_LIST_OBJ);
	    if (object_nbt != null) {
		item_list = new ItemListData();
		Reader.read(object_nbt, item_list);
	    }
	}

	@Override
	public void syncToClient(EntityPlayer player) {
	}

	@Override
	public void saveItems(List<EntityItem> items) {
	    for (EntityItem item : items) {
		item_list.items.add(item.getItem());
		item.setItem(ItemStack.EMPTY);
	    }
	}

	@Override
	public List<ItemStack> getItems() {

	    List<ItemStack> items = new ArrayList<ItemStack>(item_list.items);

	    item_list.items.clear();

	    return items;
	}

    }

}
