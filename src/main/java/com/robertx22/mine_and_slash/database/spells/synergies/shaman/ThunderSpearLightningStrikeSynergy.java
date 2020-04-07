package com.robertx22.mine_and_slash.database.spells.synergies.shaman;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
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

public class ThunderSpearLightningStrikeSynergy extends OnDamageDoneSynergy {

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

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info));

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
        c.set(SC.BASE_VALUE, 3, 20);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(new ThunderSpearThunderEssenceSynergy());
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ThunderspearSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {
        if (PotionEffectUtils.has(ctx.target, StaticEffect.INSTANCE)) {

            PotionEffectUtils.reduceStacks(ctx.target, StaticEffect.INSTANCE);

            SpellUtils.summonLightningStrike(ctx.target);

            int num = getCalc(Load.spells(ctx.source)).getCalculatedBaseValue(ctx.sourceData);

            getSynergyDamage(ctx, num).Activate();

        }
    }

}
