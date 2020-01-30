package com.robertx22.mine_and_slash.database.spells.spell_classes.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseBuffSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.cleric.RighteousFuryEffect;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class RighteousFurySpell extends BaseBuffSpell {

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Self_Buff;
    }

    @Override
    public String GUID() {
        return "righteous_fury";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.CLERIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 60;
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public int useTimeTicks() {
        return 15;
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

        list.addAll(RighteousFuryEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.RighteousFury;
    }

    @Override
    public SoundEvent getCastSound() {
        return SoundEvents.BLOCK_FIRE_EXTINGUISH;
    }

    @Override
    public BasePotionEffect getEffect() {
        return RighteousFuryEffect.INSTANCE;
    }
}