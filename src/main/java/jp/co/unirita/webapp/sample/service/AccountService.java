package jp.co.unirita.webapp.sample.service;

import jp.co.unirita.webapp.sample.domain.account.Account;
import jp.co.unirita.webapp.sample.domain.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);
        if(account == null) {
            throw new UsernameNotFoundException("username " + username + " not found.");
        }
        return account;
    }

    public boolean registerUser(String userName, String password, String passwordConfirm) {
        if(!password.equals(passwordConfirm)) {
            return false;
        }
        accountRepository.saveAndFlush(new Account(userName, passwordEncoder.encode(password)));
        return true;
    }
}
