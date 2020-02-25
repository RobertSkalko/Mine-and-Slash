package com.robertx22.mine_and_slash.new_content.chests;

import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class MapChestTile extends ChestTileEntity implements INamedContainerProvider {

    public MapChestTile() {
        super(TileEntityRegister.MAP_CHEST.get());

    }

    public enum ChestTypes {
        NORMAL
    }

    public void setItems(NonNullList<ItemStack> items) {
        super.setItems(items);
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
