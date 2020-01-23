package com.robertx22.mine_and_slash.potion_effects.cleric;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.*;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RighteousFuryEffect extends BasePotionEffect implements IStatPotion, IOnAttackPotion {

    public static final RighteousFuryEffect INSTANCE = new RighteousFuryEffect();

    private RighteousFuryEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "2cd621f2-bd94-442f-b94e-5dd6e4e8d6bc",
                                   (double) 0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {
        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        if (extraData.getStacks() >= this.maxStacks()) {
            BlazingInfernoEffect.damageMobsAroundYou(entity, instance);

            SoundUtils.playSound(entity, SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 1);

            entity.removePotionEffect(this);

        }
    }

    @Override
    public String GUID() {
        return "righteous_fury";
    }

    @Override
    public int performEachXTicks() {
        return 30;
    }

    @Override
    public String locNameForLangFile() {
        return "Righteous Fury";
    }

    @Override
    public int maxStacks() {
        return 25;
    }

    public ExactStatData getStatMod(EntityCap.UnitData data, Elements ele, ExtraPotionData extraData) {
        float statAmount = 0.25F * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalAttackDamage(ele)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public void applyStats(EntityCap.UnitData data, EffectInstance instance) {

        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        ExactStatData fire = getStatMod(data, Elements.Fire, extraData);

        fire.applyStats(data);

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(locName());

        list.add(new StringTextComponent("Adds fire damage to your attacks"));
        list.add(new StringTextComponent("Increases your movement speed."));
        list.add(new StringTextComponent("At " + maxStacks() + " stacks, buff explodes in an area attack."));
        list.add(new StringTextComponent("Gains stacks by damaging mobs"));

        ExactStatData water = getStatMod(info.unitdata, Elements.Water, new ExtraPotionData());
        ExactStatData fire = getStatMod(info.unitdata, Elements.Fire, new ExtraPotionData());

        list.addAll(water.GetTooltipString(info));
        list.addAll(fire.GetTooltipString(info));

        return list;

    }

    @Override
    public void onAttack(LivingEntity source, LivingEntity target) {
        PotionEffectUtils.reApplyToSelf(this, source);
    }

}
