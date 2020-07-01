package com.techelevator.tenmo.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.User;

@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

	private AccountsDAO accountDAO;
	private UserDAO userDAO;
	private JdbcTemplate jdbcTemplate;
	
	public UserController(AccountsDAO accountDAO, UserSqlDAO userSqlDAO) {
		this.accountDAO = accountDAO;
		this.userDAO = userSqlDAO;
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> list() {
        return userDAO.findAll();
    }

}