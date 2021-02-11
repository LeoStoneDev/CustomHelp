package me.leostone.commands.subcommands;

import me.leostone.CustomHelp;
import me.leostone.commands.SubCommand;
import me.leostone.config.DataManager;
import me.leostone.config.MessageManager;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ListCommand extends SubCommand {

    private DataManager data;
    private MessageManager message;

    public ListCommand(CustomHelp plugin) {
        this.data = new DataManager(plugin);
        this.message = new MessageManager(plugin);
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        StringBuilder namesBuilder = new StringBuilder();
        for(String string : Objects.requireNonNull(data.getConfig().getConfigurationSection("Messages")).getKeys(false)){
            namesBuilder.append(string).append("\n");
        }
        sender.sendMessage("\n" + namesBuilder.toString());
    }
}
