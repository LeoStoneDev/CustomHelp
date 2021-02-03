package me.leostone.commands;

import me.leostone.CustomHelp;
import me.leostone.commands.subcommands.CreateCommand;
import me.leostone.commands.subcommands.DeleteCommand;
import me.leostone.commands.subcommands.EditCommand;
import me.leostone.commands.subcommands.ReloadCommand;
import me.leostone.config.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subcommands = new ArrayList<>();
    public DataManager data;
    private CustomHelp plugin;


    public CommandManager(CustomHelp plugin){
        this.plugin = plugin;
        this.data = new DataManager(plugin);
        subcommands.add(new CreateCommand(plugin));
        subcommands.add(new DeleteCommand(plugin));
        subcommands.add(new EditCommand(plugin));
        subcommands.add(new ReloadCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0){
            for (int i = 0; i < getSubcommands().size(); i++){
                if (args[0].toLowerCase().equalsIgnoreCase(getSubcommands().get(i).getName())){
                    getSubcommands().get(i).perform(sender, args);
                }
            }
        }
        return true;
    }
    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

}
