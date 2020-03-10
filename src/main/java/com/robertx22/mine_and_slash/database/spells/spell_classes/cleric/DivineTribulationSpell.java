package com.robertx22.mine_and_slash.database.spells.spell_classes.cleric;

import com.robertx22.mine_and_slash.database.spells.entities.proj.DivineTribulationEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DivineTribulationSpell extends BaseProjectileSpell {

    private DivineTribulationSpell() {
    }

    public static DivineTribulationSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new DivineTribulationEntity(world);
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public SoundEvent getShootSound() {
        return SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER;
    }

    @Override
    public Elements getElement() {
        return Elements.Thunder;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.CLERIC;
    }

    @Override
    public float getShootSpeed() {
        return 0.2F;
    }

    @Override
    public int getCooldownInSeconds() {
        return 60;
    }

    @Override
    public int useTimeTicks() {
        return 15;
    }

    @Override
    public String GUID() {
        return "divine_tribulation";
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 0.4F, 8);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText("Throw out a star that floats up the sky."));
        list.add(new SText("Soon summons tribulation that attacks enemies."));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.DivineTribulation;
    }

    private static class SingletonHolder {
        private static final DivineTribulationSpell INSTANCE = new DivineTribulationSpell();
    }
}