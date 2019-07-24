package com.robertx22.mine_and_slash.items.bags;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.ItemSingle;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class BaseBagItem extends Item {

    public abstract ItemFilterGroup filterGroup();

    public abstract INamedContainerProvider getNamedContainer(ItemStack stack);

    public int size = 9 * 6;

    public BaseBagItem(String name) {

        super(new ItemSingle().group(CreativeTabs.MyModTab));

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    @Nonnull Hand hand) {
        if (!world.isRemote) {
            if (player.getHeldItemMainhand().getItem() instanceof BaseBagItem) {
                player.openContainer(getNamedContainer(player.getHeldItemMainhand()));
            }
        }
        return ActionResult.newResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }

    public IItemHandler getInventory(ItemStack bag, ItemStack stack) {

        if (stack.getCount() > 0 && filterGroup().anyMatchesFilter(stack)) {
            return bag.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
                    .orElseGet(null);
        }
        return null;

    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
        return new InvProvider(size);
    }

    public static class InvProvider implements ICapabilitySerializable<INBT> {

        int size;

        public InvProvider(int size) {
            this.size = size;
            inv = new ItemStackHandler(size);
            opt = LazyOptional.of(() -> inv);
        }

        private final IItemHandler inv;
        private final LazyOptional<IItemHandler> opt;

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, opt);
            }
            return LazyOptional.empty();
        }

        @Override
        public INBT serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
        }

        @Override
        public void deserializeNBT(INBT nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);

        }

    }

}