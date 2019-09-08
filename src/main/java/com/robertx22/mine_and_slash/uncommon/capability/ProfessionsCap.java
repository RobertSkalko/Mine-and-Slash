package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.saveclasses.professions.ProfessionData;
import com.robertx22.mine_and_slash.saveclasses.professions.ProfessionListData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.ProfessionSaving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ProfessionsCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "professions");
    static final int MAX_LEVEL = 100;

    @CapabilityInject(IProfessionsData.class)
    public static final Capability<IProfessionsData> Data = null;

    public interface IProfessionsData extends ICommonCapability {

        int getLevel(Professions prof);

        int getCurrentExp(Professions prof);

        int getExpToReachNextLevel(Professions prof);

        void gainExp(int exp, Professions prof, ServerPlayerEntity player);

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

    public static class Provider extends BaseProvider<IProfessionsData> {

        @Override
        public IProfessionsData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IProfessionsData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IProfessionsData {

        ProfessionListData data = new ProfessionListData();

        @Override
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            ProfessionSaving.Save(nbt, data);

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            ProfessionSaving.Load(nbt);
        }

        @Override
        public int getLevel(Professions prof) {
            return data.getProfessionData(prof).level;
        }

        @Override
        public int getCurrentExp(Professions prof) {
            return data.getProfessionData(prof).exp;
        }

        @Override
        public int getExpToReachNextLevel(Professions prof) {
            int lvl = this.getLevel(prof);

            return (int) calculateStatGrowth(100, lvl);
        }

        public static float calculateStatGrowth(float stat, int lvl) {
            return stat * (float) Math.pow(lvl, getMultiplier(lvl));
        }

        private static float getMultiplier(int lvl) {
            return MathHelper.clamp(0.5F + (float) lvl / 50, 0.5F, 1.5F);
        }

        @Override
        public void gainExp(int exp, Professions prof, ServerPlayerEntity player) {

            ProfessionData pdata = this.data.getProfessionData(prof);

            pdata.exp += exp;

            if (pdata.exp > this.getExpToReachNextLevel(prof) && MAX_LEVEL > pdata.level) {
                pdata.level++;
                player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1, 1);
            }

        }
    }

    public static class Storage extends BaseStorage<IProfessionsData> {

    }

}
