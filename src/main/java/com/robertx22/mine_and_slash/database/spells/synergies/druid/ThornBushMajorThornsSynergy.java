package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.ThornBushSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ThornBushMajorThornsSynergy extends OnDamageDoneSynergy {

    @Override
    public String GUID() {
        return "thorn_bush_major_thorns_synergy";
    }

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Adds extra damage to enemies affected by thorns."));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info));

        return list;
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {
        if (PotionEffectUtils.has(ctx.target, ThornsEffect.INSTANCE)) {

            PotionEffectUtils.reduceStacks(ctx.target, ThornsEffect.INSTANCE);

            int dmg = getCalc(Load.spells(ctx.source)).getCalculatedBaseValue(ctx.sourceData);

            getSynergyDamage(ctx, dmg)
                .Activate();

        }
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 2, 8);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(ThornBushSpell.getInstance());
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ThornBushSpell.getInstance();
    }
}
