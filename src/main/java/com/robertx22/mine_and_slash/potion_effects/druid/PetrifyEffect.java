package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.GorgonsGazeSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackedPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PetrifyEffect extends BasePotionEffect implements IOnBasicAttackedPotion {

    public static final PetrifyEffect INSTANCE = new PetrifyEffect();

    private PetrifyEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160892",
            (double) -0.95F, AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.tickActions.add(new OnTickAction(20, ctx -> {
            ParticleEnum.sendToClients(
                ctx.entity, new ParticlePacketData(ctx.entity.getPosition(), ParticleEnum.PETRIFY).radius(1)
                    .type(ParticleTypes.CLOUD)
                    .amount(15));

            SoundUtils.playSound(ctx.entity, SoundEvents.BLOCK_STONE_BREAK, 0.5F, 0.5F);
            return ctx;
        }, null));

    }

    @Override
    public int getDurationInSeconds() {
        return 10;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs p = new PreCalcSpellConfigs();
        p.set(SC.BASE_VALUE, 1, 5);
        return p;
    }

    @Nullable
    @Override
    public BaseSpell getSpell() {
        return GorgonsGazeSpell.getInstance();
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
    }

    @Override
    public String GUID() {
        return "petrify";
    }

    @Override
    public String locNameForLangFile() {
        return "Petrify";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();
        list.add(new StringTextComponent("Petrifies Enemy."));
        list.add(new StringTextComponent("If Attacked, does extra damage, but stops effect."));
        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void onBasicAttacked(LivingEntity source, LivingEntity target) {

        int num = getCalc(Load.spells(source)).getCalculatedValue(Load.Unit(source), Load.spells(source), this);

        DamageEffect dmg = new DamageEffect(null, source, target, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
        dmg.element = Elements.Nature;
        dmg.Activate();

        ParticleEnum.sendToClients(
            target, new ParticlePacketData(target.getPosition(), ParticleEnum.PETRIFY).radius(1)
                .type(ParticleTypes.CLOUD)
                .amount(20));

        target.playSound(SoundEvents.BLOCK_STONE_BREAK, 1, 1);

        target.removePotionEffect(this);

    }
}