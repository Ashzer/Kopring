package com.ashy.kopring.features.member.commands

import com.ashy.kopring.infrastructure.platform.BaseIdentityCommand
import com.ashy.kopring.infrastructure.response.ResponseMessage

data class UpdateMember(val id : Int,val name: String, val age: Int) : BaseIdentityCommand<ResponseMessage<String>>()