package me.leostone;

import me.leostone.commands.CommandManager;
import me.leostone.config.DataManager;
import me.leostone.config.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class CustomHelp extends JavaPlugin {

    public DataManager data;
    public MessageManager message;

    @Override
    public void onEnable() {
        getCommand("help").setExecutor(new CommandManager(this));
        this.data = new DataManager(this);
        this.message = new MessageManager(this);
    }

    @Override
    public void onDisable() {
    }


}
