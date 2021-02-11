package me.leostone.commands.subcommands;

import me.leostone.CustomHelp;
import me.leostone.commands.SubCommand;
import me.leostone.config.DataManager;
import me.leostone.config.MessageManager;
import org.bukkit.command.CommandSender;

public class HelpCommand extends SubCommand {

    private final DataManager data;
    private final MessageManager message;

    public HelpCommand(CustomHelp plugin) {
        this.data = new DataManager(plugin);
        this.message = new MessageManager(plugin);
    }

    @Override
    public String getName() {
        return "?";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        data.reloadConfig();
        message.reloadConfig();
        sender.sendMessage("\n" +message.getMessage("Help")
                .replaceAll(",", "\n")
                .replace("[", "")
                .replace("]", ""));
    }
}
