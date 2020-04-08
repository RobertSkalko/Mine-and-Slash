package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Storable
public class AllocatedAbilitiesData implements IApplyableStats {

    @Store
    public int resetPoints = 0;

    @Store
    public HashMap<String, AbilityData> map = new HashMap<>();

    @Store
    public HashMap<String, Integer> schoolPoints = new HashMap<>();

    public int getSchoolPoints(SpellSchools school) {
        return schoolPoints.getOrDefault(school.name(), 0);
    }

    public HashMap<String, AbilityData> getAbilityMap() {
        return map;
    }

    public void clearBonusLevels() {
        map.entrySet()
            .forEach(x -> x.getValue()
                .setBonusLvl(0));
    }

    public void addBonusAbilityLevelsTo(SpellSchools school, int lvls) {
        map.entrySet()
            .forEach(e -> {
                if (e.getValue()
                    .getAbility()
                    .getSchool()
                    .equals(school)) {
                    e.getValue()
                        .setBonusLvl(e.getValue()
                            .getBonusLvls() + lvls);
                }
            });
    }

    public boolean canRemoveSchoolPoint(SpellSchools school) {

        int points = getSchoolPoints(school);

        if (points < 1) {
            return false;
        }

        for (Map.Entry<String, AbilityData> entry : map.entrySet()) {
            if (entry.getValue()
                .isValid()) {

                IAbility ability = entry.getValue()
                    .getAbility();

                if (ability.getSchool()
                    .equals(school)) {
                    if (ability.getSchoolPointsNeeded() > points - 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean canAddPointsToSchool(SpellSchools school, EntityCap.UnitData data) {

        if (getFreeAbilityPoints(data) < 1) {
            return false;
        }

        if (getSchoolsWithAllocatedPoints() > 1) {
            if (getSchoolPoints(school) < 1) {
                return false; // only allow picking 2 spell schools
            }
        }

        return getSchoolPoints(school) < SpellSchools.MAXIMUM_POINTS;
    }

    public int getSchoolsWithAllocatedPoints() {
        int i = 0;

        for (Map.Entry<String, Integer> x : schoolPoints.entrySet()) {
            if (x.getValue() > 0) {
                i++;
            }
        }

        return i;

    }

    @Override
    public void applyStats(EntityCap.UnitData data, int level) {

        try {
            schoolPoints.entrySet()
                .forEach(x -> {

                    SpellSchools school = SpellSchools.valueOf(x.getKey());
                    int points = x.getValue();

                    school.getStatsFor(points)
                        .forEach(s -> {
                            s.applyStats(data);
                        });

                });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setLevel(IAbility ability, int lvl) {
        AbilityData data = new AbilityData(ability);
        data.setLevel(lvl);
        map.put(ability.GUID(), data);
    }

    public List<BaseSpell> getAllocatedSpells() {
        List<BaseSpell> list = new ArrayList<>();

        this.map.values()
            .forEach(x -> {
                if (x.isValid() && x.getCurrentLevel() > 0 && x.type == IAbility.Type.SPELL) {
                    list.add((BaseSpell) x.getAbility());
                }
            });

        return list;
    }

    public List<Synergy> getAllocatedSynergies() {

        List<Synergy> list = new ArrayList<>();

        this.map.values()
            .forEach(x -> {
                if (x.isValid() && x.getCurrentLevel() > 0 && x.type == IAbility.Type.SYNERGY) {
                    list.add((Synergy) x.getAbility());
                }
            });

        return list;
    }

    public void addSchoolPoint(SpellSchools school) {
        int points = schoolPoints.getOrDefault(school.name(), 0) + 1;
        schoolPoints.put(school.name(), points);
    }

    public void removeSchoolPoint(SpellSchools school) {
        int points = schoolPoints.getOrDefault(school.name(), 0) - 1;
        schoolPoints.put(school.name(), points);
    }

    public int getAllocatedAbilityPoints() {

        int points = 0;

        for (AbilityData x : map.values()) {
            if (x.isValid()) {
                points += x.getCurrentLevel();
            }
        }

        for (Integer x : this.schoolPoints.values()) {
            points += x;
        }

        return points;
    }

    public int getTotalAllowedAbilityPoints(EntityCap.UnitData data) {

        int perlvl = (int) ((float) ModConfig.INSTANCE.Server.SPELL_POINTS_AT_MAX_LEVEL.get() / (float) ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get() * data.getLevel());

        int starting = ModConfig.INSTANCE.Server.STARTING_SPELL_POINTS.get();

        return starting + perlvl;

    }

    public int getFreeAbilityPoints(EntityCap.UnitData data) {
        return getTotalAllowedAbilityPoints(data) - getAllocatedAbilityPoints();
    }

    public boolean canAddPointsOrHasPoints(IAbility ability, EntityCap.UnitData data) {

        if (canAddPoints(ability, data)) {
            return true;
        }

        return getLevelOf(ability) > 0;

    }

    public boolean canAddPoints(IAbility ability, EntityCap.UnitData data) {

        if (getFreeAbilityPoints(data) < 1) {
            return false;
        }

        if (ability.getRequiredAbility() != null) {
            if (getLevelOf(ability.getRequiredAbility()) < 1) {
                return false;
            }
        }

        int current = schoolPoints.getOrDefault(ability.getSchool()
            .name(), 0);
        int needed = ability.getSchoolPointsNeeded();

        return current >= needed;

    }

    public int getLevelOf(IAbility ability) {
        return map.getOrDefault(ability.GUID(), EMPTY_ABILITY)
            .getCurrentLevel();
    }

    public int getLevelOf(String id) {
        return map.getOrDefault(id, EMPTY_ABILITY)
            .getCurrentLevel();
    }

    public boolean isAllocated(IAbility ability) {
        return getLevelOf(ability) > 0;
    }

    public boolean isAllocated(String id) {
        return getLevelOf(id) > 0;
    }

    public void addPoint(IAbility ability) {

        if (!map.containsKey(ability.GUID())) {
            map.put(ability.GUID(), new AbilityData(ability));
        }

        map.get(ability.GUID())
            .addLevels(1);

    }

    public void removePoint(IAbility ability) {
        if (!map.containsKey(ability.GUID())) {
            map.put(ability.GUID(), new AbilityData(ability));
        }
        map.get(ability.GUID())
            .addLevels(-1);
    }

    public void reset() {
        this.map.clear();
        this.schoolPoints.clear();

    }

    private static AbilityData EMPTY_ABILITY = new AbilityData();

    public void clean() {
        new HashMap<String, AbilityData>(map).entrySet()
            .forEach(x -> {
                if (!x.getValue()
                    .isValid()) {
                    map.remove(x.getKey());
                }
            });

    }
}
