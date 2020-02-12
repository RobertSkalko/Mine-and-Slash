package com.robertx22.mine_and_slash.database.spells.spell_classes.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseBuffSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.cleric.DivineShieldEffect;
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

public class DivineShieldSpell extends BaseBuffSpell {

    private DivineShieldSpell() {
    }

    public static DivineShieldSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Self_Buff;
    }

    @Override
    public String GUID() {
        return "divine_shield";
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
        return 5;
    }

    @Override
    public SpellCalcData getCalculation() {
        return null;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.buff());

        list.addAll(DivineShieldEffect.getInstance().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.DivineShield;
    }

    @Override
    public SoundEvent getCastSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION;
    }

    @Override
    public BasePotionEffect getEffect() {
        return DivineShieldEffect.getInstance();
    }

    private static class SingletonHolder {
        private static final DivineShieldSpell INSTANCE = new DivineShieldSpell();
    }
}