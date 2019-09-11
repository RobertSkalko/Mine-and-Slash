package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.blocks.bases.BaseTileContainer;
import com.robertx22.mine_and_slash.blocks.slots.handlerslots.MaterialSlot;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy.AlchemyTile;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseMaterial;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ProfessionContainer extends BaseTileContainer {

    public static int size = 6 * 9;

    ProfessionTile tile;
    Professions profession = Professions.ALCHEMY;

    public BlockPos pos;

    static int PLAYER_INV_INDEX = 0;
    static int PLAYER_INV_END = 36;

    PlayerInventory playerInventory;

    public ProfessionContainer(int i, PlayerInventory playerInventory,
                               PacketBuffer packetBuffer) {
        this(i, new AlchemyTile(), packetBuffer.readBlockPos(), playerInventory);
    }

    protected ProfessionContainer(int id, ProfessionTile tile, BlockPos pos,
                                  PlayerInventory invPlayer) {
        super(6 * 9, ContainerTypeRegisters.PROFESSION_RECIPE_CONTAINER, id);
        this.profession = tile.profession;
        this.pos = pos;
        this.tile = tile;
        this.playerInventory = invPlayer;

        addPlayerSlots(invPlayer);
        addTileSlots(tile);
    }

    public void addTileSlots(ProfessionTile tile) {

        int addX = 142 + 44;
        int addY = ProfessionGui.y / 2 + 9;

        ItemStackHandler outputhandler = new ItemStackHandler(tile.outputStacks);

        for (int i = 0; i < tile.outputStacks.size(); i++) {
            this.addSlot(new SlotItemHandler(outputhandler, i, addX, addY));
            addX += 18;

        }

        addX = 142 + 44;
        addY -= 52; // for mat slots

        ItemStackHandler mathandler = new ItemStackHandler(tile.materialStacks);
        for (int i = 0; i < tile.materialStacks.size(); i++) {
            this.addSlot(new MaterialSlot(mathandler, i, addX, addY));
            addX += 18;

        }

    }

    public void addPlayerSlots(PlayerInventory playerInv) {

        int addX = 142;
        int addY = ProfessionGui.y / 2 + 35;

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                int index = column + row * 9 + 9;
                int x = 8 + column * 18 + addX;
                int y = row * 18 + addY;
                this.addSlot(new Slot(playerInv, index, x, y));
            }
        }

        for (int index = 0; index < 9; ++index) {
            this.addSlot(new Slot(playerInv, index, 8 + index * 18 + addX, addY + 3 * 18 + 4));
        }

    }

    public void onRecipeChoosen(BaseRecipe recipe) {

        clearMaterials();

        for (int i = 0; i < recipe.getMaterials().size(); i++) {
            this.gatherMaterial(i);
        }

    }

    public void clearMaterials() {
        ItemStackHandler handler = new ItemStackHandler(tile.materialStacks);

        ItemStackHandler playerHandler = this.getPlayerInventoryHandler();

        for (int i = 0; i < tile.materialStacks.size(); i++) {
            ItemStack stack = handler.extractItem(i, handler.getStackInSlot(i)
                    .getCount(), false);

            if (!stack.isEmpty()) {

                boolean inserted = false;

                for (int x = 0; x < playerHandler.getSlots(); x++) {

                    if (this.playerInventory.addItemStackToInventory(stack)) {
                        inserted = true;
                        break;
                    }

                }

                if (!inserted) {
                    handler.insertItem(i, stack, false); // put it back if no place

                }
            }

        }

    }

    public ItemStackHandler getPlayerInventoryHandler() {

        NonNullList<ItemStack> list = NonNullList.withSize(PLAYER_INV_END - PLAYER_INV_INDEX, ItemStack.EMPTY);

        int num = 0;
        for (int i = PLAYER_INV_INDEX; i < PLAYER_INV_END; i++) {
            list.set(num++, this.getInventory().get(i));
        }

        return new ItemStackHandler(list);

    }

    public void gatherMaterial(int num) {

        for (int i = 0; i < this.playerInventory.mainInventory.size(); ++i) {
            ItemStack itemstack = this.playerInventory.mainInventory.get(i).getStack();
            if (!itemstack.isEmpty()) {

                if (this.tile.currentRecipe.getMaterials().size() > num) {

                    BaseMaterial mat = this.tile.currentRecipe.getMaterials().get(num);

                    if (mat.isStackValidMaterial(itemstack)) {

                        ItemStack copy = itemstack.copy();
                        itemstack.shrink(itemstack.getCount());
                        ItemStackHandler handler = new ItemStackHandler(tile.materialStacks);
                        if (handler.insertItem(num, copy, true) == ItemStack.EMPTY) {
                            handler.insertItem(num, copy, false);
                        }

                    }
                }
            }

        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
