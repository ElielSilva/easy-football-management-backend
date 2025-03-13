package com.easyfootballmanagement.application.mediator;

import com.easyfootballmanagement.application.common.interfaces.IRequest;
import com.easyfootballmanagement.application.common.interfaces.IRequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Mediator implements IMediator {

    private final ApplicationContext context;

    public Mediator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <T> T send(IRequest<T> request) {
        IRequestHandler<IRequest<T>, T> handler = (IRequestHandler<IRequest<T>, T>) context.getBean(request.getClass().getSimpleName() + "Handler");
        return handler.handle(request);
    }
}
