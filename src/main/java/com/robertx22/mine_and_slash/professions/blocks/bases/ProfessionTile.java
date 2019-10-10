package com.robertx22.mine_and_slash.professions.blocks.bases;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.professions.recipe.BaseMaterial;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.uncommon.capability.ProfessionsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ProfessionTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public Professions profession = Professions.ALCHEMY;

    public BaseRecipe currentRecipe;

    public NonNullList<ItemStack> materialStacks;
    public NonNullList<ItemStack> outputStacks;

    public int playerProfLevel = 1;
    public int expEarned = 0;
    public String playerID = "";

    int ticks = 0;

    public ProfessionTile(Professions proff) {
        super(proff.tileEntityType);

        materialStacks = NonNullList.withSize(5, ItemStack.EMPTY);
        outputStacks = NonNullList.withSize(5, ItemStack.EMPTY);
        this.profession = proff;
    }

    public float getCookTimeCompleted() {

        if (this.hasEnoughMaterials() == false) {
            return 0;
        }

        return currentRecipe != null ? MathHelper.clamp((float) ticks / currentRecipe.getCookTimeTicks(), 0, 1) : 0;
    }

    @Override
    public void tick() {

        if (world != null && !world.isRemote) {

            BlockState state = this.getBlockState();

            if (this.currentRecipe == null) {
                this.ticks = 0;
                this.markDirty();
                state = state.with(ProfessionBlock.ENABLED, false);
                this.world.setBlockState(this.pos, state);

            } else if (!this.hasEnoughMaterials()) {
                this.ticks = 0;
                this.markDirty();
                state = state.with(ProfessionBlock.ENABLED, false);
                this.world.setBlockState(this.pos, state);

            } else {

                ticks++;

                state = state.with(ProfessionBlock.ENABLED, true);
                this.world.setBlockState(this.pos, state);

                if (ticks >= this.currentRecipe.getCookTimeTicks()) {
                    if (tryCraft()) {
                        this.expEarned += this.currentRecipe.expGiven;
                        this.ticks = 0;
                        this.markDirty();
                    }

                }
            }
        }

    }

    public boolean canPlayerOpen(ServerPlayerEntity player) {
        return this.playerID.isEmpty() || player.getUniqueID()
                .toString()
                .equals(this.playerID);

    }

    public void onOpenByPlayer(ServerPlayerEntity player) {

        ProfessionsCap.IProfessionsData cap = Load.professions(player);

        if (this.playerProfLevel > cap.getLevel(this.profession)) {
            // backup in case player somehow loses lvl data
            cap.setLevel(this.profession, this.playerProfLevel);
        }

        this.playerProfLevel = cap.getLevel(this.profession);

        if (this.playerID.isEmpty()) {
            this.playerID = player.getUniqueID().toString();
        }

        if (expEarned > 0) {
            cap.gainExp(this.expEarned, this.profession, player);
            this.expEarned = 0;
        }

    }

    public boolean hasEnoughMaterials() {

        if (this.currentRecipe == null) {
            return false;
        }

        int i = 0;
        for (BaseMaterial mat : currentRecipe.getMaterials()) {
            if (mat.isStackValidMaterial(materialStacks.get(i)) == false) {
                return false;
            }
            i++;

        }

        return true;

    }

    public boolean playerMeetsLvlReq() {
        return this.playerProfLevel >= this.currentRecipe.professionLevelReq;
    }

    public boolean tryCraft() {

        if (this.currentRecipe == null) {
            return false;
        }

        if (!playerMeetsLvlReq()) {
            return false;
        }

        if (hasEnoughMaterials()) {

            ItemStack output = this.currentRecipe.getOutput(this).generateStack(this);
            output.setCount(this.profession.getOutputAmount(this, currentRecipe.getOutput(this), this.playerProfLevel));

            if (output.isEmpty()) {
                return false;
            }

            if (!canOutput(output)) {
                return false;
            }

            // get output before materials are consumed

            int i = 0;
            for (BaseMaterial mat : currentRecipe.getMaterials()) {
                ItemStack stack = materialStacks.get(i);

                if (mat.isStackValidMaterial(stack)) {
                    mat.consume(stack, this); // consume materials
                }

                i++;
            }

            ItemStackHandler handler = new ItemStackHandler(this.outputStacks);

            for (int s = 0; s < handler.getSlots(); s++) {

                if (handler.insertItem(s, output, true) == ItemStack.EMPTY) {
                    handler.insertItem(s, output, false);
                    this.markDirty();
                    return true;
                }
            }

            return false;

        }

        return false;

    }

    public boolean canOutput(ItemStack stack) {

        ItemStackHandler handler = new ItemStackHandler(this.outputStacks);

        for (int s = 0; s < handler.getSlots(); s++) {

            if (handler.insertItem(s, stack, true) == ItemStack.EMPTY) {

                return true;
            }
        }

        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory,
                                PlayerEntity playerEntity) {
        return new ProfessionContainer(i, this, this.getPos(), playerInventory);
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
            nbt.putInt("exp", expEarned);
            nbt.putInt("lvl", playerProfLevel);
            nbt.putString("player_id", this.playerID);
            nbt.putInt("ticks", this.ticks);

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
            this.playerProfLevel = nbt.getInt("lvl");
            this.expEarned = nbt.getInt("exp");
            this.ticks = nbt.getInt("ticks");
            this.playerID = nbt.getString("player_id");

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
