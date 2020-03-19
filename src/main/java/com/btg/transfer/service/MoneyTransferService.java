package com.btg.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.transfer.exception.InsufficientAmountException;
import com.btg.transfer.model.Account;

@Service
public class MoneyTransferService {

	@Autowired
	private AccountService accountService;

	/**
	 * @param originAccountId
	 * @param destinyAccountId
	 * @param amount
	 * @return true or InsufficientAmountException
	 */
	public boolean transfer(int originAccountId, int destinyAccountId, double amount) {

		Account originAccount = accountService.findByAccountId(originAccountId);

		if (originAccount.getAmount() >= amount) {

			Account destinyAccount = accountService.findByAccountId(destinyAccountId);

			originAccount.setAmount(originAccount.getAmount() - amount);

			accountService.updateAccountAmount(originAccount);

			destinyAccount.setAmount(destinyAccount.getAmount() + amount);

			accountService.updateAccountAmount(originAccount);
			accountService.updateAccountAmount(destinyAccount);

			return true;

		} else
			throw new InsufficientAmountException(
					"Saldo insuficiente: disponível -> " + originAccount.getAmount() + " requisitado -> " + amount);
	}
}
