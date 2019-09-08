package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.blocks.bases.BaseTileContainer;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ProfessionCraftingContainer extends BaseTileContainer {

    public int numRows = 6;

    Professions profession = Professions.ALCHEMY;

    public BlockPos pos;

    public ProfessionCraftingContainer(int i, PlayerInventory playerInventory,
                                       PacketBuffer packetBuffer) {
        this(i, new AlchemyTile(), packetBuffer.readBlockPos(), playerInventory);
    }

    protected ProfessionCraftingContainer(int id, ProfessionTile tile, BlockPos pos,
                                          PlayerInventory invPlayer) {
        super(6 * 9, ContainerTypeRegisters.PROFESSION_CRAFTING_CONTAINER, id);
        this.profession = tile.profession;
        this.pos = pos;

        renderContainerInventory(tile);
        renderPlayerInventory(invPlayer);

    }

    public void renderContainerInventory(ProfessionTile tile) {

        int y = ProfessionCraftingGui.y / 2 - 79;

        int i = 0;
        int x = 47;

        for (ItemStack material : tile.materialStacks) {
            this.addSlot(new SlotItemHandler(new ItemStackHandler(tile.materialStacks), i, x, y));
            y += 18;
            i++;

        }

        i = 0;
        y = ProfessionCraftingGui.y / 2 - 79;
        x = 191;

        for (ItemStack output : tile.outputStacks) {
            this.addSlot(new SlotItemHandler(new ItemStackHandler(tile.outputStacks), i, x, y));
            y += 18;
            i++;
        }
    }

    public void renderPlayerInventory(PlayerInventory playerInv) {

        int addY = 22;
        int addX = 39;

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                int index = column + row * 9 + 9;
                int x = 8 + column * 18 + addX;
                int y = ProfessionCraftingGui.y / 2 + row * 18 + addY;
                this.addSlot(new Slot(playerInv, index, x, y));
            }
        }

        for (int index = 0; index < 9; ++index) {
            this.addSlot(new Slot(playerInv, index, 8 + index * 18 + addX, 161 + addY));
        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
