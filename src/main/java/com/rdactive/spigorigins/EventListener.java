package com.rdactive.spigorigins;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.Objects;

public class EventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        //player.sendMessage(ChatColor.YELLOW+" <== fetching asigner... ==> ");
        Asigner asigner = OriginManager.getAsigner(player,false);
        if(asigner==null){
            asigner=OriginManager.createAsignerFromConfig(player);
            if(asigner==null){
                player.sendMessage(ChatColor.RED+" <== Asigner not found! generating... ==>");
                OriginManager.createAsigner(player);
                if(OriginManager.getAsigner(player,false)==null){
                    player.kickPlayer(ChatColor.RED+" <== Asigner generation failed. contact moderators for help ==>");
                }
                //player.sendMessage(ChatColor.GREEN+" <== Asigner Generated!... ==>");

            }

        }
        //player.sendMessage(ChatColor.GREEN+" <== Asigner found! ==> ");

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
        }
        else if(itm.getItemMeta().getCustomModelData()==101){
            event.setCancelled(true);
            if(event.getView().getTitle().equals("Select origin")){
                Origin origin = OriginManager.getOrigin(Objects.requireNonNull(itm.getItemMeta().getLore()).get(0));
                if(origin==null){
                    player.sendMessage(ChatColor.RED+Spigorigins.mainConf.getString("Messages.OriginNotFound")+"\n");
                    player.sendMessage(itm.getItemMeta().getLore().get(0));
                    player.closeInventory();
                    return;
                }
                Asigner asigner = OriginManager.getAsigner(player,true);
                if(asigner==null){
                    asigner=OriginManager.createAsignerFromConfig(player);

                    if(asigner==null){
                        player.kickPlayer(ChatColor.RED+Spigorigins.mainConf.getString("Messages.AsignerGenFailed"));
                        return;
                    }
                }
                if(Objects.equals(origin.getID(), asigner.getOrigin())){
                    player.sendMessage(ChatColor.RED + Spigorigins.mainConf.getString("Messages.OriginSamePick"));
                }
                else {
                    if (Objects.equals(asigner.getOrigin(), OriginManager.getDefaultOrigin().getID())) {
                        player.closeInventory();
                        Origin.resetEffects(player,asigner);
                        asigner.setOrigin(origin.getID());
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + Spigorigins.mainConf.getString("Messages.OriginPickHeader"));
                        player.sendMessage(ChatColor.RESET + "You are a: " + ChatColor.GREEN + ChatColor.BOLD + origin.getVisibleName());
                        player.sendMessage(ChatColor.RESET + "" + ChatColor.GRAY + ChatColor.ITALIC + origin.getDescription());
                    } else {
                        if (player.getLevel() > 29) {
                            player.closeInventory();
                            Origin.resetEffects(player,asigner);
                            asigner.setOrigin(origin.getID());
                            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + Spigorigins.mainConf.getString("Messages.OriginPickHeader"));
                            player.sendMessage(ChatColor.RESET + "You changed into a: " + ChatColor.GREEN + ChatColor.BOLD + origin.getVisibleName());
                            player.sendMessage(ChatColor.RESET + "" + ChatColor.GRAY + ChatColor.ITALIC + origin.getDescription());
                            player.setLevel(player.getLevel() - 30);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + Spigorigins.mainConf.getString("Messages.OriginPickFailedCost"));
                        }
                    }
                }

            }

        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerTakeDamageEvent(EntityDamageEvent event){
        if(event.getEntityType()== EntityType.PLAYER){
            Player player = (Player) event.getEntity();
            Asigner asigner = OriginManager.getAsigner(player,true);
            assert asigner != null;
            if(Objects.equals(asigner.getOrigin(), "BLAZE")){
                if(event.getCause()== EntityDamageEvent.DamageCause.FIRE_TICK||event.getCause()== EntityDamageEvent.DamageCause.FIRE||event.getCause()== EntityDamageEvent.DamageCause.LAVA){
                    event.setCancelled(true);

                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerEatEvent(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        Asigner asigner = OriginManager.getAsigner(player,true);
        assert asigner != null;
        if(Objects.equals(asigner.getOrigin(), "FOX")){
            if(event.getItem().getType()!=Material.SWEET_BERRIES){
                event.setCancelled(true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,80,1));
            }
        }
    }
    @EventHandler
    public void WorldSaveEvent(WorldSaveEvent event) throws IOException {
        //Bukkit.broadcastMessage(ChatColor.GRAY+""+ChatColor.ITALIC+"Saving config...");
        OriginManager.getAsignerList().forEach(asigner -> Spigorigins.mainData.set(asigner.getPlayerName()+".origin",asigner.getOrigin()));
        Spigorigins.mainData.save(Spigorigins.mainDataFile);
        Spigorigins.mainConf.save(Spigorigins.mainConfFile);
    }
}
