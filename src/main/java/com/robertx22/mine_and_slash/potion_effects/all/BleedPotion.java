package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.ScalingStatCalc;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class BleedPotion extends BasePotionEffect {

    public static final BleedPotion INSTANCE = new BleedPotion();

    private BleedPotion() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(20, ctx -> {

            int num = CALC.getCalculatedValue(ctx.casterData);

            if (ctx.entity.world.isRemote) {
                ParticleUtils.spawnParticles(ParticleTypes.LAVA, ctx.entity, 5);
            } else {
                DamageEffect dmg = new DamageEffect(null, ctx.caster, ctx.entity, num, ctx.casterData, ctx.entityData,
                    EffectData.EffectTypes.DOT_DMG, WeaponTypes.None
                );
                dmg.removeKnockback();
                dmg.Activate();
            }

            return ctx;
        }, null));
    }

    public static ScalingStatCalc CALC = new ScalingStatCalc(PhysicalDamage.getInstance(), 0.15F);

    @Override
    public int getDurationInSeconds() {
        return 5;
    }

    @Override
    public String GUID() {
        return "bleed";
    }

    @Override
    public String locNameForLangFile() {
        return "Bleed";
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        return new ArrayList<>();
    }
}
