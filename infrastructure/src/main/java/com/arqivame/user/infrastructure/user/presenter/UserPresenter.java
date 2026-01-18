package com.arqivame.user.infrastructure.user.presenter;

import com.arqivame.user.domain.user.event.UserCreatedEvent;
import com.arqivame.user.infrastructure.event.presenter.EventPresenter;
import com.arqivame.user.infrastructure.user.message.UserCreatedMessage;

public interface UserPresenter {

    public static UserCreatedMessage present(final UserCreatedEvent event) {
        return new UserCreatedMessage(
                EventPresenter.present(event),
                new UserCreatedMessage.Data(event.getData().userId()));
    }

}
