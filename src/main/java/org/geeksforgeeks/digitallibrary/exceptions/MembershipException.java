package org.geeksforgeeks.digitallibrary.exceptions;

public class MembershipException extends RuntimeException {

    public MembershipException(long userId, boolean isExist) {
        super(String.format(
                "User with ID: %s %s an active membership.",
                userId,
                isExist ? "already has" : "does not have"
        ));

    }
}
