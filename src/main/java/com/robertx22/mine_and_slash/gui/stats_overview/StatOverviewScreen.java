package com.robertx22.mine_and_slash.gui.stats_overview;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.UnknownStat;
import com.robertx22.mine_and_slash.database.talent_tree.RenderUtils;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class StatOverviewScreen extends Screen implements INamedScreen {

    Stat.StatGroup statgroup = Stat.StatGroup.Main;
    int currentElement = 0;
    HashMap<String, List<Stat>> statmap = new HashMap<>();

    public StatOverviewScreen() {
        super(new StringTextComponent("Stats Screen"));

    }

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/stat_overview.png");
    }

    @Override
    public Words screenName() {
        return Words.StatOverview;
    }

    @Override
    public void init() {
        genStatList();
    }

    int sizeY = 220;
    int sizeX = 215;

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/stats_screen.png");

    @Override
    public void render(int x, int y, float ticks) {

        minecraft.getTextureManager().bindTexture(texture);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.blit(minecraft.mainWindow.getScaledWidth() / 2 - this.sizeX / 2, minecraft.mainWindow
                .getScaledHeight() / 2 - this.sizeY / 2, 0, 0, sizeX, sizeY);

        renderStats();

        super.render(x, y, ticks);

        buttons.forEach(b -> b.renderToolTip(x, y));

    }

    private int getGUIStartX() {

        return (int) (minecraft.mainWindow.getScaledWidth() / 2 - this.sizeX / 2);
    }

    private int getGUIStartY() {

        return (int) (minecraft.mainWindow.getScaledHeight() / 2 - this.sizeY / 2);
    }

    private int getTextStartX() {

        return (int) (minecraft.mainWindow.getScaledWidth() / 2 - this.sizeX / 2 + 35);
    }

    private int getTextStartY() {

        return (int) (minecraft.mainWindow.getScaledHeight() / 2 - this.sizeY / 2 + 40);
    }

    public String getStatString(Stat stat, EntityCap.UnitData data) {

        String str = stat.translate() + ": " + data.getUnit()
                .getStat(stat)
                .formattedValue();

        if (stat.IsPercent()) {
            str += '%';
        }

        if (stat instanceof IUsableStat) {
            IUsableStat usable = (IUsableStat) stat;

            String value = NumberUtils.formatNumber(usable.GetUsableValue(data.getLevel(), (int) data
                    .getUnit()
                    .getStat(stat).val) * 100);

            str += " (" + value + "%)";

        }
        return str;

    }

    private List<Stat> getList() {
        List<Stat> list = new ArrayList<>();
        statmap.forEach((key, value) -> {
            list.addAll(value);
            list.add(new UnknownStat()); // as spacing
        });

        return list;

    }

    private int drawTitleAndIncreaseSpacing(int x, int y, String str) {
        this.drawString(minecraft.fontRenderer, str, x, y, TextFormatting.GREEN.getColor());
        return this.getHeightSpacing();

    }

    private int renderStats() {

        List<Stat> list = getList();

        int x = this.getTextStartX();
        int y = this.getTextStartY();

        int added = 0;

        added += this.drawTitleAndIncreaseSpacing(x - 22, y + added, this.statgroup.word.translate() + ": ");
        added += this.getHeightSpacing() / 3;

        y += added;

        //
        List<Widget> newlist = new ArrayList<>();
        for (Widget button : new ArrayList<Widget>(buttons)) {
            if (button instanceof StatButton == false) {
                newlist.add(button);
            }
        }
        this.buttons.clear();
        this.buttons.addAll(newlist);
        //

        EntityCap.UnitData data = Load.Unit(minecraft.player);

        for (int i = currentElement; i < list.size(); i++) {
            if (i > -1) { // or scrolling crashes

                if (added < this.sizeY - 60) {

                    Stat stat = list.get(i);

                    if (stat instanceof UnknownStat) {

                    } else {
                        this.addButton(new StatButton(data, stat, x, y));
                    }

                    y += getHeightSpacing();
                    added += getHeightSpacing();
                }

            }

        }

        return list.size();

    }

    private int getHeightSpacing() {
        return 18;
    }

    @Override
    public boolean mouseScrolled(double num1, double num2, double num3) {

        this.currentElement -= num3;
        this.currentElement = MathHelper.clamp(currentElement, 0, renderStats());

        return false;
    }

    void genStatList() {

        this.statmap = new HashMap<>();

        List<Stat> statlist = SlashRegistry.Stats()
                .getAll()
                .values()
                .stream()
                .filter(stat -> stat.IsShownOnStatGui() && stat.statGroup()
                        .equals(statgroup))
                .collect(Collectors.toList());

        Collections.sort(statlist, Comparator.comparing(stat -> stat.GUID()));

        List<Stat> misc = new ArrayList<>();

        for (Stat stat : statlist) {
            List<Stat> same = statlist.stream()
                    .filter(x -> x.getClass() == stat.getClass())
                    .collect(Collectors.toList());

            if (same.size() > 1) {
                statmap.put(stat.getClass().getName(), same);
            } else {
                misc.add(stat);
            }
        }
        statmap.put("misc", misc);

    }

    @Override
    public boolean mouseClicked(double X, double Y, int idk) {
        if (super.mouseClicked(X, Y, idk)) {
            return true;
        } else {
            // my stuff

            for (Stat.StatGroup group : Stat.StatGroup.values()) {

                int x = group.X() + this.getGUIStartX();
                int y = group.Y + this.getGUIStartY();

                if (GuiUtils.isInRect(x, y, group.width, group.height, (int) X, (int) Y)) {

                    this.currentElement = 0;
                    this.statgroup = group;
                    genStatList();
                }

            }

            return true;
        }
    }

    static int button_sizeX = 18;
    static int button_sizeY = 18;

    static ResourceLocation BUTTON_TEX = new ResourceLocation("");

    class StatButton extends ImageButton {

        FontRenderer font = Minecraft.getInstance().fontRenderer;
        Stat stat;
        EntityCap.UnitData unitdata;

        public StatButton(EntityCap.UnitData unitdata, Stat statData, int xPos,
                          int yPos) {
            super(xPos, yPos, button_sizeX, button_sizeY, 0, 0, button_sizeY, BUTTON_TEX, (button) -> {
            });

            this.stat = statData;
            this.unitdata = unitdata;

        }

        @Override
        public void renderToolTip(int x, int y) {
            if (isInside(x, y)) {

                List<ITextComponent> tooltip = new ArrayList<>();

                tooltip.add(Styles.BLUECOMP().appendSibling(stat.locName()));

                tooltip.add(Styles.GREENCOMP().appendSibling(stat.locDesc()));

                StatOverviewScreen.this.renderTooltip(TooltipUtils.compsToStrings(tooltip), x, y, Minecraft
                        .getInstance().fontRenderer);

            }
        }

        public boolean isInside(int x, int y) {
            return GuiUtils.isInRect(getIconX(), getIconY(), button_sizeX, button_sizeY, x, y);
        }

        public int getIconX() {
            return this.x - 22;
        }

        public int getIconY() {
            return this.y - getHeightSpacing() / 4;
        }

        @Override
        public void renderButton(int x, int y, float f) {
            // super.renderButton(x, y, f);

            if (!(stat instanceof UnknownStat)) {

                String str = StatOverviewScreen.this.getStatString(stat, unitdata);

                ResourceLocation res = stat.getIconLocation();

                RenderUtils.renderIcon(res, getIconX(), getIconY());

                StatOverviewScreen.this.drawString(font, str, this.x, this.y, TextFormatting.GOLD
                        .getColor());
            }
        }

    }
}
