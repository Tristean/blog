package com.blog.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.ChatMessage;
import com.blog.mapper.ChatMessageMapper;
import com.blog.mapper.base.IBaseMapper;
import com.blog.service.base.BaseServiceImp;
import com.blog.services.ChatMessageService;
@Service
public class ChatMessageServiceImp extends BaseServiceImp<ChatMessage> implements ChatMessageService {
	@Autowired
	private ChatMessageMapper chatMessageMapper;
	
	@Override
	public IBaseMapper<ChatMessage> getBaseMapper() {
		// TODO Auto-generated method stub
		return chatMessageMapper;
	}
}
