package net.survivalcube.cubevanish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class JoinEvent implements Listener {

    Cubevanish plugin;
    public JoinEvent(Cubevanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if (Objects.equals(plugin.getConfig().get("players." + e.getPlayer().getName() + ".joinvanished"), true)) {
            if (!player.hasPermission("survivalcube.vanish")) return;
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                Bukkit.getServer().dispatchCommand(e.getPlayer(), "vanish");
                Bukkit.broadcast(ChatColor.GREEN+e.getPlayer().getName()+" joined vanished.", "survivalcube.vanish");
            }
        }
        for (int i = 0; i < plugin.vanished.size(); i++){
            if (player.hasPermission("survivalcube.vanish")) return;
            player.hidePlayer(plugin, plugin.vanished.get(i));
        }
    }


}
