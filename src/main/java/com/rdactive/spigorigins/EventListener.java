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
        player.sendMessage(ChatColor.YELLOW+" <== fetching asigner... ==> ");
        Asigner asigner = OriginManager.getAsigner(player,true);
        if(asigner==null){
            player.sendMessage(ChatColor.RED+" <== Asigner not found! generating... ==>");
            OriginManager.createAsigner(player);
            if(OriginManager.getAsigner(player,false)==null){
                player.kickPlayer(ChatColor.RED+" <== Asigner generation failed. contact moderators for help ==>");
            }
            else{
                player.sendMessage(ChatColor.GREEN+" <== Asigner Generated!... ==>");
            }
        }
        else{
            player.sendMessage(ChatColor.GREEN+" <== Asigner found! ==> ");
        }
        assert asigner != null;
        player.sendMessage(ChatColor.GREEN+" <== Asigner data of: ''"+asigner.getOrigin()+"'' ==> ");

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractInventoryEvent(InventoryClickEvent event){
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
                itm.setType(Material.AIR);
            }
        }
        else if(itm.getItemMeta().getCustomModelData()==101){
            event.setCancelled(true);
            if(event.getView().getTitle().equals("Select origin")){

            }
            else{
                itm.setType(Material.AIR);
            }
        }

    }
}
