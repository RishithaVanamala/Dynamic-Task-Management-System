package com.webapp7.trelloclone.Repository;

import com.webapp7.trelloclone.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT u.name FROM user u WHERE u.suid = :suid AND EXISTS (SELECT suid FROM user WHERE suid = :suid)", nativeQuery = true)
    public String fetchUsernameByUserId(@Param("suid") Long suid);
}
