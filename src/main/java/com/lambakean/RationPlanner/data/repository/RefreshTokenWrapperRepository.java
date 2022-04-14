package com.lambakean.rationplanner.data.repository;

import com.lambakean.rationplanner.data.model.RefreshTokenWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenWrapperRepository extends JpaRepository<RefreshTokenWrapper, String> {

    Optional<RefreshTokenWrapper> findByToken(String token);

    boolean existsByToken(String token);
}
