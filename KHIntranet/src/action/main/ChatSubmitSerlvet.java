package action.main;
  
import java.io.IOException;
import java.net.*;
import java.net.URLDecoder;
import java.io.*;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.main.ChatDBBean;

public class ChatSubmitSerlvet implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");  //�ѱ� �޾ƿ���
		response.setContentType("text/html;charset=UTF-8");
		 
		String chatName = request.getParameter("chatName");
		int chatNo = Integer.parseInt(request.getParameter("chatNo"));
		String chatContent = URLDecoder.decode(request.getParameter("chatContent"), "UTF-8");  //�޽��� ����
 
		ChatDBBean dao = ChatDBBean.getInstance();
		
		
		if(chatContent == null || chatContent.equals("")) {
			response.getWriter().write("0"); //���� �߻���
		}else {
			//����ð� ��������
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //HH:mm:ss
			String today = formatter.format(new java.util.Date());
			
			 //System.out.println(today);
			response.getWriter().write(dao.submit(chatNo,chatName, chatContent, today) + "");  //�޽��� ���� �־��ֱ� ���ڿ��� �ޱ� ����
		}
		return "";	
	}		
}

//ajax �������� �޽��� ����
