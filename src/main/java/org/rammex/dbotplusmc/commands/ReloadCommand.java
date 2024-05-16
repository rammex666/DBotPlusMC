package org.rammex.dbotplusmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.rammex.dbotplusmc.DBotPlusMC;

public class ReloadCommand implements CommandExecutor {

    DBotPlusMC plugin;
    public ReloadCommand(DBotPlusMC plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("dbotplusmc.reload")) {
            plugin.reloadConfig();
            this.plugin.startBot();
            commandSender.sendMessage("§a[DBotPlusMC] Config + Bot reloaded!");
            return true;
        }
        return false;
    }
}
