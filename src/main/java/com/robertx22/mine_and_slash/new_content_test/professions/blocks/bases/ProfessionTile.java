package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseMaterial;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ProfessionTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public Professions profession = Professions.ALCHEMY;

    public BaseRecipe currentRecipe;

    public NonNullList<ItemStack> recipeStacks;

    public NonNullList<ItemStack> materialStacks;
    public NonNullList<ItemStack> outputStacks;

    public ProfessionTile(Professions proff) {
        super(proff.tileEntityType);
        recipeStacks = NonNullList.withSize(6 * 9, ItemStack.EMPTY);
        materialStacks = NonNullList.withSize(5, ItemStack.EMPTY);
        outputStacks = NonNullList.withSize(5, ItemStack.EMPTY);
        this.profession = proff;
    }

    public boolean hasEnoughMaterials() {

        int i = 0;
        for (BaseMaterial mat : currentRecipe.getMaterials()) {
            if (mat.isStackValidMaterial(materialStacks.get(i)) == false) {
                return false;
            }
            i++;

        }

        return true;

    }

    public boolean tryCraft() {

        if (this.currentRecipe == null) {
            return false;
        }

        if (hasEnoughMaterials()) {

            ItemStack output = this.currentRecipe.getOutput(this).getItemStack();
            // get output before materials are consumed

            int i = 0;
            for (BaseMaterial mat : currentRecipe.getMaterials()) {
                ItemStack stack = materialStacks.get(i);

                if (mat.isStackValidMaterial(stack)) {
                    mat.consume(stack); // consume materials
                }

                i++;
            }

            ItemStackHandler handler = new ItemStackHandler(this.outputStacks);

            for (int s = 0; s < handler.getSlots(); s++) {
                if (handler.insertItem(s, output, true) == ItemStack.EMPTY) {
                    handler.insertItem(s, output, false);
                    return true;
                }
            }

            return false;

        }

        return false;

    }

    @Override
    public void tick() {

        if (tryCraft()) {
            this.markDirty();
        }

    }

    public void scrollToRow(int row) {
        List<BaseRecipe> recipes = this.profession.recipes();

        int x = 0;
        for (int i = row * 9; i < row * 9 + ProfessionRecipeContainer.size; i++) {
            if (recipes.size() > i) {
                this.recipeStacks.set(x, recipes.get(i).getPreviewRecipeStack(this));
                x++;
            }
        }
    }

    public void openCraftingForRecipe(ServerPlayerEntity player, BaseRecipe recipe) {

        currentRecipe = recipe;

        this.markDirty();

        NetworkHooks.openGui(player, this, extraData -> {
            extraData.writeBlockPos(this.getPos());
        });

    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory,
                                PlayerEntity playerEntity) {

        if (currentRecipe == null) {
            return new ProfessionRecipeContainer(i, this, this.getPos(), playerInventory);
        } else {
            return new ProfessionCraftingContainer(i, this, this.getPos(), playerInventory);
        }

    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        try {
            nbt.put("materials", stacksToNbt(this.materialStacks, "materials"));
            nbt.put("outputs", stacksToNbt(this.outputStacks, "outputs"));
            if (this.currentRecipe != null) {
                nbt.putString("recipe", this.currentRecipe.GUID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        try {
            this.currentRecipe = SlashRegistry.Recipes().get(nbt.getString("recipe"));

            final byte NBT_TYPE_COMPOUND = 10;
            addStacksToListFromNbt(nbt.getList("materials", NBT_TYPE_COMPOUND), this.materialStacks, "materials");
            addStacksToListFromNbt(nbt.getList("outputs", NBT_TYPE_COMPOUND), this.outputStacks, "outputs");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT updateTag = pkt.getNbtCompound();
        handleUpdateTag(updateTag);
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tag = getUpdateTag();
        final int METADATA = 0;
        return new SUpdateTileEntityPacket(this.pos, METADATA, tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    public static ListNBT stacksToNbt(List<ItemStack> stacks, String id) {
        ListNBT dataForAllSlots = new ListNBT();
        for (int i = 0; i < stacks.size(); ++i) {
            if (!stacks.get(i).isEmpty()) {
                CompoundNBT dataForThisSlot = new CompoundNBT();
                dataForThisSlot.putByte(id, (byte) i);
                stacks.get(i).write(dataForThisSlot);
                dataForAllSlots.add(dataForThisSlot);
            }
        }
        return dataForAllSlots;
    }

    public static void addStacksToListFromNbt(ListNBT listnbt, List<ItemStack> stacks,
                                              String id) {

        for (int i = 0; i < listnbt.size(); ++i) {
            CompoundNBT dataForOneSlot = listnbt.getCompound(i);
            byte slotNumber = dataForOneSlot.getByte(id);
            if (slotNumber >= 0 && slotNumber < stacks.size()) {
                stacks.set(slotNumber, ItemStack.read(dataForOneSlot));
            }
        }

    }

}
