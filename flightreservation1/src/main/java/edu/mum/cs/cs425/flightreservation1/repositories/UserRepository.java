package edu.mum.cs.cs425.flightreservation1.repositories;


import edu.mum.cs.cs425.flightreservation1.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
