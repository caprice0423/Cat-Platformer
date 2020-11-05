package MapEditor;

import Level.Map;
import Maps.Level2Map;
import Maps.Level4Map;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("Level2Map");
            add("Level4Map");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "Level2Map":
            	return new Level2Map();
            case "Level4Map":
            	return new Level4Map();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
