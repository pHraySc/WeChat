package com.sccc.service;

import java.sql.SQLException;
import java.util.List;

import com.sccc.bean.Message;
import com.sccc.dao.MessageDao;

/**
 * 
 * @author Sccc
 *列表相关的功能
 *
 */
public class MessageService {
	public List<Message> queryMessageList(String command, String description) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
}
