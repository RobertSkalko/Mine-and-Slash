package com.robertx22.mine_and_slash.database.spells.synergies.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RegenerateAoeSynergy extends OnSpellCastSynergy {

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
    public BaseSpell getRequiredAbility() {
        return RegenerateSpell.getInstance();
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
        c.set(SC.RADIUS, 3, 4);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(RegenerateSpell.getInstance());
    }

    @Override
    public void tryActivate(SpellCastContext ctx) {
        float radius = this.getPreCalcConfig()
            .get(SC.RADIUS)
            .get(ctx.spellsCap, this);

        BlockPos pos = ctx.caster.getPosition();

        ParticleEnum.sendToClients(pos, ctx.caster.world,
            new ParticlePacketData(pos, ParticleEnum.NOVA).radius(radius)
                .motion(new Vec3d(0, 0, 0))
                .type(ParticleTypes.HAPPY_VILLAGER)
                .amount(50)
        );

        EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
            .radius(radius)
            .searchFor(EntityFinder.SearchFor.ALLIES)
            .build()
            .forEach(x -> PotionEffectUtils.apply(RegenerateEffect.INSTANCE, ctx.caster, x));
    }
}
