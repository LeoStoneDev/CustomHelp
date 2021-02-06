package me.leostone.commands.subcommands;

import me.leostone.CustomHelp;
import me.leostone.commands.SubCommand;
import me.leostone.config.DataManager;
import me.leostone.config.MessageManager;
import org.bukkit.command.CommandSender;

public class DeleteCommand extends SubCommand {

    private DataManager data;
    private CustomHelp plugin;
    private MessageManager message;

    public DeleteCommand(CustomHelp plugin) {
        this.plugin = plugin;
        this.data = new DataManager(plugin);
        this.message = new MessageManager(plugin);
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length > 1) {
            data.reloadConfig();
            message.reloadConfig();
            if (data.getConfig().contains("Messages." + args[1])) {
                data.getConfig().set("Messages." + args[1], null);
                data.saveConfig();
                message.saveConfig();
                sender.sendMessage(message.getMessage("Delete"));
            } else if (!data.getConfig().contains("Messages." + args[1]))
                sender.sendMessage(message.getMessage("UnknownMessage") + args[1]);
        } else
            sender.sendMessage(message.getMessage("DeleteCommandUsage"));
    }
}
