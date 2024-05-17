package org.rammex.dbotplusmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.rammex.dbotplusmc.DBotPlusMC;

import java.util.Set;

public class DBPlusCommand implements CommandExecutor {
    DBotPlusMC plugin;
    public DBPlusCommand(DBotPlusMC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0){
            commandSender.sendMessage("§c[DBotPlusMC] §7Usage: /dbplus <name> [player(optional)]");
            return true;
        } else {
            ConfigurationSection commandsSection = this.plugin.getConfig().getConfigurationSection("discord.commands");
            if (commandsSection != null) {
                Set<String> commands = commandsSection.getKeys(false);
                String commandName = strings[0];
                if (commands.contains(commandName)) {
                    String ChannelID = this.plugin.getConfig().getString("discord.commands." + strings[0] + ".channelID");
                    String Message = this.plugin.getConfig().getString("discord.commands." + strings[0] + ".message").replace("%player%",strings[1]);
                    if (ChannelID != "" && Message != "") {
                        this.plugin.getJda().getTextChannelById(ChannelID).sendMessage(Message).queue();
                        return true;
                    } else {
                        commandSender.sendMessage("§c[DBotPlusMC] §7The channel ID or the message is not defined in the configuration file.");
                    }
                } else {
                    commandSender.sendMessage("§c[DBotPlusMC] §7The command " + strings[0] + " does not exist in the configuration file.");
                }
            } else {
                commandSender.sendMessage("§c[DBotPlusMC] §7The commands section is not defined in the configuration file.");
            }
        }
        return false;
    }
}
