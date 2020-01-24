package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.PetrifyEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GorgonsGazeSpell extends BaseSpell {

    public GorgonsGazeSpell() {

    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
    }

    @Override
    public int getCooldownInSeconds() {
        return 60;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Aoe_Debuff;
    }

    @Override
    public String GUID() {
        return "gorgons_gaze";
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public SpellCalcData getCalculation() {
        return PetrifyEffect.CALC;
    }

    @Override
    public Elements getElement() {
        return Elements.Nature;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Turn all enemies before you into stone: "));
        list.add(new StringTextComponent("Applies debuff: "));
        list.add(new StringTextComponent(""));

        list.addAll(PetrifyEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.GorgonsGaze;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        World world = caster.world;

        Utilities.getEntitiesInFrontOf(3, 3, 15, caster, LivingEntity.class)
                .forEach(x -> PotionEffectUtils.apply(PetrifyEffect.INSTANCE, 10 * 20, caster, x));

        return true;
    }

}
