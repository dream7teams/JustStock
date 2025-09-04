package net.juststock.trading.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import net.juststock.trading.domain.User;
import net.juststock.trading.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Override
	public Optional<User> addNewUser(User user) {
		
		
		return Optional.empty();
	}

}
