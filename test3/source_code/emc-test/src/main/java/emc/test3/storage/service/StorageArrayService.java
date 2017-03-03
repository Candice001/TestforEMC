package emc.test3.storage.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import emc.test3.storage.cache.LunCache;
import emc.test3.storage.entity.LunUnit;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class StorageArrayService {
	
	// Create 
	public JSONObject createLUN(Map<Long, Integer> idSizeMap) throws JSONException{
		JSONObject result = new JSONObject();
    	if(null == idSizeMap || idSizeMap.size() == 0){
    		result.put("Error", "Empty Request");
    		return result;
    	}
    	
    	Map<Long, LunUnit> cache = LunCache.getLunCache();

    	for(long id : idSizeMap.keySet()){
    		LunUnit lun = LunCache.getLunCache().get(id);
    		if(lun == null) {
    			lun = new LunUnit();
        		lun.setId(id);
        		lun.setSize(idSizeMap.get(id));
        		cache.put(id, lun);
        		result.put("CreateLUN", "LUN with Id: " + id + " and Size: " + idSizeMap.get(id) + " Created successfully.");
        		result.put("result", "success");
    		} else {
    			result.put("CreateLUN", "LUN with Id: " + id + " already exist.");
    			result.put("result", "fail");
    		}
    		
    	}
		return result;
	}
	
	// Delete
	public JSONObject removeLUN(long id) throws JSONException{
    	
		LunUnit lun = LunCache.getLunCache().remove(id);
		JSONObject obj = new JSONObject();
		if(null == lun){
			obj.put(String.valueOf(id), "No Such Lun Unit. Remove Failed");
		}else{
			obj.put(String.valueOf(id), "Remove Successed");
		}
		
		return obj;
		
	}
	
	// Export a lun to a host
	public JSONObject exportToHost(long id, String hostname) throws JSONException{
		LunUnit lun = LunCache.getLunCache().get(id);
		JSONObject obj = new JSONObject();
		if(null == lun){
			obj.put(String.valueOf(id), "No Such Lun Unit. Export Failed");
			return obj;
		}
		
		boolean validHost = true;
		// A method to check hostname is valid or not
		// chechHostName(hostname);
		
		if(validHost){
			lun.setMountHost(hostname);
			obj.put(String.valueOf(id), "Mount Lun on Host " + hostname + " Successfully");
		}else{
			obj.put(String.valueOf(id), "No Host Named " + hostname + ". Export Failed");
		}
		return obj;
	}
	
	// unexport a lun from a host
	public JSONObject unExport(long id) throws JSONException {
		LunUnit lun = LunCache.getLunCache().get(id);
		JSONObject obj = new JSONObject();
		if(null == lun){
			obj.put(String.valueOf(id), "No Such Lun Unit. Unexport Failed");
		} else if (null == lun.getMountHost()){
			obj.put(String.valueOf(id), "This Lun Unit Hasn't Been Exported");
		} else{
			String hostname = lun.getMountHost();
			lun.setMountHost(null);
			obj.put(String.valueOf(id), "Lun Unit Has Been Unexported from " + hostname);
		}
		
		return obj;
	}
	
	// Resize a lun
	public JSONObject resize(long id, int size) throws JSONException{
		LunUnit lun = LunCache.getLunCache().get(id);
		JSONObject obj = new JSONObject();
		if(null == lun){
			obj.put(String.valueOf(id), "No Such Lun Unit. Resize Failed");
		}else{
			lun.setSize(size);
			obj.put(String.valueOf(id), "Lun Unit Has Been Resized to " + size);
		}
		
		return obj;
	}
	
	// Get information about a lun
	public JSONObject retrieveInfo(long id) throws JSONException {
		LunUnit lun = LunCache.getLunCache().get(id);
		JSONObject obj = new JSONObject();
		if(null == lun){
			obj.put(String.valueOf(id), "No Such Lun Unit.");
		}else{
			int size = lun.getSize();
			String mountHost = ( null == lun.getMountHost() )? "Not Exported Yet." : lun.getMountHost();
			obj.put("id", String.valueOf(id));
			obj.put("size", size);
			obj.put("export", mountHost);
		}
		
		return obj;
	}
	
	public JSONObject persist() throws JSONException{
		// persist data into database
		// Here put it into a file
		String storePath = System.getProperty("user.dir") +File.separator + "src" + File.separator + "main" + File.separator + "result";
		if(LunCache.getLunCache().size() > 0){
			File dumpFile = new File(storePath, "dump.txt");
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(dumpFile));
				
				Iterator<LunUnit> it =  LunCache.getLunCache().values().iterator();
				while(it.hasNext()){
					LunUnit lun = it.next();
					String host = ( null == lun.getMountHost() )? "" : lun.getMountHost();
					bw.write(lun.getId() + ":" + lun.getSize()  + ":" + host);
					bw.newLine();
				}
				
				bw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(bw != null){
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}					
		}
		
		JSONObject obj = new JSONObject();
		obj.put("Persist Result", "Success");
		return obj;
		
	}

}
