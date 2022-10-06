package com.rdactive.spigorigins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OriginMenuCommand implements CommandExecutor {
    Plugin plugin;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        if(args.length>0&&player.isOp()){
            Asigner asigner = OriginManager.getAsigner(player,true);
            if(Objects.equals(args[0], "asi")){
                assert asigner != null;
                player.sendMessage(asigner.getPlayerName());
            }
            if(Objects.equals(args[0], "AllAsi")){
                player.sendMessage(OriginManager.getAsignerList().toString());
            }
            if(Objects.equals(args[0], "AllOri")){
                player.sendMessage(OriginManager.getOriginList().toString());
            }
            if(Objects.equals(args[0], "Ori")){
                assert asigner != null;
                player.sendMessage(asigner.getOrigin());
            }
        }
        else {
            OriginManager.openGUI(player, plugin);
        }
        return true;


    }
    public OriginMenuCommand(Plugin plugin){
        this.plugin=plugin;
    }
}
