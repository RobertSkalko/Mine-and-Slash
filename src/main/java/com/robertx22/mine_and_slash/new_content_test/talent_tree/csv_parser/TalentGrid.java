package com.robertx22.mine_and_slash.new_content_test.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        for (List<GridPoint> list : grid) {

            for (GridPoint point : list) {
                if (point.isTalent()) {

                    HashMap<GridPoint, List<GridPoint>> toCheck = new HashMap<>();

                    for (GridPoint check : getSurroundingPoints(point)) {

                        if (check.isTalent()) {
                            System.out.println("Error, can't have 2 talents together, they must have a connector");
                            System.out.println(check.x + " - " + check.y);
                        }

                        if (check.isConnector()) {
                            toCheck.put(check, getSurroundingPoints(check));
                        }

                    }

                    HashMap<GridPoint, List<GridPoint>> toCheckAlso = new HashMap<>();

                    int checks = 0;

                    while (toCheck.size() > 0 && checks < 10000) {

                        checks++;

                        //toCheckAlso.clear();

                        for (Map.Entry<GridPoint, List<GridPoint>> entry : toCheck.entrySet()) {

                            boolean checkConnections = true;

                            for (GridPoint check : entry.getValue()) {

                                if (check.isTalent()) {
                                    Perk perk = point.getPerk();
                                    Perk checkPerk = check.getPerk();

                                    boolean connected = perk.tryConnectTo(checkPerk);

                                    toCheckAlso.put(check, new ArrayList<>());

                                    if (perk != checkPerk || connected) {
                                        // disable searching this tree
                                        checkConnections = false;
                                    }

                                }

                            }

                            if (checkConnections) {
                                for (GridPoint check : entry.getValue()) {
                                    if (check.isConnector()) {
                                        toCheckAlso.put(entry.getKey(), getSurroundingPoints(check));
                                    }
                                }

                            }
                        }

                        toCheck.clear();

                        for (Map.Entry<GridPoint, List<GridPoint>> e : toCheckAlso.entrySet()) {
                            toCheck.put(e.getKey(), e.getValue());
                        }

                    }

                }

            }

        }

    }

    List<GridPoint> getAllConnectors(GridPoint point) {
        return getSurroundingPoints(point).stream()
                .filter(x -> x.isConnector())
                .collect(Collectors.toList());
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
