package net.survivalcube.cubevanish;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishedPlayersCommand implements CommandExecutor {
    Cubevanish plugin;

    public VanishedPlayersCommand(Cubevanish plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("survivalcube.vanish")) {
            if (plugin.vanished.isEmpty()) {
                sender.sendMessage(ChatColor.GREEN+"There are currently no vanished players.");
                return true;
            }
            ArrayList<String> vpList = new ArrayList<String>();
            for (int i = 0; i < plugin.vanished.size(); i++) {
                Player curr = plugin.vanished.get(i);
                vpList.add(curr.getName());
            }
            sender.sendMessage(ChatColor.GREEN+"List of vanished players: "+vpList.toString().replace("[", "").replace("]", ""));
        }

        return true;
    }
}
