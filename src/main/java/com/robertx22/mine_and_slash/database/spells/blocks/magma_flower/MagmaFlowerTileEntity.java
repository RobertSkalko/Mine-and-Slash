package com.robertx22.mine_and_slash.database.spells.blocks.magma_flower;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class MagmaFlowerTileEntity extends TileEntity implements ISpellEntity, ITickableTileEntity {

    public EntitySpellData data = new EntitySpellData();

    public MagmaFlowerTileEntity() {
        super(BlockRegister.MAGMA_FLOWER_TILE);
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        this.data = EntitySpellDataSaving.Load(nbt);
    }

    @Override
    public void tick() {

        float RADIUS = 1.5F;

        data.ticksExisted++;

        if (this.data.ticksExisted > durationInTicks()) {
            this.remove();
            this.world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {

            if (data.ticksExisted % 20 == 0) {

                LivingEntity caster = data.getCaster(world);
                EntityCap.UnitData data = Load.Unit(caster);

                ParticleEnum.sendToClients(
                        pos, world, new ParticlePacketData(pos, ParticleEnum.AOE).radius(RADIUS)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleTypes.FLAME)
                                .amount(15));

                List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(
                        RADIUS, pos.getX(), pos.getY(), pos.getZ(), world);
                entities.removeIf(x -> x == caster);

                entities.forEach(x -> {
                    SpellDamageEffect dmg = getSetupSpellDamage(x);
                    dmg.Activate();
                    SoundUtils.playSound(x, SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);

                    HealEffect heal = new HealEffect(new ResourcesData.Context(data, caster, ResourcesData.Type.HEALTH,
                                                                               getSpellData().getSpell()
                                                                                       .getCalculation()
                                                                                       .getCalculatedValue(data),
                                                                               ResourcesData.Use.RESTORE,
                                                                               getSpellData().getSpell()
                    ));

                    heal.Activate();

                });

            }
        }

    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        EntitySpellDataSaving.Save(nbt, data);
        return nbt;

    }

    public static int DURATION_SEC = 15;

    @Override
    public int durationInSeconds() {
        return DURATION_SEC;
    }

    @Override
    public EntitySpellData getSpellData() {
        return data;
    }

    @Override
    public void setSpellData(EntitySpellData data) {
        this.data = data;
    }

    @Override
    public void writeSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();
        write(nbt);
        buf.writeCompoundTag(nbt);
    }

    @Override
    public void readSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = buf.readCompoundTag();
        this.read(nbt);
    }
}
