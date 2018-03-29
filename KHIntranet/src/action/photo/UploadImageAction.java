package action.photo;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import action.photo.util.ImageUtil;

public class UploadImageAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

//		String imagePath = "D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/KHIntranet/Intranet/Photo/upload";
		String imagePath = "D:/peco/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/KHIntranet/Intranet/Photo/upload";
		int size = 10 * 1024 * 1024;
		MultipartRequest mReq = null;
		MultipartRequest multi = new MultipartRequest(request, imagePath, size, "UTF-8", new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String str = (String) files.nextElement();
		String filename = multi.getFilesystemName(str);
		File srcFile = new File(imagePath + "/" + filename);
		File destFile = new File(imagePath + "/sm_" + filename);
		ImageUtil.resize(srcFile, destFile, 100, ImageUtil.RATIO);

		return URLEncoder.encode(filename, "UTF-8");
	}

}