package com.btg.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btg.transfer.model.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

}
