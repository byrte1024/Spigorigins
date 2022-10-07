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

        //Sets up command executor:
        Objects.requireNonNull(this.getCommand("origin")).setExecutor(new OriginMenuCommand(this));

        //Set up event listener:
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        //Schedule origin ticks.
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            List<Asigner> asignerList = OriginManager.getAsignerList();
            asignerList.forEach(asigner -> {
                if(asigner.getOrigin()!=null){
                    //Get the player object from the asigner
                    Player player = Bukkit.getPlayer(asigner.getPlayerName());

                    //Make sure the player object exists
                    assert player != null;

                    //Check if the player is online. if he's not online no need to tick origin.
                    assert player.isOnline();

                    //Dead players. are not alive. dont treat them as such
                    assert !player.isDead();

                    //Grab origin from asigner using the originManager. because u don't want to do it yourself
                    Origin origin = OriginManager.getOrigin(asigner);

                    //Make sure the origin actually exists. if it dos-nt, u messed it up
                    assert origin !=null;

                    //Tick and apply effects
                    origin.applyEffects(player,asigner);
                }
            });
        },0,10);

    }

    @Override
    public void onLoad() {
        //Load config file
        mainConfFile = new File(getDataFolder(), "config.yml");

        //Load YMAL from config file
        mainConf = YamlConfiguration.loadConfiguration(mainConfFile);

        //Load playerData file
        mainDataFile = new File(getDataFolder(), "playerData.yml");

        //Load YMAL from playerData file
        mainData = YamlConfiguration.loadConfiguration(mainDataFile);
        if(!mainConfFile.exists()){
            //Checks if file exists. if not. write it.
            saveResource("config.yml",false);
        }
        if(!mainDataFile.exists()){
            //Checks if file exists. if not. write it.
            saveResource("playerData.yml",false);

        }

        //Make checks for enabled origins:
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
