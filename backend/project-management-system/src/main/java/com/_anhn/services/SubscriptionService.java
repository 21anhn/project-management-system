package com._anhn.services;

import com._anhn.models.PlanType;
import com._anhn.models.Subscription;
import com._anhn.models.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUsersSubscriptions(Long userId) throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception;
    boolean isValid(Subscription subscription);
}
