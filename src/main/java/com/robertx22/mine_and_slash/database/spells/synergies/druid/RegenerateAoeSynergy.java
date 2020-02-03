package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RegenerateAoeSynergy extends Synergy<CasterContext> {

    @Override
    public String GUID() {
        return "regenerate_aoe_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Buff is applied in AOE around the caster"));

        return list;
    }

    @Override
    public BaseSpell spellAffected() {
        return RegenerateSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterContext ctx) {

        BlockPos pos = ctx.caster.getPosition();

        ParticleEnum.sendToClients(pos, ctx.caster.world,
                                   new ParticlePacketData(pos, ParticleEnum.NOVA).radius(RegenerateSpell.RADIUS)
                                           .motion(new Vec3d(0, 0, 0))
                                           .type(ParticleTypes.HAPPY_VILLAGER)
                                           .amount(50)
        );

        EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .radius(RegenerateSpell.RADIUS)
                .searchFor(EntityFinder.SearchFor.ALLIES)
                .build()
                .forEach(x -> PotionEffectUtils.apply(RegenerateEffect.INSTANCE, ctx.caster, x));

    }
}
