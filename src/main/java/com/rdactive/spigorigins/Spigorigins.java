package com.rdactive.spigorigins;

import com.rdactive.spigorigins.Origins.Void;
import com.rdactive.spigorigins.Origins.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Objects;

public final class Spigorigins extends JavaPlugin {

    public static FileConfiguration mainConf;

    public static FileConfiguration mainData;
    @Override
    public void onEnable() {
        File mainConfFile = new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Spigorigins")).getDataFolder(), "config.yml");
        mainConf = YamlConfiguration.loadConfiguration(mainConfFile);
        File mainDataFile = new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Spigorigins")).getDataFolder(), "playerData.yml");
        mainData = YamlConfiguration.loadConfiguration(mainDataFile);
        Objects.requireNonNull(this.getCommand("origin")).setExecutor(new OriginMenuCommand(this));
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            List<Asigner> asignerList = OriginManager.getAsignerList();
            asignerList.forEach(asigner -> {
                if(asigner.getOrigin()!=null){
                    Objects.requireNonNull(OriginManager.getOrigin(asigner)).applyEffects(Bukkit.getPlayer(asigner.getPlayerName()),asigner);
                }
            });
        },0,10);
        if(!mainConfFile.exists()){
            saveResource("config.yml",false);
        }
        if(!mainDataFile.exists()){
            saveResource("playerData.yml",false);
        }

        if(mainConf.getBoolean("Origins.BlazeBorn")) {
            OriginManager.registerOrigin(new BlazeBorn());
        }
        if(mainConf.getBoolean("Origins.Fox")) {
            OriginManager.registerOrigin(new Fox());
        }
        if(mainConf.getBoolean("Origins.Goblin")) {
            OriginManager.registerOrigin(new Goblin());
        }
        if(mainConf.getBoolean("Origins.Void")) {
            OriginManager.registerOrigin(new Void());
        }
        if(mainConf.getBoolean("Origins.Werewolf")) {
            OriginManager.registerOrigin(new Werewolf());
        }



    }


    @Override
    public void onDisable() {

    }
}
