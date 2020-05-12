package com.robertx22.mine_and_slash.database.spells.synergies.hunting;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ImbueSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.ImbueEffect;
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

public class ImbueAddHunterSynergy extends OnBasicAttackSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Basic Attacks have chance to give: " + HunterInstinctEffect.getInstance()
            .locNameForLangFile()));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 2);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.CHANCE, 15, 50);
        c.setMaxLevel(8);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ImbueSpell.getInstance();
    }

    @Override
    public void tryActivate(DamageEffect ctx) {
        if (ctx.source.isPotionActive(ImbueEffect.getInstance())) {
            float chance = getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this);

            if (RandomUtils.roll(chance)) {
                PotionEffectUtils.reApplyToSelf(HunterInstinctEffect.getInstance(), ctx.source);
            }
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Imbue Instinct";
    }
}