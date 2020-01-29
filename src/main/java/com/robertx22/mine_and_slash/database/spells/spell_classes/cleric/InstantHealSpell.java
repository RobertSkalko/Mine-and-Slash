package com.robertx22.mine_and_slash.database.spells.spell_classes.cleric;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellHeal;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.BeforeHealContext;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

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
        return 30;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(Health.INSTANCE, 0.2F, 25);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Heals the user: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.InstantHeal;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        try {
            World world = caster.world;

            if (!world.isRemote()) {

                UnitData data = Load.Unit(caster);

                SpellHealEffect heal = new SpellHealEffect(
                        new ResourcesData.Context(data, caster, ResourcesData.Type.HEALTH,
                                                  getCalculation().getCalculatedValue(data), ResourcesData.Use.RESTORE,
                                                  this
                        ));

                if (Synergies.INSTANT_HEAL_REMOVE_DEBUFF.has(caster)) {
                    Synergies.INSTANT_HEAL_REMOVE_DEBUFF.tryActivate(new BeforeHealContext(caster, caster, heal));
                }

                heal.Activate();

                SoundUtils.playSound(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);

                ParticleUtils.spawnHealParticles(caster, 10);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
