package ru.vdsimako.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vdsimako.demo.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
