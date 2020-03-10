package com.github.mhzhou95.MingChatAppServer.repository;

import com.github.mhzhou95.MingChatAppServer.model.ChatRoom;
import com.github.mhzhou95.MingChatAppServer.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Collection<Message> findByChatRoomEquals(ChatRoom chatRoom);
}
