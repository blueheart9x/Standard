package com.elcom.sharedbiz.thread;

import java.util.List;
import org.apache.log4j.Logger;
import com.elcom.model.constant.InterviewConstant;
import com.elcom.sharedbiz.mail.SendMail;
import com.elcom.sharedbiz.mail.ToMail;
import com.elcom.sharedbiz.manager.BaseManager;

/**
 * Send mail
 *
 * @author anhdv
 */
public class MailThread extends BaseManager implements Runnable {

    private List<ToMail> listToMail;
    private String title;
    private String content;
    private static final Logger logger = Logger.getLogger(MailThread.class.getName());

    public MailThread(List<ToMail> listToMail, String title, String content) {
        this.listToMail = listToMail;
        this.title = title;
        this.content = content;
    }

    public void sendMail() {
        new SendMail().sendMail(InterviewConstant.MAIL_HOST,
                 InterviewConstant.MAIL_PORT,
                 InterviewConstant.MAIL_SEND_ACC,
                 InterviewConstant.MAIL_SEND_PW, "",
                 this.title, this.content, this.listToMail);
    }

    @Override
    public void run() {
        try {
            synchronized (MailThread.class) {
                sendMail();
            }
        } catch (Exception ex) {
            logger.error("MailThread.run().ex: " + ex.toString());
            Thread.currentThread().interrupt();
        }
    }

    public List<ToMail> getListToMail() {
        return listToMail;
    }

    public void setListToMail(List<ToMail> listToMail) {
        this.listToMail = listToMail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
