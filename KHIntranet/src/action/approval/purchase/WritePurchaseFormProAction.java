package action.approval.purchase;
  
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.approval.purchase.PurchaseDBBean;
import model.approval.purchase.PurchaseDataBean;

public class WritePurchaseFormProAction implements CommandAction {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {request.setCharacterEncoding("utf-8");
   
      int size = 1024 *1024 * 10; //10¸Þ°¡
      
      String savePath = "d://fileUpload";
      
      try{
         MultipartRequest multi = new MultipartRequest(request, savePath, size, "utf-8", new DefaultFileRenamePolicy());
         
         PurchaseDataBean article = new PurchaseDataBean();
         
         article.setPur_title(multi.getParameter("pur_title"));
         article.setPur_emp_no(Integer.parseInt(multi.getParameter("pur_emp_no")));
         //article.setPur_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         article.setPur_date(sdf.format(new Timestamp(System.currentTimeMillis())));
         article.setPur_receiver(multi.getParameter("pur_receiver").replaceAll("\\p{Z}", "")+','+Integer.parseInt(multi.getParameter("pur_emp_no")));      
         article.setPur_name(multi.getParameter("pur_name"));
         article.setPur_standard(multi.getParameter("pur_standard"));
         article.setPur_quan(multi.getParameter("pur_quan"));
         article.setPur_price(Integer.parseInt(multi.getParameter("pur_price")));
         article.setPur_sum(Integer.parseInt(multi.getParameter("pur_sum")));
         article.setPur_use(multi.getParameter("pur_use"));
         article.setPur_note(multi.getParameter("pur_note"));
         article.setPur_file_name(multi.getParameter("pur_file_name"));
         if(multi.getFilesystemName("pur_file_name") != null) {
            /*article.setPur_file_path(savePath + "/" + multi.getFilesystemName("pur_file_name")); */
            article.setPur_file_path(savePath);
         }else {
            article.setPur_file_path("null"); 
         }
         article.setPur_file_path(multi.getParameter("pur_file_path"));
         article.setPur_status_ny(multi.getParameter("pur_status_ny"));
         article.setDoc_no(Integer.parseInt(multi.getParameter("doc_no")));
         
         PurchaseDBBean purchaseDao = PurchaseDBBean.getInstance();
         purchaseDao.insertPurchase(article);
      }catch(Exception e){
         e.printStackTrace();
      }
      
      return "Intranet/Approval/Purchase/writePurchaseFormPro.jsp";                                            
   
   }

}