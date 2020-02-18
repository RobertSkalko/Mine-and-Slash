package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.PoisonedWeaponsSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.MajorThornsEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.MinorThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class PoisonedWeaponsThornsSynergy extends Synergy<CasterTargetContext> {

    @Override
    public String GUID() {
        return "poisoned_weapons_thorns_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Basic attacks deals extra damage to targets affected by Thorns."));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Nature), 0.2F, 3);

    static float RADIUS = 1.5F;

    @Override
    public BaseSpell spellAffected() {
        return PoisonedWeaponsSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {

        if (PotionEffectUtils.has(ctx.target, MinorThornsEffect.INSTANCE) || PotionEffectUtils.has(
            ctx.target, MajorThornsEffect.INSTANCE)) {

            ParticleEnum.sendToClients(ctx.target,
                new ParticlePacketData(ctx.target.getPosition(), ParticleEnum.NOVA).radius(
                    RADIUS)
                    .type(ParticleTypes.CRIT)
                    .amount(30)
            );

            int num = CALC.getCalculatedValue(Load.Unit(ctx.caster));

            SpellDamageEffect dmg = new SpellDamageEffect(
                ctx.caster, ctx.target, num, ctx.casterData, ctx.targetData, spellAffected());
            dmg.element = Elements.Nature;
            dmg.Activate();

        }
    }
}
