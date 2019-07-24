package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.axes.AxeFire;
import com.robertx22.mine_and_slash.database.unique_items.axes.AxeThunder;
import com.robertx22.mine_and_slash.database.unique_items.axes.AxeWaterFire;
import com.robertx22.mine_and_slash.database.unique_items.boots.BootsFire;
import com.robertx22.mine_and_slash.database.unique_items.boots.BootsNature;
import com.robertx22.mine_and_slash.database.unique_items.boots.BootsThunder;
import com.robertx22.mine_and_slash.database.unique_items.boots.BootsWater;
import com.robertx22.mine_and_slash.database.unique_items.bows.BowFire;
import com.robertx22.mine_and_slash.database.unique_items.bows.BowNature;
import com.robertx22.mine_and_slash.database.unique_items.bows.BowThunder;
import com.robertx22.mine_and_slash.database.unique_items.bows.BowWater;
import com.robertx22.mine_and_slash.database.unique_items.bracelets.*;
import com.robertx22.mine_and_slash.database.unique_items.charms.CharmFire;
import com.robertx22.mine_and_slash.database.unique_items.charms.CharmNature;
import com.robertx22.mine_and_slash.database.unique_items.charms.CharmThunder;
import com.robertx22.mine_and_slash.database.unique_items.charms.CharmWater;
import com.robertx22.mine_and_slash.database.unique_items.chest.*;
import com.robertx22.mine_and_slash.database.unique_items.hammers.HammerPhysical;
import com.robertx22.mine_and_slash.database.unique_items.hammers.HammerThunder;
import com.robertx22.mine_and_slash.database.unique_items.helmet.*;
import com.robertx22.mine_and_slash.database.unique_items.necklaces.*;
import com.robertx22.mine_and_slash.database.unique_items.pants.PantsFire;
import com.robertx22.mine_and_slash.database.unique_items.pants.PantsNature;
import com.robertx22.mine_and_slash.database.unique_items.pants.PantsThunder;
import com.robertx22.mine_and_slash.database.unique_items.pants.PantsWater;
import com.robertx22.mine_and_slash.database.unique_items.rings.*;
import com.robertx22.mine_and_slash.database.unique_items.shields.ShieldEleResist;
import com.robertx22.mine_and_slash.database.unique_items.shields.ShieldElemental;
import com.robertx22.mine_and_slash.database.unique_items.shields.ShieldWisdom;
import com.robertx22.mine_and_slash.database.unique_items.staffs.*;
import com.robertx22.mine_and_slash.database.unique_items.swords.ElementalSaber;
import com.robertx22.mine_and_slash.database.unique_items.swords.SwordNature;
import com.robertx22.mine_and_slash.database.unique_items.swords.SwordPhysical;
import com.robertx22.mine_and_slash.database.unique_items.swords.SwordWater;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import net.minecraft.item.Item;

public class UniqueGears implements ISlashRegistryInit {

    /**
     * this needs to be called before serialization of config
     */

    @Override
    public void registerAll() {

        add(new RingHermitsInsanity());
        add(new EleSpellDmgStaff(Elements.Physical));
        add(new MagesLuckyAmulet());

        add(new HelmetWisdom());
        add(new ShieldWisdom());

        add(new ElementalSaber(Elements.Physical));
        add(new ShieldElemental(Elements.Physical));

        add(new ShieldEleResist());
        // bows
        add(new BowNature());
        add(new BowWater());
        add(new BowThunder());
        add(new BowFire());

        // charms
        add(new CharmThunder());
        add(new CharmWater());
        add(new CharmFire());
        add(new CharmNature());

        // pants
        add(new PantsThunder());
        add(new PantsWater());
        add(new PantsNature());
        add(new PantsFire());

        // helmet
        add(new HelmetWater());
        add(new HelmetMana());
        add(new HelmetFire());
        add(new HelmetThunder());
        add(new HelmetNature());

        // chest
        add(new ChestFire());
        add(new ChestWater());
        add(new ChestDodge());
        add(new ChestNature());
        add(new ChestThunder());
        add(new ChestMana());

        // boots
        add(new BootsNature());
        add(new BootsWater());
        add(new BootsFire());
        add(new BootsThunder());

        // hammers
        add(new HammerThunder());
        add(new HammerPhysical());

        // swords
        add(new SwordNature());
        add(new SwordWater());
        add(new SwordPhysical());

        // axes
        add(new AxeWaterFire());
        add(new AxeFire());
        add(new AxeThunder());

        // rings
        add(new RingDodge());
        add(new RingWaterFire());
        add(new RingEnergy());
        add(new RingCrit());

        // bracelets
        add(new BraceletThunder());
        add(new BraceletWater());
        add(new BraceletThunderNature());
        add(new BraceletFire());
        add(new BraceletNature());
        add(new BraceletSetDrop());

        // necklaces
        add(new NecklaceNature());
        add(new NecklaceWater());
        add(new NecklaceFire());
        add(new NecklaceThunder());
        add(new NecklaceEnergy());
        add(new NecklaceSetDrop());

        // staffs
        add(new StaffFire());
        add(new StaffWater());
        add(new StaffThunder());
        add(new StaffNature());
        add(new StaffLifesteal());

    }

    private void add(Item item) {

        if (item instanceof IGenerated) {
            IGenerated<IUnique> gen = (IGenerated) item;
            for (IUnique uniq : gen.generateAllPossibleStatVariations()) {
                SlashRegistry.UniqueGears().register(uniq);
            }

        } else {
            IUnique uniq = (IUnique) item;
            SlashRegistry.UniqueGears().register(uniq);
        }
    }

}
