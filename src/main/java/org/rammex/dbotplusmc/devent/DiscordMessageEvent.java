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
            if(event.getChannel().getId().equals(this.plugin.getConfig().getString("discord.discordchat.channelid"))){
                this.plugin.getServer().broadcastMessage("§9[§cDiscord§7] §r" + event.getAuthor().getName() + "§7: §r" + event.getMessage().getContentDisplay());
            }
        }
    }
}