package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.ScalingStatCalc;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
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

    }

    public static ScalingStatCalc CALC = new ScalingStatCalc(PhysicalDamage.INSTANCE, 0.25F);

    @Override
    public int getDurationInSeconds() {
        return 5;
    }

    @Override
    public String GUID() {
        return "bleed";
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        ExtraPotionData data = PotionEffectUtils.getDataForTooltips(this);

        LivingEntity caster = data.getCaster(entity.world);

        EntityCap.UnitData casterData = Load.Unit(data.getCaster(entity.world));
        EntityCap.UnitData targetData = Load.Unit(entity);

        int num = CALC.getCalculatedValue(casterData);

        if (entity.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.LAVA, entity, 5);
        } else {
            DamageEffect dmg = new DamageEffect(null, caster, entity, num, casterData, targetData,
                                                EffectData.EffectTypes.DOT_DMG, WeaponTypes.None
            );
            dmg.removeKnockback();
            dmg.Activate();
        }

    }

    @Override
    public int performEachXTicks() {
        return 20;
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
