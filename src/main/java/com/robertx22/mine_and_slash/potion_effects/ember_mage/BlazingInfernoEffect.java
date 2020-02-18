package com.robertx22.mine_and_slash.potion_effects.ember_mage;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlazingInfernoEffect extends BasePotionEffect {

    public static final BlazingInfernoEffect INSTANCE = new BlazingInfernoEffect();

    private BlazingInfernoEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(30, ctx -> {
            damageMobsAroundYou(ctx.entity, ctx.data, ctx.caster);
            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.add(new StringTextComponent("Does damage to enemies around you:"));
            list.addAll(CALC.GetTooltipString(info));

            return list;
        }));

    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Fire), 0.25F, 1);

    public static float RADIUS = 3.5F;

    public static void damageMobsAroundYou(LivingEntity entity, ExtraPotionData data, LivingEntity caster) {

        if (!entity.world.isRemote) {

            ParticlePacketData pdata = new ParticlePacketData(entity.getPosition()
                .up(1), ParticleEnum.BLAZING_INFERNO);
            pdata.radius = RADIUS;
            ParticleEnum.BLAZING_INFERNO.sendToClients(entity, pdata);

            int num = CALC.getCalculatedValue(Load.Unit(caster));

            List<LivingEntity> entities = EntityFinder.start(entity, LivingEntity.class, entity.getPositionVector())
                .radius(RADIUS)
                .build();

            for (LivingEntity en : entities) {
                DamageEffect dmg = new DamageEffect(
                    null, caster, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Fire;
                dmg.Activate();

            }
        }
    }

    @Override
    public String GUID() {
        return "blazing_inferno";
    }

    @Override
    public String locNameForLangFile() {
        return "Blazing Inferno";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public int getDurationInSeconds() {
        return 15;
    }

}

