package action.mypage.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.message.MessageDBBean;

public class MessageRestoreAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		MessageDBBean msgPro = MessageDBBean.getInstance();
		String result = "";
		int x = 0;
		String status = request.getParameter("status");

		if (request.getParameterValues("msg_no[]") != null) {
			if (status.equals("3")) {
				String[] Values = request.getParameterValues("msg_no[]");
				for (String Value : Values) {
					int msg_no = Integer.parseInt(Value);
					x = msgPro.restoreMessage(msg_no);
				}
				request.getSession().setAttribute("messageType", "성공!");
				request.getSession().setAttribute("messageContent", "메세지를 복구하였습니다.");

				result = x + "";
			}
		}

		return result;
	}
}
