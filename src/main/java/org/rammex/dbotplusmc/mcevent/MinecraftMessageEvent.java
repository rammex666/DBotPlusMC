package org.rammex.dbotplusmc.mcevent;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
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
        try {
            if (this.plugin.getConfig().getBoolean("discord.minecraftchat.active")){
                String channelId = this.plugin.getConfig().getString("discord.minecraftchat.channelID");
                String guildId = this.plugin.getConfig().getString("discord.guildid");
                if (guildId != null && channelId != null) {
                    Guild guild = this.plugin.getJda().getGuildById(guildId);
                    if (guild != null) {
                        TextChannel channel = guild.getTextChannelById(channelId);
                        if (channel != null) {
                            channel.sendMessage("[Minecraft] " + event.getPlayer().getName() + " -> " + event.getMessage()).queue();
                        } else {
                            System.out.println("Le salon avec l'ID " + channelId + " n'existe pas ou n'est pas accessible.");
                        }
                    } else {
                        System.out.println("La guilde avec l'ID " + guildId + " n'existe pas ou n'est pas accessible.");
                    }
                } else {
                    System.out.println("L'ID de la guilde ou du salon n'est pas défini dans le fichier de configuration.");
                }
            }
        } catch (Exception e) {
            // Vous pouvez choisir de ne rien faire ici, ce qui empêchera l'erreur d'être affichée dans la console
        }
    }
}