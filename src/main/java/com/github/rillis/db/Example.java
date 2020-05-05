package com.github.rillis.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;

public class Example {

	public static void main(String[] args) {
		Connection c = null;
		try {
			
			c = Connector.connect("A:\\database.db");
			
			//Shows command sent to SQLite
			//SQLite.CONFIG_DEBUG=true;
			
			String table = "test";
			
			//AUTO: "if not exists"
			SQLite.dropTable(c, table);
			
			//AUTO: "if not exists"
			SQLite.createTable(c, table, new String[] {"name varchar(70) PRIMARY KEY NOT NULL", "age integer"});
			
			SQLite.insert(c, table, new String[]{"name", "age"}, new Object[]{"Foo1", 10});
			SQLite.insert(c, table, new String[]{"name", "age"}, new Object[]{"Foo2", 20});
			SQLite.insert(c, table, new String[]{"name", "age"}, new Object[]{"Foo3 Bar", 30});
			
			SQLite.update(c, table, new String[]{"name", "age"}, new Object[]{"FooBar", 15}, "age=20");
			
			//If field "condition" have space he must have \" 
			SQLite.delete(c, table, "name=\"Foo3 Bar\"");
			
			//If String[] fields = "new String[] {}" or "null" so = * (only in SQLite.select)
			ResultSet rs = SQLite.select(c, table, null);
			
			while(rs.next()) {
				System.out.println("name: \""+rs.getString("name")+"\" age: "+rs.getInt("age"));
			}
			//Results:
			//name: "Foo1" age: 10
			//name: "FooBar" age: 15
			
			
			c.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}
