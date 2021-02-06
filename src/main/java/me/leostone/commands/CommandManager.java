package me.leostone.commands;

import me.leostone.CustomHelp;
import me.leostone.commands.subcommands.CreateCommand;
import me.leostone.commands.subcommands.DeleteCommand;
import me.leostone.commands.subcommands.EditCommand;
import me.leostone.config.DataManager;
import me.leostone.config.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subcommands = new ArrayList<>();
    public DataManager data;
    public MessageManager message;


    public CommandManager(CustomHelp plugin){
        this.data = new DataManager(plugin);
        this.message = new MessageManager(plugin);
        subcommands.add(new CreateCommand(plugin));
        subcommands.add(new DeleteCommand(plugin));
        subcommands.add(new EditCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("help")){
            if (args.length > 0) {
                for (int i = 0; i < getSubcommands().size(); i++){
                    if (args[0].toLowerCase().equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(sender, args);
                        return true;
                    }
                }
                data.reloadConfig();
                if (data.getConfig().contains("Messages." + args[0])) {
                    sender.sendMessage(data.getMessage("Messages." + args[0]));
                    return true;
                } else
                    sender.sendMessage(message.getMessage("UnknownMessage") + args[0]);
                sender.sendMessage("\n" +message.getMessage("Help")
                        .replaceAll(",", "\n")
                        .replace("[", "")
                        .replace("]", ""));
                return true;
            }
            sender.sendMessage("\n" +message.getMessage("Help")
                    .replaceAll(",", "\n")
                    .replace("[", "")
                    .replace("]", ""));
        }
        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

}
