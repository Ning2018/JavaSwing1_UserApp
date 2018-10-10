package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBCon;
import dao.IDeptDao;
import entity.Dept;

public class DeptDaoImpl implements IDeptDao{
	DBCon util=new DBCon();
	@Override
	public List<Dept> queryAll() {
		// TODO Auto-generated method stub
		return _list(util.query("select * from dept"));
	}
	private List<Dept> _list(ResultSet rs){
		List<Dept> _list=new ArrayList<Dept>();
		try {
			while(rs.next()){
				Dept dept=new Dept();
				dept.setDeptid(rs.getInt("deptid"));
				dept.setDeptname(rs.getString("deptname"));
				_list.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeAll();
		}
		return _list;
	}

}
