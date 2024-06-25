package com.example.rental.utils;

import com.example.rental.service.IFinanceService;
import com.example.rental.service.IMailService;
import com.example.rental.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class SendMailUtil {

    @Autowired
    private IFinanceService financeService;

    @Autowired
    private IMailService mailService;

    @Value("${spring.mail.username}")
    private String from;

    //@Scheduled(cron = "0 0 0 * * ?")//每日0点发送
    //@Scheduled(cron = "*/10 * * * * ?")  //每10秒发送
    public void sendMail() {
        StringBuffer content = new StringBuffer();
        content.append("<p>今日收入！</p>")
                .append("租金收入：")
                .append(financeService.sumRentPayMonth().getCountRentActual())
                .append("<p>押金收入！</p>")
                .append(financeService.sumDepositMonth());
        MailVo mailVo = new MailVo();
        mailVo.setFrom(from);
        mailVo.setTo("lingdelv@outlook.com");
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        mailVo.setSubject(date + "收入");
        mailVo.setContent(content.toString());
        mailService.sendMail(mailVo);
    }

}
