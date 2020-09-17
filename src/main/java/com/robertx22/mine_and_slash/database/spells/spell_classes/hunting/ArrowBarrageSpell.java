package com.robertx22.mine_and_slash.database.spells.spell_classes.hunting;

import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ArrowBarrageSpell extends BaseSpell {

    private ArrowBarrageSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.HUNTING;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_ARROW_SHOOT;
                }

                @Override
                public Elements element() {
                    return Elements.Elemental;
                }
            }.addCastRequirement(SpellPredicates.REQUIRE_SHOOTABLE)
                .cooldownIfCanceled(true)
                .summonsEntity(world -> new RangerArrowEntity(world)));
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 15, 25);
        c.set(SC.BASE_VALUE, 2, 6);
        c.set(SC.ATTACK_SCALE_VALUE, 0.4F, 0.6F);
        c.set(SC.PROJECTILE_COUNT, 1, 1);
        c.set(SC.SHOOT_SPEED, 2.5F, 3.75F);
        c.set(SC.CAST_TIME_TICKS, 40, 30);
        c.set(SC.COOLDOWN_SECONDS, 30, 20);
        c.set(SC.TIMES_TO_CAST, 4, 6);
        c.set(SC.DURATION_TICKS, 100, 160);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(0, 0);
    }

    public static ArrowBarrageSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "arrow_barrage";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Shoots out many arrows while casting: "));
        list.add(new StringTextComponent("Requires Bow/Crossbow to use: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ArrowBarrage;
    }

    private static class SingletonHolder {
        private static final ArrowBarrageSpell INSTANCE = new ArrowBarrageSpell();
    }
}
