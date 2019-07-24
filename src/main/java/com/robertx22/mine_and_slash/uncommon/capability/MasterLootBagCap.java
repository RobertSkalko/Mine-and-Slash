package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.items.bags.master_bag.ContainerMasterBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.ItemMasterBag;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class MasterLootBagCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "master_loot_bag_data");

    @CapabilityInject(IMasterLootBagData.class)
    public static final Capability<IMasterLootBagData> Data = null;

    public interface IMasterLootBagData extends ICommonCapability {

        ItemStackHandler getInventory(ContainerMasterBag.ItemType type);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onMake(AttachCapabilitiesEvent<ItemStack> event) {
            if (event.getObject().getItem() instanceof ItemMasterBag) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<IMasterLootBagData> {

        @Override
        public IMasterLootBagData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IMasterLootBagData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IMasterLootBagData {

        HashMap<String, ItemStackHandler> items = new HashMap<>();

        @Override
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (items != null) {

                for (Map.Entry<String, ItemStackHandler> entry : items.entrySet()) {
                    nbt.put(entry.getKey(), entry.getValue().serializeNBT());
                }

            }

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT value) {

            this.items.clear();

            CompoundNBT nbt = value;

            for (ContainerMasterBag.ItemType type : ContainerMasterBag.ItemType.values()) {

                CompoundNBT tag = nbt.getCompound(type.toString());

                if (tag != null) {
                    ItemStackHandler handler = new ItemStackHandler(6 * 9);
                    handler.deserializeNBT(tag);

                    ItemStackHandler handler2 = new ItemStackHandler(6 * 9);

                    for (int i = 0; i < handler.getSlots(); i++) {
                        handler2.setStackInSlot(i, handler.getStackInSlot(i));
                    }

                    items.put(type.toString(), handler2);
                }

            }

        }

        @Override
        public ItemStackHandler getInventory(ContainerMasterBag.ItemType type) {

            String key = type.toString();

            if (items.containsKey(key) == false) {
                items.put(key, new ItemStackHandler(6 * 9));
            }

            return items.get(key);
        }
    }

    public static class Storage extends BaseStorage<IMasterLootBagData> {

    }

}
