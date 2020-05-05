package com.github.rillis.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
	public static boolean CONFIG_DEBUG = false;
	
	public static boolean run(Connection c, String sql) throws SQLException {
		if(CONFIG_DEBUG) {
			System.out.println("DB RUN: "+sql);
		}
		Statement stm = c.createStatement();
		boolean b = stm.execute(sql);
		stm.close();
		return b;
	}
	
	public static ResultSet query(Connection c, String sql) throws SQLException{
		if(CONFIG_DEBUG) {
			System.out.println("DB QUERY: "+sql);
		}
        Statement stmt  = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);      
        return rs;        
	}
	
	public static boolean createTable(Connection c, String name, String[] fields) throws SQLException {
		//Inicio
		String base = "CREATE TABLE IF NOT EXISTS \""+name+"\" (";
		String sql = base;
		
		//Fields
		for (String field : fields) {
			if(!sql.equals(base)) {
				sql+=",";
			}
			sql+=field;
		}
		
		//Final e run
		sql+=");";
		return run(c, sql);
	}
	
	public static boolean insert(Connection c, String table, String[] fields, Object[] values) throws SQLException {
		//Inicio
		String base = "INSERT INTO \""+table+"\" (";
		String sql = base;
		
		//Fields
		for (String field : fields) {
			if(!sql.equals(base)) {
				sql+=",";
			}
			sql+=field;
		}
		
		sql+=") values(";
		base=sql;
		
		//Values
		for (Object value : values) {
			if(!sql.equals(base)) {
				sql+=",";
			}
			
			if(value instanceof String) {
				sql+="\""+value+"\"";
			}else {
				sql+=value.toString();
			}
		}
		
		//Final e run
		sql+=");";
		
		return run(c, sql);
	}
	
	public static boolean update(Connection c, String table, String[] fields, Object[] values, String condition) throws SQLException {
		//Inicio
		String base = "UPDATE  \""+table+"\" SET ";
		String sql = base;
		
		//Fields e Values intercalados
		for (int i = 0; i < values.length; i++) {
			if(!sql.equals(base)) {
				sql+=",";
			}
			
			sql+=fields[i]+"=";
			
			if(values[i] instanceof String) {
				sql+="\""+values[i]+"\"";
			}else {
				sql+=values[i].toString();
			}
		}
		
		sql+=" WHERE ";
		
		sql+=condition+";";
		
		return run(c, sql);
	}
	
	public static boolean delete(Connection c, String table, String condition) throws SQLException {
		String sql = "DELETE FROM \""+table+"\" WHERE " + condition + ";";
		return run(c,sql);
	}
	
	public static boolean truncate(Connection c, String table) throws SQLException {
		String sql = "DELETE FROM \""+table+"\";";
		return run(c,sql);
	}
	
	public static boolean update(Connection c, String table, String[] fields, Object[] values) throws SQLException {
		//Inicio
		String base = "UPDATE  \""+table+"\" SET ";
		String sql = base;
		
		//Fields e Values intercalados
		for (int i = 0; i < values.length; i++) {
			if(!sql.equals(base)) {
				sql+=",";
			}
			
			sql+=fields[i]+"=";
			
			if(values[i] instanceof String) {
				sql+="\""+values[i]+"\"";
			}else {
				sql+=values[i].toString();
			}
		}
		
		sql+=";";
		
		return run(c, sql);
	}
	
	public static boolean dropTable(Connection c, String table) throws SQLException {
		String sql = "DROP TABLE IF EXISTS \""+table+"\";";
		return run(c, sql);
	}
	
	public static ResultSet select(Connection c, String table, String[] fields, String condition) throws SQLException{
		//Inicio
		String base = "SELECT ";
		String sql = base;
		
		if(fields == null) {
			sql+="*";
		}else if(fields.length==0) {
			sql+="*";
		}else {
			for (String field : fields) {
				if(!sql.equals(base)) {
					sql+=",";
				}
				
				sql+=field;
			}
		}
		
		sql+=" FROM \""+table+"\" WHERE "+condition+";";
		
		return query(c,sql);
	}
	
	public static ResultSet select(Connection c, String table, String[] fields) throws SQLException{
		//Inicio
		String base = "SELECT ";
		String sql = base;
		
		if(fields == null) {
			sql+="*";
		}else if(fields.length==0) {
			sql+="*";
		}else {
			for (String field : fields) {
				if(!sql.equals(base)) {
					sql+=",";
				}
				
				sql+=field;
			}
		}
		
		sql+=" FROM \""+table+"\";";
		
		return query(c,sql);
	}
}
