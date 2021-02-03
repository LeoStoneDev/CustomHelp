package me.leostone.commands.subcommands;

import me.leostone.CustomHelp;
import me.leostone.commands.SubCommand;
import me.leostone.config.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class EditCommand extends SubCommand {

    private DataManager data;
    private CustomHelp plugin;

    public EditCommand(CustomHelp plugin) {
        this.plugin = plugin;
        this.data = new DataManager(plugin);
    }

    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length > 1) {
            data.reloadConfig();
            if (data.getConfig().contains("Messages." + args[1])) {
                String messages = "";
                for (int i = 2; i < args.length; i++) {
                    messages += args[i] + " ";
                }
                data.getConfig().set("Messages." + args[1], messages.trim());
                data.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "You successfully edited: " + args[1]);
            } else if (!data.getConfig().contains("Messages." + args[1])) {
                sender.sendMessage(ChatColor.RED + "There's no message named: " + args[1]);
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Invalid Arguments!");
        }

    }
}
