package com.sanurah.one.contoller;

import com.sanurah.one.entity.VerificationToken;
import com.sanurah.one.event.RegistrationCompletedEvent;
import com.sanurah.one.exception.EntityNotFoundException;
import com.sanurah.one.exception.VerificationTokenExpiredException;
import com.sanurah.one.model.UserModel;
import com.sanurah.one.service.UserService;
import com.sanurah.one.service.VerificationTokenService;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel user, final HttpServletRequest request) {
        UserModel registeredUser = userService.registerUser(user);
        publisher.publishEvent(new RegistrationCompletedEvent(registeredUser, getApplicationUrl(request)));
        return ResponseEntity.ok().body(registeredUser);
    }

    @GetMapping("/verify")
    public ResponseEntity<UserModel> verifyUser(@RequestParam UUID token)
            throws EntityNotFoundException, VerificationTokenExpiredException {
        UserModel user = userService.verifyUser(token);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/refresh")
    public ResponseEntity<VerificationToken> refreshUserVerificationToken(@RequestParam UUID token)
            throws EntityNotFoundException, VerificationTokenExpiredException {
        VerificationToken verificationToken = verificationTokenService.refreshVerificationToken(token);
        return ResponseEntity.ok().body(verificationToken);
    }

    @GetMapping
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUsersById(@PathVariable Long id) throws EntityNotFoundException {
        return userService.getUserById(id);
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return String.format("http://%s:%s%s", request.getServerName(), request.getServerPort(),
                request.getContextPath());
    }
}
