package com.robertx22.mine_and_slash.database.items.spell_items;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.Callable;

@OnlyIn(Dist.CLIENT)
public class TomeRenderer extends ItemStackTileEntityRenderer implements Callable<ItemStackTileEntityRenderer> {
    public final ItemStackTileEntityRenderer instance;

    private final TomeModel tomeModel = new TomeModel();
    Minecraft mc;

    public TomeRenderer() {
        instance = this;
        this.mc = Minecraft.getInstance();
    }

    @Override
    public void renderByItem(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof BaseSpellItem) {

            BaseSpellItem tome = (BaseSpellItem) item;

            float scale = 1.2F;
            float rotation = 290;

            CompoundNBT nbt = mc.player.getPersistentData();

            float openPercent = nbt.getFloat(Ref.MODID + ":openPercent");

            float changeOpenPercent = (float) 1 / (float) item.getUseDuration(stack) / 3.5F;

            Minecraft.getInstance().getTextureManager().bindTexture(tome.texture);

            GlStateManager.pushMatrix();
            GlStateManager.rotatef(rotation, 0.05F, 0.2F, 0);
            GlStateManager.scaled(scale, -scale, -scale);
            GlStateManager.translatef(0.15F, -0.45F, -0.2F);
            GlStateManager.enableCull();

            if (isTheOneThatIsCurrentlyInUse(stack)) {
                openPercent += changeOpenPercent;
            } else {
                openPercent -= changeOpenPercent;
            }

            openPercent = MathHelper.clamp(openPercent, 0, 1);

            nbt.putFloat(Ref.MODID + ":openPercent", openPercent);

            float ticks = mc.getRenderPartialTicks() + mc.player.ticksExisted;

            tomeModel.render(ticks, 0, 0, openPercent, 0.0F, 0.0625F);
            GlStateManager.popMatrix();

        }

    }

    private boolean isTheOneThatIsCurrentlyInUse(ItemStack stack) {

        return mc.player.getActiveItemStack()
                .getItem() instanceof BaseSpellItem && mc.player.getActiveItemStack()
                .equals(stack);

    }

    @Override
    public ItemStackTileEntityRenderer call() throws Exception {
        return instance;
    }
}

