package com.javabatista.ciclovida.repository;

import com.javabatista.ciclovida.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username= (:username)")
    User findByUsername(@Param("username") String username);

    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.email= (:email)")
    User findByEmail(@Param("email") String email);
}
