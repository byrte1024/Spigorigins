package com.rdactive.spigorigins;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class EventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        //player.sendMessage(ChatColor.YELLOW+" <== fetching asigner... ==> ");
        Asigner asigner = OriginManager.getAsigner(player,true);
        if(asigner==null){
            player.sendMessage(ChatColor.RED+" <== Asigner not found! generating... ==>");
            OriginManager.createAsigner(player);
            if(OriginManager.getAsigner(player,false)==null){
                player.kickPlayer(ChatColor.RED+" <== Asigner generation failed. all things related to the origins will not be active for you. contact moderators for help ==>");
            }
            else{
                //player.sendMessage(ChatColor.GREEN+" <== Asigner Generated!... ==>");
            }
        }
        else{
            //player.sendMessage(ChatColor.GREEN+" <== Asigner found! ==> ");
        }
        assert asigner != null;
        //player.sendMessage(ChatColor.GREEN+" <== Asigner data of: ''"+asigner.getOrigin()+"'' detected ==> ");

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractInventoryEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getCurrentItem()==null){
            return;
        }
        event.getInventory();
        ItemStack itm = event.getCurrentItem();
        if(!itm.hasItemMeta()){
            return;
        }
        if(!Objects.requireNonNull(itm.getItemMeta()).hasCustomModelData()){
            return;
        }

        if(itm.getItemMeta().getCustomModelData()==100){
            event.setCancelled(true);
            if(event.getView().getTitle().equals("Select origin")){

            }
            else{
                //itm.setType(Material.AIR);
            }
        }
        else if(itm.getItemMeta().getCustomModelData()==101){
            event.setCancelled(true);
            if(event.getView().getTitle().equals("Select origin")){
                Origin origin = OriginManager.getOrigin(itm.getItemMeta().getLore().get(0));
                if(origin==null){
                    player.sendMessage(ChatColor.RED+" <== The origin you clicked on was not found! this could be a server error or a refresh. try again later. ==>\n");
                    player.sendMessage(itm.getItemMeta().getLore().get(0));
                    player.closeInventory();
                    return;
                }
                Asigner asigner = OriginManager.getAsigner(player,true);
                if(asigner==null){
                    player.sendMessage(ChatColor.RED+" <== Asigner not found! generating... ==>");
                    OriginManager.createAsigner(player);
                    if(OriginManager.getAsigner(player,false)==null){
                        player.kickPlayer(ChatColor.RED+" <== Asigner generation failed. contact moderators for help ==>");
                        return;
                    }
                    else{
                        //player.sendMessage(ChatColor.GREEN+" <== Asigner Generated!... ==>");
                    }
                }
                else{

                }
                assert asigner != null;
                if(origin.getID()==asigner.getOrigin()){
                    player.sendMessage(ChatColor.RED + "<== you are already this origin! you cant switch to something you are! ==>");
                }
                else {
                    if (origin.getID() == OriginManager.getDefaultOrigin().getID()) {
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " <==================================>");
                        player.sendMessage(ChatColor.RESET + "You are a: " + ChatColor.GREEN + ChatColor.BOLD + origin.getVisibleName());
                        player.sendMessage(ChatColor.RESET + "" + ChatColor.GRAY + ChatColor.ITALIC + origin.getDescription());
                    } else {
                        if (player.getLevel() > 29) {
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " <==================================>");
                            player.sendMessage(ChatColor.RESET + "You changed into a: " + ChatColor.GREEN + ChatColor.BOLD + origin.getVisibleName());
                            player.sendMessage(ChatColor.RESET + "" + ChatColor.GRAY + ChatColor.ITALIC + origin.getDescription());
                            player.setLevel(player.getLevel() - 30);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "<== you already picked an origin! to pick an origin again costs 30 levels! ==>");
                        }
                    }
                }

            }
            else{
                //itm.setType(Material.AIR);
            }
        }

    }
}
