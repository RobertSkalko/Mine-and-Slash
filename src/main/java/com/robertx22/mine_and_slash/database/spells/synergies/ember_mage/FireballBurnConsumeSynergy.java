package com.robertx22.mine_and_slash.database.spells.synergies.ember_mage;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.FireballSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BurnEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class FireballBurnConsumeSynergy extends Synergy<CasterTargetContext> {

    @Override
    public String GUID() {
        return "fireball_consume_burn_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Burn to Erupt in AOE"));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Fire), 0.5F, 10);

    static float RADIUS = 2;

    @Override

    public BaseSpell spellAffected() {
        return new FireballSpell();
    }

    @Override
    public void tryActivate(CasterTargetContext ctx) {

        if (PotionEffectUtils.has(ctx.target, BurnEffect.INSTANCE)) {

            ParticleEnum.sendToClients(ctx.target,
                                       new ParticlePacketData(ctx.target.getPosition(), ParticleEnum.AOE).radius(RADIUS)
                                               .type(ParticleTypes.EXPLOSION)
                                               .amount(1)
            );

            SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0.5F);

            PotionEffectUtils.reduceStacks(ctx.target, BurnEffect.INSTANCE);

            int num = CALC.getCalculatedValue(Load.Unit(ctx.caster));

            EntityCap.UnitData casterData = Load.Unit(ctx.caster);
            EntityCap.UnitData targetData = Load.Unit(ctx.target);

            List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(RADIUS, ctx.target, LivingEntity.class);
            entities.removeIf(x -> x == ctx.caster);

            entities.forEach(e -> {
                SpellDamageEffect dmg = new SpellDamageEffect(ctx.caster, e, num, casterData, targetData,
                                                              spellAffected()
                );
                dmg.element = Elements.Fire;
                dmg.Activate();

            });

        }
    }
}
