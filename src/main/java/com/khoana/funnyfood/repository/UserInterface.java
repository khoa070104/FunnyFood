package com.khoana.funnyfood.repository;


import com.khoana.funnyfood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);

}
