package com.btg.transfer.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.btg.transfer.exception.AccountNotFoundException;
import com.btg.transfer.model.Account;
import com.btg.transfer.repository.IAccountRepository;
import com.btg.transfer.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	
	@Autowired
	private IAccountRepository accountRepo;
	
	@Autowired
	private AccountService accountService;	

	@Test(expected = AccountNotFoundException.class)
	public void findNotExistentAccount() {		
		Account account = new Account(1, 430.00);		
		accountRepo.save(account);
		accountService.findByAccountId(988);			
	}
	
	@Test
	public void findExistentAccount() {		
		Account account = new Account(1, 430.00);		
		accountRepo.save(account);
		assert(accountService.findByAccountId(account.getId()) != null);
	}
	
	@Test
	public void updateAccountAmount() {
		Account account = new Account(1, 430.00);		
		Account savedAccount = accountRepo.save(account);
		double newAmount = 0.50;
		savedAccount.setAmount(newAmount);
		accountService.updateAccountAmount(savedAccount);		
		assert(accountService.findByAccountId(savedAccount.getId()).getAmount() == newAmount);
	}

}
