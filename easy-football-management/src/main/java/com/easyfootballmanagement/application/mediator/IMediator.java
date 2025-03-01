package com.easyfootballmanagement.application.mediator;

import com.easyfootballmanagement.application.common.interfaces.IRequest;

public interface IMediator {
    <T> T send(IRequest<T> request);
}
