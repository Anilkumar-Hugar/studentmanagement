package com.studentmanagement;

import java.io.*;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class StudentBean implements Serializable {
	private String name;
	private String email;
	private String address;
	private String course;
	private String password;
	private long phone;
}
