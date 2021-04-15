package net.survivalcube.cubevanish;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class VanishCommand implements CommandExecutor {

    Cubevanish plugin;

    public VanishCommand(Cubevanish plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender.hasPermission("survivalcube.vanish")) {
                if (plugin.vanished.contains(p)) {
                    for (Player people : Bukkit.getOnlinePlayers()) {
                        people.showPlayer(plugin, p);
                    }
                    p.sendMessage(ChatColor.GREEN+"You are now invisible to other players");
                    plugin.vanished.add(p);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN+"You are vanished!"));
                } else if (!plugin.vanished.contains(p)) {
                    for (Player people : Bukkit.getOnlinePlayers()) {
                        people.hidePlayer(plugin, p);
                    }
                    p.sendMessage(ChatColor.GREEN+"You are now visible to other players");
                    plugin.vanished.remove(p);
                }
            }
        } else sender.sendMessage("Only players can vanish!");

        return true;
    }
}
