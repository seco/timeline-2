-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS wedul DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE IF NOT EXISTS `wedul`.`wedul_timeline` (
  wedul_timeline_id int not null auto_increment primary key,
  sitye_type tinyint not null comment '사이트 타입',
  site_name char(20) not null comment '사이트 명',
  site_url char(255) not null comment '사이트 명',
  image_url char(255) not null comment '이미지 url',
  content char(255) not null comment '내용',
  update_at int not null comment '업데이트 시간',
  create_at int not null comment '생성 시간',
  KEY `wedul_timeline_sitye_type_IDX` (`sitye_type`,`site_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;