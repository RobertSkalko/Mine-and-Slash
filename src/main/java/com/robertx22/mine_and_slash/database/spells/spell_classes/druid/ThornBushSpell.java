package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.blocks.thorn_bush.ThornBushTileEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSeedSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.IBlockSpawner;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ThornBushSpell extends BaseSeedSpell implements IBlockSpawner {

    private ThornBushSpell() {
    }

    public static ThornBushSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
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
        return "thorn_bush";
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Nature), 0.5F, 7);

    @Override
    public SpellCalcData getCalculation() {
        return CALC;
    }

    @Override
    public Elements getElement() {
        return Elements.Nature;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText("Summons a bush that attacks enemies nearby."));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThornBush;
    }

    @Override
    public void spawnBlock(LivingEntity caster, World world, BlockPos pos, BaseSpell spell) {
        caster.world.setBlockState(pos, BlockRegister.THORN_BUSH.get()
            .getDefaultState());
        ThornBushTileEntity tile = new ThornBushTileEntity();
        tile.setSpellData(new EntitySpellData(spell, caster, ThornBushTileEntity.DURATION_SEC * 20));

        world.setTileEntity(pos, tile);
        tile.initSpellEntity();
    }

    private static class SingletonHolder {
        private static final ThornBushSpell INSTANCE = new ThornBushSpell();
    }
}
