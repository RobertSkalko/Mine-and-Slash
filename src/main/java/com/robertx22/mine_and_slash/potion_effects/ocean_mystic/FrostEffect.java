package com.robertx22.mine_and_slash.potion_effects.ocean_mystic;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionDataSaving;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

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
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Frost";
    }

    @Override
    public int maxStacks() {
        return 5;
    }

    public ExactStatData getStatMod(EntityCap.UnitData data, Elements ele, ExtraPotionData extraData) {
        int statAmount = -1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(ele)).scaleToLvl(extraData.casterLvl);
    }

    @Override
    public void applyStats(EntityCap.UnitData data, EffectInstance instance) {

        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        ExactStatData water = getStatMod(data, Elements.Water, extraData);
        ExactStatData fire = getStatMod(data, Elements.Fire, extraData);

        water.applyStats(data);
        fire.applyStats(data);

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(locName());

        list.add(new StringTextComponent("Slows and Reduces Resistances;"));

        ExactStatData water = getStatMod(info.unitdata, Elements.Water, new ExtraPotionData());
        ExactStatData fire = getStatMod(info.unitdata, Elements.Fire, new ExtraPotionData());

        list.addAll(water.GetTooltipString(info));
        list.addAll(fire.GetTooltipString(info));

        return list;

    }

}

