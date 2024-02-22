package org.personal.projectjot;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.personal.projectjot.models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomUserRepository customUserRepository;

    @PostMapping("/new/user")
    public ResponseEntity<?> createNewUser(@RequestBody CustomUser user) {
        Optional<CustomUser> customUserOptional = customUserRepository.findCustomUserByUsername(user.getUsername());

        customUserOptional.orElseThrow(() -> new UsernameNotFoundException("Username already exists."));

        return ResponseEntity.status(HttpStatus.CREATED).;
    }

}
