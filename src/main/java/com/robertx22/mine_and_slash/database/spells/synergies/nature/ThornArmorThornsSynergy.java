package com.robertx22.mine_and_slash.database.spells.synergies.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.ThornArmorSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornArmorEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ThornArmorThornsSynergy extends OnBasicAttackSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Attacking has a  "));
        list.add(new StringTextComponent("% chance of applying effect."));

        list.addAll(ThornsEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;
    }

    @Override
    public void tryActivate(DamageEffect ctx) {

        float chance = getContext(ctx.source).getConfigFor(this)
            .get(SC.CHANCE)
            .get(Load.spells(ctx.source), this);

        if (RandomUtils.roll(chance)) {
            if (PotionEffectUtils.has(ctx.source, ThornArmorEffect.INSTANCE)) {
                PotionEffectUtils.apply(ThornsEffect.INSTANCE, ctx.source, ctx.target);
            }
        }
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.CHANCE, 10, 30);
        c.setMaxLevel(6);
        return c;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ThornArmorSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Thorny Armor";
    }

}
