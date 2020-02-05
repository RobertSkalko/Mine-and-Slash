package com.robertx22.mine_and_slash.gui.map_info_gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.database.quests.data.QuestTaskData;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class MapInfoScreen extends BaseScreen implements INamedScreen {

    ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/map_info/window.png");
    public Minecraft mc;

    static int x = 318;
    static int y = 232;

    int mapX = 0;
    int mapY = 0;
    int questX = 0;
    int questY = 0;

    public MapInfoScreen() {
        super(x, y);
        this.mc = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        mapX = guiLeft + 57;
        mapY = guiTop + 165;
        questX = guiLeft + 246;
        questY = guiTop + 165;

        PlayerMapCap.IPlayerMapData data = Load.playerMapData(mc.player);

        if (data.hasTimeForMap()) {
            List<ITextComponent> questTooltip = new ArrayList<>();
            questTooltip.addAll(data.getMap().getTooltip());

            addButton(new ItemButton(
                    new ItemStack(ItemMap.Items.getOrDefault(data.getMap().rarity, ItemMap.Items.get(0))), questTooltip,
                    mapX, mapY
            ));

            QuestSaveData questData = Load.quests(mc.player).getMapQuestData();

            if (questData != null) {

                List<ITextComponent> taskTooltip = new ArrayList<>();

                ResourceLocation icon = new ResourceLocation("");

                for (QuestTaskData task : questData.tasks) {
                    taskTooltip.addAll(task.getQuest().getTooltip(task));
                    icon = task.getQuest().icon();
                }

                addButton(new ItemButton(icon, taskTooltip, questX, questY));

            }
        }
    }

    @Override
    public void render(int x, int y, float ticks) {

        drawBackground(ticks, x, y);

        super.render(x, y, ticks);

        renderTexts();

        this.buttons.forEach(b -> b.renderToolTip(x, y));

    }

    public void renderTexts() {

        FontRenderer font = mc.fontRenderer;

        String title = "Current Adventure Map Info";

        GuiUtils.renderScaledText(guiLeft + x / 2, guiTop + 15, 1.6D, title, TextFormatting.YELLOW);

        String timeLeft = "Time left: " + Load.playerMapData(mc.player).getMinutesLeft() + " minutes";

        GuiUtils.renderScaledText(guiLeft + x / 2, guiTop + 50, 1.5D, timeLeft, TextFormatting.AQUA);

        int y = 20;
        int x = 8;

        GuiUtils.renderScaledText(mapX + x, mapY - y, 1.5D, "Adventure Map", TextFormatting.RED);
        GuiUtils.renderScaledText(questX + x, questY - y, 1.5D, "Quest", TextFormatting.GREEN);

    }

    protected void drawBackground(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.x, this.y, 256, 512);

    }

    public static int xSize = 18;
    public static int ySize = 18;

    static ResourceLocation img = new ResourceLocation("");

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/map_info.png");
    }

    @Override
    public Words screenName() {
        return Words.MapInfo;
    }

    class ItemButton extends ImageButton {
        List<ITextComponent> tooltip;
        ItemStack stack;
        ResourceLocation icon;

        public ItemButton(ItemStack stack, List<ITextComponent> tooltip, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
            });

            this.stack = stack;
            this.tooltip = tooltip;

        }

        public ItemButton(ResourceLocation icon, List<ITextComponent> tooltip, int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
            });

            this.icon = icon;
            this.tooltip = tooltip;

        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            if (stack != null) {
                mc.getItemRenderer().renderItemAndEffectIntoGUI(stack, this.x, this.y);
            } else {
                RenderUtils.renderIcon(icon, this.x, this.y);
            }
        }

        @Override
        public void renderToolTip(int x, int y) {
            if (GuiUtils.isInRect(this.x, this.y, xSize, ySize, x, y)) {

                MapInfoScreen.this.renderTooltip(
                        TooltipUtils.compsToStrings(tooltip), x, y, Minecraft.getInstance().fontRenderer);

            }
        }
    }

}

