package com.easyfootballmanagement.features.users;

import com.easyfootballmanagement.application.common.interfaces.IRequest;
import com.easyfootballmanagement.domain.entities.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetByIdUserQuery implements IRequest<Users> {
    private long id;

    public GetByIdUserQuery() {
    }

    public GetByIdUserQuery(long id) {
        this.id = id;
    }
}
