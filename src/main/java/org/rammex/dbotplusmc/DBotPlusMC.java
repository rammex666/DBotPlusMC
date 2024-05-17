package org.rammex.dbotplusmc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;
import org.rammex.dbotplusmc.commands.DBPlusCommand;
import org.rammex.dbotplusmc.commands.ReloadCommand;

import org.rammex.dbotplusmc.devent.DiscordBoostEvent;
import org.rammex.dbotplusmc.devent.DiscordMessageEvent;
import org.rammex.dbotplusmc.mcevent.MinecraftMessageEvent;


public final class DBotPlusMC extends JavaPlugin {

    private JDA jda;

    @Override
    public void onEnable() {
        startBot();
        this.getLogger().info("[DBotPlusMC] Plugin on !");
        this.getLogger().info("[DBotPlusMC] Plugin by .rammex");
        saveDefaultConfig();

        this.getCommand("dbotreload").setExecutor(new ReloadCommand(this));
        this.getCommand("dbplus").setExecutor(new DBPlusCommand(this));
        this.getServer().getPluginManager().registerEvents(new MinecraftMessageEvent(this), this);
        this.jda.addEventListener(new DiscordMessageEvent(this));
        this.jda.addEventListener(new DiscordBoostEvent(this));

        int pluginId = 21957;
        Metrics metrics = new Metrics(this, pluginId);

    }

    @Override
    public void onDisable() {
        if (jda != null) {
            jda.shutdown();
        }
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
                    .build();


            System.out.println("Bot on !");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("ERREUR !");
        }
    }

    public JDA getJda(){
        return jda;
    }
}
