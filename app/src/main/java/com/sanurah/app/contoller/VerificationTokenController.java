package com.sanurah.app.contoller;

import com.sanurah.app.event.SendVerificationTokenEvent;
import com.sanurah.app.exception.OneBusinessException;
import com.sanurah.app.model.UserModel;
import com.sanurah.app.service.UserService;
import com.sanurah.app.service.VerificationTokenService;
import java.net.URI;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
public class VerificationTokenController {

    private ApplicationEventPublisher publisher;
    private VerificationTokenService verificationTokenService;
    private UserService userService;

    @Autowired
    public VerificationTokenController(ApplicationEventPublisher publisher,
            VerificationTokenService verificationTokenService, UserService userService) {
        this.publisher = publisher;
        this.verificationTokenService = verificationTokenService;
        this.userService = userService;
    }

    @GetMapping("/verify")
    public ResponseEntity<UserModel> verifyUser(@RequestParam UUID token) throws OneBusinessException {
        UserModel userModel = verificationTokenService.deleteVerificationToken(token);
        UserModel user = userService.verifyUser(userModel);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/resend")
    public ResponseEntity resendUserVerificationToken(@RequestParam UUID token, final HttpServletRequest request)
            throws OneBusinessException {
        UserModel userModel = verificationTokenService.deleteVerificationToken(token);
        publisher.publishEvent(new SendVerificationTokenEvent(userModel, getApplicationUri(request)));
        return ResponseEntity.accepted().build();
    }

    private URI getApplicationUri(HttpServletRequest request) {
        String uri = String.format("http://%s:%s%s", request.getServerName(), request.getServerPort(),
                request.getContextPath());
        return URI.create(uri);
    }
}
