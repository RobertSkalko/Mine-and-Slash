package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellHeal;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeHealContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.Sounds;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HeartOfIceSpell extends BaseSpellHeal {

    @Override
    public int useTimeTicks() {
        return 40;
    }

    @Override
    public String GUID() {
        return "heart_of_ice";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.OCEAN_MYSTIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 30;
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    public static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Water), 0.25F, 5);

    @Override
    public SpellCalcData getCalculation() {
        return CALC;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Restores health to caster:"));

        list.addAll(CALC.GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.HeartOfIce;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {
        try {
            World world = caster.world;

            if (!world.isRemote) {

                SoundUtils.playSound(caster, SoundEvents.MUSIC_UNDER_WATER, 1, 1);

                UnitData data = Load.Unit(caster);

                SpellHealEffect heal = new SpellHealEffect(
                        new ResourcesData.Context(data, caster, ResourcesData.Type.HEALTH,
                                                  getCalculation().getCalculatedValue(data), ResourcesData.Use.RESTORE,
                                                  this
                        ));

                if (Synergies.HEART_OF_ICE_FROST.has(caster)) {
                    Synergies.HEART_OF_ICE_FROST.tryActivate(new BeforeHealContext(caster, caster, heal));

                }

                heal.Activate();

                SoundUtils.playSound(caster, Sounds.FREEZE, 1, 1);

                ParticleUtils.spawnParticles(ParticleRegister.BUBBLE, caster, 25);

            } else {
                SoundUtils.playSound(caster, Sounds.FREEZE, 1, 1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
