package com.microfocus.interApp.repositories;

import com.microfocus.interApp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface UserRepository extends CrudRepository<User, Long> {

   default Stream<User> findByName(String name) {
       Iterable<User> users = this.findAll();
       return StreamSupport.stream(users.spliterator(), false).filter((user -> Objects.equals(user.getName(),name)));
   }

}
