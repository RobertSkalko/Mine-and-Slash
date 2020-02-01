package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseBuffSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterContext;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RegenerateSpell extends BaseBuffSpell {

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public String GUID() {
        return "regenerate";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
    }

    @Override
    public int getCooldownInSeconds() {
        return 25;
    }

    @Override
    public SpellType getSpellType() {
        return null;
    }

    @Override
    public int getManaCost() {
        return 50;
    }

    @Override
    public SpellCalcData getCalculation() {
        return RegenerateEffect.CALC;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Applies buff: "));

        list.addAll(RegenerateEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        boolean bool = super.cast(caster, ticksInUse);

        if (bool) {
            if (Synergies.REGEN_AOE.has(caster)) {
                Synergies.REGEN_AOE.tryActivate(new CasterContext(caster));
            }
            if (Synergies.REGEN_THORNS.has(caster)) {
                Synergies.REGEN_THORNS.tryActivate(new CasterContext(caster));
            }
        }

        return bool;

    }

    @Override
    public Words getName() {
        return Words.Regeneration;
    }

    @Override
    public void spawnParticles(LivingEntity caster) {
        if (caster.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, caster, 10);
        }
    }

    @Override
    public SoundEvent getCastSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION;
    }

    @Override
    public BasePotionEffect getEffect() {
        return RegenerateEffect.INSTANCE;
    }
}
