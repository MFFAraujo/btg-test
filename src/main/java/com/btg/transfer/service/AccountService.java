package com.btg.transfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.transfer.exception.AccountNotFoundException;
import com.btg.transfer.model.Account;
import com.btg.transfer.repository.IAccountRepository;

@Service
public class AccountService {

	@Autowired
	private IAccountRepository accountRepo;
	
	public Account findByAccountId(int id) {
		return findById(id);
	}
	
	public void updateAccountAmount(Account account) {
		Account savedAccount = findById(account.getId());
		savedAccount.setAmount(account.getAmount());
		accountRepo.save(savedAccount);
	}
	
	private Account findById(int id) {
		Optional<Account> optAccount = accountRepo.findById(id);
		if (!optAccount.isPresent())
			throw new AccountNotFoundException("Conta não encontrada");
		return optAccount.get();
	}
	
}
