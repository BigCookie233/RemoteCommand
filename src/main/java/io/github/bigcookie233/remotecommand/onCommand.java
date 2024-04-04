package io.github.bigcookie233.remotecommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class onCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
