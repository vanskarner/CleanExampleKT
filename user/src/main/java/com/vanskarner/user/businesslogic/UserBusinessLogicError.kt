package com.vanskarner.user.businesslogic

/**
 * Business logic layer errors
 */
sealed class UserBusinessLogicError : RuntimeException() {

    /**
     * Error related to invalidations during the recording process of a user.
     *
     * @property types List of types of invalidation's. It can contain a maximum of 2 elements:
     * -[TypeInvalidation.InvalidName] The username is invalid because it is empty
     * -[TypeInvalidation.InvalidAge] The user's age is invalid because it is less than 18
     *
     */
    @Suppress("unused")
    class Invalidations(val types: List<TypeInvalidation>) : UserBusinessLogicError()

}


sealed class TypeInvalidation {

    object InvalidName : TypeInvalidation()

    object InvalidAge : TypeInvalidation()

}