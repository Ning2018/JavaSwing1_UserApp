package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhanglj
 *鏁版嵁搴撹繛鎺ュ伐鍏疯緟鍔╃被
 */

public class DBCon {
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/javaswing";
	public static final String USER="root";
	public static final String PWD="root";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	public DBCon(){
		try {
			//鍔犺浇椹卞姩绋嬪簭
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return杩斿洖鏁版嵁搴撹繛鎺�
	 */
	public Connection getCon(){
		try {
			con=DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 鍏抽棴璧勬簮 
	 */
	public void closeAll(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * @param sql锟斤拷sql璇彞 
	 * @param pras锟斤拷锟斤拷锟斤拷锟斤拷鍙傛暟鍒楄〃 
	 * @return鍙楀奖鍝嶇殑鏉℃暟
	 */
	public int update(String sql,Object... pras){
		int resu=0;
		con=getCon();
		try {
			ps=con.prepareStatement(sql);
			if(pras!=null){
				for(int i=0;i<pras.length;i++){
					ps.setObject(i+1, pras[i]);
				}
			}
			resu=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return resu;
	}
	
	
	/**
	 * @param sql sql璇彞 
	 * @param pras
	 * @return 缁撴灉闆�
	 */
	public ResultSet query(String sql,Object... pras){
		con=getCon();
		try {
			ps=con.prepareStatement(sql);
			if(pras!=null){
				for(int i=0;i<pras.length;i++){
					ps.setObject(i+1, pras[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
