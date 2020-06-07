package com.syh.uit.auth_service.Service;

import com.syh.uit.auth_service.dao.AccountDAO;
import com.syh.uit.auth_service.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BaseUserDetailService implements UserDetailsService  {

    private AccountDAO accountDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        Account info = accountDAO.getAccountByID(username);
        if (info==null) throw new UsernameNotFoundException("not found");
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(info.getId(), info.getPassword(),
                true, true, true, true, new ArrayList<>());
        return user;
    }
}
