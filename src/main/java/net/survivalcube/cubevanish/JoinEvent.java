package net.survivalcube.cubevanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class JoinEvent implements Listener {

    Cubevanish plugin;
    public JoinEvent(Cubevanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        for (int i = 0; i < plugin.vanished.size(); i++){
            if (player.hasPermission("survivalcube.vanish")) return;
            player.hidePlayer(plugin, plugin.vanished.get(i));
        }
    }


}
