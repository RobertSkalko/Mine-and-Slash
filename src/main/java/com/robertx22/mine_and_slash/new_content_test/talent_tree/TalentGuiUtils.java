package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.gui.TalentPointButton;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.gui.TalentScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TalentGuiUtils {

    public static List<Point> getPointsBetween(int x, int y, int x2, int y2) {
        List<Point> points = new ArrayList<>();

        boolean finished = false;
        int tries = 0;

        int posx = x;
        int posy = y;

        int finalx = x2;
        int finaly = y2;

        boolean growX = x < x2;
        boolean growY = y < y2;

        while (!finished && tries < 5000) {
            tries++;

            boolean grows = false;

            if (posx != finalx) {
                if (growX) {
                    posx++;
                } else {
                    posx--;
                }
                grows = true;
            }
            if (posy != finaly) {
                if (growY) {
                    posy++;
                } else {
                    posy--;
                }
                grows = true;
            }

            if (!grows) {
                finished = true;
            } else {
                points.add(new Point(posx, posy));
            }

        }

        return points;
    }

    public static void drawConnection(TalentScreen screen, TalentPointButton first,
                                      TalentPointButton second) {

        int x1 = first.getPosX((int) screen.scrollX);
        int y1 = first.getPosY((int) screen.scrollY);

        int x2 = second.getPosX((int) screen.scrollX);
        int y2 = second.getPosY((int) screen.scrollY);

    }

}
