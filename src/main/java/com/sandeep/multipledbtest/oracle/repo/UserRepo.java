package com.sandeep.multipledbtest.oracle.repo;

import com.sandeep.multipledbtest.oracle.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
