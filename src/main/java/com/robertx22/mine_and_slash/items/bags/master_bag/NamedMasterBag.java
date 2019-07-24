package com.robertx22.mine_and_slash.items.bags.master_bag;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class NamedMasterBag implements INamedContainerProvider {

    private final ItemStack stack;
    ContainerMasterBag.ItemType type;

    public NamedMasterBag(ItemStack stack, ContainerMasterBag.ItemType type) {
        this.stack = stack;
        this.type = type;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerMasterBag(i, inventory, new InventoryMasterBag(stack, type), type);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");
    }

}

