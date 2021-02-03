package me.leostone.commands;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    public abstract String getName();

    public abstract void perform(CommandSender sender, String args[]);

}

