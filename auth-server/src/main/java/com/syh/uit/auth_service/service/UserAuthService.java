package com.syh.uit.auth_service.service;

import com.syh.uit.auth_service.dao.AccountDAO;
import com.syh.uit.auth_service.model.Account;
import com.syh.uit.auth_service.model.response.LoginSuccessResponse;
import com.syh.uit.auth_service.utils.JwtGenerator;
import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    private AccountDAO accountDAO;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public LoginSuccessResponse login(int uid, String rawPassword) throws APIGeneralException{
        Account stored = accountDAO.getAccountByID(uid);
        if (passwordEncoder.matches(rawPassword,stored.getEncodedPassword())){
            return new LoginSuccessResponse(JwtGenerator.createToken(uid), 1);
        }else{
            throw new BadRequestException("wrong uid or password");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
