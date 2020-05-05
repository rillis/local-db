package com.github.rillis.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.github.rillis.Files.FileUtils;

public class Connector{
	
	public static Connection connect(File archive) throws ClassNotFoundException, SQLException, IOException {
		FileUtils.create(archive);
		
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + archive);
	}
	
	public static Connection connect(String archive) throws ClassNotFoundException, SQLException, IOException {
		File file = new File(archive);
		FileUtils.create(file);
		
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + file);
	}
}
