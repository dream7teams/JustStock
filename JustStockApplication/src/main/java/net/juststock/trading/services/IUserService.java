package net.juststock.trading.services;

import java.util.Optional;

import net.juststock.trading.domain.User;


public interface IUserService {

	public Optional<User> addNewUser(User user);

}
