package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.database.bosses.base.BossData;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.MobCaps;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonMobCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jline.utils.Log;

@Mod.EventBusSubscriber
public class BossCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "boss");
    public static final String LOC = "data";

    @CapabilityInject(IBossData.class)
    public static final Capability<IBossData> Data = null;

    public interface IBossData extends ICommonMobCap {

        boolean isBoss();

        void onMobCreation(LivingEntity en);

        void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold treshhold);

        void setIsBoss(boolean b);

        void onHealthChanged(LivingEntity en, IBossData x);
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {
            if (EntityTypeUtils.isMob(event.getObject())) {
                event.addCapability(RESOURCE, new Provider());
            }
        }
    }

    public static class Provider extends BaseProvider<IBossData> {

        @Override
        public IBossData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IBossData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IBossData {

        BossData data = null;
        boolean isBoss = false;
        boolean doneGenerating = false;

        @Override
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            nbt.putBoolean("isBoss", isBoss);
            nbt.putBoolean("doneGen", doneGenerating);

            if (data != null) {
                LoadSave.Save(data, nbt, LOC);
            }
            return nbt;

        }

        @Override
        public MobCaps getCapType() {
            return MobCaps.BOSS;
        }

        @Override
        public void setNBT(CompoundNBT nbt) {

            this.isBoss = nbt.getBoolean("isBoss");
            this.doneGenerating = nbt.getBoolean("doneGen");

            if (isBoss) {
                try {
                    this.data = LoadSave.Load(BossData.class, new BossData(), nbt, LOC);
                } catch (Exception e) {
                    this.data = new BossData();
                    Log.error("Boss Mob data is null, this shouldn't happen!");
                    e.printStackTrace();
                }
            } else {
                data = null;
            }
        }

        @Override
        public boolean isBoss() {
            return isBoss;
        }

        @Override
        public void onMobCreation(LivingEntity en) {
            if (!doneGenerating) {
                if (EntityTypeUtils.isMob(en)) {
                    if (RandomUtils.roll(100)) {

                        this.data = new BossData();
                        this.data.boss = SlashRegistry.Bosses().random().GUID();
                        this.isBoss = true;
                        this.doneGenerating = true;
                    }
                }
            }
        }

        @Override
        public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold treshhold) {
            this.data.getBoss().onHealthTreshholdTriggered(en, treshhold);
        }

        @Override
        public void setIsBoss(boolean b) {

            this.isBoss = b;

            if (!isBoss) {
                doneGenerating = true;
            }
        }

        @Override
        public void onHealthChanged(LivingEntity en, IBossData x) {
            if (isBoss) {
                data.onHealthChanged(en, x);
            }
        }

    }

    public static class Storage extends BaseStorage<IBossData> {

    }

}
