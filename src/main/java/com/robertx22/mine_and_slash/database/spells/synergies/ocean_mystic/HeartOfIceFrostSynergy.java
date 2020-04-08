package com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.HeartOfIceSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ColdEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class HeartOfIceFrostSynergy extends OnSpellCastSynergy {

    @Override
    public String GUID() {
        return "heart_of_ice_frost_synergy";
    }

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Frost Essence stacks to increase heal"));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info));

        return list;
    }

    @Override
    public PreCalcSpellConfigs getConfigsAffectingSpell() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 2, 5);
        return c;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 1, 6);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public BaseSpell getRequiredAbility() {
        return HeartOfIceSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellCastContext ctx) {

        int stacks = PotionEffectUtils.getStacks(ctx.caster, ColdEssenceEffect.INSTANCE);

        if (stacks > 0) {
            PotionEffectUtils.reduceStacks(ctx.caster, ColdEssenceEffect.INSTANCE, 500);
            float amount = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(Load.Unit(ctx.caster)) * stacks;

            SpellUtils.heal(ctx.spell, ctx.caster, amount);

        }

    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(getRequiredAbility());
    }

}