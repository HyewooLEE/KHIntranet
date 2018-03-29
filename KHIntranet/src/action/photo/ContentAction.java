package action.photo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.employee.EmployeeAllDBBean;
import model.employee.EmployeeAllDataBean;
import model.photo.LikeDBBean;
import model.photo.PhotoCommentDBBean;
import model.photo.PhotoCommentDataBean;
import model.photo.PhotoDBBean;
import model.photo.PhotoDataBean;

public class ContentAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int photo_no = Integer.parseInt(request.getParameter("photo_no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		PhotoDBBean dbPro = PhotoDBBean.getInstance();
		PhotoDataBean article = dbPro.getArticle(photo_no);
		String parti = (article.getPhoto_file_nm()).trim();
		String[] file_name = parti.split(",");
		int length = file_name.length;

		PhotoCommentDBBean commentPro = PhotoCommentDBBean.getInstance();
		ArrayList<PhotoCommentDataBean> commentArticle = commentPro.getPhoto_comment(photo_no);
		int comment_count = commentPro.getCommentCount(photo_no);

		EmployeeAllDataBean empBean = (EmployeeAllDataBean) request.getSession().getAttribute("emp_all");
		String emp_name = empBean.getEmp_name();
		int emp_no = empBean.getEmp_no();

		EmployeeAllDBBean empPro = EmployeeAllDBBean.getInstance();

		int writer_no = article.getEmp_no();
		EmployeeAllDataBean writerBean = empPro.getEmp_no(writer_no);
		
		if(emp_no==writer_no) {
			request.setAttribute("me", "1");
		}else {
			request.setAttribute("me", "2");
			
		}
		
		LikeDBBean likePro = LikeDBBean.getInstance();
		int check = likePro.cofirmLike(photo_no, emp_no);
		int like_count = likePro.getLikeCount(photo_no);

		request.setAttribute("check", check);
		request.setAttribute("empBean", empBean);
		request.setAttribute("article", article);
		request.setAttribute("file_name", file_name);
		request.setAttribute("length", length);
		request.setAttribute("writerBean", writerBean);
		request.setAttribute("commentBean", commentArticle);
		request.setAttribute("photo_no", new Integer(photo_no));
		request.setAttribute("emp_name", emp_name);
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("comment_count", comment_count);
		request.setAttribute("like_count", like_count);

		return "/Intranet/Photo/content.jsp";

	}

}
