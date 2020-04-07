package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.ThornArmorSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornArmorEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ThornArmorThornsSynergy extends OnDamageDoneSynergy {

    @Override
    public String GUID() {
        return "thorn_armor_thorns_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Attacking has a  "));
        list.add(new StringTextComponent("% chance of applying effect."));

        list.addAll(ThornsEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {

        float chance = getContext(ctx.caster).getConfigFor(this)
            .get(SC.CHANCE)
            .get(ctx.spellsCap, this);

        if (RandomUtils.roll(chance)) {
            if (PotionEffectUtils.has(ctx.caster, ThornArmorEffect.INSTANCE)) {
                PotionEffectUtils.apply(ThornsEffect.INSTANCE, ctx.caster, ctx.target);
            }
        }
    }

    @Override
    public PreCalcSpellConfigs getConfigsAffectingSpell() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 2, 3);
        return c;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.CHANCE, 10, 30);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(ThornArmorSpell.getInstance());
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ThornArmorSpell.getInstance();
    }
}
