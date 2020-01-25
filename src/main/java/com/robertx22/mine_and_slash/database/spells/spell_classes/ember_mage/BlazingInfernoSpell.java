package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlazingInfernoSpell extends BaseSpell {

    public BlazingInfernoSpell() {

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
        return "blazing_inferno";
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public SpellCalcData getCalculation() {
        return BlazingInfernoEffect.CALC;
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.buff());

        list.addAll(BlazingInfernoEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.BlazingInferno;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        PotionEffectUtils.applyToSelf(BlazingInfernoEffect.INSTANCE, caster);

        SoundUtils.playSoundAtPlayer(caster, SoundEvents.ITEM_FIRECHARGE_USE, 1, 1);

        return true;
    }

}
