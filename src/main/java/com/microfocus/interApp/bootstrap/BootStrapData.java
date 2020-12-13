package com.microfocus.interApp.bootstrap;

import com.microfocus.interApp.domain.MailAccount;
import com.microfocus.interApp.domain.User;
import com.microfocus.interApp.repositories.MailAccountRepository;
import com.microfocus.interApp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final MailAccountRepository mailAccountRepository;
    private final UserRepository userRepository;

    public BootStrapData(UserRepository userRepository, MailAccountRepository mailAccountRepository) {
        this.mailAccountRepository = mailAccountRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        User bill = new User("Bill Johnson");
        User tom = new User("Tom Smith");
        MailAccount account1 = new MailAccount("johnson@mail.com");
        MailAccount account2 = new MailAccount("dudeXoXo@gmail.com");
        MailAccount account3 = new MailAccount("smith@mail.com");
        bill.getMailAccounts().add(account1);
        bill.getMailAccounts().add(account2);
        tom.getMailAccounts().add(account3);
        account1.setUser(bill);
        account2.setUser(bill);
        account3.setUser(tom);

        userRepository.save(bill);
        userRepository.save(tom);
        mailAccountRepository.save(account1);
        mailAccountRepository.save(account2);
        mailAccountRepository.save(account3);

    }
}