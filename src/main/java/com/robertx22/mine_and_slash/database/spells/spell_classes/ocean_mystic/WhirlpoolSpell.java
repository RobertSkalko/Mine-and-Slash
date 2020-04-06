package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.entities.proj.WhirlpoolEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WhirlpoolSpell extends BaseSpell {

    public Elements element = Elements.Water;

    private WhirlpoolSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public SpellSchools school() {
                    return SpellSchools.OCEAN_MYSTIC;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE;
                }

                @Override
                public Function<World, Entity> newEntitySummoner() {
                    return world -> new WhirlpoolEntity(world);
                }

                @Override
                public Elements element() {
                    return Elements.Water;
                }
            });
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 15, 30);
        c.set(SC.BASE_VALUE, 1, 3);
        c.set(SC.SHOOT_SPEED, 0.6F, 0.9F);
        c.set(SC.PROJECTILE_COUNT, 1, 1);
        c.set(SC.CAST_TIME_TICKS, 30, 20);
        c.set(SC.COOLDOWN_SECONDS, 45, 30);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(1, 2);
    }

    public static WhirlpoolSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "whirlpool";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summons a whirpool that slows and damages enemies: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Whirpool;
    }

    private static class SingletonHolder {
        private static final WhirlpoolSpell INSTANCE = new WhirlpoolSpell();
    }
}
