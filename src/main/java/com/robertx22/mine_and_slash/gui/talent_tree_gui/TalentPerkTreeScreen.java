package com.robertx22.mine_and_slash.gui.talent_tree_gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.gui.bases.BasePerkTreeScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.saveclasses.talents.PlayerTalentsData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap.IPlayerTalentsData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class TalentPerkTreeScreen extends BasePerkTreeScreen<Perk, PlayerTalentsData, IPlayerTalentsData> {

    public TalentPerkTreeScreen() {
        super();
    }

    @Override
    public ResourceLocation TEXTURE() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/talent_frame.png");
    }

    @Override
    public ResourceLocation SPACE() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/space.png");
    }

    @Override
    public ResourceLocation LINES() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/lines.png");
    }

    @Override
    public CapTypes getCapType() {
        return CapTypes.TALENTS;
    }

    @Override
    public void reloadData() {
        this.capData = Load.talents(mc.player);
    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/talents.png");
    }

    @Override
    public Words screenName() {
        return Words.Talents;
    }

    public void refreshConnections() {
        connections = this.capData.getConnections();
        this.buttonConnections = getButtonConnections();
    }

    @Override
    public void init(Minecraft mc, int x, int y) {
        super.init(mc, x, y);

        for (Perk talent : SlashRegistry.Perks().getList()) {
            this.addButton(new PerkButton(talent.getStatus(capData), talent, unitData));
        }

        returnToCenter();

        refresh();

    }

    @Override
    protected void drawPointsLeftNumber() {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int offsetX = mc.mainWindow.getScaledWidth() / 2 - sizeX / 2;
        int offsetY = mc.mainWindow.getScaledHeight() / 2 - sizeY / 2 + 10;

        String str2 = "Reset Points (RMB): " + this.capData.getData().resetPoints;

        mc.fontRenderer.drawStringWithShadow(str2, offsetX + 10, offsetY, TextFormatting.GREEN
                .getColor());

        String str = "Points (LMB): " + this.capData.getFreePoints(unitData);

        mc.fontRenderer.drawStringWithShadow(str, offsetX + 10, offsetY + mc.fontRenderer.FONT_HEIGHT + 5, TextFormatting.GREEN
                .getColor());

    }

}
