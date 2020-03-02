package com.robertx22.mine_and_slash.uncommon.capability.player;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerLastThrownItemCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "thrown_item");

    @CapabilityInject(ILastThrownItem.class)
    public static final Capability<ILastThrownItem> Data = null;

    public interface ILastThrownItem extends ICommonPlayerCap {

        ItemStack get(PlayerEntity player);

        void set(ItemStack stack, PlayerEntity player);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

            if (event.getObject() instanceof PlayerEntity) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<ILastThrownItem> {

        @Override
        public ILastThrownItem defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<ILastThrownItem> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements ILastThrownItem {

        ItemStack stack = ItemStack.EMPTY;
        int livingTicks = 0;

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            nbt.put("stack", stack.serializeNBT());
            nbt.putInt("ticks", livingTicks);

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {

            try {
                this.stack = ItemStack.read(nbt.getCompound("stack"));
                this.livingTicks = nbt.getInt("ticks");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public ItemStack get(PlayerEntity player) {
            if (player.ticksExisted > livingTicks + 100) {
                this.stack = ItemStack.EMPTY;
                return stack;
            }

            return stack == null ? ItemStack.EMPTY : stack;
        }

        @Override
        public void set(ItemStack stack, PlayerEntity player) {
            this.stack = stack;
            this.livingTicks = player.ticksExisted;
            Load.Unit(player)
                .setEquipsChanged(true);
        }

        @Override
        public PlayerCaps getCapType() {
            return PlayerCaps.LAST_THROWN_ITEM;
        }
    }

    public static class Storage extends BaseStorage<ILastThrownItem> {

    }

}
