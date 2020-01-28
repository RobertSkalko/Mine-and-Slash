package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.blocks.magma_flower.MagmaFlowerTileEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SComp;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class MagmaFlowerSpell extends BaseSpell {

    public MagmaFlowerSpell() {

    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.EMBER_MAGE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 30;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.LASTING_AOE;
    }

    @Override
    public String GUID() {
        return "magma_flower";
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public int useTimeTicks() {
        return 0;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Fire), 0.2F, 2);

    @Override
    public SpellCalcData getCalculation() {
        return CALC;
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new SComp("Attacks enemies nearby."));
        list.add(new SComp("Heals user for same value for every hit."));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.BlazingInferno;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        RayTraceResult ray = caster.pick(20, 0.0F, false);

        if (ray instanceof BlockRayTraceResult) {
            BlockRayTraceResult blockray = (BlockRayTraceResult) ray;

            BlockPos pos = blockray.getPos().up();

            if (caster.world.getBlockState(pos).isAir(caster.world, pos))

                caster.world.setBlockState(pos, BlockRegister.MAGMA_FLOWER_BLOCK.getDefaultState());

            MagmaFlowerTileEntity tile = new MagmaFlowerTileEntity();
            tile.setSpellData(new EntitySpellData(this, caster, MagmaFlowerTileEntity.DURATION_SEC));

            caster.world.setTileEntity(pos, tile);

        }

        SoundUtils.playSound(caster, SoundEvents.BLOCK_FIRE_AMBIENT, 1, 1);

        return true;
    }

}
