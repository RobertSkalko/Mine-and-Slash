package com.robertx22.mine_and_slash.database.spells.synergies.shaman;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeDamageContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThunderSpearLightningStrikeSynergy extends Synergy<BeforeDamageContext> {

    @Override
    public String GUID() {
        return "thunder_spear_lightning_strike_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Static."));
        list.add(new StringTextComponent("Summons a lightning bolt."));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Thunder), 0.75F, 15);

    @Override

    public BaseSpell spellAffected() {
        return ThunderspearSpell.getInstance();
    }

    @Override
    public void tryActivate(BeforeDamageContext ctx) {

        if (PotionEffectUtils.has(ctx.target, StaticEffect.INSTANCE)) {

            PotionEffectUtils.reduceStacks(ctx.target, StaticEffect.INSTANCE);

            SpellUtils.summonLightningStrike(ctx.target);

            ctx.dmg.number += CALC.getCalculatedValue(Load.Unit(ctx.caster));
        }
    }
}
