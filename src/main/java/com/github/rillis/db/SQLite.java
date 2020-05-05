package com.github.rillis.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
	public static boolean run(Connection c, String sql) throws SQLException {
		Statement stm = c.createStatement();
		boolean b = stm.execute(sql);
		stm.close();
		return b;
	}
	
	public static boolean createTable(Connection c, String name, String[] fields) throws SQLException {
		String sql = "CREATE TABLE \""+name+"\" (";
		
		for (String field : fields) {
			if(!sql.equals("CREATE TABLE \""+name+"\" (")) {
				sql+=",";
			}
			sql+=field;
		}
		
		sql+=");";
		return run(c, sql);
	}
}
