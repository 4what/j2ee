package sample.java.io;

import java.io.IOException;

/**
 * https://docs.oracle.com/javase/tutorial/essential/io/file.html
 */
public class File {


	public static void main(String[] args) throws IOException {
		String path = "dir/";

		java.io.File dir = new java.io.File(path);

		System.out.println("mkdir: " + dir.mkdir());
		//System.out.println("mkdirs: " + dir.mkdirs());

		java.io.File file = new java.io.File(path + "file.txt");
		java.io.File other = new java.io.File(path + "other.txt");

		System.out.println("createNewFile: " + file.createNewFile());

		for (String o : dir.list()) {
			System.out.println("list: " + o);
		}

		for (java.io.File o : dir.listFiles()) {
			System.out.println("listFiles: " + o);
		}

		System.out.println("getAbsolutePath: " + file.getAbsolutePath());
		System.out.println("getName: " + file.getName());
		System.out.println("getParent: " + file.getParent());
		System.out.println("getPath: " + file.getPath());

		System.out.println("getFreeSpace: " + file.getFreeSpace());
		System.out.println("getTotalSpace: " + file.getTotalSpace());
		System.out.println("getUsableSpace: " + file.getUsableSpace());

		System.out.println("isDirectory: " + file.isDirectory());
		System.out.println("isFile: " + file.isFile());

		System.out.println("exists: " + file.exists());
		System.out.println("lastModified: " + file.lastModified());
		System.out.println("toURI: " + file.toURI());

		System.out.println("length: " + file.length());
		System.out.println("renameTo: " + file.renameTo(other));

		System.out.println("delete: " + file.delete());
		System.out.println("delete: " + other.delete());
		System.out.println("delete: " + dir.delete());
	}
}
