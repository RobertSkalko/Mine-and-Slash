package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeDamageContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlizzardFrostEssenceSynergy extends Synergy<BeforeDamageContext> {

    @Override
    public String GUID() {
        return "blizzard_frost_essence_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Increases Damage per Caster's Frost Essence stack."));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    static SpellCalcData CALC = SpellCalcData.one(ElementalSpellDamage.MAP.get(Elements.Water), 0.02F, 1);

    @Override
    public BaseSpell spellAffected() {
        return BlizzardSpell.getInstance();
    }

    @Override
    public void tryActivate(BeforeDamageContext ctx) {

        int stacks = PotionEffectUtils.getStacks(ctx.caster, FrostEssenceEffect.INSTANCE);
        ctx.dmg.number += CALC.getCalculatedValue(Load.Unit(ctx.target)) * stacks;

    }
}
