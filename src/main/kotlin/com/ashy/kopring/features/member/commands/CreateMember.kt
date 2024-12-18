package com.ashy.kopring.features.member.commands

import com.ashy.kopring.infrastructure.platform.BaseIdentityCommand
import com.ashy.kopring.infrastructure.response.ResponseMessage

class CreateMember(val name: String, val age: Int) : BaseIdentityCommand<ResponseMessage<String>>()
