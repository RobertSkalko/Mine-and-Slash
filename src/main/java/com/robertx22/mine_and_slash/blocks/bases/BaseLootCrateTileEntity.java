package com.robertx22.mine_and_slash.blocks.bases;

import com.robertx22.mine_and_slash.blocks.conditions.IConditionalLootCrate;
import com.robertx22.mine_and_slash.database.quests.actions.OpenedCrateData;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.PlayerOncePerMapData;
import com.robertx22.mine_and_slash.uncommon.capability.QuestsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.PlayerOncePerMap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.item.FireworkRocketEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public abstract class BaseLootCrateTileEntity extends TileEntity implements ITickableTileEntity, IConditionalLootCrate {

    public static final String DATA_LOC = "cdata";
    public static final String IS_DROPPING_LOC = "isD";
    public static final String LOOT_TICKS_LOC = "lticks";
    public static final String TIMES_TO_DROP = "timesToDrop";

    PlayerOncePerMapData data = new PlayerOncePerMapData();
    PlayerEntity player;

    int timesToDrop = getTimesToDrop();
    public boolean isDroppingLoot = false;
    int dropLootTicks = 0;

    public BaseLootCrateTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public void firework() {

        // turns invisible for some reason?
        FireworkRocketEntity firework = new FireworkRocketEntity(
                world, pos.getX(), pos.getY() + 2, pos.getZ(), ItemStack.EMPTY);
        firework.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
        firework.setInvulnerable(true);
        WorldUtils.spawnEntity(world, firework);

    }

    public abstract List<ItemStack> generateLoot(PlayerEntity player);

    private final void dropLoot(PlayerEntity player) {

        timesToDrop--;

        if (player != null) {

            List<ItemStack> loot = MasterLootGen.generateLoot(new LootInfo(player).setMinimum(1));

            for (ItemStack stack : loot) {
                WorldUtils.spawnEntity(world, new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack));
            }
            if (loot.size() > 0) {
                firework();
            }
        }

    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        CompoundNBT datanbt = new CompoundNBT();
        PlayerOncePerMap.Save(datanbt, data);
        nbt.put(DATA_LOC, datanbt);
        nbt.putInt(TIMES_TO_DROP, this.timesToDrop);
        nbt.putBoolean(IS_DROPPING_LOC, isDroppingLoot);
        nbt.putInt(LOOT_TICKS_LOC, dropLootTicks);

        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        this.data = PlayerOncePerMap.Load(nbt.getCompound(DATA_LOC));
        if (data == null) {
            data = new PlayerOncePerMapData();
        }

        this.timesToDrop = nbt.getInt(TIMES_TO_DROP);
        this.isDroppingLoot = nbt.getBoolean(IS_DROPPING_LOC);
        this.dropLootTicks = nbt.getInt(LOOT_TICKS_LOC);

    }

    public void activateDrops(PlayerEntity player) {
        data.add(player);
        this.player = player;
        this.isDroppingLoot = true;
        this.timesToDrop = this.getTimesToDrop();

        player.getCapability(QuestsCap.Data)
                .ifPresent(x -> x.onAction(player, new OpenedCrateData(player, Load.Unit(player), this)));

    }

    public int getTimesToDrop() {
        return 5;
    }

    public void reset() {

        isDroppingLoot = false;
        this.timesToDrop = getTimesToDrop();

    }

    public boolean finished() {
        return timesToDrop < 1;
    }

    @Override
    public void tick() {

        if (world == null || pos == null) {
            return;
        }
        if (world.isRemote) {
            return;
        }

        if (isDroppingLoot) {

            if (finished()) {
                reset();
            }

            ParticleEnum.sendToClients(
                    getPos(), world, new ParticlePacketData(getPos(), ParticleEnum.CIRCLE_REDSTONE).radius(1.3F)
                            .color(Elements.Thunder.getRGBColor()));

            dropLootTicks++;

            if (timesToDrop > 0) {
                if (dropLootTicks > 20) {
                    dropLootTicks = 0;
                    this.dropLoot(player);
                }
            }
        }

    }

    public void tryActivate(PlayerEntity player) {

        if (data == null) {
            data = new PlayerOncePerMapData();
        }

        if (condition().canOpenCrate(player)) {

            if (data.canDo(player)) {

                if (isDroppingLoot) {
                    player.sendMessage(new StringTextComponent("This crate is currently being used."));
                } else {
                    activateDrops(player);
                }

            } else {
                player.sendMessage(new StringTextComponent("You have already used this crate. Come again next map!"));
            }

        } else {
            player.sendMessage(condition().tellCondition());
        }

    }

}

