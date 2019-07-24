package com.robertx22.mine_and_slash.spells.self;

import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.bases.SpellBuffCheck;
import com.robertx22.mine_and_slash.potion_effects.all.AoeRegenPotion;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;

public abstract class BaseSpellHeal extends BaseSpell {

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public SpellType Type() {
        return SpellType.Self_Heal;
    }

    @Override
    public Elements Element() {
        return Elements.Physical;
    }

    public int Weight() {
        return super.Weight() * 3;
    }

    public void checkZephyrSpeedBoost(PlayerEntity caster, SpellBuffCheck buffable) {

        if (buffable.getBuff().equals(SpellBuffType.Zephyr_Speed_Boost)) {
            caster.addPotionEffect(new EffectInstance(Effects.SPEED, 200));
        }

    }

    public void checkPurityRemoveNegativeEffect(PlayerEntity caster,
                                                SpellBuffCheck buffable) {

        if (buffable.getBuff().equals(SpellBuffType.Purity_Remove_Negative_Effects)) {
            for (EffectInstance pot : new ArrayList<EffectInstance>(caster.getActivePotionEffects())) {
                if (pot.getPotion().isBeneficial() == false) {
                    caster.removePotionEffect(pot.getPotion());
                    break;
                }
            }
        }

    }

    public void checkAddLightBuff(PlayerEntity caster, SpellBuffCheck buffable) {
        if (buffable.getBuff().equals(SpellBuffType.Light_Aoe_Regen)) {
            caster.addPotionEffect(new EffectInstance(AoeRegenPotion.INSTANCE, 200));
        }

    }

    public void checkSpellBuffs(PlayerEntity caster, SpellBuffCheck buffable) {
        checkZephyrSpeedBoost(caster, buffable);
        checkAddLightBuff(caster, buffable);
        checkPurityRemoveNegativeEffect(caster, buffable);
    }

}
