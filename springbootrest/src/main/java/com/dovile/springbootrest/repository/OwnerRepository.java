package com.dovile.springbootrest.repository;

import com.dovile.springbootrest.entities.Owner;
import com.dovile.springbootrest.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Query(name = "Owner.findBYName")
    Owner findBYName (@Param("owner") String owner);

}
