package com.robertx22.mine_and_slash.database.data.spells.spell_classes.divine.buffs;

import com.robertx22.mine_and_slash.database.data.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.data.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.divine.TrickeryEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class TrickerySpell extends BaseDivineBuffSpell {
    private TrickerySpell() {
        super(new ImmutableSpellConfigs() {
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
        }.addsEffect(TrickeryEffect.INSTANCE));
    }

    public static TrickerySpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "trickery";
    }

    @Override
    public Words getName() {
        return Words.Trickery;
    }

    private static class SingletonHolder {
        private static final TrickerySpell INSTANCE = new TrickerySpell();
    }
}
