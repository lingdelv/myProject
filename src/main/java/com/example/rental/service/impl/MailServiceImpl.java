package com.example.rental.service.impl;

import com.example.rental.service.IMailService;
import com.example.rental.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(MailVo mailVo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailVo.getFrom());
        message.setTo(mailVo.getTo());
        message.setSubject(mailVo.getSubject());
        message.setText(mailVo.getContent());
        message.setCc(mailVo.getFrom());
        javaMailSender.send(message);

    }
}
