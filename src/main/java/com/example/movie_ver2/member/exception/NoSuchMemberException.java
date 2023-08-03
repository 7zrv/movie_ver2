package com.example.movie_ver2.member.exception;

public class NoSuchMemberException extends RuntimeException {
    public NoSuchMemberException(String message) {
        super(message);
    }
}
