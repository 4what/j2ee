package sample.spring.controller;

import $java.CommonsFileUpload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonsFileUploadController {
	private String path = "upload/";

	/**
	 * @see org.springframework.web.multipart.commons.CommonsMultipartResolver
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public List<String> upload(HttpServletRequest request, String name, MultipartFile[] file) throws IOException {
		List<String> result = new ArrayList<>();

		System.out.println("name: " + name);

		for (MultipartFile item : file) {
			if (!item.isEmpty()) {
				String filename = item.getOriginalFilename();
				System.out.println("filename: " + filename);

				String pathname = path + CommonsFileUpload.hash(filename);
				System.out.println("pathname: " + pathname);

				item.transferTo(new File(request.getServletContext().getRealPath("/") + pathname));

				result.add(pathname);
			}
		}

		return result;
	}
}
