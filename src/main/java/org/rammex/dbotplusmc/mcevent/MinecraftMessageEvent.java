package org.rammex.dbotplusmc.mcevent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.rammex.dbotplusmc.DBotPlusMC;

public class MinecraftMessageEvent implements Listener {
    DBotPlusMC plugin;
    public MinecraftMessageEvent(DBotPlusMC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMessageReceived(org.bukkit.event.player.AsyncPlayerChatEvent event) {
        if (this.plugin.getConfig().getBoolean("discord.discordchat.active")){
            this.plugin.getJda().getTextChannelById(this.plugin.getConfig().getString("discord.discordchat.channelid"))
                    .sendMessage("[Minecraft] " + event.getPlayer().getName() + " " + event.getMessage()).queue();
        }
    }
}
