package com.robertx22.mine_and_slash.uncommon.localization;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;

import java.util.Locale;

public enum Chats implements IAutoLocName {

    DivineMastery("[Healing & Damage]: " +
        "Invoke both the benevolence and wrath of the gods through you as you bless and recover your allies around you whilst smiting down blasphemers and heretics with the destructive powers of fire and lightning.  Like two sides of the same coin, Mastery of the Divine can either bring Restoration or Destruction."),

    HuntingMastery("(Damage & Utility): " +
        "With eyes like an eagle, send your arrows imbued with magic flying towards your prey, and rain the flurry of a 1000 arrows onto the land to secure the hunt.  Mastering Hunting is done with bows and crossbows and deals big hitting damage and evasive maneuvers."),

    NatureMastery("(DoT Damage, & Healing): " +
        "Summon the drums of the wild as you wrap yourself in thorny armor for protection and provide healing for yourself and allies whilst covering enemies in poisonous vines.  Mastery of Nature provides a versatile playstyle ready for any situation through defense, DoTs and Healing."),

    StormMastery("[Damage & Utility]: " +
        "Harness tridents of lightning and blitz your way through crowds in a flash whilst casting destructive electrical storms upon the battlefield.  Fast casting and incredible mobility serves as the Storm Master's unique ally."),

    FireMastery("[Damage]: " +
        "Blast away those you cross in an all-consuming infernal fire and summon forth eruptions of volcanic plume that smother your enemies in molten rocks and fireballs.  The Masters of Pyromancery excels in their big damage and relentless DoT's."),

    OceanMastery(" [Utility & Healing]: " +
        "Calling upon the depths of ocean's abyss, you may draw forth the chill of the deep to slow your foes or send the healing waters of the world over yourself and allies."),

    MapStarted("Adventure Map Successfully Sacrificed"),

    Youneedheartstone("You need a hearthstone item to attune to this block."),

    NoSpaceForPortal("No Space for the portals. Please make 13x13 wide room."),

    Dev_tools_enabled_contact_the_author("Devs tools enabled, if you see this please contact the author of Mine and Slash [robertx22], he forgot to disable them!"),

    Ran_Out_Of_Time("Ran Out Of Time"),

    You_have_attuned_to_this_Altar("You have attuned to this Altar"),

    Infusion_Failed_Horribly("Infusion Failed Horribly"),

    Not_enough_time("Not enough time"),

    Infusion_Failed("Infusion Failed"),

    Infusion_was_incredibly_Successful("Infusion was incredibly Successful"),

    A_Piece_of_gear_is_too_high_level_for_you("You Don't meet requirements of a piece of current gear."),

    Map_time_penalty_activated("Map time penalty activated"),

    Player_died_in_a_map_world("Player died in a map world"),

    You_have_ran_out_of_time("You have ran out of time"),

    Teleporting_back("Teleporting back"),

    No_targets_found("No targets found"),

    You_are_too_low_level("You are too low level"),

    Cooldown_not_met("Cooldown not met"),

    Weapon_durability_is_too_low("Weapon durability is too low"),

    You_have_leveled_up("You have leveled up"),

    No_bed_found("No bed found"),

    Teleport_canceled_due_to_movement("Teleport canceled due to movement"),

    You_are_not_inside_a_map_world("You are not inside a map world"),

    has_died_by_the_hands_of("has died by the hands of"),

    Not_attuned_to_any_altars("Not attuned to any altars"),

    Distance_too_high_to_teleport("Distance too high to teleport"),

    Teleport_started("Teleport started"),

    Not_enough_experience("Not enough experience"),

    Time_ran_out_due_to_Permadeath("Time ran out due to Permadeath"),

    Remaining_Map_Time_is("Remaining Map Time is"),

    Can_not_go_over_maximum_level("Can not go over maximum level"),

    This_is_not_an_Attunement_Altar("This is not an Attunement Altar");

    private String localization = "";

    Chats(String str) {
        this.localization = str;

    }

    @Override
    public IAutoLocName.AutoLocGroup locNameGroup() {
        return AutoLocGroup.Chat_Messages;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".chat." + formattedGUID();
    }

    @Override
    public String locNameForLangFile() {
        return localization;
    }

    @Override
    public String GUID() {
        return this.name()
            .toLowerCase(Locale.ROOT);
    }
}
