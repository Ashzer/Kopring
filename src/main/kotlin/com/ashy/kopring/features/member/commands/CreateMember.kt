package com.ashy.kopring.features.member.commands

import an.awesome.pipelinr.Command

data class CreateMember(val name: String, val age: Int) : Command<String>
