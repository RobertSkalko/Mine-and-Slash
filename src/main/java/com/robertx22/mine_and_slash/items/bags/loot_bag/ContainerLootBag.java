package com.robertx22.mine_and_slash.items.bags.loot_bag;

import com.robertx22.mine_and_slash.blocks.slots.LootBagSlot;
import com.robertx22.mine_and_slash.items.bags.BaseContainer;
import com.robertx22.mine_and_slash.items.bags.BaseInventory;
import com.robertx22.mine_and_slash.items.bags.BaseSlot;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerLootBag extends BaseContainer {

    public ContainerLootBag(int i, PlayerInventory playerInventory) {
        this(i, playerInventory, new InventoryLootBag(new ItemStack(ItemLootBag.ITEM)));
    }

    public ContainerLootBag(int i, PlayerInventory playerInv, BaseInventory basebag) {
        super(ContainerTypeRegisters.LOOT_BAG, i, playerInv, basebag);
    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new LootBagSlot(inv, index, x, y);
    }

}