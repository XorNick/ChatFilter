package net.xornick.chatfilter;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatFilter extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        for (String filteredWords : getConfig().getStringList("filtered")) {
            if (message.contains(filteredWords)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You are not allowed to say this word in chat.");
            }
        }
    }
}
