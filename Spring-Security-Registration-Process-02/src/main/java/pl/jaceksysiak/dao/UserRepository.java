package pl.jaceksysiak.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.jaceksysiak.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String email);

    @Override
    void delete(User user);
 

}
