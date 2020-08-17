package $java;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonsFileUpload {
	private static String charset = "UTF-8";

	/**
	 * @param filename
	 * @return
	 */
	public static String getExt(String filename) {
		int dot = filename.lastIndexOf(".");

		return dot > -1 ? filename.substring(dot) : "";
	}

	/**
	 * @param items
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map getParams(List<FileItem> items) throws UnsupportedEncodingException {
		Map result = new HashMap();

		// Process the uploaded items
		for (FileItem o : items) {
			DiskFileItem item = (DiskFileItem) o;

			String fieldname = item.getFieldName();

			List values = result.containsKey(fieldname) ? (List) result.get(fieldname) : new ArrayList();

			values.add(
				item.isFormField() ?
					item.getString(charset) // Process a regular form field
					: item // Process a file upload
			);

			result.put(fieldname, values);
		}

		for (Object o : result.entrySet()) {
			Map.Entry entry = (Map.Entry) o;

			List values = (List) entry.getValue();

			if (values.size() == 1) {
				result.put(entry.getKey(), values.get(0));
			}
		}

		return result;
	}

	/**
	 * @param request
	 * @return
	 * @throws FileUploadException
	 */
	public static List<FileItem> parseRequest(HttpServletRequest request) throws FileUploadException {
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			// Create a factory for disk-based file items
			//FileItemFactory
			DiskFileItemFactory
				factory = new DiskFileItemFactory();

			// Set factory constraints
			// maximum size that will be stored in memory
			//factory.setSizeThreshold(10240);
			// the location for saving data that is larger than getSizeThreshold()
			//factory.setRepository(null);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// encoding
			upload.setHeaderEncoding(charset);

			// Set overall request size constraint
			//upload.setSizeMax(-1);

			//upload.setFileSizeMax(-1);

			// Parse the request
			List<FileItem> items = upload.parseRequest(request);

			return items;
		}

		return null;
	}

	/**
	 * @param items
	 * @return
	 * @throws IOException
	 */
	public static List<InputStream> upload(List<FileItem> items) throws IOException {
		List<InputStream> result = new ArrayList<>();

		for (FileItem o : items) {
			DiskFileItem item = (DiskFileItem) o;

			if (!item.isFormField()) {
				result.add(item.getInputStream());
			}
		}

		return result;
	}

	/**
	 * @param items
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static List<String> upload(List<FileItem> items, String path) throws Exception {
		List<String> result = new ArrayList<>();

		for (FileItem o : items) {
			DiskFileItem item = (DiskFileItem) o;

			if (!item.isFormField()) {
				result.add(write(item, path, true));
			}
		}

		return result;
	}

	/**
	 * @see org.apache.commons.codec.digest.DigestUtils#md5Hex(java.lang.String)
	 *
	 * @param item
	 * @param path
	 * @param rename
	 * @return
	 * @throws Exception
	 */
	public static String write(FileItem item, String path, Boolean rename) throws Exception {
		if (item.getSize() == 0) {
			return null;
		}

		String filename = item.getName();

		if (rename) {
			filename = hash(filename);
		}

		String pathname = path + filename;

		File file = new File(pathname);

		//if (file.exists()) {} // TODO:

		item.write(file);

		return filename;
	}

	/**
	 * @param filename
	 * @return
	 */
	public static String hash(String filename) {
		return DigestUtils.md5Hex(filename + String.valueOf(System.currentTimeMillis())) + getExt(filename);
	}
}
