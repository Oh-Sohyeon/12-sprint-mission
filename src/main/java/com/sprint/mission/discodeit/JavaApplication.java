package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

public class JavaApplication {
    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();
        JCFChannelService channelService = new JCFChannelService();
        JCFMessageService messageService = new JCFMessageService(userService, channelService);

        User user1 = userService.create(new User("hong", "1234", "hong@gmail.com"));
        User user2 = userService.create(new User("park", "1234", "park@gmail.com"));
        User user3 = userService.create(new User("kim", "1234", "kim@gmail.com"));
        Channel channel1 = channelService.create(new Channel("general", "기본 채널"));
        Channel channel2 = channelService.create(new Channel("game", "게임 채널"));

        Message message1 = messageService.create(
                new Message("안녕하세요", user1.getId(), channel1.getId())
        );
        Message message2 = messageService.create(
                new Message("반갑습니다", user2.getId(), channel2.getId())
        );
        System.out.println("==== 등록 완료 ====");
        System.out.println("User1 등록 완료 : " + user1.getUsername());
        System.out.println("User2 등록 완료 : " + user2.getUsername());
        System.out.println("Channel1 등록 완료 : " + channel1.getName());
        System.out.println("Channel2 등록 완료 : " + channel2.getName());
        System.out.println("Message1 등록 완료 : " + message1.getContent());
        System.out.println("Message2 등록 완료 : " + message2.getContent());

        System.out.println("\n==== 단건 조회 ====");
        System.out.println("== User 단건 조회 ==");
        userService.findById(user1.getId()).ifPresent(user ->
                System.out.println(user)
        );
        System.out.println("== Channel 단건 조회 ==");
        channelService.findById(channel1.getId()).ifPresent(channel ->
                System.out.println(channel)
        );
        System.out.println("== Message 단건 조회 ==");
        messageService.findById(message1.getId()).ifPresent(message ->
                System.out.println(message)
        );

        System.out.println("\n==== 다건 조회 ====");
        System.out.println("== User 전체 조회 ==");
        for (User user : userService.findAll()) {
            System.out.println(user);
        }
        System.out.println("== Channel 전체 조회 ==");
        for (Channel channel : channelService.findAll()) {
            System.out.println(channel);
        }
        System.out.println("== Message 전체 조회 ==");
        for (Message message : messageService.findAll()) {
            System.out.println(message);
        }

        System.out.println("\n==== 수정 ====");
        userService.update(user1.getId(), "hongman", "1212", "hongman@gmail.com");
        channelService.update(channel1.getId(), "communication", "소통 채널");
        messageService.update(message1.getId(), "수정된 메시지입니다.");

        System.out.println("== 수정 완료 ==");

        System.out.println("\n==== 수정된 데이터 조회 ====");
        System.out.println("== User 전체 조회 ==");
        for (User user : userService.findAll()) {
            System.out.println(user);
        }
        System.out.println("== Channel 전체 조회 ==");
        for (Channel channel : channelService.findAll()) {
            System.out.println(channel);
        }
        System.out.println("== Message 전체 조회 ==");
        for (Message message : messageService.findAll()) {
            System.out.println(message);
        }

        System.out.println("\n==== 삭제 ====");
        userService.delete(user3.getId());
        channelService.delete(channel2.getId());
        messageService.delete(message2.getId());
        System.out.println("== 삭제 완료 ==");

        System.out.println("\n==== 삭제 확인 ====");
        System.out.println("user3 존재 여부 : " + userService.findById(user3.getId()).isPresent());
        System.out.println("channel2 존재 여부 : " + channelService.findById(channel2.getId()).isPresent());
        System.out.println("message2 존재 여부 : " + messageService.findById(message2.getId()).isPresent());

        System.out.println("\n==== 잘못된 Message 생성 테스트====");
        try {
            Message wrongMessage = new Message(
                    "잘못된 메시지",
                    java.util.UUID.randomUUID(),
                    channel1.getId()
            );
            messageService.create(wrongMessage);
        } catch (IllegalArgumentException e) {
            System.out.println("메시지 생성 실패: " + e.getMessage());
        }
    }
}
