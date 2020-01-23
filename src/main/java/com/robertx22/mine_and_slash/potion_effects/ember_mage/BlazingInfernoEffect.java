package com.robertx22.mine_and_slash.potion_effects.ember_mage;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionDataSaving;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlazingInfernoEffect extends BasePotionEffect implements IStatPotion {

    public static final BlazingInfernoEffect INSTANCE = new BlazingInfernoEffect();

    private BlazingInfernoEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Fire), 0.25F, 1);

    public static float RADIUS = 3.5F;

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        if (!entity.world.isRemote) {
            ExtraPotionData extraData = PotionDataSaving.getData(instance);

            int num = CALC.getCalculatedValue(Load.Unit(entity));

            for (LivingEntity en : Utilities.getEntitiesWithinRadius(RADIUS, entity, LivingEntity.class)) {
                DamageEffect dmg = new DamageEffect(
                        null, entity, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Fire;
                dmg.Activate();

            }
        } else {
            for (int i = 0; i < 100; i++) {
                Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(entity.getPositionVector(), RADIUS);
                entity.world.addParticle(ParticleTypes.FLAME, p.x, p.y + entity.getEyeHeight() / 2, p.z, 0, 0, 0);
                entity.world.addParticle(ParticleTypes.SMOKE, p.x, p.y + entity.getEyeHeight() / 2, p.z, 0, 0, 0);

            }

            SoundUtils.playSound(entity, SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);

        }

    }

    @Override
    public String GUID() {
        return "blazing_inferno";
    }

    @Override
    public int performEachXTicks() {
        return 30;
    }

    @Override
    public String locNameForLangFile() {
        return "Blazing Inferno";
    }

    @Override
    public int maxStacks() {
        return 1;
    }

    @Override
    public void applyStats(EntityCap.UnitData data, EffectInstance instance) {

        // ExtraPotionData extraData = PotionDataSaving.getData(instance);

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(locName());

        list.add(new StringTextComponent("Does damage to enemies around you:"));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }
}

