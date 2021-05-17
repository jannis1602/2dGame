package bufferedtxt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import gui.Options;

public class Writer {
	public Writer() {

		String content = "This is the content to write into file\n  lol";
		content = String.valueOf(Options.cursornum);
		try (FileWriter writer = new FileWriter(readFileFromClasspath("optionsave"));
				BufferedWriter bw = new BufferedWriter(writer)) {
			System.out.println("write " + content);
			bw.write(content);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}

	public File readFileFromClasspath(String datei) {
		URL fileUrl = getClass().getResource(datei);
		return new File(fileUrl.getFile());
	}
}
