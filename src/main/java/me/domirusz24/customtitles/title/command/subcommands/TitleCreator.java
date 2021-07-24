package me.domirusz24.customtitles.title.command.subcommands;

import me.domirusz24.customtitles.title.Rarity;
import me.domirusz24.customtitles.title.Title;
import me.domirusz24.customtitles.title.stringobject.RarityObj;
import me.domirusz24.customtitles.title.stringobject.ShortNameObj;
import me.domirusz24.plugincore.command.abstractclasses.BaseSubCommand;
import me.domirusz24.plugincore.config.annotations.Language;
import me.domirusz24.plugincore.core.stringobject.InputForm;
import me.domirusz24.plugincore.core.stringobject.StringObj;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

public class TitleCreator extends BaseSubCommand {

    private static final InputForm.Builder TITLE_CREATE = InputForm.newBuilder()
            .setName("Title")
            .setArgs(new ShortNameObj("Name"), new StringObj("Display"), new StringObj("Description"), new RarityObj("Rarity"))
            .setOnComplete((data) -> {
               new Title((String) data[0], (String) data[1], (String) data[2], (Rarity) data[3]).save();
            });

    @Language("Command.TitleConfig.TitleCreator.Description")
    public static String LANG_DESCRIPTION = "Title creator.";

    @Override
    protected void execute(CommandSender commandSender, List<String> list) {
        if (isPlayer(commandSender) && hasPermission(commandSender) && correctLength(commandSender, list.size(), 0, 0)) {
            TITLE_CREATE.create((Player) commandSender);
        }
    }

    @Override
    public List<String> autoComplete(CommandSender commandSender, List<String> list) {
        return null;
    }

    @Override
    protected String name() {
        return "titlecreator";
    }

    @Override
    protected String usage() {
        return "titlecreator";
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
