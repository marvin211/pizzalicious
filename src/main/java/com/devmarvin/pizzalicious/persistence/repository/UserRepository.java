package com.devmarvin.pizzalicious.persistence.repository;

import com.devmarvin.pizzalicious.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
