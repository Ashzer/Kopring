package com.ashy.kopring.infrastructure.platform

import an.awesome.pipelinr.Command

open class BaseIdentityCommand<T>(
    val memberId : Int,
    val nickname : String,
    val role : Collection<String>,
) : Command<T> {
    constructor() : this(0, "", emptyList())
}