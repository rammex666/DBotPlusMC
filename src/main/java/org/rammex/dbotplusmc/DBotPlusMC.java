package org.rammex.dbotplusmc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;
import org.rammex.dbotplusmc.commands.ReloadCommand;


public final class DBotPlusMC extends JavaPlugin {

    @Override
    public void onEnable() {
        startBot();
        this.getLogger().info("[DBotPlusMC] Plugin activé + Bot en ligne !");
        saveDefaultConfig();

        this.getCommand("dbotreload").setExecutor(new ReloadCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void startBot(){
        try {
            String token = getConfig().getString("discord.token");
            if (token == null) {
                this.getLogger().severe("Discord token is not set in the configuration!");
                return;
            }
            String activity = getConfig().getString("discord.activity");
            if (activity == null) {
                this.getLogger().severe("Discord activity is not set in the configuration!");
                return;
            }
            JDA jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                    .setActivity(Activity.playing(activity))
                    .build();


            System.out.println("Bot en ligne !");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("ERREUR !");
        }
    }
}
