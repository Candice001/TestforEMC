package emc.test3.storage.cache;

import java.util.HashMap;
import java.util.Map;

import emc.test3.storage.entity.LunUnit;

//This class is used to declare memory cache
public class LunCache {
	private static Map<Long, LunUnit> lunCache;
	private static LunCache instance = null;
	
	// use singleton to make sure only one instance
	private LunCache(){
		lunCache = new HashMap<Long, LunUnit>();
	}
	
	public static Map<Long, LunUnit> getLunCache(){
		if(instance == null) {
			instance = new LunCache();
		}
		return lunCache;
	}

}
