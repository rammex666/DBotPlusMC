package org.rammex.dbotplusmc.devent;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.rammex.dbotplusmc.DBotPlusMC;


public class DiscordMessageEvent extends ListenerAdapter {
    DBotPlusMC plugin;
    public DiscordMessageEvent(DBotPlusMC plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()){
            return;
        }
        this.plugin.getLogger().info("[DBotPlusMC] Message received on Discord");
        this.plugin.getServer().broadcastMessage("Test message");
        if (this.plugin.getConfig().getBoolean("discord.discordchat.active")){
            String receivedChannelId = event.getChannel().getId();
            String configuredChannelId = this.plugin.getConfig().getString("discord.discordchat.channelid");
            this.plugin.getLogger().info("[DBotPlusMC] Received channel ID: " + receivedChannelId);
            this.plugin.getLogger().info("[DBotPlusMC] Configured channel ID: " + configuredChannelId);
            if(receivedChannelId.equals(configuredChannelId)){
                this.plugin.getServer().broadcastMessage("§9[§cDiscord§7] §r" + event.getAuthor().getName() + "§7: §r" + event.getMessage().getContentDisplay());
            } else {
                this.plugin.getLogger().info("[DBotPlusMC] Message received in a different channel");
            }
        } else {
            this.plugin.getLogger().info("[DBotPlusMC] Discord chat is not active");
        }
    }
}