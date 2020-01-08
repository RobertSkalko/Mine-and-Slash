package com.robertx22.mine_and_slash.gui.map_info_gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.gui.BaseScreen;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class MapInfoScreen extends BaseScreen {

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/map_info/window.png");
    public Minecraft mc;

    static int x = 318;
    static int y = 232;

    public MapInfoScreen() {
        super(x, y);
        this.mc = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        PlayerMapCap.IPlayerMapData data = Load.playerMapData(mc.player);

        if (data.hasTimeForMap()) {
            List<ITextComponent> questTooltip = new ArrayList<>();
            questTooltip.addAll(data.getMap().getTooltip());

            int x = guiLeft + 9;
            int y = guiTop + 8;

            addButton(new ItemButton(new ItemStack(ItemMap.Items.getOrDefault(data.getMap().rarity, ItemMap.Items
                    .get(0))), questTooltip, x, y));

            QuestSaveData questData = Load.quests(mc.player).getMapQuestData();

            x += 30;

            for (QuestTaskData task : questData.tasks) {

                List<ITextComponent> taskTooltip = new ArrayList<>();
                taskTooltip.addAll(task.getQuest().getTooltip(task));

                addButton(new ItemButton(new ItemStack(Items.MAP), taskTooltip, x, y));

                x += 20;

            }

        }
    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        super.render(x, y, ticks);

        this.buttons.forEach(b -> b.renderToolTip(x, y));
    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.blitOffset, 0.0F, 0.0F, this.x, this.y, 256, 512);

    }

    public static int xSize = 18;
    public static int ySize = 18;

    static ResourceLocation img = new ResourceLocation("");

    class ItemButton extends ImageButton {
        List<ITextComponent> tooltip;
        ItemStack stack;

        public ItemButton(ItemStack stack, List<ITextComponent> tooltip, int xPos,
                          int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
            });

            this.stack = stack;
            this.tooltip = tooltip;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {

            mc.getItemRenderer().renderItemAndEffectIntoGUI(stack, this.x, this.y);

        }

        @Override
        public void renderToolTip(int x, int y) {
            if (GuiUtils.isInRect(this.x, this.y, xSize, ySize, x, y)) {

                MapInfoScreen.this.renderTooltip(TooltipUtils.compsToStrings(tooltip), x, y, Minecraft
                        .getInstance().fontRenderer);

            }
        }
    }

}

