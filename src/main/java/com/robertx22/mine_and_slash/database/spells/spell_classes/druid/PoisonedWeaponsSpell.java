package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SetupPreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.PoisonedWeaponsEffect;
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

public class PoisonedWeaponsSpell extends BaseSpell {

    private PoisonedWeaponsSpell() {
        super(new ImmutableSpellConfigs() {

                  @Override
                  public BasePotionEffect potionEffect() {
                      return PoisonedWeaponsEffect.getInstance();
                  }

                  @Override
                  public SpellSchools school() {
                      return SpellSchools.DRUID;
                  }

                  @Override
                  public SpellCastType castType() {
                      return SpellCastType.GIVE_EFFECT;
                  }

                  @Override
                  public SoundEvent sound() {
                      return SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED;
                  }

                  @Override
                  public int maxSpellLevel() {
                      return 12;
                  }

                  @Override
                  public Elements element() {
                      return Elements.Nature;
                  }
              },
            new SetupPreCalcSpellConfigs() {
                @Override
                public LevelBased manaCost() {
                    return new LevelBased(20, 40);
                }

                @Override
                public LevelBased baseValue() {
                    return new LevelBased(3, 10);
                }

                @Override
                public LevelBased castTimeTicks() {
                    return new LevelBased(30, 15);
                }

                @Override
                public LevelBased cooldownTicks() {
                    return new LevelBased(120, 60);
                }
            });
    }

    public static PoisonedWeaponsSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "poisoned_weapons";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Applies buff: "));

        list.addAll(PoisonedWeaponsEffect.getInstance()
            .GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.PoisonedWeapons;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(6, 0);
    }

    private static class SingletonHolder {
        private static final PoisonedWeaponsSpell INSTANCE = new PoisonedWeaponsSpell();
    }
}
