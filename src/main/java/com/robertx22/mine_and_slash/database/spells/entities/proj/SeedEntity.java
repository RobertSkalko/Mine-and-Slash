package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.ArrayList;
import java.util.List;

public class SeedEntity extends EntityBaseProjectile {

    public SeedEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public SeedEntity(World worldIn) {
        super(EntityRegister.SEED, worldIn);

    }

    public SeedEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.SEED, world);

    }

    @Override
    public double radius() {
        return 0;
    }

    @Override
    protected void onImpact(RayTraceResult ray) {

    }

    public boolean canPlace(BlockPos pos) {

        if (!world.getBlockState(pos.down())
            .isSolid()) {
            return false;// dont spawn block unless there's solid underneath
        }

        if (!world.getBlockState(pos)
            .isAir(world, pos)) {
            return false; // only replace air
        }
        return true;

    }

    @Override
    public void onTick() {

        try {

            if (!getSpellData().activated && this.getTicksInGround() > 5) {

                getSpellData().activated = true;
                this.remove();

                LivingEntity caster = getCaster();

                List<BlockPos> posToTry = new ArrayList<>();
                posToTry.add(getPosition());
                posToTry.add(getPosition().up());
                posToTry.add(getPosition().down());

                for (BlockPos pos : posToTry) {

                    if (canPlace(pos)) {

                        BaseSpell spell = getSpellData().getSpell();

                        caster.world.setBlockState(pos, spell.getImmutableConfigs().spellBlockToSpawn()
                            .getDefaultState());

                        TileEntity tile = world.getTileEntity(pos);

                        if (tile instanceof ISpellEntity) {
                            ISpellEntity se = (ISpellEntity) tile;
                            se.setSpellData(new EntitySpellData(spell, caster, getSpellData().configs));
                            se.initSpellEntity();
                        }

                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.WHEAT_SEEDS);
    }

    @Override
    public int durationInSeconds() {
        return 10;
    }
}
