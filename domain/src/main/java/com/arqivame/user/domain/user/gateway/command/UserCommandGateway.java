package com.arqivame.user.domain.user.gateway.command;

import com.arqivame.user.domain.user.PreUser;
import com.arqivame.user.domain.user.User;

public interface UserCommandGateway {

    User create(PreUser preUser);

}
