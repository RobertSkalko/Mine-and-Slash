package com.robertx22.mine_and_slash.database.spells.synergies.fire;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.FireballSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BurnEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FireballBurnConsumeSynergy extends OnDamageDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Consumes Burn to Erupt in AOE"));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void tryActivate(SpellDamageEffect ctx) {

        if (PotionEffectUtils.has(ctx.target, BurnEffect.INSTANCE)) {

            float radius = getContext(ctx.source).getConfigFor(this)
                .get(SC.RADIUS)
                .get(Load.spells(ctx.source), this);

            ParticleEnum.sendToClients(ctx.target,
                new ParticlePacketData(ctx.target.getPosition(), ParticleEnum.AOE).radius(radius)
                    .type(ParticleTypes.EXPLOSION)
                    .amount(1)
            );

            SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 1, 0.5F);

            PotionEffectUtils.reduceStacks(ctx.target, BurnEffect.INSTANCE);

            int num = getCalc(Load.spells(ctx.source)).getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

            List<LivingEntity> entities = EntityFinder.start(
                ctx.source, LivingEntity.class, ctx.target.getPositionVector())
                .radius(radius)
                .build();

            entities.forEach(e -> {

                getSynergyDamage(ctx, e, num).Activate();

            });

        }
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 2);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 2, 8);
        c.set(SC.RADIUS, 1.5F, 3);
        c.setMaxLevel(6);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return FireballSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Fireball Explosion";
    }
}
