package com.ashy.kopring.features.member.commands

import an.awesome.pipelinr.Command
import com.ashy.kopring.infrastructure.response.ResponseMessage

data class CreateMember(val name: String, val age: Int) : Command<ResponseMessage<String>>
