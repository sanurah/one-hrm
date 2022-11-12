package com.sanurah.app.contoller;

import com.sanurah.app.event.SendPasswordResetEvent;
import com.sanurah.app.event.SendVerificationTokenEvent;
import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.model.UserModel;
import com.sanurah.app.service.UserService;
import java.net.URI;
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

    UserService userService;
    ApplicationEventPublisher publisher;

    @Autowired
    public UserController(UserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user, final HttpServletRequest request) {
        UserModel registeredUser = userService.createUser(user);
        URI uri = getApplicationUri(request);
        publisher.publishEvent(new SendVerificationTokenEvent(registeredUser, uri));
        return ResponseEntity.created(uri).body(registeredUser);
    }

    @GetMapping("/verification/verify")
    public ResponseEntity<UserModel> verifyUser(@RequestParam UUID token) throws OneBusinessException {
        UserModel user = userService.verifyUser(token);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/verification/resend")
    public ResponseEntity resendUserVerificationToken(@RequestParam UUID token, final HttpServletRequest request)
            throws OneBusinessException {
        UserModel userModel = userService.resendVerificationToken(token);
        publisher.publishEvent(new SendVerificationTokenEvent(userModel, getApplicationUri(request)));
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/all")
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUsersById(@PathVariable Long id) throws OneBusinessException {
        UserModel user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/password/update")
    public ResponseEntity updatePassword(@RequestBody UserModel user) throws OneBusinessException {
        userService.updatePassword(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password/reset")
    public ResponseEntity resetPassword(@RequestBody UserModel user, final HttpServletRequest request) {
        publisher.publishEvent(new SendPasswordResetEvent(user, getApplicationUri(request)));
        return ResponseEntity.ok().build();
    }

    private URI getApplicationUri(HttpServletRequest request) {
        String uri = String.format("http://%s:%s%s", request.getServerName(), request.getServerPort(),
                request.getContextPath());
        return URI.create(uri);
    }
}
