package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RegenerateThornsSynergy extends OnSpellCastSynergy {

    @Override
    public String GUID() {
        return "regenerate_thorns_synergy";
    }

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Also applies debuff in aoe: " + ThornsEffect.INSTANCE.locNameForLangFile()));

        return list;
    }

    @Override
    public void tryActivate(SpellCastContext ctx) {
        BlockPos pos = ctx.caster.getPosition();

        float radius = ctx.getConfigFor(this)
            .get(SC.RADIUS)
            .get(ctx.spellsCap, this);

        ParticleEnum.sendToClients(pos, ctx.caster.world,
            new ParticlePacketData(pos, ParticleEnum.THORNS).radius(radius)
                .amount(30)
        );

        EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
            .radius(radius)
            .build()
            .forEach(x -> PotionEffectUtils.apply(ThornsEffect.INSTANCE, ctx.caster, x));

    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.RADIUS, 2, 4);
        c.setMaxLevel(3);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(new RegenerateAoeSynergy());
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return RegenerateSpell.getInstance();
    }
}

