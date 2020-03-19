package com.btg.transfer.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.btg.transfer.exception.InsufficientAmountException;
import com.btg.transfer.model.Account;
import com.btg.transfer.repository.IAccountRepository;
import com.btg.transfer.service.MoneyTransferService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferServiceTest {
	
	@Autowired 
	private MoneyTransferService transferService;
	
	@Autowired 
	private IAccountRepository accountRepo;
	
	@Test(expected = InsufficientAmountException.class)
	public void transferInsuffucientAmount() {		
		Account origin = new Account(1, 430.00);
		Account destiny = new Account(2, 430.00);		
		accountRepo.save(origin);
		accountRepo.save(destiny);		
		double amount = 450.01;		
		transferService.transfer(origin.getId(), destiny.getId(), amount);
	}
	
	@Test
	public void validateTransfer() {		
		Account origin = new Account(1, 100.00);
		Account destiny = new Account(2, 200.00);		
		accountRepo.save(origin);
		accountRepo.save(destiny);
		
		assert(transferService.transfer(origin.getId(), destiny.getId(), 50.00));
		assert(transferService.transfer(origin.getId(), destiny.getId(), 30.00));
		//result after money transfered
		assert(accountRepo.findById(origin.getId()).get().getAmount() == 20.00);
		assert(accountRepo.findById(destiny.getId()).get().getAmount() == 280.00);		
	}

	@Test
	public void validateIntegrityIfAccountNotFound() {		
		Account origin = new Account(1, 100.00);
		Account destiny = new Account(2, 200.00);		
		accountRepo.save(origin);
		accountRepo.save(destiny);				
		assert(transferService.transfer(origin.getId(), destiny.getId(), 50.00));
		assert(transferService.transfer(origin.getId(), destiny.getId(), 30.00));
		assert(accountRepo.findById(origin.getId()).get().getAmount() == 20.00);
		assert(accountRepo.findById(destiny.getId()).get().getAmount() == 280.00);		
	}
}
