package com.moli.mail.infra.controller;

import com.moli.mail.common.ReturnData;
import com.moli.mail.infra.config.MailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author moli
 * @time 2024-07-26 17:43:02
 */
@Slf4j
@RestController
@RequestMapping("mail")
public class MailController {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private MailProperties mailProperties;

    @Resource
    private TemplateEngine templateEngine;

    /**
     * 发送简单邮件
     */
    @PostMapping("sendSimpleMail")
    public ReturnData<?> sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        String prefix = "wangquanfeng"; // 设置别名
        message.setFrom(prefix + "<" + mailProperties.getUsername() + ">");
        message.setTo(mailProperties.getUsername());
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
        return ReturnData.ok();
    }

    /**
     * 发送附件邮件
     */
    @PostMapping("sendMimeMail")
    public ReturnData<?> sendMimeMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(mailProperties.getUsername());
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        File file = new File("D:\\JAVA入门\\mail_demo\\infrastructure\\src\\main\\resources\\run-server.bat");

        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment("附件-1.jpg", fileSystemResource);
        helper.addAttachment("附件-2.jpg", fileSystemResource);

        mailSender.send(mimeMessage);
        return ReturnData.ok();
    }

    /**
     * 发送附件邮件
     */
    @PostMapping("sendMimeMailWithStatic")
    public ReturnData<?> sendMimeMailWithStatic() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(mailProperties.getUsername());
        helper.setSubject("主题：携带静态资源");

        helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);
        ClassPathResource file = new ClassPathResource("13200155.png");
        helper.addInline("weixin", file);

        mailSender.send(mimeMessage);
        return ReturnData.ok();
    }

    /**
     * 发送模版邮件
     */
    @PostMapping("sendTemplateMail")
    public ReturnData<?> sendTemplateMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(mailProperties.getUsername());
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashMap<>();
        model.put("username", "wangquanfeng");
        String text = templateEngine.process("template", new Context(null, model));
        helper.setText(text, true);

        mailSender.send(mimeMessage);
        return ReturnData.ok();
    }
}
