package me.leostone.commands.subcommands;

import me.leostone.CustomHelp;
import me.leostone.commands.SubCommand;
import me.leostone.config.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidPluginException;

public class ReloadCommand extends SubCommand {

    private CustomHelp plugin;
    private MessageManager message;

    public ReloadCommand(CustomHelp plugin) {
      this.plugin = plugin;
      this.message = new MessageManager(plugin);
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        plugin.getPluginLoader().disablePlugin(plugin);
        plugin.getPluginLoader().enablePlugin(plugin);
        sender.sendMessage(ChatColor.GREEN + "Successfully reloaded!");
    }
}
