import java.io.File;

import com.sun.jna.platform.FileUtils;


public class MoveRemove {
	public MoveRemove(String path){
		//FileUtils.cleanDirectory(new File(path)); 
		File f = new File(path);
		if(f.exists()){
			f.delete();
		}
	}
}
