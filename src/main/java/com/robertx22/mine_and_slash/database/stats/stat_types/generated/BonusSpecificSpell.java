package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.offense.BonusSpecificSpellEffect;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.spells.BaseSpellItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BonusSpecificSpell extends Stat implements IStatEffects, IGenerated<Stat> {

    private BaseSpell spell;

    public BonusSpecificSpell(BaseSpell type) {
        this.spell = type;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Effect of that spell, whether that's damage, heal etc";
    }

    // to reduce the number of desc
    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "bonus_specific_spell";
    }

    public BaseSpell spell() {
        return this.spell;
    }

    @Override
    public String GUID() {
        return this.spell.formattedGUID() + "_power";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return spell.Element();
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new BonusSpecificSpellEffect(this.spell));
    }

    @Override
    public String locNameForLangFile() {
        BaseSpellItem item = (BaseSpellItem) this.spell.SpellItem();

        return item.locNameForLangFile() + " Power";
    }

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        SlashRegistry.Spells()
                .getAll()
                .values()
                .forEach(x -> list.add(new BonusSpecificSpell(x)));
        return list;

    }

    public boolean IsShownOnTooltip() {
        return false;
    }

}
