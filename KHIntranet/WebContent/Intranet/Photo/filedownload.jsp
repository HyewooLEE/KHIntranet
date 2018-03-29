<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="UTF-8"%>
<%
   int photo_no = Integer.parseInt(request.getParameter("photo_no"));
   String photo_file_nm = null;
   
   if(photo_no != 0) {
      if (request.getParameter("photo_file_nm") != null) {
         photo_file_nm = request.getParameter("photo_file_nm");
         String savePath = request.getParameter("photo_file_addr");
         File file = null;

         BufferedInputStream fin = null;
         BufferedOutputStream outs = null;

         try {
            file = new File(savePath, photo_file_nm); // 파일경로에 파일 만들기
            response.reset();

            response.setHeader("Content-Disposition", "attachment; filename=\""+ new String(photo_file_nm.getBytes("KSC5601"), "ISO8859_1") + "\"");
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