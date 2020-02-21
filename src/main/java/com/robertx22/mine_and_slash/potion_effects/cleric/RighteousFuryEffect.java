package com.robertx22.mine_and_slash.potion_effects.cleric;

import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.PotionContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionDataSaving;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RighteousFuryEffect extends BasePotionEffect implements IApplyStatPotion, IOnBasicAttackPotion {

    public static final RighteousFuryEffect INSTANCE = new RighteousFuryEffect();

    private RighteousFuryEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "2cd621f2-bd94-442f-b94e-5dd6e4e8d6bc",
            (double) 0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );

    }


    @Override
    public String GUID() {
        return "righteous_fury";
    }

    @Override
    public int getDurationInSeconds() {
        return 45;
    }

    @Override
    public String locNameForLangFile() {
        return "Righteous Fury";
    }

    @Override
    public int getMaxStacks() {
        return 5;
    }

    public ExactStatData getStatMod(EntityCap.UnitData data, Elements ele, ExtraPotionData extraData) {
        float statAmount = 0.25F * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, ElementalAttackDamage.MAP.get(ele)).scaleToLvl(
            extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getStatMod(data, Elements.Fire, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Increases your movement speed."));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Gains stacks by damaging mobs"));

        return list;

    }

    @Override
    public void OnBasicAttack(LivingEntity source, LivingEntity target) {

        EffectInstance instance = source.getActivePotionEffect(this);

        if (instance != null) {
            ExtraPotionData extraData = PotionDataSaving.getData(instance);

            if (extraData != null) {
                extraData.addStacks(1, this);

                if (extraData.getStacks() >= getMaxStacks()) {

                    if (Synergies.RIGHTEOUS_FURY_AOE.has(source)) {
                        extraData.decreaseStacks(500, this);
                        extraData.timesUsed++;

                        Synergies.RIGHTEOUS_FURY_AOE.tryActivate(new PotionContext(source, target, instance));

                        if (extraData.timesUsed > 3) {
                            source.removePotionEffect(this);
                        } else {
                            PotionDataSaving.saveData(instance, extraData);
                        }
                    }
                } else {
                    PotionDataSaving.saveData(instance, extraData);
                }
            }
        }
    }

}

