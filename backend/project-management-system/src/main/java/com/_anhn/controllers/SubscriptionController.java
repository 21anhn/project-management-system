package com._anhn.controllers;

import com._anhn.models.PlanType;
import com._anhn.models.Subscription;
import com._anhn.models.User;
import com._anhn.services.SubscriptionService;
import com._anhn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserSubscription(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserProfileByJwt(token);

        Subscription subscription = subscriptionService.getUsersSubscriptions(user.getId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PutMapping("/upgrade")
    public ResponseEntity<?> upgradeSubscription(@RequestHeader("Authorization") String token,
                                                 @RequestParam PlanType planType) throws Exception {
        User user = userService.findUserProfileByJwt(token);
        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
