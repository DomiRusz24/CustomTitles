package me.domirusz24.customtitles.title;

import me.domirusz24.customtitles.DatabaseManager;
import me.domirusz24.plugincore.managers.database.DataBaseTable;
import me.domirusz24.plugincore.managers.database.DatabaseObject;
import me.domirusz24.plugincore.util.UtilMethods;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Title implements DatabaseObject {

    private static final HashMap<String, Title> TITLES = new HashMap<>();

    public static Collection<Title> getTitles() {
        return TITLES.values();
    }

    private final String name;
    private final String display;
    private final String description;

    private final Rarity rarity;

    public Title(String name, String display, String description, Rarity rarity) {
        this.name = name;
        this.display = UtilMethods.translateColor(display);
        this.description = UtilMethods.translateColor(description);
        this.rarity = rarity;
        TITLES.put(name, this);
    }

    public void save() {
        putDefault();
        setStringField("display", display);
        setStringField("description", description);
        setStringField("rarity", rarity.getName());
    }

    public static Title get(String name) {
        return TITLES.get(name);
    }

    public String getName() {
        return name;
    }

    public String getDisplay() {
        return display;
    }

    public String getDescription() {
        return description;
    }

    public static HashMap<String, Title> getTITLES() {
        return TITLES;
    }

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public DataBaseTable getTable() {
        return DatabaseManager.titleTable;
    }

    @Override
    public String getIndex() {
        return name;
    }

    @Override
    public int hashCode() {
        return 13 * name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Title) {
            return ((Title) obj).name.equals(name);
        }
        return false;
    }
}
