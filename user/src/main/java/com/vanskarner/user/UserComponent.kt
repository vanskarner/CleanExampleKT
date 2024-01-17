package com.vanskarner.user

import com.vanskarner.user.businesslogic.UserData
import com.vanskarner.user.businesslogic.UserBusinessLogicError
import com.vanskarner.user.persistence.UserPersistenceError

/**
 * Component that contains operations related to user management, the provided methods encapsulate
 * the results in a [Result] type that indicates whether the operation was successful or if an error
 * occurred.
 */
interface UserComponent {

    /**
     * Saves the entered user in memory.
     * @param userData user to record.
     * @return [Result], encapsulates success or failure.
     * - [Result.onSuccess], user recorded successfully
     * - [Result.onFailure], one of the following errors occurred:
     * [UserBusinessLogicError.Invalidations]
     */
    suspend fun save(userData: UserData): Result<Unit>

    /**
     * Gets the in-memory list of recorded users. If there are no registered users, an empty list
     * will be returned.
     * @return [Result], encapsulates success or failure.
     * - [Result.onSuccess], returns the list of users
     * - [Result.onFailure], does not generate any type of error
     */
    suspend fun getList(): Result<List<UserData>>

    /**
     * Find the user by user ID.
     * @param userId user identifier
     * @return [Result], encapsulates success or failure.
     * - [Result.onSuccess], returns the searched [UserData]
     * - [Result.onFailure], one of the following errors occurred:
     * [UserPersistenceError.NotFound]
     */
    suspend fun find(userId: Int): Result<UserData>

}