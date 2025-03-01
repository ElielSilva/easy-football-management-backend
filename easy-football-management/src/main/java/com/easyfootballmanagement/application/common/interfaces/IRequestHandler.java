package com.easyfootballmanagement.application.common.interfaces;

public interface IRequestHandler <TRequest extends IRequest<TResponse>, TResponse> {
    TResponse handle(TRequest request);
}
