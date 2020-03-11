package com.robertx22.mine_and_slash.new_content.trader;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.packets.trader.TraderPacket;
import com.robertx22.mine_and_slash.uncommon.datasaving.TraderSaving;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class TraderEntity extends VillagerEntity implements IEntityAdditionalSpawnData {

    public TraderEntity(World world) {
        super(EntityRegister.TRADER, world);
        init();
    }

    public TraderData data = new TraderData();

    public TraderEntity(EntityType type, World world) {
        super(type, world);
        init();
    }

    public TraderEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.TRADER, world);
        init();
    }

    public void init() {
        this.ageUp(100000, false);
        this.addGrowth(10000);
    }

    public void tryBuyItem(PlayerEntity player, int item) {

        ItemStack stack = data.stacks.get(item);

        int price = ISellPrice.getSavedPriceInCommonOres(stack);

        if (ISellPrice.hasEnoughMoney(player, price)) {
            ISellPrice.spendMoney(player, price);

            ISellPrice.removePrice(stack);

            PlayerUtils.giveItem(stack, player);

            SoundUtils.playSound(this, SoundEvents.ENTITY_VILLAGER_TRADE, 1, 1);

        } else {
            SoundUtils.playSound(this, SoundEvents.ENTITY_VILLAGER_NO, 1, 1);
        }

        MMORPG.sendToClient(new TraderPacket(data, this), (ServerPlayerEntity) player);

    }

    @Override
    public void tick() {

        super.tick();

        if (!data.generated) {
            data.generated = true;

            data.generateMerchandise(new LootInfo(world, getPosition()));
        }

        if (!WorldUtils.isMapWorldClass(this.world)) {
            // TODO this.damageEntity(DamageSource.causeThornsDamage(this), 10000);
        }

    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);

        this.data = TraderSaving.Load(nbt);

        if (data == null) {
            data = new TraderData();
        }
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);

        TraderSaving.Save(nbt, data);

    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();
        writeAdditional(nbt);
        buf.writeCompoundTag(nbt);
    }

    @Override
    public void readSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = buf.readCompoundTag();
        this.readAdditional(nbt);
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {

        if (!world.isRemote) {
            MMORPG.sendToClient(new TraderPacket(data, this), (ServerPlayerEntity) player);
        }

        return true;
    }
}
