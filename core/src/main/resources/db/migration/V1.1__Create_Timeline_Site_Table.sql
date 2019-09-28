-- 크롤링 사이트 리스트
CREATE TABLE IF NOT EXISTS `wedul`.`timeline_site` (
    site_id int not null auto_increment primary key,
    site_type char(20) not null comment '사이트 타입',
    site_name char(20) not null comment '사이트 이름',
    site_url text not null comment '크롤링 사이트 url',
    update_at LONG not null comment '업데이트 시간',
    create_at LONG not null comment '생성 시간',
    KEY `wedul_timeline_type_IDX` (`site_id`),
    UNIQUE KEY `unique_index` (`site_name`,`site_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;