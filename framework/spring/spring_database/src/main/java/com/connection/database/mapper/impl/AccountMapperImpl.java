package com.connection.database.mapper.impl;

import com.connection.database.dto.AccountDTO;
import com.connection.database.entity.jpa.Account;
import com.connection.database.mapper.AccountMapper;

public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountDTO toDto(Account account) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setEmail(account.getEmail());
        return accountDTO;
    }
}
