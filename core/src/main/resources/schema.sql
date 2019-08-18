-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS wedul DEFAULT CHARACTER SET utf8mb4;

-- 크롤링 사이트 리스트
CREATE TABLE IF NOT EXISTS `wedul`.`timeline_site` (
   site_id int not null auto_increment primary key,
   site_type char(20) not null comment '사이트 타입',
   site_name char(20) not null comment '사이트 이름',
   site_url text not null comment '크롤링 사이트 url',
   update_at LONG not null comment '업데이트 시간',
   create_at LONG not null comment '생성 시간',
   KEY `wedul_timeline_type_IDX` (`site_id`),
   UNIQUE KEY `unique_index` (`site_name`,`site_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 크롤링 된 아이템 리스트
CREATE TABLE IF NOT EXISTS `wedul`.`timeline_item` (
   item_id int not null auto_increment primary key,
   site_id int not null comment '사이트 타입',
   source_id char(64) not null comment '고유 아이디',
   landing_url text not null comment '랜딩 url',
   logo_url char(255) not null comment 'logo url',
   title char(255) not null comment '타이틀',
   content text not null comment '내용',
   published_at LONG not null comment '컨텐츠 작성 시간' default 0,
   update_at LONG not null comment '업데이트 시간',
   create_at LONG not null comment '생성 시간',
   KEY `wedul_timeline_item_IDX` (`site_id`),
   UNIQUE KEY `unique_timeline_item_index` (`source_id`),
   FOREIGN KEY (`site_id`) REFERENCES `timeline_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

