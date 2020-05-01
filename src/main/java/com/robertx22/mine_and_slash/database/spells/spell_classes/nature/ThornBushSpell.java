package com.robertx22.mine_and_slash.database.spells.spell_classes.nature;

import com.robertx22.mine_and_slash.database.spells.entities.proj.SeedEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThornBushSpell extends BaseSpell {

    private ThornBushSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.NATURE;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.PROJECTILE;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_EGG_THROW;
            }

            @Override
            public Elements element() {
                return Elements.Nature;
            }
        }.spawnBlock(ModBlocks.THORN_BUSH)
            .summonsEntity((world) -> new SeedEntity(world))
            .setSwingArmOnCast());
    }

    public static ThornBushSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 20, 40);
        c.set(SC.PROJECTILE_COUNT, 1, 3);
        c.set(SC.SHOOT_SPEED, 0.4F, 0.6F);
        c.set(SC.BASE_VALUE, 3, 12);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_SECONDS, 50, 30);
        c.set(SC.TICK_RATE, 30, 30);
        c.set(SC.RADIUS, 1.5F, 3);
        c.set(SC.DURATION_TICKS, 100, 150);

        c.setMaxLevel(12);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 2);
    }

    @Override
    public String GUID() {
        return "thorn_bush";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new SText("Summons a bush that attacks enemies nearby."));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThornBush;
    }

    private static class SingletonHolder {
        private static final ThornBushSpell INSTANCE = new ThornBushSpell();
    }
}
