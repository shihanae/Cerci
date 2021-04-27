package com.giftery.Exception;

import org.springframework.security.core.AuthenticationException;

public class AccountDeletedException extends AuthenticationException
{
    public AccountDeletedException(String msg)
    {
        super(msg);
    }

    public AccountDeletedException(String msg, Throwable t)
    {
        super(msg, t);
    }
}
