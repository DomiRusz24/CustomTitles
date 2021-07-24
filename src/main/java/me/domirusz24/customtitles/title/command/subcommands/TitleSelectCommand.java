package me.domirusz24.customtitles.title.command.subcommands;

import me.domirusz24.customtitles.CustomTitles;
import me.domirusz24.customtitles.title.Profile;
import me.domirusz24.customtitles.title.Title;
import me.domirusz24.plugincore.command.abstractclasses.BaseSubCommand;
import me.domirusz24.plugincore.config.annotations.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TitleSelectCommand extends BaseSubCommand {

    @Language("Command.Title.Select.Description")
    public static String LANG_DESCRIPTION = "Select title.";

    @Language("Command.Title.Select.YouDontHave")
    public static String LANG_FAIL = "&cYou don't have this title unlocked!";

    @Language("Command.Title.DoesntExist")
    public static String LANG_NOT_EXIST = "&cThis title doesn't exist!";

    @Override
    protected void execute(CommandSender commandSender, List<String> list) {
        if (isPlayer(commandSender) && correctLength(commandSender, list.size(), 1, 1) && hasPermission(commandSender)) {
            Title title = Title.get(list.get(0));
            if (title == null) {
                commandSender.sendMessage(LANG_NOT_EXIST);
                return;
            }
            Profile profile = (Profile) CustomTitles.playerDataM.getPlayer((Player) commandSender);
            if (profile.setSelected(title)) {}
        }
    }

    @Override
    public List<String> autoComplete(CommandSender commandSender, List<String> list) {
        if (list.size() == 1 && commandSender instanceof Player) {
            Profile profile = (Profile) CustomTitles.playerDataM.getPlayer((Player) commandSender);
            if (profile == null) return new ArrayList<>();
            return profile.getTitles().stream().map(Title::getName).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    protected String name() {
        return "select";
    }

    @Override
    protected String usage() {
        return "select <TITLE>";
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
