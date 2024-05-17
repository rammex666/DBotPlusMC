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
        if (this.plugin.getConfig().getBoolean("discord.discordchat.active")){
            String receivedChannelId = event.getChannel().getId();
            String configuredChannelId = this.plugin.getConfig().getString("discord.discordchat.channelID");
            if(receivedChannelId.equals(configuredChannelId)){
                this.plugin.getServer().broadcastMessage("§1§l[§9Discord§1§l] §r" + event.getAuthor().getName() + "§7 -> §r" + event.getMessage().getContentDisplay());
            }
        }
    }
}