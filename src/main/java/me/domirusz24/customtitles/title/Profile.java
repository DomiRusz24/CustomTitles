package me.domirusz24.customtitles.title;

import me.domirusz24.customtitles.DatabaseManager;
import me.domirusz24.plugincore.attributes.PlayerAttribute;
import me.domirusz24.plugincore.core.players.PlayerData;
import me.domirusz24.plugincore.managers.database.DataBaseTable;
import me.domirusz24.plugincore.util.UtilMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Profile extends PlayerData {

    private final List<Title> TITLES = new ArrayList<>();

    private Title selected = null;

    public Profile(String name, UUID uuid) {
        super(name, uuid);
    }

    public boolean setSelected(Title title) {
        if (TITLES.contains(title) || title == null) {
            selected = title;
            getAttribute(PlayerAttribute.SQL).setStringValue("selected", title == null ? "NONE" : title.getName());
            return true;
        }
        return false;
    }

    public boolean addTitle(Title title) {
        if (TITLES.contains(title)) return false;
        TITLES.add(title);
        getAttribute(PlayerAttribute.SQL).setStringValue("titles", DataBaseTable.listToString(TITLES.stream().map(Title::getName).collect(Collectors.toList())));
        return true;
    }

    public boolean removeTitle(Title title) {
        if (!TITLES.contains(title)) return false;
        TITLES.remove(title);
        if (TITLES.isEmpty()) {
            getAttribute(PlayerAttribute.SQL).setStringValue("titles", "NONE");
        } else {
            getAttribute(PlayerAttribute.SQL).setStringValue("titles", DataBaseTable.listToString(TITLES.stream().map(Title::getName).collect(Collectors.toList())));
        }
        return true;
    }

    public Title getSelected() {
        return selected;
    }

    public List<Title> getTitles() {
        return new ArrayList<>(TITLES);
    }

    @Override
    protected void onSqlLoad() {
        String s = getAttribute(PlayerAttribute.SQL).getStringValue("selected");
        if ("NONE".equals(s)) {
            selected = null;
        } else {
            selected = Title.get(s);
        }
        for (String title : DataBaseTable.stringToList(getAttribute(PlayerAttribute.SQL).getStringValue("titles"))) {
            if (title.equals("NONE")) continue;
            Title t = Title.get(title);
            if (t != null) TITLES.add(t);
        }
    }

    @Override
    protected void onPlayerJoin() {

    }

    @Override
    public DataBaseTable getTable() {
        return DatabaseManager.playerTable;
    }
}
