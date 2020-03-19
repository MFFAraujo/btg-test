package com.btg.transfer.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.btg.transfer.model.Account;
import com.btg.transfer.repository.IAccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {
	
	@Autowired
	private IAccountRepository accountRepo;	

	@Test
	public void createAccount() {		
		Account account = new Account(1, 430.00);		
		Account savedAccount = accountRepo.save(account);
		assert(savedAccount.getAmount() == account.getAmount());
		assert(savedAccount.getId() == account.getId());	
	}
	
	@Test
	public void findAccount() {		
		Account account = new Account(2, 600.00);		
		accountRepo.save(account);
		assert(accountRepo.findById(account.getId()).isPresent());	
	}
	
	@Test
	public void updateAccount() {		
		Account account = new Account(3, 900.00);		
		accountRepo.save(account);
		Account savedAccount = accountRepo.findById(account.getId()).get();
		savedAccount.setAmount(100.01);
		Account updatedAccount = accountRepo.save(savedAccount);
		assert(updatedAccount.getAmount() == savedAccount.getAmount());	
	}
	
	@Test
	public void deleteAccount() {	
		Account account = new Account(4, 1000.00);		
		accountRepo.save(account);
		accountRepo.deleteById(account.getId());		
		assert(!accountRepo.findById(account.getId()).isPresent());	
	}
}
