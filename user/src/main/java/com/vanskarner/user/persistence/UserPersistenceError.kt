package com.vanskarner.user.persistence

/**
 * Persistence layer errors
 */
sealed class UserPersistenceError : RuntimeException() {

    /**
     * This object occurs when trying to access an element that is not found in data persistence.
     */
    object NotFound : UserPersistenceError()

}