package me.domirusz24.customtitles.title.command;

import me.domirusz24.plugincore.command.abstractclasses.BaseCommand;
import me.domirusz24.plugincore.config.annotations.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TitlesConfigCommand extends BaseCommand {

    @Language("Command.TitleConfig.Description")
    public static String LANG_DESCRIPTION = "Title configuration.";

    @Override
    public void selfExecute(CommandSender commandSender) {
        help(commandSender, false);
    }

    @Override
    protected List<String> aliases() {
        return Arrays.asList("titlec", "titleconf");
    }

    @Override
    protected String name() {
        return "titleconfig";
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
