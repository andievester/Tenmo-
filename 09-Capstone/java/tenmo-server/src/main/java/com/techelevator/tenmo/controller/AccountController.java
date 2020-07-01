package com.techelevator.tenmo.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Accounts;

@RequestMapping("/accounts")
@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

	private AccountsDAO accountDAO;
	private UserDAO userDAO;
	private JdbcTemplate jdbcTemplate;
	
	public AccountController(AccountsDAO accountDAO, UserSqlDAO userSqlDAO) {
		this.accountDAO = accountDAO;
		this.userDAO = userSqlDAO;
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
    public List<Accounts> list() {
        return accountDAO.list();
    }

	@RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) throws UsernameNotFoundException{	
		int userId = getCurrentUserId(principal);
		return accountDAO.getBalance(userId);
    }
	
	@RequestMapping(path = "/update", method = RequestMethod.GET)
    public void updateBalance(Accounts account, Principal principal) throws AccountNotFoundException{	
		BigDecimal balance = account.getBalance();
    }
	
	private int getCurrentUserId(Principal principal) {
		return userDAO.findByUsername(principal.getName()).getId();
	}

}
