package com.DanielSilva.salesmanagement.security.domain.exceptions.user;

import org.springframework.dao.DataIntegrityViolationException;

public class UserInsertException extends DataIntegrityViolationException {
    public UserInsertException(String message) {super(message);}
    public UserInsertException(String msg, Throwable cause) {super(msg, cause);}
}
