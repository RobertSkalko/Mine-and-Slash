package com.robertx22.mine_and_slash.gui.spell_hotbar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.onevent.ontick.OnClientTick;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpellHotbarOverlay extends AbstractGui {

    public static PlayerSpellsData.Hotbar CURRENT_HOTBAR = PlayerSpellsData.Hotbar.FIRST;

    private static final ResourceLocation HOTBAR_TEX = new ResourceLocation(Ref.MODID,
                                                                            "textures/gui/spells/hotbar.png"
    );
    private static final ResourceLocation COOLDOWN_TEX = new ResourceLocation(Ref.MODID,
                                                                              "textures/gui/spells/cooldown.png"
    );
    private static final ResourceLocation SPELL_READY_TEXT = new ResourceLocation(Ref.MODID,
                                                                                  "textures/gui/spells/spell_ready.png"
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

        x += 3;
        y += 3;

        for (int i = 0; i < 5; i++) {
            BaseSpell spell = data.getSpellByKeybind(i, CURRENT_HOTBAR);

            if (spell != null) {
                double scale = 0.5D;
                RenderSystem.scaled(scale, scale, scale);

                int xs = (int) (x * 1 / scale);
                int ys = (int) (y * 1 / scale);

                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

                mc.getTextureManager().bindTexture(spell.getIcon());
                this.blit(xs, ys, 0, 0, 32, 32, 32, 32);

                SpellData spelldata = data.getSpellData().getDataBySpell(spell, CURRENT_HOTBAR);

                if (spelldata != null) {
                    if (spelldata.cooldownIsReady() == false) {
                        float percent = (float) spelldata.getRemainingCooldown() / (float) spell.getCooldownInTicks();

                        RenderSystem.enableBlend(); // enables transparency

                        mc.getTextureManager().bindTexture(COOLDOWN_TEX);
                        this.blit(xs, ys, 0, 0, 32, (int) (32 * percent), 32, 32);

                        RenderSystem.disableBlend(); // enables transparency

                    }
                }

                RenderSystem.scaled(1 / scale, 1 / scale, 1 / scale);

                if (spelldata != null) {
                    if (spelldata.cooldownIsReady()) {
                        if (OnClientTick.COOLDOWN_READY_MAP.getOrDefault(spell.GUID(), 0) > 0) {

                            RenderSystem.enableBlend(); // enables transparency
                            mc.getTextureManager().bindTexture(SPELL_READY_TEXT);
                            this.blit(x - 2, y - 2, 0, 0, 20, 20, 20, 20);
                            RenderSystem.disableBlend(); // enables transparency

                        }
                    }
                }
            }
            y += 20;
        }
    }

    private void renderHotbar(int x, int y) {

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(HOTBAR_TEX);

        this.blit(x, y, 0, 0, WIDTH, HEIGHT);

    }
}