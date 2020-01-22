package com.robertx22.mine_and_slash.potion_effects.ocean_mystic;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.ExtraPotionData;
import com.robertx22.mine_and_slash.potion_effects.IStatPotion;
import com.robertx22.mine_and_slash.potion_effects.PotionDataSaving;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class FrostEffect extends BasePotionEffect implements IStatPotion {

    public static final FrostEffect INSTANCE = new FrostEffect();

    private FrostEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890",
                                   (double) -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {
        ParticleUtils.spawnParticles(ParticleTypes.ITEM_SNOWBALL, entity, 5);
    }

    @Override
    public String GUID() {
        return "frost";
    }

    @Override
    public int performEachXTicks() {
        return 40;
    }

    @Override
    public String locNameForLangFile() {
        return "Frost";
    }

    @Override
    public int maxStacks() {
        return 10;
    }

    @Override
    public void applyStats(EntityCap.UnitData data, EffectInstance instance) {

        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        int statAmount = 10 * extraData.getStacks();

        ExactStatData water = new ExactStatData(
                statAmount, StatTypes.Flat, new ElementalResist(Elements.Water)).scaleToLvl(extraData.casterLvl);
        ExactStatData fire = new ExactStatData(
                statAmount, StatTypes.Flat, new ElementalResist(Elements.Fire)).scaleToLvl(extraData.casterLvl);

        water.applyStats(data);
        fire.applyStats(data);

    }
}

