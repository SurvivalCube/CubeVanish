package net.survivalcube.cubevanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.Bukkit.getLogger;

public class LeaveEvent implements Listener {
    Cubevanish plugin;
    public LeaveEvent(Cubevanish plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void PlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (plugin.t.getEntries().contains(p.getName())) {
            plugin.t.removeEntry(p.getName());
        }
        if (plugin.vanished.contains(p)){
            plugin.vanished.remove(p);
        }
    }


}
