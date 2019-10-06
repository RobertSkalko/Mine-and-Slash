package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;

import java.util.*;

public class TalentGrid {

    List<List<GridPoint>> grid = new ArrayList<>();

    public GridPoint get(int x, int y) {
        return grid.get(x).get(y);
    }

    public TalentGrid(String str) {

        int x = 0;
        for (String line : str.split("\n")) {
            int y = 0;
            for (String s : line.split(",")) {

                if (grid.size() <= x) {
                    grid.add(new ArrayList<>());
                }

                grid.get(x).add(new GridPoint(x, y, s));

                y++;

            }

            x++;
        }
    }

    public void createConnections() {

        List<GridPoint> talents = new ArrayList<>();

        for (List<GridPoint> list : grid) {
            for (GridPoint point : list) {
                if (point.isTalent()) {
                    talents.add(point);
                }
            }

        }

        for (GridPoint one : talents) {
            for (GridPoint two : talents) {
                if (hasPath(one, two)) {
                    one.getPerk().tryConnectTo(two.getPerk());
                }
            }
        }

    }

    boolean hasPath(GridPoint start, GridPoint end) {
        Queue<GridPoint> openSet = new ArrayDeque<>();
        openSet.add(start);

        Set<GridPoint> closedSet = new HashSet<>();

        while (!openSet.isEmpty()) {
            GridPoint current = openSet.poll();

            if (current.equals(end)) {
                return true;
            }

            if (!closedSet.add(current)) {
                continue; // we already visited it
            }

            if (current.isTalent() && current != start) {
                continue; // skip exploring this path
            }

            openSet.addAll(getSurroundingPoints(start, current));
        }

        return false;
    }

    public List<GridPoint> getSurroundingPoints(GridPoint start, GridPoint p) {

        List<GridPoint> list = new ArrayList<>();
        int x = p.x;
        int y = p.y;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                if (isInRange(x + dx, y + dy)) {
                    GridPoint point = get(x + dx, y + dy);

                    if (Math.abs(dx) == 1 && Math.abs(dy) == 1) { // we are discovering a diagonal
                        if (get(x + dx, y).isTalent() || get(x, y + dy).isTalent()) {
                            continue; // skip this diagonal, it crosses a talent
                        }
                    }

                    if (point.isTalent() || point.isConnector()) {
                        list.add(point);
                    }
                }
            }
        }

        return list;
    }

    public boolean isInRange(int x, int y) {

        if (y < 2) {
            return false;
        }

        try {
            return get(x, y) != null;
        } catch (Exception e) {
        }

        return false;
    }

    public void createPerks() {

        for (List<GridPoint> list : grid) {
            for (GridPoint point : list) {

                if (point.isTalent()) {
                    Perk perk = PerkBuilder.create(point.getID())
                            .pos(point.x, point.y)
                            .effect(PerkEffects.MAGIC_SHIELD_PERCENT.small())
                            .connections()
                            .build();

                    perk.registerToSlashRegistry();

                }

            }

        }

    }

}
