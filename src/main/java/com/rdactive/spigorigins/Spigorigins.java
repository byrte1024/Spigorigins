package com.rdactive.spigorigins;

import com.rdactive.spigorigins.Origins.*;
import com.rdactive.spigorigins.Origins.Void;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public final class Spigorigins extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("origin")).setExecutor(new OriginMenuCommand(this));
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this,new Runnable() {
            @Override
            public void run() {
                List<Asigner> asignerList = OriginManager.getAsignerList();
                asignerList.forEach(asigner -> {
                    if(asigner.getOrigin()!=null){
                        OriginManager.getOrigin(asigner).applyEffects(Bukkit.getPlayer(asigner.getPlayerName()),asigner);
                    }
                });
            }
        },0,10);
        OriginManager.registerOrigin(new BlazeBorn());
        OriginManager.registerOrigin(new Fox());
        OriginManager.registerOrigin(new Goblin());
        OriginManager.registerOrigin(new Void());
        OriginManager.registerOrigin(new Werewolf());
    }

    @Override
    public void onDisable() {

    }
}
