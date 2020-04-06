package com.spring.boot.building.blocks.SpringBoot01.Repository;

import com.spring.boot.building.blocks.SpringBoot01.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

public User findByUsername(String username);
}
