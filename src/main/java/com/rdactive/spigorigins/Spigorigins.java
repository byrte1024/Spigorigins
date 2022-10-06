package com.rdactive.spigorigins;

import com.rdactive.spigorigins.Origins.Void;
import com.rdactive.spigorigins.Origins.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Objects;

public final class Spigorigins extends JavaPlugin {

    public static File mainConfFile;

    public static File mainDataFile;

    public static FileConfiguration mainConf;

    public static FileConfiguration mainData;
    @Override
    public void onEnable() {

        Objects.requireNonNull(this.getCommand("origin")).setExecutor(new OriginMenuCommand(this));
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            List<Asigner> asignerList = OriginManager.getAsignerList();
            asignerList.forEach(asigner -> {
                if(asigner.getOrigin()!=null){
                    Player player = Bukkit.getPlayer(asigner.getPlayerName());
                    assert player != null;
                    assert player.isOnline();
                    assert !player.isDead();
                    Origin origin = OriginManager.getOrigin(asigner);
                    assert origin !=null;
                    origin.applyEffects(player,asigner);
                }
            });
        },0,10);

    }

    @Override
    public void onLoad() {
        mainConfFile = new File(getDataFolder(), "config.yml");
        mainConf = YamlConfiguration.loadConfiguration(mainConfFile);
        mainDataFile = new File(getDataFolder(), "playerData.yml");
        mainData = YamlConfiguration.loadConfiguration(mainDataFile);
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
