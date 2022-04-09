package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.digitalone.houzingapp.entity.auth.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
