package me.domirusz24.customtitles.title.command.subcommands;

import me.domirusz24.customtitles.CustomTitles;
import me.domirusz24.customtitles.title.Profile;
import me.domirusz24.customtitles.title.Title;
import me.domirusz24.plugincore.command.Languages;
import me.domirusz24.plugincore.command.abstractclasses.BaseSubCommand;
import me.domirusz24.plugincore.config.annotations.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TitleRemoveCommand extends BaseSubCommand {

    @Language("Command.TitleConfig.TitleRemove.Description")
    public static String LANG_DESCRIPTION = "Remove title.";

    @Override
    protected void execute(CommandSender commandSender, List<String> list) {
        if (hasPermission(commandSender) && correctLength(commandSender, list.size(), 2, 2)) {
            Player player = Bukkit.getPlayer(list.get(0));
            if (player == null) {
                commandSender.sendMessage(Languages.PLAYER_NOT_ONLINE.replaceAll("%player%", list.get(0)));
                return;
            }
            Title title = Title.get(list.get(1).replaceAll("_", " "));
            if (title == null) {
                commandSender.sendMessage(Languages.INCORRECT_SELECTION);
                return;
            }
            Profile profile = (Profile) CustomTitles.playerDataM.getPlayer(player.getName(), player.getUniqueId());
            profile.removeTitle(title);
            player.sendMessage(Languages.SUCCESS);
        }
    }

    @Override
    public List<String> autoComplete(CommandSender commandSender, List<String> list) {
        if (list.size() == 1) {
            return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
        } else if (list.size() == 2) {
            return Title.getTitles().stream().map(Title::getName).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    protected String name() {
        return "remove";
    }

    @Override
    protected String usage() {
        return "remove <PLAYER> <TITLE>";
    }

    @Override
    protected String description() {
        return LANG_DESCRIPTION;
    }

    @Override
    public PermissionDefault getPermissionDefault() {
        return null;
    }
}
