package io.github.bigcookie233.remotecommand;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RemoteCommand extends JavaPlugin {
    public FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.config = this.getConfig();
        this.getCommand("remotecommand").setExecutor(new Executor());
    }
}
