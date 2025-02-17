package com.rescureSync.ResucueSync.repository;

import com.rescureSync.ResucueSync.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByVerificationToken(String confirmationToken);
}

