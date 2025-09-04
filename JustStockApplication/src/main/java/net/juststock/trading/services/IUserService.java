package net.juststock.trading.services;

import java.util.List;
import java.util.Optional;

import net.juststock.trading.domain.User;


public interface IUserService {

	public Optional<User> addNewUser(User user);

	public Optional<List<User>> viewAllUsers();

	public Optional<User> contactNumberWiseUser(String contact);

	public boolean checkContactNumber(Long contact);

}
