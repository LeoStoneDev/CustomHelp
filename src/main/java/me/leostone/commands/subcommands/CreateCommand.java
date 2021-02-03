package me.leostone.commands.subcommands;

import me.leostone.CustomHelp;
import me.leostone.commands.CommandManager;
import me.leostone.commands.SubCommand;
import me.leostone.config.DataManager;
import me.leostone.config.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CreateCommand extends SubCommand {

    private DataManager data;
    private CustomHelp plugin;
    private MessageManager message;

    public CreateCommand(CustomHelp plugin) {
        this.plugin = plugin;
        this.data = new DataManager(plugin);
        this.message = new MessageManager(plugin);
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length > 1) {
            if (args.length > 2) {
                data.reloadConfig();
                if (!data.getConfig().contains("Messages." + args[1])) {
                    String messages = "";
                    for (int i = 2; i < args.length; i++) {
                        messages += args[i] + " ";
                    }
                    data.getConfig().set("Messages." + args[1], messages.trim());
                    sender.sendMessage(ChatColor.GREEN + "Successful created!");
                    data.saveConfig();
                    String coloredMessage = ChatColor.translateAlternateColorCodes('&', messages);
                    sender.sendMessage(ChatColor.GREEN + "Name: " + args[1] + " Message: " + coloredMessage);
                } else if (data.getConfig().contains("Messages." + args[1])){
                    sender.sendMessage(ChatColor.RED + "You already created a message named: " + ChatColor.RESET + args[1]);
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "Correct Usage: ");
            }

        } else {
            sender.sendMessage(message.getMessage("InvalidArguments"));
        }
    }
}
