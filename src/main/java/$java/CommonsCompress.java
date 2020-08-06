package $java;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.Enumeration;

public class CommonsCompress {
	/**
	 * @param archive
	 * @param files
	 * @throws IOException
	 */
	public static void zip(String archive, String[] files) throws IOException {
		OutputStream output = new FileOutputStream(archive);
		ZipArchiveOutputStream zip = new ZipArchiveOutputStream(output);
		try {
			for (String file : files) {
				zip.putArchiveEntry(new ZipArchiveEntry(file));

				IOUtils.copy(new FileInputStream(file), zip);

				zip.closeArchiveEntry();
			}
		} finally {
			zip.close();
			output.close();
		}
	}

	/**
	 * TODO: folder
	 *
	 * @param archive
	 * @param dir
	 * @throws IOException
	 */
	public static void unzip(String archive, String dir) throws IOException {
		ZipFile zip = new ZipFile(archive);
		try {
			Enumeration<ZipArchiveEntry> entries = zip.getEntries();
			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();

				InputStream input = zip.getInputStream(entry);
				OutputStream output = new FileOutputStream(new File(dir, entry.getName()));
				try {
					IOUtils.copy(input, output);
				} finally {
					input.close();
					output.close();
				}
			}
		} finally {
			zip.close();
		}
	}


	public static void main(String[] args) throws IOException {
		String archive = "archive.zip";

		zip(archive, new String[]{
			"file.txt",
			"dir/other.txt"
		});

		//unzip(archive, "dir");
	}
}
