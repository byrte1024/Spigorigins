package com.rdactive.spigorigins;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public abstract class Origin {

    public Origin(String visibleName, String ID,String description,List<String> Advantages,List<String> Disadvantages,Material Icon,char emojNam){
        this.Icon=Icon;
        this.description=description;
        this.ID=ID;
        this.emojNam=emojNam;
        this.Advantages=Advantages;
        this.Disadvantages=Disadvantages;
        this.visibleName=visibleName;
    }
    protected String visibleName; // the name that will appear when the user views the origin from the in game viewer. try not to include these letters: []{}.,;:&% these wont break it. but the display name might not render them in the name
    protected String ID; //SPACES & SPECIAL CHARACTERS WILL BREAK IT!
    protected String description; // The description for the origin. (should be about the origin's lore and about the creature not advantages / disadvantages!) try not to include these letters: []{}.,;:&% these wont break it. but might not render in your text
    protected List<String> Advantages; // The list of all advantages for the origin. try not to include these letters: []{}.,;:&% these wont break it. but might not render in your text
    protected List<String> Disadvantages; // The list of all disadvantages for the origin. try not to include these letters: []{}.,;:&% these wont break it. but might not render in your text
    protected Material Icon; // The icon displayed in the select menu | it is important that the material used is an ITEM material. so for example WALL_TORCH will not work as it cannot be obtained as an item
    protected char emojNam; // The emoji associated with the origin (for resource packs) | do not insert regular emojis! insert either unicode characters that minecraft renders like emojis. or a character changed with a resource pack. you can leave this empty by entering ' ' as the input
    public abstract void applyEffects(Player player, Asigner asigner, Plugin originPL); // This function is called every 10 ticks. use this to apply the effects for the origin
    public abstract void onOriginSelect(Player player,Asigner asigner,Plugin originPL); // This function is called when a player switches to this origin
    public abstract void onOriginLeave(Player player,Asigner asigner,Plugin originPL); // This function is called when a player switches from this origin


    public char getEmojNam()// Retrieves the emoji name of the origin
    {
        return emojNam;
    }

    public Material getIcon()

    {
        return Icon;
    }



    static public void resetEffects(Player player, Asigner asigner, Plugin originPL) // Used to reset all active effects (called when switching origins and joining the server)

    {

    }
    public String getDescription() // Retrieves the description of the origin. if it includes these letters: []{}.,;:&% it is possible you are not getting the full string
    {
        return description;
    }

    public String getID() // Retrieves the ID of the origin
    {
        return ID;
    }

    public String getVisibleName()// Retrieves the visible name of the origin. if it includes these letters: []{}.,;:&% it is possible you are not getting the full string
    {
        return visibleName;
    }

    public List<String> getAdvantages()// Retrieves the advantage of the origin. if any member includes these letters: []{}.,;:&% it is possible you are not getting the full list
    {
        return Advantages;
    }

    public List<String> getDisadvantages()// Retrieves the disadvantage of the origin. if any member includes these letters: []{}.,;:&% it is possible you are not getting the full list
    {
        return Disadvantages;
    }


    @Override
    public String toString()
    {
        return ID;
    }
}
