package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.talent_tree.BasePerkEffect;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class SpellPerkEffect extends BasePerkEffect implements ISlashRegistryEntry<SpellPerkEffect> {

    public PerkType type = PerkType.SMALL;
    boolean hasTexture = false;
    BaseSpell spell;
    private String guid;
    private ResourceLocation TEXTURE;

    public SpellPerkEffect setGameChanger() {
        this.isGameChanger = true;
        return this;
    }

    public SpellPerkEffect(String guid, BaseSpell spell, String render) {
        this.spell = spell;
        this.guid = guid;

        setupTexture(render);

        this.registerToSlashRegistry();
    }

    public boolean isGameChanger() {
        return isGameChanger;
    }

    private void setupTexture(Stat stat) {
        this.TEXTURE = stat.getIconLocation();
        this.hasTexture = true;

    }

    private void setupTexture(String id) {
        this.TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/talents/icons/" + id + ".png");
        this.hasTexture = !id.isEmpty();
    }

    public SpellPerkEffect type(PerkType type) {
        this.type = type;
        return this;
    }

    public ITextComponent getName() {
        return null;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;
    }

    public void render(int x, int y) {

        if (hasTexture) {
            RenderUtils.renderIcon(this.TEXTURE, x, y);
        }

    }

    @Override
    public PerkType getPerkType() {
        return type;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL_PERK_EFFECT;
    }

    @Override
    public String GUID() {
        return this.guid;
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
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }
}
