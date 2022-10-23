package com.rdactive.spigorigins;

import com.rdactive.spigorigins.Origins.Human;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OriginManager {


    protected static List<Asigner> AsignerList = new ArrayList<>();
    protected static List<Origin> OriginList = new ArrayList<>();
    protected static Origin DefaultOrigin = new Human();


    /**
     * Retrieves the list of all registered asigners
     * @return List of asigner
     */
    public static List<Asigner> getAsignerList() {
        return AsignerList;
    }

    /**
     * Creates a new asigner
     * @param playerName The player's name
     */
    public static void createAsigner(String playerName){
        Asigner as = new Asigner(playerName);
        as.setOrigin(getDefaultOrigin().getID());
        AsignerList.add(as);
    }

    /**
     * Creates a new asigner
     * @param player The player
     */
    public static void createAsigner(@NotNull Player player){
        Asigner as = new Asigner(player.getName());
        as.setOrigin(getDefaultOrigin().getID());
        AsignerList.add(as);
    }

    /**
     * Creates a new asigner
     * @param playerName The player's name
     * @param originName The origin's name
     * @return The asigner
     */
    public static @NotNull Asigner createAsigner(String playerName, String originName){
        Asigner as = new Asigner(playerName);
        as.setOrigin(originName);
        AsignerList.add(as);
        return as;
    }

    /**
     * Creates a new asigner
     * @param player The player
     * @param originName The origin's name
     * @return The asigner
     */
    public static @NotNull Asigner createAsigner(@NotNull Player player, String originName){
        Asigner as = new Asigner(player.getName());
        as.setOrigin(originName);
        AsignerList.add(as);
        return as;
    }

    /**
     * Creates a new asigner
     * @param playerName The player's name
     * @param origin The origin
     * @return The asigner
     */
    public static @NotNull Asigner createAsigner(String playerName, @NotNull Origin origin){
        Asigner as = new Asigner(playerName);
        as.setOrigin(origin.getID());
        AsignerList.add(as);
        return as;
    }

    /**
     * Creates a new asigner.
     * @param player The player.
     * @param origin The origin.
     * @return The asigner.
     */
    public static @NotNull Asigner createAsigner(@NotNull Player player, @NotNull Origin origin){
        Asigner as = new Asigner(player.getName());
        as.setOrigin(origin.getID());
        AsignerList.add(as);
        return as;
    }

    /**
     * Gets an asigner
     * @param name the player
     * @param shouldGen Should an asigner be created if one cannot be found?
     * @return The asigner
     */
    public static @Nullable Asigner getAsigner(String name, boolean shouldGen){
        for (Asigner asigner : AsignerList) {
            if (Objects.equals(asigner.getPlayerName(), name)) {
                return asigner;
            }
        }
        if(shouldGen){
            createAsigner(name);
            for (Asigner asigner : AsignerList) {
                if (Objects.equals(asigner.getPlayerName(), name)) {
                    return asigner;
                }
            }
        }
        return null;
    }

    /**
     * Gets an asigner
     * @param name Name of the player
     * @param shouldGen Should an asigner be created if one cannot be found?
     * @return The asigner
     */
    public static @Nullable Asigner getAsigner(Player name, boolean shouldGen){
        for (Asigner asigner : AsignerList) {
            if (name.getName().equals(asigner.getPlayerName())) {
                return asigner;
            }
        }
        if(shouldGen){
            createAsigner(name);
            for (Asigner asigner : AsignerList) {
                if (name.getName().equals(asigner.getPlayerName())) {
                    return asigner;
                }
            }
        }
        return null;
    }
    /**
     * Checks config data for the player provided and creates an asigner based on said data
     * @param player the player
     * @return Returns asigner
     */
    public static @Nullable Asigner createAsignerFromConfig(@NotNull Player player){
        String org = Spigorigins.mainData.getString(player.getName()+".origin");
        if(org==null){
            return null;
        }
        else{
            return createAsigner(player,org);
        }
    }

    /**
     * Checks config data for the player provided and creates an asigner based on said data
     * @param name Name of the player
     * @return Returns asigner
     */
    public static @Nullable Asigner createAsignerFromConfig(String name){
        String org = Spigorigins.mainData.getString(name+".origin");
        if(org==null){
            return null;
        }
        else{
            return createAsigner(name,org);
        }
    }

    public static Origin getDefaultOrigin() {
        return DefaultOrigin;
    }


    /**
     *Returns all the origins registered
     * @return list of all origins
     */
    public static List<Origin> getOriginList() {
        return OriginList;
    }


    /**
     * Sets an origin to be the default origin for new players
     * @param defaultOrigin the origin
     */
    public static void setDefaultOrigin(Origin defaultOrigin) {
        DefaultOrigin = defaultOrigin;
    }


    /**
     *
     * Registers an origin to the main origin List
     * @param origin The origin to register
     */
    public static void registerOrigin(Origin origin){
        assert origin!=null : "Origin must not be null";
        assert !OriginList.contains(origin) : "Origin is already registered!";
        try {
            OriginList.add(origin);
        }
        catch (Error err){
            Bukkit.broadcastMessage("Origin: "+origin.getClass().getCanonicalName()+" Failed registering... error: "+err.getMessage());
        }
    }

    /**
     * Opens the GUI for selection
     * @param player Player that needs to open the GUI
     * @param mainOR the main Origin plugin (Spigorigins)
     */
    public static void openGUI(Player player, Plugin mainOR){
        //Create the inventory
        Inventory inventory= Bukkit.createInventory(player,27,"Select origin");
        //Fetch player asigner
        Asigner asigner = getAsigner(player,true);
        //Fetch the list of all origins
        List<Origin> originList=getOriginList();
        //Create a new ItemStack for the BG of the inventory
        ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        //Get the meta for the item
        ItemMeta emptyMeta = empty.getItemMeta();
        //Make sure meta isnt null!!
        assert emptyMeta != null;
        //Set CUSTOMMODELDATA to 100 for click detection
        emptyMeta.setCustomModelData(100);
        //Apply the new meta
        empty.setItemMeta(emptyMeta);


        //Loop through the inventory
        for(int i = 0; i < inventory.getSize(); i++){
            //Check if the index fits in the origin list
            if(originList.size()>i){
                //Create a new ItemStack for the origin
                ItemStack itmOrg = new ItemStack(originList.get(i).getIcon());
                //Get itemMeta
                ItemMeta itemMeta = itmOrg.getItemMeta();
                //Make sure meta isnt null!
                assert itemMeta != null;
                //Change the display name to state the origin
                itemMeta.setDisplayName(ChatColor.GOLD+"<==- "+ChatColor.GREEN+originList.get(i).getVisibleName()+ChatColor.GREEN+" -==>");
                //Create a string[] to store the object LORE
                List<String> lore = new ArrayList<>();
                //Add the origin ID for parsing
                lore.add(originList.get(i).getID());
                //Loop through advantages
                for(int l = 0; l < originList.get(i).getAdvantages().size(); l++){
                    //Add the advantage to the lore
                    lore.add(ChatColor.BOLD+""+ChatColor.GREEN+"+ "+ChatColor.RESET+ChatColor.WHITE+originList.get(i).getAdvantages().get(l));
                }
                //Loop through disadvantages
                for(int l = 0; l < originList.get(i).getDisadvantages().size(); l++){
                    //Add the disadvantage to the lore
                    lore.add(ChatColor.BOLD+""+ChatColor.RED+"- "+ChatColor.RESET+ChatColor.WHITE+originList.get(i).getDisadvantages().get(l));
                }

                //Set the lore to the meta
                itemMeta.setLore(lore);
                //Set CUSTOMMODELDATA to 101 for click detection
                itemMeta.setCustomModelData(101);
                //Set the meta
                itmOrg.setItemMeta(itemMeta);
                inventory.setItem(i,itmOrg);
            }
            else{
                //If not set to the background item
                inventory.setItem(i,empty);
            }

        }
        //Create a new ItemStack for the default origin
        ItemStack itmOrg = new ItemStack(getDefaultOrigin().getIcon());
        //Get itemMeta
        ItemMeta itemMeta = itmOrg.getItemMeta();
        //Make sure meta isnt null!
        assert itemMeta != null;
        //Change the display name to state the origin
        itemMeta.setDisplayName(ChatColor.GOLD+"<==- "+ChatColor.GREEN+getDefaultOrigin().getVisibleName()+ChatColor.GREEN+" -==>");
        //Create a string[] to store the object LORE
        List<String> lore = new ArrayList<>();
        //Add the origin ID for parsing
        lore.add(getDefaultOrigin().getID());
        //Loop through advantages
        for(int l = 0; l < getDefaultOrigin().getAdvantages().size(); l++){
            //Add the advantage to the lore
            lore.add(ChatColor.BOLD+""+ChatColor.GREEN+"+ "+ChatColor.RESET+ChatColor.WHITE+getDefaultOrigin().getAdvantages().get(l));
        }
        //Loop through disadvantages
        for(int l = 0; l < getDefaultOrigin().getDisadvantages().size(); l++){
            //Add the disadvantage to the lore
            lore.add(ChatColor.BOLD+""+ChatColor.RED+"- "+ChatColor.RESET+ChatColor.WHITE+getDefaultOrigin().getDisadvantages().get(l));
        }

        //Set the lore to the meta
        itemMeta.setLore(lore);
        //Set CUSTOMMODELDATA to 101 for click detection
        itemMeta.setCustomModelData(101);
        //Set the meta
        itmOrg.setItemMeta(itemMeta);
        inventory.setItem(26,itmOrg);

        player.openInventory(inventory);
    }

    /**
     * Gets the origin object from a player object
     * @param player The player
     * @return Origin object
     */
    public static @Nullable Origin getOrigin(Player player){
        Asigner asigner = getAsigner(player,true);
        if(asigner==null){
            return null;
        }
        for(int i = 0; i < getOriginList().size(); i++){
            if(getOriginList().get(i).getID().equalsIgnoreCase(asigner.getOrigin())){
                return getOriginList().get(i);
            }
        }
        if(asigner.getOrigin().equalsIgnoreCase(getDefaultOrigin().getID())){
            return getDefaultOrigin();
        }
        return null;
    }

    /**
     * Gets the origin object from an asigner
     * @param asigner The asigner
     * @return Origin object
     */
    public static @Nullable Origin getOrigin(Asigner asigner){
        for(int i = 0; i < getOriginList().size(); i++){
            if(getOriginList().get(i).getID().equalsIgnoreCase(asigner.getOrigin())){
                return getOriginList().get(i);
            }
        }
        if(asigner.getOrigin().equalsIgnoreCase(getDefaultOrigin().getID())){
            return getDefaultOrigin();
        }
        return null;
    }

    /**
     * Gets the origin object from an ID
     * @param ID The ID
     * @return Origin object
     */
    public static @Nullable Origin getOrigin(String ID){
        for(int i = 0; i < getOriginList().size(); i++){
            if(getOriginList().get(i).getID().equalsIgnoreCase(ID)){
                return getOriginList().get(i);
            }
            //Bukkit.broadcastMessage(ID+"!="+getOriginList().get(i).getID());

        }
        if(ID.equalsIgnoreCase(getDefaultOrigin().getID())){
            return getDefaultOrigin();
        }
        /* Bukkit.broadcastMessage(ID+"!="+getDefaultOrigin().getID()); */
        return null;
    }
}
