package net.survivalcube.cubevanish;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class JoinVanishedCommand implements CommandExecutor {
    Cubevanish plugin;

    public JoinVanishedCommand(Cubevanish plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("survivalcube.vanish")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"You are not a player!");
            return true;
        }
        Player p = (Player) sender;
        if (Objects.equals(plugin.getConfig().get("players." + p.getName() + ".joinvanished"), true)) {
            plugin.getConfig().set("players."+p.getName()+".joinvanished", false);
            p.sendMessage(ChatColor.GREEN+"You will not join vanished");
            plugin.saveConfig();
        } else {
            plugin.getConfig().set("players."+p.getName()+".joinvanished", true);
            p.sendMessage(ChatColor.GREEN+"You will now join vanished");
            plugin.saveConfig();
        }

        return true;
    }
}
