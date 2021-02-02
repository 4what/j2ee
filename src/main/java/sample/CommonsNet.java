package sample;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPHTTPClient;

import java.io.*;

public class CommonsNet {


	public static void main(String[] args) throws IOException {
		String host = "localhost";
		int port = 21;

		String username = "root";
		String password = "";

		FTPClient ftp =
			new FTPClient()
			//new FTPHTTPClient(proxyHost, proxyPort, proxyUser, proxyPass)
		;

		try {
			ftp.connect(host, port);
			ftp.login(username, password);

			//ftp.setFileType(FTP.BINARY_FILE_TYPE);

			/* list */
			FTPFile[] files = ftp.listFiles("/");
			for (FTPFile file : files) {
				System.out.println("file: " + file);
			}

			String remote = "/file.txt";
			String local = "/file.txt";

			/* download */
/*
			OutputStream output = new FileOutputStream(local);
			try {
				ftp.retrieveFile(remote, output);
			} finally {
				output.close();
			}
*/

			/* upload */
/*
			InputStream input = new FileInputStream(local);
			try {
				ftp.storeFile(remote, input);
			} finally {
				input.close();
			}
*/

			ftp.logout();

			//System.out.println(ftp.getReplyString());
		} finally {
			if (ftp.isConnected()) {
				ftp.disconnect();
			}
		}
	}
}
