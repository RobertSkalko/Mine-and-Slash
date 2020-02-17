package com.robertx22.mine_and_slash.gui.talent_tree_gui;

import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.gui.bases.BasePerkTreeScreen;
import com.robertx22.mine_and_slash.gui.bases.IAlertScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.saveclasses.talents.PlayerTalentsData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap.IPlayerTalentsData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TalentPerkTreeScreen extends BasePerkTreeScreen<Perk, PlayerTalentsData, IPlayerTalentsData> implements IAlertScreen {

    public TalentPerkTreeScreen() {
        super();
    }

    @Override
    public ResourceLocation getBorderTexture() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/talent_frame.png");
    }

    @Override
    public ResourceLocation getSpaceTexture() {
        return new ResourceLocation(Ref.MODID, "textures/gui/talents/space.png");
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
    public boolean shouldAlert() {
        try {
            return Load.talents(Minecraft.getInstance().player)
                    .getFreePoints(Load.Unit(Minecraft.getInstance().player)) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
