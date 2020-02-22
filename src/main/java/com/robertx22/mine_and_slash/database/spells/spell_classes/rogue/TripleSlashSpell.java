package com.robertx22.mine_and_slash.database.spells.spell_classes.rogue;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.AfterDamageContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class TripleSlashSpell extends BaseSpell {

    private TripleSlashSpell() {
        this.castRequirements.add(REQUIRE_MELEE);
    }

    public static TripleSlashSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.ROGUE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 15;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

    @Override
    public String GUID() {
        return "triple_slash";
    }

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public int useTimeTicks() {
        return 30;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.allAttackAndSpellDamages(0.05F, 0.25F, 2);
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public void onCastingTick(PlayerEntity caster, PlayerSpellCap.ISpellsCap spells, int tick) {
        if (tick % 10 == 0) {

            List<LivingEntity> entities = EntityFinder.start(
                caster, LivingEntity.class, caster.getPositionVector())
                .radius(2)
                .build();

            EntityCap.UnitData data = Load.Unit(caster);

            entities.forEach(x -> {

                int num = getCalculation().getCalculatedValue(data);

                SpellDamageEffect dmg = new SpellDamageEffect(caster, x, num, data, Load.Unit(x),
                    this
                );

                dmg.Activate();

                if (!caster.world.isRemote) {
                    if (isLastTick(tick)) {

                        if (Synergies.TRIPLE_SLASH_WOUNDS.has(caster)) {
                            Synergies.TRIPLE_SLASH_WOUNDS.tryActivate(new AfterDamageContext(caster, x, dmg));
                        }
                    }
                }
            });

            if (caster instanceof PlayerEntity) {
                ((PlayerEntity) caster).spawnSweepParticles();

            }
            caster.setActiveHand(Hand.MAIN_HAND);

            SoundUtils.playSound(caster, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1);
        }
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Attack 3 times in a row: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public boolean goesOnCooldownIfCastCanceled() {
        return true;
    }

    @Override
    public Words getName() {
        return Words.TripleSlash;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        return true;
    }

    private static class SingletonHolder {
        private static final TripleSlashSpell INSTANCE = new TripleSlashSpell();
    }
}
