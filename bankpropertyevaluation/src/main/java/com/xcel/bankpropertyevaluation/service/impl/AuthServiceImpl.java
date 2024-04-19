package com.xcel.bankpropertyevaluation.service.impl;

import com.xcel.bankpropertyevaluation.repository.InitiatorRepository;
import com.xcel.bankpropertyevaluation.service.AuthService;
import com.xcel.bankpropertyevaluation.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private InitiatorRepository initiatorRepository;
    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public String login(String username, String password) {
        log.info("AuthService >> login");
        var user = initiatorRepository.findByInitiatorBusinessUnit(username);

        if (user != null && passwordUtil.checkPassword(password, user.getPassword())) {
            return generateToken();
        } else {
            return null;

        }

    }

    private String generateToken() {
        // Generate token logic
        return "sampleToken";
    }

}
