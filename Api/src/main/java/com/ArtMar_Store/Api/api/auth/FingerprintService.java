package com.ArtMar_Store.Api.api.auth;

import com.ArtMar_Store.Api.domain.users.UserId;
import com.ArtMar_Store.Api.infrastructure.FingerprintRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class FingerprintService {

    private final FingerprintRepository fingerprintRepository;

    public FingerprintService(FingerprintRepository fingerprintRepository) {
        this.fingerprintRepository = fingerprintRepository;
    }

    private String generateFingerprintValue()
    {
        return UUID.randomUUID().toString();
    }

    public void newFingerprint(UserId userId) {
        if(fingerprintRepository.existsById(userId))
            fingerprintRepository.deleteAllByUserId(userId);
        fingerprintRepository.save(new Fingerprint(generateFingerprintValue(), userId));
    }

    public Fingerprint getFingerprint(UserId userId) {
        Optional<Fingerprint> fingerprintOptional = fingerprintRepository.findFingerprintByUserId(userId);
        if(fingerprintOptional.isEmpty()){
            newFingerprint(userId);
            return fingerprintRepository.findFingerprintByUserId(userId).orElseThrow();
        }
        else
            return fingerprintOptional.orElseThrow();
    }

}
