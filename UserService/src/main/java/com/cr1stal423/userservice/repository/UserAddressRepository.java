package com.cr1stal423.userservice.repository;

import com.cr1stal423.userservice.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {
}
