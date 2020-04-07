package com.robertx22.mine_and_slash.database.spells.spell_classes.shaman;

import com.robertx22.mine_and_slash.database.spells.entities.proj.LightningTotemEntity;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class LightningTotemSpell extends BaseSpell {

    private LightningTotemSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public SpellSchools school() {
                    return SpellSchools.SHAMAN;
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
                    return Elements.Thunder;
                }
            }.cooldownIfCanceled(true)
                .summonsEntity(w -> new LightningTotemEntity(w))
        );
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 15, 25);
        c.set(SC.BASE_VALUE, 2, 8);
        c.set(SC.SHOOT_SPEED, 1F, 1.2F);
        c.set(SC.PROJECTILE_COUNT, 1, 3);
        c.set(SC.CAST_TIME_TICKS, 20, 10);
        c.set(SC.COOLDOWN_SECONDS, 15, 10);
        c.set(SC.TICK_RATE, 30, 15);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(3, 1);
    }

    public static LightningTotemSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "lightning_totem";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summons a totem that damages enemies: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.LightningTotem;
    }

    private static class SingletonHolder {
        private static final LightningTotemSpell INSTANCE = new LightningTotemSpell();
    }
}
