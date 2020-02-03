package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.HeartOfIceSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeHealContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class HeartOfIceFrostSynergy extends Synergy<BeforeHealContext> {

    @Override
    public String GUID() {
        return "heart_of_ice_frost_synergy";
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Water), 0.1F, 2);

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Frost Essence stacks to increase heal"));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return HeartOfIceSpell.getInstance();
    }

    @Override
    public void tryActivate(BeforeHealContext ctx) {

        int stacks = PotionEffectUtils.getStacks(ctx.caster, FrostEssenceEffect.INSTANCE);

        if (stacks > 0) {
            PotionEffectUtils.reduceStacks(ctx.caster, FrostEssenceEffect.INSTANCE, 500);
            ctx.heal.number += CALC.getCalculatedValue(Load.Unit(ctx.caster)) * stacks;

        }
    }
}