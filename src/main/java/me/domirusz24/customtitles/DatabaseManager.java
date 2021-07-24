package me.domirusz24.customtitles;

import me.domirusz24.plugincore.managers.database.DataBaseTable;
import me.domirusz24.plugincore.managers.database.values.DataBaseValue;
import me.domirusz24.plugincore.managers.database.values.StringValue;
import me.domirusz24.plugincore.managers.database.values.TextValue;

import java.util.ArrayList;

public class DatabaseManager {

    public static DataBaseTable playerTable;
    public static DataBaseTable titleTable;
    public static DataBaseTable rarityTable;

    public static DataBaseTable[] getTable() {

        playerTable = new DataBaseTable() {
            @Override
            public String getName() {
                return "profile";
            }

            @Override
            public DataBaseValue<?>[] getValues() {
                return new DataBaseValue[] {
                        new StringValue("name", 32),
                        new StringValue("selected", 32),
                        new TextValue("titles"),
                };
            }

            @Override
            public String getIndex() {
                return "uuid";
            }

            @Override
            public int getIndexSize() {
                return 36;
            }
        };

        titleTable = new DataBaseTable() {
            @Override
            public String getName() {
                return "titles";
            }

            @Override
            public DataBaseValue<?>[] getValues() {
                return new DataBaseValue[]{
                        new StringValue("display", 128),
                        new StringValue("description", 128),
                        new StringValue("rarity", 32),
                };
            }

            @Override
            public String getIndex() {
                return "name";
            }

            @Override
            public int getIndexSize() {
                return 32;
            }
        };
        rarityTable = new DataBaseTable() {
            @Override
            public String getName() {
                return "rarities";
            }

            @Override
            public DataBaseValue<?>[] getValues() {
                return new DataBaseValue[]{
                        new StringValue("prefix", 12),
                        new StringValue("icon", 4)
                };
            }

            @Override
            public String getIndex() {
                return "name";
            }

            @Override
            public int getIndexSize() {
                return 32;
            }
        };


        return new DataBaseTable[]{
                rarityTable,
                titleTable,
                playerTable
        };
    }
}
