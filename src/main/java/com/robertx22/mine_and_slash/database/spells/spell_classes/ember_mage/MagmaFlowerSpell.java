package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.blocks.magma_flower.MagmaFlowerTileEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.SeedEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.IBlockSpawner;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MagmaFlowerSpell extends BaseSpell implements IBlockSpawner {

    public MagmaFlowerSpell() {

    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.EMBER_MAGE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 45;
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
        return 20;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Fire), 0.5F, 5);

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

        list.add(new SText("Summons a flower that attacks enemies nearby."));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.MagmaFlower;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        World world = caster.world;
        Vec3d pos = caster.getPositionVector();
        SeedEntity en = SpellUtils.getSpellEntity(new SeedEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, 0.5F);
        caster.world.addEntity(en);

        caster.world.playMovingSound(null, en, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);

        return true;
    }

    @Override
    public void spawnBlock(LivingEntity caster, World world, BlockPos pos, BaseSpell spell) {
        caster.world.setBlockState(pos, BlockRegister.MAGMA_FLOWER_BLOCK.getDefaultState());
        MagmaFlowerTileEntity tile = new MagmaFlowerTileEntity();
        tile.setSpellData(new EntitySpellData(spell, caster, MagmaFlowerTileEntity.DURATION_SEC * 20));
        world.setTileEntity(pos, tile);
    }

}
