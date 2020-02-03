package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.base.BossData;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.MobCaps;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonMobCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
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

        Boss getBoss();

        void setIsBoss(boolean b);

        void spawnParticle(LivingEntity en);

        void onHealthChanged(LivingEntity en, IBossData x);

        boolean hasSynergy(Synergy synergy);

        void setBoss(Boss random);

        void onTick(LivingEntity entityLiving);
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
                if (RandomUtils.roll(0.1F)) {
                    if (WorldUtils.isMapWorldClass(en.world)) {
                        if (EntityTypeUtils.isMob(en)) {

                            this.data = new BossData();
                            this.data.boss = SlashRegistry.Bosses().random().GUID();
                            this.isBoss = true;
                            this.doneGenerating = true;
                        }
                    }
                }
            }
        }

        @Override
        public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold treshhold) {
            this.data.getBoss().onHealthTreshholdTriggered(en, treshhold);
        }

        @Override
        public Boss getBoss() {

            if (data != null) {
                return data.getBoss();
            }

            return null;
        }

        @Override
        public void setIsBoss(boolean b) {

            this.isBoss = b;

            if (!isBoss) {
                doneGenerating = true;
            }
        }

        @Override
        public void spawnParticle(LivingEntity en) {
            if (isBoss && data != null) {

                IParticleData p = getBoss().getParticle();
                if (p != null) {
                    particle(en, p);
                }
            }
        }

        @Override
        public void onHealthChanged(LivingEntity en, IBossData x) {
            if (isBoss) {
                data.onHealthChanged(en, x);
            }
        }

        @Override
        public boolean hasSynergy(Synergy synergy) {
            return getBoss().hasSynergy(synergy);
        }

        @Override
        public void setBoss(Boss boss) {

            this.isBoss = true;
            this.data = new BossData();
            data.boss = boss.GUID();
            this.doneGenerating = true;

        }

        @Override
        public void onTick(LivingEntity en) {
            if (this.isBoss) {
                if (en.isAlive()) {

                    Boss boss = getBoss();
                    if (boss != null) {
                        boss.onTick(en);
                    } else {
                        Log.error("Boss is null for some reason!");
                    }
                }
            }
        }

        private void particle(LivingEntity ent, IParticleData p) {
            Minecraft.getInstance().worldRenderer.addParticle(p, true,
                                                              ent.posX + (ent.world.rand.nextDouble() - 0.5D) * (double) ent
                                                                      .getWidth(),
                                                              ent.posY + ent.world.rand.nextDouble() * (double) ent.getHeight() - 0.25D,
                                                              ent.posZ + (ent.world.rand.nextDouble() - 0.5D) * (double) ent
                                                                      .getWidth(),
                                                              (ent.world.rand.nextDouble() - 0.5D) * 2.0D,
                                                              -ent.world.rand.nextDouble(),
                                                              (ent.world.rand.nextDouble() - 0.5D) * 2.0D
            );
        }

    }

    public static class Storage extends BaseStorage<IBossData> {

    }

}
