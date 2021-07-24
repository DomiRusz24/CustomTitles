package me.domirusz24.customtitles.title.stringobject;

import me.domirusz24.customtitles.title.Rarity;
import me.domirusz24.plugincore.core.stringobject.StringObject;

public class RarityObj extends StringObject<Rarity> {
    public RarityObj(String name) {
        super(name);
    }

    @Override
    public Class<Rarity> getClazz() {
        return Rarity.class;
    }

    @Override
    public Rarity fromString(String s) {
        return Rarity.get(s);
    }
}
