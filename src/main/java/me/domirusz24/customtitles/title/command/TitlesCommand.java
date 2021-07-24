package me.domirusz24.customtitles.title.command;

import me.domirusz24.customtitles.CustomTitles;
import me.domirusz24.customtitles.title.Profile;
import me.domirusz24.customtitles.title.panel.SelectTitlePanel;
import me.domirusz24.plugincore.command.abstractclasses.BaseCommand;
import me.domirusz24.plugincore.config.annotations.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class TitlesCommand extends BaseCommand {

    @Language("Command.Title.Description")
    public static String LANG_DESCRIPTION = "Title managing.";

    @Override
    public void selfExecute(CommandSender commandSender) {
        if (isPlayer(commandSender) && hasPermission(commandSender, "view")) {
            Player player = (Player) commandSender;
            new SelectTitlePanel(player);
        }
    }

    @Override
    protected String name() {
        return "titles";
    }

    @Override
    protected String description() {
        return LANG_DESCRIPTION;
    }

    @Override
    public PermissionDefault getPermissionDefault() {
        return PermissionDefault.TRUE;
    }
}
