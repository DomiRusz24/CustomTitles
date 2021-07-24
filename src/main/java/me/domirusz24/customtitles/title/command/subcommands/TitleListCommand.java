package me.domirusz24.customtitles.title.command.subcommands;

import me.domirusz24.customtitles.title.Title;
import me.domirusz24.plugincore.command.abstractclasses.BaseSubCommand;
import me.domirusz24.plugincore.config.annotations.Language;
import me.domirusz24.plugincore.core.chatgui.ChatGUI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

public class TitleListCommand extends BaseSubCommand {

    @Language("Command.TitleConfig.Titelist.Description")
    public static String LANG_DESCRIPTION = "Get all titles.";

    @Language("Command.TitleConfig.Titelist.Start")
    public static String LANG_START = "&a&lAvailable titles: ";

    @Override
    protected void execute(CommandSender commandSender, List<String> list) {
        commandSender.sendMessage("§0" + ChatGUI.LANG_LINE);
        commandSender.sendMessage(LANG_START);
        if (correctLength(commandSender, list.size(), 0, 0)) {
            for (Title title : Title.getTitles()) {
                TextComponent message = new TextComponent(" - §7§l" + title.getName());
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§7Display:§r " + title.getDisplay() + "\n§r§7Description:§r " + title.getDescription())));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/titleconfig add " + commandSender.getName() + " " + title.getName()));
            }
        }
        commandSender.sendMessage("§0" + ChatGUI.LANG_LINE);
    }

    @Override
    public List<String> autoComplete(CommandSender commandSender, List<String> list) {
        return null;
    }

    @Override
    protected String name() {
        return "titlelist";
    }

    @Override
    protected String usage() {
        return "titlelist";
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
