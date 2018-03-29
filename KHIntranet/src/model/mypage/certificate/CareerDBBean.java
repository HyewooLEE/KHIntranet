package model.mypage.certificate;
       
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jdbc.JdbcUtil;

public class CareerDBBean {
	private static CareerDBBean instance = new CareerDBBean();

		public static CareerDBBean getInstance() {
			return instance;
		}
		
		private CareerDBBean() {}
		
		private Connection getConnection() throws Exception{
			String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
			return DriverManager.getConnection(jdbcDriver);
		}
		//Career Bean 가져오기 
		public ArrayList<CareerDataBean> getCareers(int emp_no)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<CareerDataBean> careerList = null;
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy년 MM월 dd일"); 


			String sql = "SELECT * FROM CAREER WHERE CAR_EMP_NO = ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp_no);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					careerList = new ArrayList<CareerDataBean>();
				do {
					CareerDataBean career = new CareerDataBean();
					career.setCar_emp_no(rs.getInt("CAR_EMP_NO"));
					career.setCar_dept_name(rs.getString("CAR_DEPT_NAME"));
					career.setCar_position_name(rs.getString("CAR_POSITION_NAME"));
					career.setCar_start_date(sdf.format(rs.getTimestamp("CAR_START_DATE")));
					if(rs.getTimestamp("CAR_END_DATE")!=null) {
						career.setCar_end_date(sdf.format(rs.getTimestamp("CAR_END_DATE")));
					}else {
						career.setCar_end_date("");
					}
					careerList.add(career);
				}while(rs.next());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			return careerList;
		}
		
		//커리어 입력
		public int insertCareer(int car_emp_no, String car_dept_name,String car_position_name,String car_start_date)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO CAREER VALUES ( ? , ? , ? , ? , ?)";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, car_emp_no);
				pstmt.setString(2, car_dept_name);
				pstmt.setString(3, car_position_name);
				pstmt.setString(4, car_start_date);
				pstmt.setString(5, "" );
				
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			return -1;
		}
		
		//커리어 수정
		public int updateCareer(int car_emp_no,String car_end_date)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = "update career set car_end_date = ? where car_emp_no = ? and car_end_date is null";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_end_date);
				pstmt.setInt(2, car_emp_no);
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(conn);
			}
			return -1;
		}
		
		
}
