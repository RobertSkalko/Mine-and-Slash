package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseBuffSpell;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornArmorEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThornArmorSpell extends BaseBuffSpell {

    private ThornArmorSpell() {
    }

    public static ThornArmorSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public String GUID() {
        return "thorn_armor";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
    }

    @Override
    public int getCooldownInSeconds() {
        return 40;
    }

    @Override
    public SpellType getSpellType() {
        return null;
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.empty();
    }

    @Override
    public Elements getElement() {
        return Elements.Nature;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Applies buff: "));

        list.addAll(ThornArmorEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThornArmor;
    }

    @Override
    public SoundEvent getCastSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public BasePotionEffect getEffect() {
        return ThornArmorEffect.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ThornArmorSpell INSTANCE = new ThornArmorSpell();
    }
}
