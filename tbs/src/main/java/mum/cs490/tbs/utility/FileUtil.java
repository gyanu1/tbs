package mum.cs490.tbs.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	public static boolean createDirectory(String location) {
		File md = new File(location);
		return md.mkdir();
	}

	public static String createTemporaryFile(String name, byte[] data) {
		File tempFile;
		try {
			tempFile = File.createTempFile(name, ".json");
			tempFile.deleteOnExit();
			FileOutputStream fos;
			fos = new FileOutputStream(tempFile);
			fos.write(data);
			return tempFile.getAbsolutePath();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static boolean deleteAndCreateDirectory(String location) {
		if (fileExists(location)) {
			deleteDirectory(location);
		}
		return createDirectory(location);

	}

	public static boolean deleteDirectory(String location) {
		File dir = new File(location);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(dir + "/" + children[i]);
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
	}

	public static boolean deleteFile(String location) {
		return new File(location).delete();
	}

	public static boolean fileExists(String path) {
		return new File(path).exists() ? true : false;
	}

}
