import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MoveRemove {
	public MoveRemove(String path){
		File f = new File(path);
		if(f.exists()){
			f.delete();
		}
	}
}
