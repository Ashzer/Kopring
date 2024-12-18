package com.ashy.kopring.features.member.queries

import com.ashy.kopring.infrastructure.model.MemberDto
import com.ashy.kopring.infrastructure.platform.BaseIdentityCommand
import com.ashy.kopring.infrastructure.response.ResponseMessage

class GetMembers : BaseIdentityCommand<ResponseMessage<List<MemberDto>>>()