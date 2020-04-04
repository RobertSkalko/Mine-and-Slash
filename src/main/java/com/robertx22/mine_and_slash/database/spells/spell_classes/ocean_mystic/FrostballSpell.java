package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FrostballEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FrostballSpell extends BaseProjectileSpell {

    private FrostballSpell() {
        this.allowedAsRightClickOn = AllowedAsRightClickOn.MAGE_WEAPON;
    }

    public static FrostballSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new FrostballEntity(world);
    }

    @Override
    public SoundEvent getCastSound() {
        return SoundEvents.ENTITY_SNOWBALL_THROW;
    }

    @Override
    public Elements getElement() {
        return Elements.Water;
    }

    @Override
    public float getShootSpeed() {
        return 0.5F;
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
    public int getCooldownInTicks() {
        return 10;
    }

    @Override
    public int useTimeTicks() {
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

    private static class SingletonHolder {
        private static final FrostballSpell INSTANCE = new FrostballSpell();
    }
}