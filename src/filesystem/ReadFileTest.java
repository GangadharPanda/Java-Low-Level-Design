package filesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileTest {

	public static void main(String[] args) throws IOException {
		
		File file = new File("./abc.txt");
		System.out.println(file.getAbsolutePath());
		
		BufferedReader br = new BufferedReader(new FileReader("./abc.txt"));
		String line;
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}

	}

}
