package com.robertx22.mine_and_slash.database.spells.spell_classes.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellHeal;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellBuffCheck;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellBuffEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class InstantHealSpell extends BaseSpellHeal {

    @Override
    public SpellType getSpellType() {
        return SpellType.Self_Heal;
    }

    @Override
    public String GUID() {
        return "instant_heal";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.CLERIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 20;
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(Health.INSTANCE, 0.2F, 25);
    }

    @Override
    public ITextComponent GetDescription() {
        return CLOC.tooltip("instant_heal");
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        try {
            World world = caster.world;

            if (!world.isRemote()) {

                UnitData data = Load.Unit(caster);

                data.getResources()
                        .modify(new ResourcesData.Context(data, caster, ResourcesData.Type.HEALTH,
                                                          getCalculation().getCalculatedValue(data),
                                                          ResourcesData.Use.RESTORE, this
                        ));

                SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);

                // spell buffs
                SpellBuffCheck check = new SpellBuffCheck(this.getSpellType());
                SpellBuffEffect spelleffect = new SpellBuffEffect(caster, check);
                spelleffect.Activate();
                checkSpellBuffs(caster, check);

                ParticleUtils.spawnHealParticles(caster, 10);
                //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
