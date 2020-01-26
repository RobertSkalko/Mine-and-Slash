package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellHeal;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RegenerateSpell extends BaseSpellHeal {

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
    public int getManaCost() {
        return 50;
    }

    @Override
    public SpellCalcData getCalculation() {
        return RegenerateEffect.CALC;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Applies buff: "));

        list.addAll(RegenerateEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Regeneration;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        try {
            World world = caster.world;

            if (!world.isRemote) {

                SoundUtils.playSound(caster, SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION, 1, 1);

                PotionEffectUtils.applyToSelf(RegenerateEffect.INSTANCE, caster);

            } else {
                ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, caster, 10);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
