package com.ranine.rls.repositories;

import com.ranine.rls.models._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<_User,Long> {
Optional<_User> findByUserName (String Username);
Optional<_User> findByEmail (String Email);

}
