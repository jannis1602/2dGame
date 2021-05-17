package bufferedtxt;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import gui.Options;

public class Reader {

	public Reader() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(readFileFromClasspath("optionsave"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Datei nicht gefunden");
		}
		String zeile1 = scanner.nextLine();
		System.out.println("read" + zeile1);
		Txtoptions.optionCursor = Integer.valueOf(zeile1);
		Options.cursornum = Txtoptions.optionCursor;
//		String zeile2 = scanner.nextLine();
//		System.out.println(zeile2);

	}

	public File readFileFromClasspath(String datei) {
		URL fileUrl = getClass().getResource(datei);
		return new File(fileUrl.getFile());
	}
}
