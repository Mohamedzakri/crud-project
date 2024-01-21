package it.mohamed.crudproject.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LogMessage {
    INVOKE_CONTROLLER_REGISTRY_METHOD("Register controller invoked"),
    INVOKE_SERVICE_REGISTRY_METHOD("Register service invoked"),
    CREATING_USER("Creating new user"),
    SAVING_NEW_USER("Saving new user"),
    SAVING_JWT_TOKEN("Saving jwt token"),
    SAVING_REFRESH_TOKEN("Saving refresh token"),
    SAVING_USER_TOKEN("Saving user token"),
    NEW_USER_CREATED("New user created"),
    NEW_USER_SAVED("New user saved"),
    NEW_JWT_TOKEN_SAVED("Jwt token saved"),
    NEW_REFRESH_TOKEN_SAVED("Refresh token saved"),
    NEW_USER_TOKEN_SAVED("User token saved"),
    EXIT_REGISTER_SERVICE_METHOD("New user registered"),
    REGISTER_REQUEST_BODY_IS_NULL("Empty register request body"),
    REGISTER_REQUEST_BODY_IS_INVALID("Invalid register request body"),
    FAILED_TO_REGISTER_USER("Failed to register user"),
    INVOKE_SERVICE_AUTHENTICATE_METHOD("Authenticate service for users invoked"),
    SELECTING_USER_BY_EMAIL("Selecting user by email"),
    SELECTED_USER_BY_EMAIL("User found by email"),
    REVOKING_ALL_USER_TOKENS("Revoking all user tokens"),
    ALL_USER_TOKENS_REVOKED("All user tokens revoked"),
    INVOKE_AUTHENTICATE_REGISTRY_METHOD("Authenticate register method invoked"),
    INVOKE_CONTROLLER_REFRESH_TOKEN_METHOD("Refresh token controller invoked"),
    INVOKE_CONTROLLER_SELECT_USER_BY_PARAM_METHOD("Select controller user by param method invoked"),
    INVOKE_CONTROLLER_SELECT_USER_BY_EMAIL_METHOD("Select Controller select user by email method invoked"),
    INVOKE_SERVICE_SELECT_TASK_BY_PRIORITY_METHOD("Select Service select task by priority method invoked"),
    EXIT_SERVICE_SELECT_TASKS_BY_PRIORITY_METHOD("Exit Service method select task by priority"),
    INVOKE_SERVICE_SELECT_DONE_TASKS_BY_USER_METHOD("Invoke Service method select done tasks by user"),
    EXIT_SERVICE_SELECT_DONE_TASKS_BY_USER_METHOD("Exit Service method select done tasks by user");
    private final String description;

    @Override
    public String toString() {
        return description;
    }
}
