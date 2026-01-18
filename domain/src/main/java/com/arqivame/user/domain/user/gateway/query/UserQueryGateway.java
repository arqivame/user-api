package com.arqivame.user.domain.user.gateway.query;

import java.util.Optional;

import com.arqivame.user.domain.user.User;
import com.arqivame.user.domain.user.UserID;

public interface UserQueryGateway {

    Optional<User> findById(UserID id);

}
