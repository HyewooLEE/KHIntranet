package action.mypage.message;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.message.MessageDBBean;

public class MessageSendAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		int msg_tono = 0;
		int msg_fromno = Integer.parseInt(request.getParameter("msg_fromno"));
		String emp_name = request.getParameter("emp_name");
		String msg_content = request.getParameter("msg_content");

		if (request.getParameter("msg_tono") == "none" || request.getParameter("msg_tono").equals("none")) {
			msg_tono = -1;
		} else {
			msg_tono = Integer.parseInt(request.getParameter("msg_tono"));
		}

		if (emp_name == null || emp_name.equals("") || msg_content == null || msg_content.equals("")) {
			request.getSession().setAttribute("check", "-1");
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			return "Intranet/Mypage/Message/MessageSendPro.jsp";
		} else if (msg_tono == -1) {
			request.getSession().setAttribute("check", "-1");
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "수신자가 정확히 지정되지 않았습니다.");
			return "Intranet/Mypage/Message/MessageSendPro.jsp";
		} else if (msg_fromno == msg_tono) {
			request.getSession().setAttribute("check", "-1");
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "저희는 자신에게 쓰는 쪽지를 지원하지 않습니다.");
			return "Intranet/Mypage/Message/MessageSendPro.jsp";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(new Timestamp(System.currentTimeMillis()));

		MessageDBBean msgPro = MessageDBBean.getInstance();
		int check = msgPro.sendMessage(msg_fromno, msg_tono, msg_content, now);
		if (check == 1) {
			request.getSession().setAttribute("check", "1");
			request.getSession().setAttribute("messageType", "성공!");
			request.getSession().setAttribute("messageContent", "전송완료하였습니다.");
		} else {
			request.getSession().setAttribute("check", "0");
			request.getSession().setAttribute("messageType", "오류!");
			request.getSession().setAttribute("messageContent", "대화상대가 존재하지않습니다.");
		}
		return "Intranet/Mypage/Message/MessageSendPro.jsp";
	}
}
