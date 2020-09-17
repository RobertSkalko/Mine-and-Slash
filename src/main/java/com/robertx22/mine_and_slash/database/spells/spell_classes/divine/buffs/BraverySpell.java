package com.robertx22.mine_and_slash.database.spells.spell_classes.divine.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.potion_effects.divine.BraveryEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class BraverySpell extends BaseDivineBuffSpell {
    private BraverySpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.DIVINE;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.AOE_EFFECT;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            }

            @Override
            public Elements element() {
                return Elements.Elemental;
            }
        }.addsEffect(BraveryEffect.INSTANCE));
    }

    public static BraverySpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "bravery";
    }

    @Override
    public Words getName() {
        return Words.Bravery;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 0);
    }

    private static class SingletonHolder {
        private static final BraverySpell INSTANCE = new BraverySpell();
    }
}
