package com.dtyunxi.dtplatform.dao;

import com.dtyunxi.dtplatform.domain.User;

public interface UserDAO {
	public User findByName(String username) throws Exception;
	public int findByEmail(String email) throws Exception;

	public int findByUser(String user) throws Exception;
	
	public void addUser(User user) throws Exception;
}
