package com.ashy.kopring.infrastructure.services.impl

import com.ashy.kopring.infrastructure.extensions.logger
import com.ashy.kopring.infrastructure.failures.EmailFailure.EmailSendFailure
import com.ashy.kopring.infrastructure.model.EmailDetailDto
import com.ashy.kopring.infrastructure.services.EmailService
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class EmailServiceImpl(
    val javaMailSender: JavaMailSender,
    @Value("\${spring.mail.username}") val sender: String,
    @Value("\${mail-sender-name}") val senderName: String
) : EmailService {

    override suspend fun sendEmail(emailDetailDto: EmailDetailDto): Result<Unit> = coroutineScope {
        val mimeMessage = javaMailSender.createMimeMessage()
        val senderName = emailDetailDto.senderName ?: senderName

        try {
            val helper = MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.toString())

            if (emailDetailDto.cc.isNotEmpty()) {
                helper.setCc(emailDetailDto.cc.toTypedArray())
            }
            helper.setFrom(sender, senderName)
            helper.setTo(emailDetailDto.recipients.toTypedArray())
            helper.setSubject(emailDetailDto.subject)
            helper.setText(emailDetailDto.body, true)
            
            logger().info("Sending email to ${emailDetailDto.recipients.joinToString()}")
            javaMailSender.send(mimeMessage)
            logger().info("Email sent to ${emailDetailDto.recipients.joinToString()}")

            Result.success(Unit)
        } catch (mailException: MailException) {
            Result.failure(EmailSendFailure("Failed to send email", mailException))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}