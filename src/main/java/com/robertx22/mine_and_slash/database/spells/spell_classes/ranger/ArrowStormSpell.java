package com.robertx22.mine_and_slash.database.spells.spell_classes.ranger;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.ArrowStormEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSummonAtSightSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ArrowStormSpell extends BaseSummonAtSightSpell {

    private ArrowStormSpell() {
        this.castRequirements.add(BaseSpell.REQUIRE_SHOOTABLE);
    }

    public static ArrowStormSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SoundEvent getCastSound() {
        return null;
    }

    @Override
    public int getMaxSpellLevelNormal() {
        return 0;
    }

    @Override
    public Function<World, Entity> summonNewEntity() {
        return null;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.RANGER;
    }

    @Override
    public int getCooldownInSeconds() {
        return 45;
    }

    @Override
    public Entity newEntity(World world) {
        return new ArrowStormEntity(world);
    }

    @Override
    public String GUID() {
        return "arrow_storm";
    }

    @Override
    public int getManaCost() {
        return 50;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.scaleWithAttack(0.05F, 0.2F, 2);
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summons an arrow storm, dealing damage with each arrow: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ArrowStorm;
    }

    private static class SingletonHolder {
        private static final ArrowStormSpell INSTANCE = new ArrowStormSpell();
    }
}
