package com.rdactive.spigorigins;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public abstract class Origin {

    /**
     *
     * @param visibleName the name that will appear when the user views the origin from the in game viewer. try not to include these letters: []{}.,;:&% these wont break it. but the display name might not render them in the name
     * @param ID SPACES & SPECIAL CHARACTERS WILL BREAK IT!
     * @param description The description for the origin. (should be about the origin's lore and about the creature not advantages / disadvantages!) try not to include these letters: []{}.,;:&% these wont break it. but might not render in your text
     * @param Advantages The list of all advantages for the origin. try not to include these letters: []{}.,;:&% these wont break it. but might not render in your text
     * @param Disadvantages The list of all disadvantages for the origin. try not to include these letters: []{}.,;:&% these wont break it. but might not render in your text
     * @param Icon The icon displayed in the select menu | it is important that the material used is an ITEM material. so for example WALL_TORCH will not work as it cannot be obtained as an item
     * @param emojNam The emoji associated with the origin (for resource packs) | do not insert regular emojis! insert either unicode characters that minecraft renders like emojis. or a character changed with a resource pack. you can leave this empty by entering ' ' as the input
     */
    public Origin(String visibleName, String ID,String description,List<String> Advantages,List<String> Disadvantages,Material Icon,char emojNam){
        this.Icon=Icon;
        this.description=description;
        this.ID=ID;
        this.emojNam=emojNam;
        this.Advantages=Advantages;
        this.Disadvantages=Disadvantages;
        this.visibleName=visibleName;
    }
    protected String visibleName;
    protected String ID;
    protected String description;
    protected List<String> Advantages;
    protected List<String> Disadvantages;
    protected Material Icon;
    protected char emojNam;

    /**
     * This function is called every 10 ticks. use this to apply the effects for the origin
     * @param player The player to apply the effects to
     * @param asigner The asigner connected to said player
     */
    public abstract void applyEffects(Player player, Asigner asigner);

    /**
     * This function is called when a player switches from another origin to this one
     * @param player The player switching
     * @param asigner The asigner connected to said player
     */
    public abstract void onOriginSelect(Player player,Asigner asigner);

    /**
     * This function is called when a player switches from this origin to another.
     * @param player The player switching
     * @param asigner The asigner connected to said player
     */
    public abstract void onOriginLeave(Player player,Asigner asigner);


    /**
     * Retrieves the emoji name of the origin
     * @return Single emoji char
     */
    public char getEmojNam()
    {
        return emojNam;
    }

    /**
     * Retrieves the material for the origin preview (icon)
     * @return Material for icon
     */
    public Material getIcon()

    {
        return Icon;
    }


    /**
     * Used to reset all active effects (called when switching origins and joining the server)
     * @param player The player to reset
     * @param asigner The asigner of said player
     */
    static public void resetEffects(Player player, Asigner asigner)

    {
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.1);
        player.setAllowFlight(false);
        //player.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
    }

    /**
     * Retrieves the description of the origin. if it includes these letters: []{}.,;:&% it is possible you are not getting the full string
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Retrieves the ID of the origin
     * @return The ID
     */
    public String getID()
    {
        return ID;
    }

    /**
     * Retrieves the visible name of the origin. if it includes these letters: []{}.,;:&% it is possible you are not getting the full string
     * @return The visible name
     */
    public String getVisibleName()
    {
        return visibleName;
    }

    /**
     * Retrieves the advantages of the origin. if any member includes these letters: []{}.,;:&% it is possible you are not getting the full list
     * @return The advantages
     */
    public List<String> getAdvantages()
    {
        return Advantages;
    }

    /**
     * Retrieves the disadvantages of the origin. if any member includes these letters: []{}.,;:&% it is possible you are not getting the full list
     * @return THe disadvantages
     */
    public List<String> getDisadvantages()
    {
        return Disadvantages;
    }


    /**
     * Gives you a string value of the origin
     * @return the ID
     */
    @Override
    public String toString()
    {
        return ID;
    }
}
