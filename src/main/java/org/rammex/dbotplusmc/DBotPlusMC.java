package org.rammex.dbotplusmc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;
import org.rammex.dbotplusmc.commands.ReloadCommand;

import org.rammex.dbotplusmc.devent.DiscordBoostEvent;
import org.rammex.dbotplusmc.devent.DiscordMessageEvent;
import org.rammex.dbotplusmc.mcevent.MinecraftMessageEvent;


public final class DBotPlusMC extends JavaPlugin {

    private JDA jda;

    @Override
    public void onEnable() {
        startBot();
        this.getLogger().info("[DBotPlusMC] Plugin activé !");
        this.getLogger().info("[DBotPlusMC] Plugin créé par .rammex");
        saveDefaultConfig();

        this.getCommand("dbotreload").setExecutor(new ReloadCommand(this));
        this.getServer().getPluginManager().registerEvents(new MinecraftMessageEvent(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void startBot(){
        try {
            String token = getConfig().getString("discord.token");
            if (token == "") {
                this.getLogger().severe("Discord token is not set in the configuration!");
                return;
            }
            String activity = getConfig().getString("discord.activity");
            if (activity == null) {
                this.getLogger().severe("Discord activity is not set in the configuration!");
                return;
            }
            jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                    .setActivity(Activity.playing(activity))
                    .addEventListeners(new DiscordMessageEvent(this))
                    .addEventListeners(new DiscordBoostEvent(this))
                    .build();


            System.out.println("Bot en ligne !");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("ERREUR !");
        }
    }

    public JDA getJda(){
        return jda;
    }
}
