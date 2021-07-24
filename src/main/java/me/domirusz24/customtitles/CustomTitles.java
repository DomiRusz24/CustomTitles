package me.domirusz24.customtitles;

import me.domirusz24.customtitles.title.Profile;
import me.domirusz24.customtitles.title.Rarity;
import me.domirusz24.customtitles.title.Title;
import me.domirusz24.customtitles.title.command.TitlesCommand;
import me.domirusz24.customtitles.title.command.TitlesConfigCommand;
import me.domirusz24.customtitles.title.command.subcommands.*;
import me.domirusz24.customtitles.title.placeholder.TitlePlaceholder;
import me.domirusz24.plugincore.PluginCore;
import me.domirusz24.plugincore.managers.database.DataBaseTable;

import java.util.UUID;

import static me.domirusz24.customtitles.DatabaseManager.rarityTable;
import static me.domirusz24.customtitles.DatabaseManager.titleTable;

public final class CustomTitles extends PluginCore {

    @Override
    protected void _initialize() {}

    @Override
    protected void _loadDependencies() {

    }

    @Override
    protected String databasePrefix() {
        return "title_";
    }

    @Override
    public String packageName() {
        return "me.domirusz24.customtitles";
    }

    @Override
    public DataBaseTable[] getTables() {
        return DatabaseManager.getTable();
    }

    @Override
    protected void loadConfigs() {

    }

    @Override
    public void sqlLoad() {
        rarityTable.putDefault("DEFAULT");
        rarityTable.setStringField("DEFAULT", "prefix", "&7");
        rarityTable.setStringField("DEFAULT", "icon", "█");

        rarityTable.getObjects((data) -> {
            for (String name : data.keySet()) {
                String prefix = (String) data.get(name).get("prefix");
                String icon = (String) data.get(name).get("icon");
                new Rarity(name, icon, prefix);
            }
        });

        titleTable.getObjects((data) -> {
            for (String name : data.keySet()) {
                Rarity rarity = Rarity.get((String) data.get(name).get("rarity"));
                if (rarity == null) rarity = Rarity.get("DEFAULT");
                String description = (String) data.get(name).get("description");
                String display = (String) data.get(name).get("display");
                new Title(name, display, description, rarity);
            }
        });
    }

    @Override
    protected void _loadManagers() {
        new TitlePlaceholder().register();
    }

    @Override
    protected void _loadCommands() {

        commandM.registerCommand(new TitlesCommand()
                .addSubCommand(new TitleSelectCommand())
        );
        commandM.registerCommand(new TitlesConfigCommand()
                .addSubCommand(new RarityCreator())
                .addSubCommand(new TitleCreator())
                .addSubCommand(new TitleAddCommand())
                .addSubCommand(new TitleRemoveCommand())
                .addSubCommand(new TitleListCommand())
        );
    }

    @Override
    protected void _registerEvents() {

    }

    @Override
    protected void _disable() {

    }

    @Override
    protected void _shutOffPlugin() {

    }

    @Override
    public Profile registerPlayer(String s, UUID uuid) {
        return new Profile(s, uuid);
    }
}
