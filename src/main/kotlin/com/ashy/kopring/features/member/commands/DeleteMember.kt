package com.ashy.kopring.features.member.commands

import com.ashy.kopring.infrastructure.platform.BaseIdentityCommand
import com.ashy.kopring.infrastructure.response.ResponseMessage

data class DeleteMember(val id: Int) : BaseIdentityCommand<ResponseMessage<String>>()