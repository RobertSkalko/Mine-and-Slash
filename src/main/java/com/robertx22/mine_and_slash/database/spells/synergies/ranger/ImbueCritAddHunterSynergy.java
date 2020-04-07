package com.robertx22.mine_and_slash.database.spells.synergies.ranger;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ranger.ImbueSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ImbueCritAddHunterSynergy extends OnDamageDoneSynergy {

    @Override
    public String GUID() {
        return "imbue_crit_add_hunter_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Chance to give Hunter Instinct"));

        list.addAll(HunterInstinctEffect.getInstance()
            .GetTooltipString(info));

        return list;
    }

    @Override
    public PreCalcSpellConfigs getConfigsAffectingSpell() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 1, 2);
        return c;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.CHANCE, 20, 75);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(getSpell());
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ImbueSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {
        if (RandomUtils.roll(getContext(ctx.source).getConfigFor(this)
            .get(SC.CHANCE)
            .get(Load.spells(ctx.source), this))) {
            PotionEffectUtils.reApplyToSelf(HunterInstinctEffect.getInstance(), ctx.source);
        }
    }

}