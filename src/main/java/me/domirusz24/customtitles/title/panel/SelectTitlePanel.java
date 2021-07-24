package me.domirusz24.customtitles.title.panel;

import me.domirusz24.customtitles.CustomTitles;
import me.domirusz24.customtitles.title.Profile;
import me.domirusz24.customtitles.title.Title;
import me.domirusz24.plugincore.config.annotations.Language;
import me.domirusz24.plugincore.core.chatgui.ChatGUI;
import me.domirusz24.plugincore.core.gui.CustomGUI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SelectTitlePanel extends ChatGUI {

    //█  ✖↑ ↓ → ← ♦ ★

    private final Profile profile;

    public SelectTitlePanel(Player player) {
        super(player);
        profile = (Profile) CustomTitles.playerDataM.getPlayer(player.getName(), player.getUniqueId());
        update();
    }

    @Language("Title.Panel.Top")
    public static String LANG_TOP = "&aYour titles:";

    @Language("Title.Panel.None")
    public static String LANG_NONE = "&a&lYou don't have any titles yet!";

    @Override
    protected void _update() {
        reset();
        line("&a");
        newLine();
        addMessage(LANG_TOP);
        tab(1);
        putX();
        newLine(2);
        if (profile.getTitles().isEmpty()) {
            addMessage(LANG_NONE);
            newLine();
        } else {
            for (Title title : profile.getTitles()) {
                if (Objects.equals(profile.getSelected(), title)) {
                    addHoverAndClickMessage(title.getRarity().getPrefix() + title.getRarity().getIcon() + "§r§e§l → §r§e" + title.getDisplay() + "§r§e§l ←", title.getRarity().getPrefix() + title.getDescription(), (p) -> {
                        profile.setSelected(null);
                        update();
                    });
                } else {
                    addHoverAndClickMessage(title.getRarity().getPrefix() + title.getRarity().getIcon() + "§r§7 " + title.getDisplay(), title.getRarity().getPrefix() + title.getDescription(), (p) -> {
                        profile.setSelected(title);
                        update();
                    });
                }
                newLine();
            }
        }
        newLine();
        line("&a");
    }

    @Override
    public boolean resetOnMove() {
        return true;
    }
}
