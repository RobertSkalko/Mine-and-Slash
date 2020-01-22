package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WeaponSpeedCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "weapon_speed");

    @CapabilityInject(IWeaponSpeedCap.class)
    public static final Capability<IWeaponSpeedCap> Data = null;

    public interface IWeaponSpeedCap extends ICommonCapability {
        void onAttack(PlayerEntity en);

        boolean canAttack();

        void onTick();
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (event.side.isServer()) {
            Load.weaponSpeed(event.player).onTick();
        }
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

    public static class Provider extends BaseProvider<IWeaponSpeedCap> {

        @Override
        public IWeaponSpeedCap defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IWeaponSpeedCap> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IWeaponSpeedCap {

        int cooldownTicks = 0;

        @Override
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            nbt.putInt("ticks", cooldownTicks);

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.cooldownTicks = nbt.getInt("ticks");
        }

        @Override
        public void onAttack(PlayerEntity en) {
            ItemStack stack = en.getHeldItemMainhand();
            GearItemData gear = Gear.Load(stack);
            if (gear != null) {
                this.cooldownTicks = gear.GetBaseGearType().cooldownTicks();
            }
        }

        @Override
        public boolean canAttack() {
            return cooldownTicks <= 0;
        }

        @Override
        public void onTick() {
            if (cooldownTicks > 0) {
                this.cooldownTicks = MathHelper.clamp(this.cooldownTicks - 1, 0, 1000);
            }
        }

        @Override
        public CapTypes getCapType() {
            return null;
        }
    }

    public static class Storage extends BaseStorage<IWeaponSpeedCap> {

    }

}
