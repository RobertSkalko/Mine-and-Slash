package com.robertx22.mine_and_slash.items.bags.loot_bag;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class InteractLootBag implements INamedContainerProvider {

    private final ItemStack stack;

    public InteractLootBag(ItemStack stack) {
        this.stack = stack;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerLootBag(i, inventory, new InventoryLootBag(stack));
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Loot Bag");
    }
}
