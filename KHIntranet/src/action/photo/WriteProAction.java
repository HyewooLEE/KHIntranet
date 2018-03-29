package action.photo;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import action.CommandAction;
import model.photo.PhotoDBBean;
import model.photo.PhotoDataBean;

public class WriteProAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Random random = new Random();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String photo_file_nm = "";
		String photo_file_addr = "http://192.168.40.79:8328/KHIntranet/Intranet/Photo/upload";
//		String imagePath = "D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/KHIntranet/Intranet/Photo/upload";
		String imagePath = "D:/peco/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/KHIntranet/Intranet/Photo/upload";
		String emp_no = "";
		String photo_title = "";
		String photo_content = "";
		String password = "";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (!item.isFormField()) {
						long currentTime = System.currentTimeMillis();
             			int randomValue = random.nextInt(50);
						String fileName = item.getName();
						File path = new File(imagePath);
						String realName = Long.toString(currentTime) + "_" + Integer.toString(randomValue) + ".jpg";
						photo_file_nm +=  realName + ",";
						File uploadedFile = new File(path + "/" + realName);
						if (fileName != "")
							item.write(uploadedFile);
						else
							System.out.println("file not found");
					} else {
						String parm = item.getFieldName();
						if (parm.equals("emp_no")) {
							emp_no = item.getString();
						} else if (parm.equals("photo_title")) {
							photo_title = item.getString();
						} else if (parm.equals("photo_content")) {
							photo_content = item.getString();
						} else if (parm.equals("password")) {
							password = item.getString();
						}

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("not a multipart");
		}

		PhotoDataBean article = new PhotoDataBean();
		article.setEmp_no(Integer.parseInt(emp_no));
		article.setPhoto_title(photo_title);
		article.setPhoto_content(photo_content);
		article.setPhoto_date(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));
		article.setPhoto_file_addr(photo_file_addr);
		article.setPhoto_file_nm(photo_file_nm);
		article.setPassword(password);
		PhotoDBBean dbPro = PhotoDBBean.getInstance();
		int check = dbPro.insertArtcile(article);
		request.setAttribute("check", new Integer(check));
		if (check == 1) {
			request.getSession().setAttribute("check", "1");
			request.getSession().setAttribute("messageType", "성공!");
			request.getSession().setAttribute("messageContent", "글쓰기 성공하였습니다.");
		} else {
			request.getSession().setAttribute("check", "-1");
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "글쓰기 실패하였습니다.");
		}
		return "/Intranet/Photo/writePro.jsp";
	}

}