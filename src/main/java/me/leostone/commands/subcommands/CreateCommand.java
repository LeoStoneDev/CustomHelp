package me.leostone.commands.subcommands;

import me.leostone.CustomHelp;
import me.leostone.commands.SubCommand;
import me.leostone.config.DataManager;
import me.leostone.config.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CreateCommand extends SubCommand {

    private final DataManager data;
    private final MessageManager message;

    public CreateCommand(CustomHelp plugin) {
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
            data.reloadConfig();
            message.reloadConfig();
            if (!data.getConfig().contains("Messages." + args[1])) {
                StringBuilder messagesBuilder = new StringBuilder();
                for (int i = 2; i < args.length; i++)
                    messagesBuilder.append(args[i]).append(" ");
                String messages = messagesBuilder.toString().trim();
                data.getConfig().set("Messages." + args[1], messages);
                data.saveConfig();
                message.saveConfig();
                sender.sendMessage(message.getMessage("Create"));
                sender.sendMessage(message.getMessage("Name") +
                        ChatColor.translateAlternateColorCodes('&', args[1]));
                sender.sendMessage(message.getMessage("Message") +
                        ChatColor.translateAlternateColorCodes('&', messages));
            } else if (data.getConfig().contains("Messages." + args[1]))
                sender.sendMessage(message.getMessage("MessageAlreadyCreated"));
        } else
            sender.sendMessage(message.getMessage("CreateCommandUsage"));
    }
}
