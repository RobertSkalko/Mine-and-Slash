package com.robertx22.mine_and_slash.gui.spell_perk_tree;

import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.gui.bases.BasePerkTreeScreen;
import com.robertx22.mine_and_slash.gui.talent_tree_gui.PerkButton;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellPerksData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SpellPerkTreeScreen extends BasePerkTreeScreen<SpellPerk, SpellPerksData, PlayerSpellCap.ISpellsCap> {

    public SpellPerkTreeScreen() {
        super();
    }

    @Override
    public ResourceLocation getBorderTexture() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/talent_frame.png");
    }

    @Override
    public ResourceLocation getSpaceTexture() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/space2.png");
    }

    @Override
    public ResourceLocation getLineTexture() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/lines.png");
    }

    @Override
    public PlayerCaps getCapType() {
        return PlayerCaps.TALENTS;
    }

    @Override
    public void reloadData() {
        this.capData = Load.spells(mc.player);
    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/spell_perks.png");
    }

    @Override
    public Words screenName() {
        return Words.Spell;
    }

    @Override
    public void init(Minecraft mc, int x, int y) {
        super.init(mc, x, y);

        for (SpellPerk perk : SlashRegistry.SpellPerks().getList()) {
            this.addButton(new PerkButton(perk.getStatus(capData), perk, unitData));
        }

        returnToCenter();

        refresh();

    }

}
