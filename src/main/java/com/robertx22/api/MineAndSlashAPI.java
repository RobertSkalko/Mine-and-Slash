package com.robertx22.api;

import com.robertx22.config.non_mine_items.ConfigItem;
import com.robertx22.config.non_mine_items.ConfigItems;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.MapAffixes;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.db_lists.Runes;
import com.robertx22.db_lists.Sets;
import com.robertx22.db_lists.Spells;
import com.robertx22.db_lists.StatusEffects;
import com.robertx22.db_lists.Suffixes;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.saveclasses.gearitem.gear_bases.BaseAffix;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.spells.bases.BaseSpell;


public class MineAndSlashAPI {

  public enum GearFamilly {
    Armor, Weapon, Jewerly, All
  }

  public static void addCompatibleItem(String itemID, ConfigItem item) {
    ConfigItems.INSTANCE.map.put(itemID, item);
    ConfigItems.INSTANCE.refreshList();
  }

  public static void addAffix(BaseAffix affix, GearFamilly family) {
    if (affix instanceof Prefix) {

      if (family == GearFamilly.All) {
        Prefixes.Armor.add((Prefix) affix);
        Prefixes.Jewerly.add((Prefix) affix);
        Prefixes.Weapon.add((Prefix) affix);

      } else if (family == GearFamilly.Armor) {
        Prefixes.Armor.add((Prefix) affix);
      } else if (family == GearFamilly.Jewerly) {
        Prefixes.Jewerly.add((Prefix) affix);
      } else {
        Prefixes.Weapon.add((Prefix) affix);
      }
      Prefixes.all.put(affix.GUID(), (Prefix) affix);

    } else {

      if (family == GearFamilly.All) {
        Suffixes.Armor.add((Suffix) affix);
        Suffixes.Jewerly.add((Suffix) affix);
        Suffixes.Weapon.add((Suffix) affix);

      } else if (family == GearFamilly.Armor) {
        Suffixes.Armor.add((Suffix) affix);
      } else if (family == GearFamilly.Jewerly) {
        Suffixes.Jewerly.add((Suffix) affix);
      } else {
        Suffixes.Weapon.add((Suffix) affix);
      }
      Suffixes.all.put(affix.GUID(), (Suffix) affix);
    }

  }

  public static void addMapAffix(BaseMapAffix affix) {
    MapAffixes.All.put(affix.GUID(), affix);
  }

  public static void addRuneWord(RuneWord word) {

    RuneWords.All.put(word.GUID(), word);
  }

  public static void addSet(Set set) {
    Sets.All.put(set.GUID(), set);
  }

  public static void addSpell(BaseSpell spell) {
    Spells.All.put(spell.GUID(), spell);
  }

  public static void addMobEffect(BaseStatusEffect effect) {
    StatusEffects.All.put(effect.GUID(), effect);
  }

  public static void addGearItemType(GearItemSlot type) {
    GearTypes.All.put(type.GUID(), type);
  }

  public static void addRune(BaseRuneItem rune) {
    Runes.All.put(rune.name(), rune);
  }



}
