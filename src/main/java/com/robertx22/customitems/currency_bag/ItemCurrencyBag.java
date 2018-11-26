package com.robertx22.customitems.currency_bag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.gui.GuiHandler;
import com.robertx22.mmorpg.gui.GuiHandlerRegistry;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

@EventBusSubscriber
public class ItemCurrencyBag extends Item {

	public static final int GUI_NUMBER = 356514;

	@GameRegistry.ObjectHolder(Ref.MODID + ":currency_bag")
	public static final Item ITEM = null;

	private static final String TAG_ITEMS = "InvItems";

	public ItemCurrencyBag() {
		setMaxStackSize(1);
		this.setCreativeTab(CreativeTabList.MyModTab);
		RegisterItemUtils.RegisterItemName(this, "currency_bag");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemCurrencyBag());

		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GUI_NUMBER);
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

	@Nonnull
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound oldCapNbt) {
		return new InvProvider();
	}

	private static class InvProvider implements ICapabilitySerializable<NBTBase> {

		private final IItemHandler inv = new ItemStackHandler(16);

		@Override
		public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
		}

		@Override
		public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv);
			else
				return null;
		}

		@Override
		public NBTBase serializeNBT() {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
		}

		@Override
		public void deserializeNBT(NBTBase nbt) {
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(TAG_ITEMS)) {
			NBTTagList oldData = stack.getTagCompound().getTagList(TAG_ITEMS, Constants.NBT.TAG_COMPOUND);
			IItemHandler newInv = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(newInv, null, oldData);

			stack.getTagCompound().removeTag(TAG_ITEMS);

			if (stack.getTagCompound().getSize() == 0)
				stack.setTagCompound(null);
		}
	}

	public static boolean IsValidItem(ItemStack stack) {

		return stack.getItem() instanceof CurrencyItem || stack.getItem() instanceof ItemOre;
	}

	@SubscribeEvent
	public static void onPickupItem(EntityItemPickupEvent event) {
		ItemStack stack = event.getItem().getItem();
		if (IsValidItem(stack) && stack.getCount() > 0) {

			for (int i = 0; i < event.getEntityPlayer().inventory.getSizeInventory(); i++) {
				if (i == event.getEntityPlayer().inventory.currentItem)
					continue; // prevent item deletion

				ItemStack bag = event.getEntityPlayer().inventory.getStackInSlot(i);
				if (!bag.isEmpty() && bag.getItem() == ITEM) {
					IItemHandler bagInv = bag.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

					for (int x = 0; x < bagInv.getSlots(); x++) {
						ItemStack result = bagInv.insertItem(x, stack, false);
						int numPickedUp = stack.getCount() - result.getCount();

						event.getItem().setItem(result);

						if (numPickedUp > 0) {
							event.setCanceled(true);
							if (!event.getItem().isSilent()) {
								event.getItem().world
										.playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY,
												event.getEntityPlayer().posZ, SoundEvents.ENTITY_ITEM_PICKUP,
												SoundCategory.PLAYERS, 0.2F,
												((event.getItem().world.rand.nextFloat()
														- event.getItem().world.rand.nextFloat()) * 0.7F + 1.0F)
														* 2.0F);
							}
							((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(new SPacketCollectItem(
									event.getItem().getEntityId(), event.getEntityPlayer().getEntityId(), numPickedUp));
							event.getEntityPlayer().openContainer.detectAndSendChanges();

							return;
						}
					}
				}
			}
		}
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		player.openGui(Main.instance, this.GUI_NUMBER, world, hand == EnumHand.OFF_HAND ? 1 : 0, 0, 0);
		return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Nonnull
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side,
			float xs, float ys, float zs) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null) {
			if (!world.isRemote) {
				IItemHandler tileInv = null;
				if (tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side))
					tileInv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
				else if (tile instanceof IInventory)
					tileInv = new InvWrapper((IInventory) tile);

				if (tileInv == null)
					return EnumActionResult.FAIL;

				IItemHandlerModifiable bagInv = (IItemHandlerModifiable) player.getHeldItem(hand)
						.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

				for (int i = 0; i < bagInv.getSlots(); i++) {
					ItemStack flower = bagInv.getStackInSlot(i);
					bagInv.setStackInSlot(i, ItemHandlerHelper.insertItemStacked(tileInv, flower, false));
				}
			}

			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

}
