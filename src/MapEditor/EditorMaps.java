package MapEditor;

import Level.Map;
import Maps.FinalLevelMap;
import Maps.Level2Map;
import Maps.Level3Map;
import Maps.Level4Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import java.util.ArrayList;

public class EditorMaps {
	public static ArrayList<String> getMapNames() {
		return new ArrayList<String>() {
			{
				add("TestMap");
				add("TitleScreen");
			 	add("Level2Map");
				add("Level3Map");
				add("Level4Map");
				add("FinalLevelMap");

			}
		};
	}

	public static Map getMapByName(String mapName) {
		switch (mapName) {
		case "TestMap":
			return new TestMap();
		case "TitleScreen":
			return new TitleScreenMap();
		case "Level2Map":
			return new Level2Map();

		case "Level3Map":
			return new Level3Map();

		case "Level4Map":
			return new Level4Map();
		
		case "FinalLevelMap":
			return new FinalLevelMap();

		default:
			throw new RuntimeException("Unrecognized map name");
		}
	}
}
