package com.robertx22.mine_and_slash.gui.trader;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.List;

public class TraderScreen extends BaseScreen {

    public TraderScreen(List<ItemStack> stacks) {
        super(176, 166);
        this.stacks = stacks;

        Preconditions.checkArgument(stacks.size() < 28);
    }

    List<ItemStack> stacks;

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        super.render(mouseX, mouseY, partialTicks);

        drawTooltips(mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();

        int xpos;
        int ypos;

        int i = 0;
        for (xpos = 0; xpos < 3; ++xpos) {
            for (ypos = 0; ypos < 9; ++ypos) {

                if (stacks.size() > i) {

                    int x = guiLeft + 8 + ypos * 18;
                    int y = guiTop + 18 + xpos * 18;

                    this.addButton(new SoldItemButton(stacks.get(i), x, y));
                    i++;
                }
            }
        }
    }

    protected void drawTooltips(int mouseX, int mouseY) {
        this.buttons.forEach(x -> x.renderToolTip(mouseX, mouseY));
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/chest.png");

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.minecraft.getTextureManager()
            .bindTexture(texture);

        int x = (this.width - this.sizeX) / 2;
        int y = (this.height - this.sizeY) / 2;

        blit(x, y, 0, 0, this.sizeX, this.sizeY);
    }

    static class SoldItemButton extends ImageButton {

        public static int xSize = 16;
        public static int ySize = 16;

        static ResourceLocation BUTTON_TEX = new ResourceLocation(
            Ref.MODID, "textures/gui/hotbar_setup/hotbar_button.png");

        ItemStack stack;

        public SoldItemButton(ItemStack stack, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, new ResourceLocation(""), (button) -> {
            });

            this.stack = stack;

        }

        @Override
        public void renderToolTip(int mouseX, int mouseY) {

            if (GuiUtils.isInRectPoints(new Point(x, y), new Point(xSize, ySize), new Point(mouseX, mouseY))) {
                TooltipInfo info = new TooltipInfo(Minecraft.getInstance().player);
                GuiUtils.renderTooltip(stack, mouseX, mouseY);
            }
        }

        @Override
        public void onPress() {
            super.onPress();

        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            Minecraft mc = Minecraft.getInstance();
            mc.getTextureManager()
                .bindTexture(BUTTON_TEX);

            RenderSystem.disableDepthTest();

            // blit(this.x, this.y, 0, 0, this.width, this.height);

            RenderSystem.enableDepthTest();

            if (!stack.isEmpty()) {
                Minecraft.getInstance()
                    .getItemRenderer()
                    .renderItemAndEffectIntoGUI(stack, this.x, this.y);
            }
        }

    }
}
