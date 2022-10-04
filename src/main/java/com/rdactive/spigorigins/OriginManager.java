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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OriginManager {

    protected static List<Asigner> AsignerList = new ArrayList<>();
    protected static List<Origin> OriginList = new ArrayList<>();
    protected static Origin DefaultOrigin = new Human();

    public static List<Asigner> getAsignerList() {
        return AsignerList;
    }
    public static void createAsigner(String playerName){
        Asigner as = new Asigner(playerName);
        AsignerList.add(as);
    }
    public static void createAsigner(Player player){
        Asigner as = new Asigner(player.getName());
        AsignerList.add(as);
    }
    public static void createAsigner(String playerName,String originName){
        Asigner as = new Asigner(playerName);
        as.setOrigin(originName);
        AsignerList.add(as);
    }
    public static void createAsigner(Player player,String originName){
        Asigner as = new Asigner(player.getName());
        as.setOrigin(originName);
        AsignerList.add(as);
    }
    public static void createAsigner(String playerName,Origin origin){
        Asigner as = new Asigner(playerName);
        as.setOrigin(origin.getID());
        AsignerList.add(as);
    }
    public static void createAsigner(Player player,Origin origin){
        Asigner as = new Asigner(player.getName());
        as.setOrigin(origin.getID());
        AsignerList.add(as);
    }


    public static Asigner getAsigner(String name,boolean shouldGen){
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
    public static Asigner getAsigner(Player name,boolean shouldGen){
        for (Asigner asigner : AsignerList) {
            if (name.getName()  ==asigner.getPlayerName()) {
                return asigner;
            }
        }
        if(shouldGen){
            createAsigner(name);
            for (Asigner asigner : AsignerList) {
                if (name.getName()==asigner.getPlayerName()) {
                    return asigner;
                }
            }
        }
        return null;
    }

    public static Origin getDefaultOrigin() {
        return DefaultOrigin;
    }

    public static List<Origin> getOriginList() {
        return OriginList;
    }

    public static void setDefaultOrigin(Origin defaultOrigin) {
        DefaultOrigin = defaultOrigin;
    }

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
    public static void openGUI(Player player, Plugin mainOR){
        Inventory inventory= Bukkit.createInventory(player,27,"Select origin");
        Asigner asigner = getAsigner(player,true);
        List<Origin> originList=getOriginList();
        ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        Objects.requireNonNull(empty.getItemMeta()).setCustomModelData(100);

        for(int i = 0; i < inventory.getSize(); i++){
            if(originList.size()>i){
                ItemStack itmOrg = new ItemStack(originList.get(i).getIcon());
                ItemMeta itemMeta = itmOrg.getItemMeta();
                assert itemMeta != null;
                itemMeta.setDisplayName(ChatColor.GOLD+"<==- "+ChatColor.GREEN+originList.get(i).getVisibleName()+ChatColor.GREEN+" -==>");
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.ITALIC+""+ChatColor.GRAY+originList.get(i).getID());
                for(int l = 0; l < originList.get(i).getAdvantages().size(); l++){
                    lore.add(ChatColor.BOLD+""+ChatColor.GREEN+"+ "+ChatColor.RESET+ChatColor.WHITE+originList.get(i).getAdvantages().get(l));
                }
                for(int l = 0; l < originList.get(i).getDisadvantages().size(); l++){
                    lore.add(ChatColor.BOLD+""+ChatColor.GREEN+"+ "+ChatColor.RESET+ChatColor.WHITE+originList.get(i).getDisadvantages().get(l));
                }


                itemMeta.setLore(lore);
                itemMeta.setCustomModelData(101);

                itmOrg.setItemMeta(itemMeta);
                inventory.setItem(i,itmOrg);
            }
            else{
                inventory.setItem(i,empty);
            }

        }

        player.openInventory(inventory);
    }
}