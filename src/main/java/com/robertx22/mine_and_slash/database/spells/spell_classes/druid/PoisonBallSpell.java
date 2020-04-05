package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.PoisonBallEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SetupPreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PoisonBallSpell extends BaseSpell {

    private PoisonBallSpell() {
        super(
            new ImmutableSpellConfigs() {
                @Override
                public SpellSchools school() {
                    return SpellSchools.DRUID;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_SNOWBALL_THROW;
                }

                @Override
                public Function<World, Entity> newEntitySummoner() {
                    return world -> new PoisonBallEntity(world);
                }

                @Override
                public int maxSpellLevel() {
                    return 16;
                }

                @Override
                public Elements element() {
                    return Elements.Nature;
                }
            },
            new SetupPreCalcSpellConfigs() {
                @Override
                public LevelBased manaCost() {
                    return new LevelBased(5, 15);
                }

                @Override
                public LevelBased baseValue() {
                    return new LevelBased(3, 10);
                }

                @Override
                public LevelBased shootSpeed() {
                    return new LevelBased(0.4F, 0.6F);
                }

                @Override
                public LevelBased castTimeTicks() {
                    return new LevelBased(0, 0);
                }

                @Override
                public LevelBased cooldownTicks() {
                    return new LevelBased(15, 10);
                }
            });

    }

    public static PoisonBallSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "poison_ball";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.singleTargetProjectile());

        list.addAll(getCalculation(ctx).GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.PoisonBall;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(0, 0);
    }

    private static class SingletonHolder {
        private static final PoisonBallSpell INSTANCE = new PoisonBallSpell();
    }
}