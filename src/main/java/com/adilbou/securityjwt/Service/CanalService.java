package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.Entities.Message;
import com.adilbou.securityjwt.Entities.User;
import com.adilbou.securityjwt.Enumration.TYPE_MSG;

import java.util.List;

public interface CanalService {

    public Message sendMessage(Message message);
    public List<Message> getMssgByDepart(Integer idSender);

}
