package org.rammex.dbotplusmc.devent;

import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.rammex.dbotplusmc.DBotPlusMC;

public class DiscordBoostEvent extends ListenerAdapter {
    DBotPlusMC plugin;
    public DiscordBoostEvent(DBotPlusMC plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event) {
        if(this.plugin.getConfig().getBoolean("discord.boostmodule.active")){
            String receivedGuildId = event.getGuild().getId();
                String configuredChannelId = this.plugin.getConfig().getString("discord.guildid");
                if(receivedGuildId.equals(configuredChannelId)){
                    String command = this.plugin.getConfig().getString("discord.boostmodule.command");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
        }
    }
}