package com.robertx22.mine_and_slash.uncommon.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.UnknownStat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public class StatGUI extends Screen {

    Stat.StatGroup statgroup = Stat.StatGroup.Main;
    int currentElement = 0;
    HashMap<String, List<Stat>> statmap = new HashMap<>();

    public StatGUI() {
        super(new StringTextComponent("Stats Screen"));

        genStatList();

    }

    public static final String ID = Ref.MODID + ":stats_screen_gui";

    int sizeY = 220;
    int sizeX = 215;

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/stats_screen.png");
    private static final ResourceLocation icons = new ResourceLocation(Ref.MODID, "textures/gui/stat_icons.png");

    // Returns true if the given x,y coordinates are within the given rectangle
    public boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    float textScale = 0.8F;

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        super.render(mouseX, mouseY, partialTicks);

        //this.drawDefaultBackground();

        minecraft.getTextureManager().bindTexture(texture);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.blit(minecraft.mainWindow.getScaledWidth() / 2 - this.sizeX / 2, minecraft.mainWindow
                .getScaledHeight() / 2 - this.sizeY / 2, 0, 0, sizeX, sizeY);

        renderStats();

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

    private String getStatString(Stat stat, EntityCap.UnitData data) {

        String str = stat.translate() + ": " + data.getUnit()
                .getStat(stat)
                .formattedValue();

        if (stat.IsPercent()) {
            str += '%';
        }

        if (stat instanceof IUsableStat) {
            IUsableStat usable = (IUsableStat) stat;

            String value = formattedValue(usable.GetUsableValue(data.getLevel(), (int) data
                    .getUnit()
                    .getStat(stat).Value) * 100);

            str += " (" + value + "%)";

        }
        return str;

    }

    private List<Stat> getList() {

        List<Stat> list = new ArrayList<>();

        for (Map.Entry<String, List<Stat>> entry : statmap.entrySet()) {
            for (Stat stat : entry.getValue()) {
                list.add(stat);
            }
            list.add(new UnknownStat());
        }

        return list;

    }

    private int drawAndIncreaseSpacing(int x, int y, String str) {
        this.drawString(minecraft.fontRenderer, str, x, y, TextFormatting.GOLD.getColor());
        return this.getHeightSpacing();

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

        EntityCap.UnitData data = Load.Unit(minecraft.player);

        for (int i = currentElement; i < list.size(); i++) {
            if (i > -1) { // or scrolling crashes

                if (list.get(i) instanceof UnknownStat) {
                    added += this.drawAndIncreaseSpacing(x, y + added, "");
                    continue;
                }

                Stat stat = list.get(i);
                String str = this.getStatString(stat, data);

                if (added < this.sizeY - 50) {

                    // so i can use icons from spritesheet
                    minecraft.getTextureManager()
                            .deleteTexture(icons); // seems i need to delete and then
                    minecraft.getTextureManager()
                            .bindTexture(icons); // add it or else it wont works
                    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

                    blit(x - 22, y + added - this.getHeightSpacing() / 4, stat.getSpriteX(), stat
                            .getSpriteY(), 16, 16);

                    added += this.drawAndIncreaseSpacing(x, y + added, str);

                }
            }

        }

        return list.size();

    }

    private int getHeightSpacing() {
        return 18;
    }

    public static String formattedValue(float val) {

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

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
                .filter(stat -> stat.IsShownOnTooltip() && stat.statGroup()
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

                if (isInRect(x, y, group.width, group.height, (int) X, (int) Y)) {

                    this.currentElement = 0;
                    this.statgroup = group;
                    genStatList();
                }

            }

            return true;
        }
    }

}
