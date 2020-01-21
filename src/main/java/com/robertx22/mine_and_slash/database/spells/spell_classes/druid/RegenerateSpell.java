package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellHeal;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.EffectCalculation;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellBuffCheck;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.potion_effects.all.HealthRegenPotion;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellBuffEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class RegenerateSpell extends BaseSpellHeal {

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public String GUID() {
        return "spell_self_regen";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
    }

    @Override
    public int getManaCost() {
        return 50;
    }

    @Override
    public int getBaseValue() {
        return 5;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(HealthRegen.INSTANCE, 0.75F);

    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("spell_self_regen");
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse, SpellItemData data) {
        try {
            World world = caster.world;

            if (!world.isRemote) {

                SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);

                UnitData unit = Load.Unit(caster);

                int healed = (int) (data.getBaseValue() + data.getScalingValue() * unit.getUnit()
                        .healthData().val / 100);

                caster.addPotionEffect(new EffectInstance(HealthRegenPotion.INSTANCE, 500, healed));

                // spell buffs
                SpellBuffCheck check = new SpellBuffCheck(this.getSpellType());
                SpellBuffEffect spelleffect = new SpellBuffEffect(caster, check);
                spelleffect.Activate();
                checkSpellBuffs(caster, check);
                //

            } else {

                ParticleUtils.spawnHealParticles(caster, 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
