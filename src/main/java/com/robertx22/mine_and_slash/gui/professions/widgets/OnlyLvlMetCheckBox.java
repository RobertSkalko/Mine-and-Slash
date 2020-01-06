package com.robertx22.mine_and_slash.gui.professions.widgets;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import static com.mojang.blaze3d.platform.GlStateManager.*;

public class OnlyLvlMetCheckBox extends AbstractButton {
    public enum PickedRecipes {
        LVL_MET(1),
        LVL_NOT_MET(2),
        ALL(0);

        PickedRecipes(int n) {
            this.num = n;
        }

        public int num = 0;

    }

    private static final ResourceLocation img = new ResourceLocation(Ref.MODID, "textures/gui/profession/checkbox.png");
    public PickedRecipes checked = PickedRecipes.ALL;
    public boolean changed = false;

    public OnlyLvlMetCheckBox(int x, int y, int p_i51140_3_) {
        super(x, y, p_i51140_3_, 15, "");

    }

    @Override
    public void onPress() {

        if (this.checked == PickedRecipes.ALL) {
            this.checked = PickedRecipes.LVL_MET;

        } else if (this.checked == PickedRecipes.LVL_MET) {
            this.checked = PickedRecipes.LVL_NOT_MET;
        } else {
            this.checked = PickedRecipes.ALL;
        }
        this.changed = true;
    }

    @Override
    public void renderButton(int x, int y, float f) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(img);
        enableDepthTest();
        FontRenderer font = mc.fontRenderer;
        color4f(1.0F, 1.0F, 1.0F, this.alpha);
        enableBlend();
        blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
        blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
        blit(this.x, this.y, 0.0F, this.checked.num * 16, 15, this.height, 64, 64);
        this.renderBg(mc, x, y);
        this.drawString(font, this.getMessage(), this.x + 24, this.y + (this.height - 8) / 2, 14737632 | MathHelper
                .ceil(this.alpha * 255.0F) << 24);
    }
}
