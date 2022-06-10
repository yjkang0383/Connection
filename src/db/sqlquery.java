package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlquery {
	public static void main(String[] args){
		Connection con;
		Statement stmt;
		ResultSet rs;
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Oracle 드라이버 로딩");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@10.20.50.12:1521:NMCASH","MOTRD","MOTRD");
			System.out.println("Connection 생성 성공");
			
			stmt=con.createStatement();
			System.out.println("Statement 객체 생성 성공");
			
			String query = ("SELECT TO_CHAR(TO_DATE(A.TRD_DT||A.TRD_TM,'YYYYMMDDHH24MISS'), 'YYYY-MM-DD HH24:MI:SS') AS TRD_DTM, A.MRCT_TRD_NO , B.TRD_NO , A.SVC_ID , B.TRD_AMT" + 
							"  FROM MOTRD.PN_M_TRD A, MOTRD.PN_M_TRD_DTL B" + 
							" WHERE A.M_TRD_NO=B.M_TRD_NO" + 
							"   AND A.TRD_DT = B.TRD_DT" + 
							"   AND B.TRD_DT = '20210708'" + 
							"   AND A.TRD_TM = '153455'" + 
							"ORDER BY B.TRD_DT , A.TRD_TM");
			
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				System.out.println(rs.getString("TRD_DTM"));
				System.out.println(rs.getString("MRCT_TRD_NO"));
				System.out.println(rs.getString("TRD_NO"));
				System.out.println(rs.getString("SVC_ID"));
				System.out.println(rs.getString("TRD_AMT"));
			}
			
			rs.close();
			stmt.close();
			con.close();
			
			
			/**
			 * 거래일시 : 2022-03-21 15:34:55
			 * 가맹점 거래번호
			 * 모빌 거래번호
			 * 서비스ID
			 * 거래금액
			 */
		}
		
		// Handle any errors that may have occurred.
		
		catch ( ClassNotFoundException en) {
			en.printStackTrace();
			System.out.print("사유 " + en.getMessage());
			}
		
		catch (SQLException e) {
			e.printStackTrace();
			System.out.print("사유 " + e.getMessage());
			}
	}
}

	
