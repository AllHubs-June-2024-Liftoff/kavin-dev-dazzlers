package org.launchcode.moviedock.data;

import org.launchcode.moviedock.models.Review;
import org.launchcode.moviedock.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);


    @Query(value ="SELECT * FROM user WHERE username LIKE %:username%",nativeQuery = true)
    List<User> findByUsernameLike(String username);

}
