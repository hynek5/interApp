package com.microfocus.interApp.services;

import com.microfocus.interApp.domain.MailAccount;
import com.microfocus.interApp.domain.User;
import com.microfocus.interApp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements DataService<User> {

    UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public String insertToDb(User candidate) {
        if (candidate.getName().isEmpty() || candidate.getMailAccount().isEmpty()) {
            return "Please fill up all required fields";
        }

        if (userRepository.findAllExistingEmails().contains(candidate.getMailAccount())) {
            return "Email address:" + candidate.getMailAccount() + " already registered, pick a different one.";
        }

        List<User> usersWithTheSameName = userRepository.findByName(candidate.getName()).collect(Collectors.toList());
        if (usersWithTheSameName.size() == 0) {
                //no such user exist add to the table
                candidate.setMailAccounts();
                userRepository.save(candidate);
                return "User has been added";
        }
        if (usersWithTheSameName.size() == 1) {
            //user with the same name exists so add his new email account
            if (usersWithTheSameName.get(0).getMailAccounts().add(new MailAccount(candidate.getMailAccount()))) {
                userRepository.save(usersWithTheSameName.get(0));
                return "User " + candidate.getName() + " has been updated";
            }
        }
        return "No change";
    }
}
