package edu.mum.cs.cs425.flightreservation1.repositories;


import edu.mum.cs.cs425.flightreservation1.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
