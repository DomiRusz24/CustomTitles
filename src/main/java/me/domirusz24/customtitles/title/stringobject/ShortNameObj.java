package me.domirusz24.customtitles.title.stringobject;

import me.domirusz24.plugincore.core.stringobject.StringObject;

public class ShortNameObj extends StringObject<String> {
    public ShortNameObj(String name) {
        super(name);
    }

    @Override
    public Class<String> getClazz() {
        return String.class;
    }

    @Override
    public String fromString(String s) {
        if (s.contains("&") || s.contains("§") || s.length() >= 32 || s.contains(" ")) return null;
        return s;
    }
}
