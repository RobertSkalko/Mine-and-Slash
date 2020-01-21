package com.robertx22.mine_and_slash.gui.spell_hotbar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.cleric.InstantHealSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.BlizzardSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic.FrostballSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderspearSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.shaman.ThunderstormSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class SpellHotbarOverlay extends AbstractGui {

    private static final ResourceLocation HOTBAR_TEX = new ResourceLocation(Ref.MODID,
                                                                            "textures/gui/spells/hotbar.png"
    );

    static int WIDTH = 22;
    static int HEIGHT = 102;

    Minecraft mc = Minecraft.getInstance();

    PlayerSpellCap.ISpellsCap data;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderPlayerOverlay(RenderGameOverlayEvent event) {

        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        data = Load.spells(mc.player);

        int x = 0;
        int y = (int) (mc.mainWindow.getScaledHeight() / 2 - HEIGHT / 2);

        renderHotbar(x, y);
        renderSpellsOnHotbar(x, y);

    }

    private void renderSpellsOnHotbar(int x, int y) {

        HashMap<Integer, String> bar = data.getSpellData().getMap(PlayerSpellsData.Hotbar.FIRST);
        bar.put(1, new FrostballSpell().GUID());
        bar.put(2, new BlizzardSpell().GUID());
        bar.put(3, new ThunderspearSpell().GUID());
        bar.put(4, new ThunderstormSpell().GUID());
        bar.put(5, new InstantHealSpell().GUID());
        // TODO TEMP

        x += 3;
        y += 3;

        for (int i = 1; i < 6; i++) {
            BaseSpell spell = data.getSpellByKeybind(i);

            double scale = 0.5D;
            RenderSystem.scaled(scale, scale, scale);

            int xs = (int) (x * 1 / scale);
            int ys = (int) (y * 1 / scale);

            mc.getTextureManager().bindTexture(spell.getSchool().getGuiIconLocation());
            this.blit(xs, ys, 0, 0, 32, 32, 32, 32);

            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            mc.getTextureManager().bindTexture(spell.getIcon());
            this.blit(xs, ys, 0, 0, 32, 32, 32, 32);

            RenderSystem.scaled(1 / scale, 1 / scale, 1 / scale);

            y += 20;

        }

    }

    private void renderHotbar(int x, int y) {

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(HOTBAR_TEX);

        this.blit(x, y, 0, 0, WIDTH, HEIGHT);

    }
}