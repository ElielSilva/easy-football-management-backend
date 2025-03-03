package com.easyfootballmanagement.application.common.interfaces;

import org.springframework.data.crossstore.ChangeSetPersister;

public interface IRequestHandler <TRequest extends IRequest<TResponse>, TResponse> {
    TResponse handle(TRequest request) throws ChangeSetPersister.NotFoundException;
}
