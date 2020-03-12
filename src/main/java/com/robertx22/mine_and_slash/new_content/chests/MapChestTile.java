package com.robertx22.mine_and_slash.new_content.chests;

import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModTileEntities;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MapChestTile extends ChestTileEntity implements INamedContainerProvider {

    public MapChestTile() {
        super(ModTileEntities.MAP_CHEST.get());

    }

    public enum ChestTypes {
        NORMAL

    }

    public NonNullList<ItemStack> getItems() {
        return super.getItems();
    }

    public void addItems(NonNullList<ItemStack> items) {
        NonNullList<ItemStack> inv = getItems();

        addMiscResources(items);

        List<ItemStack> list = addEmptyStacksAndShuffle(items);

        int i = 0;
        for (ItemStack x : list) {
            inv.set(i, x);

            i++;
        }

    }

    public void addMiscResources(NonNullList<ItemStack> items) {

        if (RandomUtils.roll(25)) {
            ItemStack tome = new ItemStack(ModItems.IDENTIFY_TOME.get());
            tome.setCount(RandomUtils.RandomRange(1, 3));
            items.add(tome);

        }

        if (RandomUtils.roll(30)) {
            ItemStack apple = new ItemStack(Items.APPLE);
            items.add(apple);
        }
        if (RandomUtils.roll(15)) {
            ItemStack food = new ItemStack(Items.COOKED_PORKCHOP);
            items.add(food);
        }

    }

    public List<ItemStack> addEmptyStacksAndShuffle(NonNullList<ItemStack> items) {
        List<ItemStack> shuffled = new ArrayList<>();

        int n = 0;

        for (int i = 0; i < MapChestContainer.SIZE; i++) {
            if (n >= items.size() || RandomUtils.roll(50)) {
                shuffled.add(ItemStack.EMPTY);
            } else {
                shuffled.add(items.get(n));
                n++;
            }

        }

        return shuffled;
    }

    @Override
    public void fillWithLoot(@Nullable PlayerEntity p_184281_1_) {
        // we dont want to set loot now. We want to do that by processing structure blocks and spawning the chest with the loot
    }

    @Override
    public ITextComponent getDisplayName() {
        return new SText("");
    }

    @Nullable
    @Override
    public Container createMenu(int num, PlayerInventory inventory, PlayerEntity player) {
        return new MapChestContainer(num, inventory, this);
    }

    @Override
    protected void onOpenOrClose() {
        Block block = this.getBlockState()
            .getBlock();
        if (block instanceof MapChestBlock) {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

}
