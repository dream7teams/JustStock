package net.juststock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.juststock.trading.domain.User;


@Repository
public interface IUserRepository extends JpaRepository<User,Long>{
  public  User findByContact(long contact);
}
