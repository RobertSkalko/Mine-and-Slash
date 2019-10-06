package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;

import java.util.ArrayList;
import java.util.List;

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
        int x = 0;
        for (List<GridPoint> list : grid) {
            int y = 0;
            for (GridPoint point : list) {
                if (point.isTalent()) {

                    List<GridPoint> alreadyChecked = new ArrayList<>();
                    List<GridPoint> toCheck = new ArrayList<>();
                    alreadyChecked.add(point);
                    toCheck.addAll(getSurroundingPoints(point));

                    List<GridPoint> toCheckAlso = new ArrayList<>();

                    int checks = 0;

                    while (toCheck.size() > 0 && checks < 50) {

                        checks++;

                        toCheckAlso.clear();
                        boolean checkConnections = true;

                        for (GridPoint check : toCheck) {
                            if (!alreadyChecked.contains(check)) {

                                if (check.isTalent()) {
                                    Perk perk = point.getPerk();
                                    Perk checkPerk = check.getPerk();

                                    alreadyChecked.add(check);

                                    boolean connected = perk.tryConnectTo(checkPerk);

                                }
                            }

                        }

                        if (checkConnections) {
                            for (GridPoint check : toCheck) {
                                if (!alreadyChecked.contains(check)) {
                                    alreadyChecked.add(check);
                                    if (check.isConnector()) {
                                        toCheckAlso.addAll(getSurroundingPoints(check));
                                    }
                                }

                            }

                        }

                        toCheck.clear();
                        toCheck.addAll(toCheckAlso);

                    }

                }
                y++;
            }
            x++;

        }

    }

    public List<GridPoint> getSurroundingPoints(GridPoint p) {

        List<GridPoint> list = new ArrayList<>();

        int x = p.x + 1;
        int y = p.y;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        x = p.x;
        y = p.y + 1;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        x = p.x + 1;
        y = p.y + 1;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        x = p.x - 1;
        y = p.y - 1;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        x = p.x - 1;
        y = p.y;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        x = p.x;
        y = p.y - 1;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        x = p.x + 1;
        y = p.y - 1;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        x = p.x - 1;
        y = p.y + 1;
        if (isInRange(x, y)) {
            list.add(get(x, y));
        }

        return list;

    }

    public boolean isInRange(int x, int y) {

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
