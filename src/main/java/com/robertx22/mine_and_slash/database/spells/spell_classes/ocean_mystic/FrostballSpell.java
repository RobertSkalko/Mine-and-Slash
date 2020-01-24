package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.proj.FrostballEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FrostballSpell extends BaseProjectileSpell {

    public FrostballSpell() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Water;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.OCEAN_MYSTIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public String GUID() {
        return "frostball";
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 1F, 10);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.singleTargetProjectile());

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Frostball;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        World world = caster.world;
        Vec3d pos = caster.getPositionVector();
        FrostballEntity en = SpellUtils.getSpellEntity(new FrostballEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, 2);
        caster.world.addEntity(en);

        caster.world.playMovingSound(
                (PlayerEntity) null, en, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);

        return true;
    }
}