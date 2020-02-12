package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseBuffSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.PoisonedWeaponsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class PoisonedWeaponsSpell extends BaseBuffSpell {

    private PoisonedWeaponsSpell() {
    }

    public static PoisonedWeaponsSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public String GUID() {
        return "poisoned_weapons";
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
        return SpellType.Self_Buff;
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

        list.addAll(PoisonedWeaponsEffect.getInstance().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.PoisonedWeapons;
    }

    @Override
    public SoundEvent getCastSound() {
        return SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED;
    }

    @Override
    public BasePotionEffect getEffect() {
        return PoisonedWeaponsEffect.getInstance();
    }

    private static class SingletonHolder {
        private static final PoisonedWeaponsSpell INSTANCE = new PoisonedWeaponsSpell();
    }
}
