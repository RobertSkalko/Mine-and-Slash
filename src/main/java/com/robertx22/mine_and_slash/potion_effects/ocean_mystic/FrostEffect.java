package com.robertx22.mine_and_slash.potion_effects.ocean_mystic;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class FrostEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final FrostEffect INSTANCE = new FrostEffect();

    private FrostEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890",
                                   (double) -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.tickActions.add(new OnTickAction(20, ctx -> {
            ParticleUtils.spawnParticles(ParticleTypes.ITEM_SNOWBALL, ctx.entity, 5);
            return ctx;
        }, null));

    }

    @Override
    public int getDurationInSeconds() {
        return 8;
    }

    @Override
    public String GUID() {
        return "frost";
    }

    @Override
    public String locNameForLangFile() {
        return "Frost";
    }

    @Override
    public int getMaxStacks() {
        return 5;
    }

    public ExactStatData getStatMod(EntityCap.UnitData data, Elements ele, ExtraPotionData extraData) {
        int statAmount = -1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, new ElementalResist(ele)).scaleToLvl(extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getStatMod(data, Elements.Water, extraData));
        list.add(getStatMod(data, Elements.Fire, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Slows"));

        return list;

    }

}

