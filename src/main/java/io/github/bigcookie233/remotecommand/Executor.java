package io.github.bigcookie233.remotecommand;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Executor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
        if (strings.length < 1) {
            return false;
        }
        String apiUrl = RemoteCommand.getPlugin(RemoteCommand.class).config.getString("apiUrl");
        try {
            JsonObject params = new JsonObject();
            if (commandSender instanceof Player player) {
                params.addProperty("name", player.getName());
                params.addProperty("uuid", player.getUniqueId().toString());
            }
            params.addProperty("command", strings[0]);
            Gson gson = new Gson();
            JsonArray jsonArray = gson.toJsonTree(Arrays.copyOfRange(strings, 1, strings.length)).getAsJsonArray();
            params.add("params", jsonArray);
            HttpRequestUtils httpRequestUtils = new HttpRequestUtils();
            JsonObject response = httpRequestUtils.doPost(apiUrl, params);
            commandSender.sendMessage(response.get("message").getAsString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
