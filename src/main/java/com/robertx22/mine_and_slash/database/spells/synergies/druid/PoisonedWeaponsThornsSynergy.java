package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.PoisonedWeaponsSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class PoisonedWeaponsThornsSynergy extends OnDamageDoneSynergy {

    @Override
    public String GUID() {
        return "poisoned_weapons_thorns_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Basic attacks deals extra damage to targets affected by Thorns."));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info));

        return list;
    }

    @Override
    public PreCalcSpellConfigs getConfigsAffectingSpell() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 1, 4);
        return c;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 2, 9);
        return c;
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {

        if (PotionEffectUtils.has(ctx.target, ThornsEffect.INSTANCE)) {

            ParticleEnum.sendToClients(ctx.target,
                new ParticlePacketData(ctx.target.getPosition(), ParticleEnum.NOVA).radius(
                    2)
                    .type(ParticleTypes.CRIT)
                    .amount(30)
            );

            int num = getPreCalcConfig().getCalc(ctx.spellsCap, this)
                .getCalculatedValue(Load.Unit(ctx.caster));

            SpellDamageEffect dmg = new SpellDamageEffect(
                ctx.caster, ctx.target, num, ctx.casterData, ctx.targetData, getSpell());
            dmg.element = Elements.Nature;
            dmg.Activate();

        }
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(getRequiredAbility());
    }

    @Override
    public IAbility getRequiredAbility() {
        return PoisonedWeaponsSpell.getInstance();
    }

}
