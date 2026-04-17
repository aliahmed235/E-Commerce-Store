package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}