package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.MinorThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RegenerateThornsSynergy extends Synergy<CasterContext> {

    @Override
    public String GUID() {
        return "regenerate_thorns_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Spell cast also applies debuff to enemies nearby"));

        list.addAll(MinorThornsEffect.INSTANCE.GetTooltipString(info));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return new RegenerateSpell();
    }

    @Override
    public void tryActivate(CasterContext ctx) {

        float radius = 3;

        BlockPos pos = ctx.caster.getPosition();

        ParticleEnum.sendToClients(
                pos, ctx.caster.world, new ParticlePacketData(pos, ParticleEnum.THORNS).radius(radius).amount(30));

        EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .radius(radius)
                .build()
                .forEach(x -> PotionEffectUtils.apply(MinorThornsEffect.INSTANCE, ctx.caster, x));

    }
}

