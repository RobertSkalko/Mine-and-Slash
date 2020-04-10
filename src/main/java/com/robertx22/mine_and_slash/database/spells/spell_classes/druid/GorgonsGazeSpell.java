package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.PetrifyEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GorgonsGazeSpell extends BaseSpell {

    private GorgonsGazeSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public SpellSchools school() {
                return SpellSchools.DRUID;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.SPECIAL;
            }

            @Override
            public SoundEvent sound() {
                return null;
            }

            @Override
            public Elements element() {
                return Elements.Nature;
            }
        });
    }

    public static GorgonsGazeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 4);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 25, 35);
        c.set(SC.BASE_VALUE, 2, 3);
        c.set(SC.SHOOT_SPEED, 0.4F, 0.6F);
        c.set(SC.CAST_TIME_TICKS, 25, 20);
        c.set(SC.COOLDOWN_SECONDS, 60, 45);
        c.set(SC.DURATION_TICKS, 60, 100);
        c.set(SC.TICK_RATE, 20, 20);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public String GUID() {
        return "gorgons_gaze";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Turn all enemies before you into stone: "));
        list.add(new StringTextComponent("Applies debuff: "));
        list.add(new StringTextComponent(""));

        list.addAll(PetrifyEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.GorgonsGaze;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        LivingEntity caster = ctx.caster;

        World world = caster.world;

        SoundUtils.playSound(caster, ModSounds.STONE_CRACK.get(), 1, 1);

        EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
            .radius(3)
            .distance(15)
            .finder(EntityFinder.Finder.IN_FRONT)
            .build()
            .forEach(x -> PotionEffectUtils.apply(PetrifyEffect.INSTANCE, caster, x));

    }

    private static class SingletonHolder {
        private static final GorgonsGazeSpell INSTANCE = new GorgonsGazeSpell();
    }
}
