package com.robertx22.mine_and_slash.database.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkBuilder;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.database.talent_tree.data.StartPerkEffects;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.*;

public class PerkGrid<T extends GridPoint> {

    protected List<List<T>> grid = new ArrayList<>();

    public T get(int x, int y) {
        return grid.get(x)
            .get(y);
    }

    public T newGridPoint(int x, int y, String s) {
        return (T) new GridPoint(x, y, s);
    }

    public PerkGrid(String str) {

        int y = 0;
        for (String line : str.split("\n")) {
            int x = 0;
            for (String s : line.split(",")) {

                if (grid.size() <= x) {
                    grid.add(new ArrayList<>());
                }

                grid.get(x)
                    .add(newGridPoint(x, y, s));

                x++;

            }

            y++;
        }
    }

    public void createConnections() {

        List<T> talents = new ArrayList<>();

        for (List<T> list : grid) {
            for (T point : list) {
                if (point.isTalent()) {
                    talents.add(point);
                }
            }

        }

        for (T one : talents) {
            for (T two : talents) {
                if (hasPath(one, two)) {
                    one.getPerk()
                        .tryConnectTo(two.getPerk());
                }
            }
        }

    }

    private boolean hasPath(T start, T end) {
        Queue<T> openSet = new ArrayDeque<>();
        openSet.add(start);

        Set<T> closedSet = new HashSet<>();

        while (!openSet.isEmpty()) {
            T current = openSet.poll();

            if (current.equals(end)) {
                return true;
            }

            if (!closedSet.add(current)) {
                continue; // we already visited it
            }

            if (current.isTalent() && current != start) {
                continue; // skip exploring this path
            }

            openSet.addAll(getEligibleSurroundingPoints(current));
        }

        return false;
    }

    public List<T> getEligibleSurroundingPoints(T p) {

        List<T> list = new ArrayList<>();
        int x = p.x;
        int y = p.y;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                if (isInRange(x + dx, y + dy)) {
                    T point = get(x + dx, y + dy);

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

    public void createAndRegisterAll() {

        for (List<T> list : grid) {
            for (T point : list) {

                if (point.isTalent()) {

                    String id = point.getEffectID();

                    if (!SlashRegistry.PerkEffects()
                        .isRegistered(id)) {
                        id = id.toLowerCase(Locale.ROOT);
                    }

                    PerkEffect effect = null;

                    if (SlashRegistry.PerkEffects()
                        .isRegistered(id)) {
                        effect = SlashRegistry.PerkEffects()
                            .get(id);
                    }
                    if (effect == null) {
                        effect = StartPerkEffects.WARRIOR;

                        System.out.println(point.getID() + ": " + point.getEffectIDRaw() + " is a broken talent.");
                    }

                    Perk perk = (Perk) PerkBuilder.create(point.getID())
                        .pos(point.x, point.y)
                        .effect(effect)
                        .connections()
                        .build();

                }
            }

        }

    }

}
