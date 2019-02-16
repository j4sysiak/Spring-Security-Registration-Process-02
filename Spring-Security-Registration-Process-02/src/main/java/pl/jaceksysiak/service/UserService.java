package pl.jaceksysiak.service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.jaceksysiak.dao.RoleRepository;
import pl.jaceksysiak.dao.UserRepository;
import pl.jaceksysiak.model.User;
import pl.jaceksysiak.web.dto.UserDto;
import pl.jaceksysiak.web.error.UserAlreadyExistException;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();

        //user.setFirstName(accountDto.getFirstName());
        //user.setLastName(accountDto.getLastName());
        //user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        //user.setUsing2FA(accountDto.isUsing2FA());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }


    
    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
    
    private boolean emailExist(final String email) {
        return userRepository.findByEmail(email) != null;
    }



	@Override
	public User getUser(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void saveRegisteredUser(User user) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void createVerificationTokenForUser(User user, String token) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User getUserByPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Optional<User> getUserByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void changeUserPassword(User user, String password) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean checkIfValidOldPassword(User user, String password) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String generateQRUrl(User user) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User updateUser2FA(boolean use2fa) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<String> getUsersFromSessionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}
  
}
