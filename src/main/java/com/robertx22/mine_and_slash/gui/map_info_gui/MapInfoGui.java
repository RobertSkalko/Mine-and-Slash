package com.robertx22.mine_and_slash.gui.map_info_gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.gui.BaseScreen;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class MapInfoGui extends BaseScreen {

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/map_info/window.png");
    public Minecraft mc;

    static int x = 318;
    static int y = 232;

    public MapInfoGui() {
        super(x, y);
        this.mc = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        addButton(new MapItemButton(Load.playerMapData(mc.player)
                .getMap(), guiLeft + 9, guiTop + 8));

    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);
        super.render(x, y, ticks);

    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.blitOffset, 0.0F, 0.0F, this.x, this.y, 256, 512);

    }

    public static int xSize = 18;
    public static int ySize = 18;

    static ResourceLocation img = new ResourceLocation("");

    class MapItemButton extends ImageButton {

        MapItemData mapdata;
        ItemStack stack;

        public MapItemButton(MapItemData mapdata, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
            });

            this.mapdata = mapdata;
            this.stack = new ItemStack(ItemMap.Items.getOrDefault(mapdata.rarity, ItemMap.Items
                    .get(0)));

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            mc.getItemRenderer().renderItemAndEffectIntoGUI(stack, this.x, this.y);

            renderToolTip(x, y);
        }

        @Override
        public void renderToolTip(int x, int y) {
            if (GuiUtils.isInRect(this.x, this.y, xSize, ySize, x, y)) {

                List<ITextComponent> tooltip = new ArrayList<>();

                tooltip.addAll(mapdata.getTooltip());

                MapInfoGui.this.renderTooltip(TooltipUtils.compsToStrings(tooltip), x, y, Minecraft
                        .getInstance().fontRenderer);

            }
        }
    }

}

