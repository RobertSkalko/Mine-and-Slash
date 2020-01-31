package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerkEffect;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellPerkEffect extends BasePerkEffect implements ISlashRegistryEntry<SpellPerkEffect> {

    public BaseSpell spell;
    public List<ExactStatData> exactStats = new ArrayList<>();

    public SpellPerkEffect setGameChanger() {
        this.isGameChanger = true;
        return this;
    }

    public SpellPerkEffect(String guid, ExactStatData exactStat, Stat stat) {
        this.exactStats = Arrays.asList(exactStat);
        this.guid = guid;

        setupTexture(stat);

        this.registerToSlashRegistry();
    }

    public SpellPerkEffect(String guid, ExactStatData exactStat, String render) {
        this.exactStats = Arrays.asList(exactStat);
        this.guid = guid;

        this.TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/talents/icons/" + render + ".png");

        this.registerToSlashRegistry();
    }

    public SpellPerkEffect(BaseSpell spell) {
        this.spell = spell;
        this.guid = spell.GUID();

        setupTexture(spell);

        this.registerToSlashRegistry();
    }

    public boolean isGameChanger() {
        return isGameChanger;
    }

    private void setupTexture(Stat stat) {
        this.TEXTURE = stat.getIconLocation();
        this.hasTexture = true;

    }

    private void setupTexture(BaseSpell spell) {
        this.TEXTURE = spell.getIcon();
        this.hasTexture = true;
    }

    public ITextComponent getName() {
        return null;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        if (spell != null) {
            list.addAll(spell.GetTooltipString(info));
        }

        return list;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL_PERK_EFFECT;
    }

    @Override
    public String GUID() {
        return this.guid;
    }

    public SpellPerkEffect setStart() {
        isStart = true;
        return this;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }
}
