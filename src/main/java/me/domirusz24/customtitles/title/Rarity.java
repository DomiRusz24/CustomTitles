package me.domirusz24.customtitles.title;

import me.domirusz24.customtitles.DatabaseManager;
import me.domirusz24.plugincore.managers.database.DataBaseTable;
import me.domirusz24.plugincore.managers.database.DatabaseObject;
import me.domirusz24.plugincore.util.UtilMethods;

import java.util.HashMap;

public class Rarity implements DatabaseObject {

    private static HashMap<String, Rarity> RARITIES = new HashMap<>();

    private final String name;
    private final String icon;
    private final String prefix;

    public Rarity(String name, String icon, String prefix) {
        this.name = name;
        this.icon = UtilMethods.translateColor(icon);
        this.prefix = UtilMethods.translateColor(prefix);
        RARITIES.put(name, this);
    }

    public void save() {
        putDefault();
        setStringField("icon", icon);
        setStringField("prefix", prefix);
    }

    public static Rarity get(String name) {
        return RARITIES.get(name);
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public DataBaseTable getTable() {
        return DatabaseManager.rarityTable;
    }

    @Override
    public String getIndex() {
        return name;
    }

    @Override
    public int hashCode() {
        return 14 * name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Rarity) {
            return ((Rarity) obj).getName().equals(name);
        }
        return false;
    }
}
