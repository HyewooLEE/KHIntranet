<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%
	int atd_no = Integer.parseInt(request.getParameter("atd_no"));
	
	String atd_file_name = null;
	
	if(atd_no != 0) {
		if (request.getParameter("atd_file_name") != null) {
			atd_file_name = request.getParameter("atd_file_name");
			String atd_file_path = request.getParameter("atd_file_path");
			File file = null;

			BufferedInputStream fin = null;
			BufferedOutputStream outs = null;

			try {
				file = new File(atd_file_path,atd_file_name); // 파일경로에 파일 만들기
				response.reset();

				response.setHeader("Content-Disposition", "attachment; filename=\""+ new String(atd_file_name.getBytes("KSC5601"), "ISO8859_1") + "\"");
				response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");

				fin = new BufferedInputStream(new FileInputStream(file)); // 파일 읽기
				out.clear();
				outs = new BufferedOutputStream(response.getOutputStream()); // 파일 쓰기

				int read = 0;

				while ((read = fin.read()) != -1) {
					outs.write(read);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(outs!=null) try { outs.close(); }catch(IOException e) { }
				if(fin!=null) try { fin.close(); }catch(IOException e) { }
			}
		}
	}	
	%>