package me.leostone.config;

import me.leostone.CustomHelp;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class MessageManager {

    private CustomHelp plugin;
    private FileConfiguration messageConfig = null;
    private File configFile = null;

    public MessageManager(CustomHelp plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "message.yml");
        }

        this.messageConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("message.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.messageConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.messageConfig == null)
            reloadConfig();
        return this.messageConfig;
    }

    public void saveConfig() {
        if (this.messageConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Couldn't save config to " + this.configFile, e);
        }
    }
    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(path)) ;
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "message.yml");

        if (!this.configFile.exists()) {
            this.plugin.saveResource("message.yml", false);
        }
    }
}
