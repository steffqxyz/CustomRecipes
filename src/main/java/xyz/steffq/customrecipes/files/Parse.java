package xyz.steffq.customrecipes.files;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Parse {

    public static List<String> parsePlaceholders(@NotNull final List<String> input, final Player player) {
        if (player == null) return input;
        final List<String> parsed = new ArrayList<>();
        input.forEach(string ->
                parsed.add(PlaceholderAPI.setPlaceholders(player, string))
        );
        return parsed;
    }
    public static List<String> parseColors(@NotNull final List<String> input) {
        final List<String> parsed = new ArrayList<>();
        input.forEach(string ->
                parsed.add(ChatColor.translateAlternateColorCodes('&', string))
        );
        return parsed;
    }

    public static String parseColor(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

}
