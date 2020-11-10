import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadAndWriteFile {
    
    public ArrayList<Student> readData(String fileName) {
		
		File f = new File(fileName);
		ArrayList<Student> deserialize = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			deserialize = (ArrayList<Student>) ois.readObject();
			//ois.readObject();
			ois.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
        }
        return deserialize;
    }
    public void writeData(String fileName, ArrayList<Student> l) {
		File f = new File(fileName);
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(l);
			oos.close();
			System.out.println("Writing Done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
