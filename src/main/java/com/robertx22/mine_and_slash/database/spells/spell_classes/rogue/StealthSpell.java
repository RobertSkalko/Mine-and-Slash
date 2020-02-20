package com.robertx22.mine_and_slash.database.spells.spell_classes.rogue;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseBuffSpell;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.rogue.StealthEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class StealthSpell extends BaseBuffSpell {

    private StealthSpell() {
    }

    public static StealthSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SoundEvent getCastSound() {
        return SoundEvents.BLOCK_SMOKER_SMOKE;
    }

    @Override
    public BasePotionEffect getEffect() {
        return StealthEffect.getInstance();
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.ROGUE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 5;
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Self_Buff;
    }

    @Override
    public String GUID() {
        return "rogue_stealth";
    }

    @Override
    public int getManaCost() {
        return 15;
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
        return Elements.Elemental;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {
        caster.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 1000, 1));
        return super.cast(caster, ticksInUse);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();
        list.add(new StringTextComponent("Applies buff: "));
        list.addAll(StealthEffect.getInstance()
            .GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Stealth;
    }

    private static class SingletonHolder {
        private static final StealthSpell INSTANCE = new StealthSpell();
    }
}
