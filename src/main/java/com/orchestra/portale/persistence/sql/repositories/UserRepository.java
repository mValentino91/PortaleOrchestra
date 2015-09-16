package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
        
        User findByFbUser(String fbUser);
        
        User findByFbEmail(String fbEmail);
        
        User findByFbEmailOrFbUser(String fbEmail, String fbUser);

        User findByUsernameAndPassword(String username, String password);

        User findById(Long id);
}
