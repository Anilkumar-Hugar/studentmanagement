package com.studentmanagementTest;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import org.junit.Test;

import com.student.DBConnection;

public class DBConnectionTest {

	@Test
	public void test() {
		Connection connection=DBConnection.getcon();
		assertEquals(DBConnection.getcon(),connection);
	}

}
