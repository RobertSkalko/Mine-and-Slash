package com.robertx22.mine_and_slash.database.spells.synergies.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeHealContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstantHealRemoveDebuffSynergy extends Synergy<BeforeHealContext> {

    @Override
    public String GUID() {
        return "instant_heal_remove_debuff_synergy";
    }

    public static SpellCalcData CALC = SpellCalcData.allSpellDamages(0.5F, 5);

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Removes 1 negative effect."));

        list.add(new StringTextComponent("Increases heal by:"));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return InstantHealSpell.getInstance();
    }

    @Override
    public void tryActivate(BeforeHealContext ctx) {

        ctx.heal.number += CALC.getCalculatedValue(Load.Unit(ctx.caster));

        Optional<EffectInstance> neg = ctx.target.getActivePotionEffects()
            .stream()
            .filter(x -> x.getPotion()
                .getEffectType() == EffectType.HARMFUL)
            .findAny();

        if (neg.isPresent()) {
            ctx.target.removePotionEffect(neg.get()
                .getPotion());
        }

    }
}
