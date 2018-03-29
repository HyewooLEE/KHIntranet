package model.mypage.certificate;
       
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jdbc.JdbcUtil;

public class SalaryDBBean {
	private static SalaryDBBean instance = new SalaryDBBean();

		public static SalaryDBBean getInstance() {
			return instance;
		}
		
		private SalaryDBBean() {}
		
		private Connection getConnection() throws Exception{
			String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
			return DriverManager.getConnection(jdbcDriver);
		}
		
		//Salary Bean 가져오기 
				public ArrayList<SalaryDataBean> getSalarys(int emp_no)throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					ArrayList<SalaryDataBean> SalaryList = null;
					String sql = "SELECT * FROM SALARY WHERE SAL_EMP_NO = ? ORDER BY SAL_START_DATE DESC ";
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, emp_no);
						rs=pstmt.executeQuery();
						if(rs.next()) {
							SalaryList = new ArrayList<SalaryDataBean>();
						do {
							SalaryDataBean Salary = new SalaryDataBean();
							Salary.setSal_emp_no(rs.getInt("SAL_EMP_NO"));
							Salary.setSal_position_rank(rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_position_sal(rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_emp_sal(rs.getInt("SAL_EMP_SAL"));
							Salary.setSal_sum(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_first(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_second(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_third(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_sum(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							String sal_start_year = (rs.getString("SAL_START_DATE").substring(0, 4));
							String sal_start_month = (rs.getString("SAL_START_DATE").substring(5, 7));
							Salary.setSal_start_year(sal_start_year);
							Salary.setSal_start_month(sal_start_month);
							if(rs.getString("SAL_END_DATE")!=null) {
								String sal_end_year = (rs.getString("SAL_END_DATE").substring(0, 4));
								String sal_end_month = (rs.getString("SAL_END_DATE").substring(5, 7));
								Salary.setSal_end_year(sal_end_year);
								Salary.setSal_end_month(sal_end_month);
							}else {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
							    String now_year = sdf.format(new Timestamp(System.currentTimeMillis())); 
							    SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
							    String now_month = sdf2.format(new Timestamp(System.currentTimeMillis())); 
							    Salary.setSal_end_year(now_year);
								Salary.setSal_end_month(now_month);
							}
							SalaryList.add(Salary);
						}while(rs.next());
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						JdbcUtil.close(rs);
						JdbcUtil.close(pstmt);
						JdbcUtil.close(conn);
					}
					return SalaryList;
				}
				
				//Salary Bean 가져오기 
				public SalaryDataBean getSalary(int sal_emp_sal,int sal_emp_no)throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					SalaryDataBean Salary = null;
					String sql = "SELECT * FROM SALARY WHERE SAL_EMP_SAL = ? AND SAL_EMP_NO = ? ";
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1,sal_emp_sal);
						pstmt.setInt(2,sal_emp_no);
						rs=pstmt.executeQuery();
						while(rs.next()) {
							Salary = new SalaryDataBean();
							Salary.setSal_emp_no(rs.getInt("SAL_EMP_NO"));
							Salary.setSal_position_rank(rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_position_sal(rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_emp_sal(rs.getInt("SAL_EMP_SAL"));
							Salary.setSal_sum(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_first(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_second(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_third(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_ins_sum(rs.getInt("SAL_EMP_SAL"), rs.getInt("SAL_POSITION_RANK"));
							Salary.setSal_start_date(rs.getString("SAL_START_DATE"));
							Salary.setSal_end_date(rs.getString("SAL_END_DATE"));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						JdbcUtil.close(rs);
						JdbcUtil.close(pstmt);
						JdbcUtil.close(conn);
					}
					return Salary;
				}
				
				//salary 입력
				public int insertSalary(int sal_emp_no, int sal_position_rank , int sal_emp_sal ,String sal_month )throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					String sql = "INSERT INTO Salary VALUES ( ? , ? , ? , ? , ? )";
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, sal_emp_no);
						pstmt.setInt(2, sal_position_rank);
						pstmt.setInt(3, sal_emp_sal);
						pstmt.setString(4, sal_month);
						pstmt.setString(5, "");
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
				public int updateSalary(int sal_emp_no,String sal_end_date)throws Exception{
					Connection conn = null;
					PreparedStatement pstmt = null;
					
					String sql = "update salary set sal_end_date = ? where sal_emp_no = ? and sal_end_date is null";
					try {
						conn = getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, sal_end_date);
						pstmt.setInt(2, sal_emp_no);
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
