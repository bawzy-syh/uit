package com.syh.uit.auth_service.dao;

import com.syh.uit.auth_service.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO {
    Account getAccountByID(String id);
}
