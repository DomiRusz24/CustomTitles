package me.domirusz24.customtitles.title.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.domirusz24.customtitles.CustomTitles;
import me.domirusz24.customtitles.title.Profile;
import me.domirusz24.customtitles.title.Title;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TitlePlaceholder extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "titles";
    }

    @Override
    public @NotNull String getAuthor() {
        return CustomTitles.plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return CustomTitles.plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equals("selected")) {
            Profile profile = (Profile) CustomTitles.playerDataM.getPlayer(player);
            if (profile == null) {
                return "";
            }
            if (profile.getSelected() == null) {
                return "";
            }
            Title title = profile.getSelected();
            return title.getRarity().getPrefix() + title.getDisplay() + " ";
        }
        return null;
    }
}
