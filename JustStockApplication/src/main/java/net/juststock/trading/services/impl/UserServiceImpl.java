	package net.juststock.trading.services.impl;

import net.juststock.trading.domain.User;
import net.juststock.trading.repository.IUserRepository;
import net.juststock.trading.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Optional<User> addNewUser(User user) {
		var savedUser = userRepository.save(user);
		return Optional.ofNullable(savedUser);
	}

	@Override
	public Optional<List<User>> viewAllUsers() {
		List<User> savedUser = userRepository.findAll();
		return Optional.ofNullable(savedUser);
	}

	@Override
	public Optional<User> contactNumberWiseUser(String contact) {
		Long contacts = Long.parseLong(contact);
		User user = userRepository.findByContact(contacts);
		return Optional.ofNullable(user);
	}

	@Override
	public boolean checkContactNumber(Long contact) {

		User user = userRepository.findByContact(contact);
		if (user.getContact() == 0) {
			return true;
		}
		return false;
	}
}