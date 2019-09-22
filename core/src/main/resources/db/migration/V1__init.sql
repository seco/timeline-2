-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS wedul DEFAULT CHARACTER SET utf8mb4;

-- 크롤링 사이트 리스트
CREATE TABLE IF NOT EXISTS `wedul`.`timeline_site` (
    site_id int not null auto_increment primary key,
    site_type char(20) not null comment '사이트 타입',
    site_name char(20) not null comment '사이트 이름',
    site_url char(255) not null comment '크롤링 사이트 url',
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
    published_at LONG not null comment '컨텐츠 작성 시간',
    update_at LONG not null comment '업데이트 시간',
    create_at LONG not null comment '생성 시간',
    KEY `wedul_timeline_item_IDX` (`site_id`),
    UNIQUE KEY `unique_timeline_item_index` (`source_id`),
    FOREIGN KEY (`site_id`) REFERENCES `timeline_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- job
-- 우아한 형제들
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('JOB', 'WoowahanJob', 'https://www.woowahan.com/jobapi/jobs/list?searchword=w011600&cc=', 1560065629, 1560065629);
-- 카카오
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('JOB', 'KakaoJob', 'https://careers.kakao.com/jobs', 1560065629, 1560065629);
-- 당근마켓
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('JOB', 'DngnJob', 'https://www.notion.so/api/v3/getRecordValues', 1560065629, 1560065629);
-- line plus
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('JOB', 'LineJob', 'https://recruit.linepluscorp.com/lineplus/career/list?classId=148', 1560065629, 1560065629);
-- 마이리얼트립
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('JOB', 'MyRealTripJob', 'https://www.rocketpunch.com/companies/myrealtrip/jobs', 1560065629, 1560065629);
-- vcnc
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('JOB', 'VcncJob', 'https://www.rocketpunch.com/companies/vcnc/jobs', 1560065629, 1560065629);
-- coupang
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('JOB', 'CoupangJob', 'https://rocketyourcareer.kr.coupang.com/search-jobs/results?ActiveFacetID=62822&CurrentPage=1&RecordsPerPage=15&Distance=50&RadiusUnitType=0&Keywords=&Location=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD&Latitude=&Longitude=&ShowRadius=False&CustomFacetName=&FacetTerm=&FacetType=0&FacetFilters[0].ID=62822&FacetFilters[0].FacetType=1&FacetFilters[0].Count=10&FacetFilters[0].Display=Software+Development+Engineers&FacetFilters[0].IsApplied=true&FacetFilters[0].FieldName=&FacetFilters[1].ID=1835841&FacetFilters[1].FacetType=2&FacetFilters[1].Count=80&FacetFilters[1].Display=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD&FacetFilters[1].IsApplied=true&FacetFilters[1].FieldName=&SearchResultsModuleName=Search+Results&SearchFiltersModuleName=Search+Filters&SortCriteria=0&SortDirection=0&SearchType=6&CategoryFacetTerm=&CategoryFacetType=&LocationFacetTerm=&LocationFacetType=&KeywordType=&LocationType=&LocationPath=&OrganizationIds=24450&PostalCode=&fc=&fl=&fcf=&afc=&afl=&afcf=', 1560065629, 1560065629);

-- tech
-- 우아한 형제들
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('TECH', 'WoowahanTech', 'https://woowabros.github.io/feed.xml', 1563091845, 1563091845);
-- 줌 인터넷
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('TECH', 'ZumInternetTech', 'https://zuminternet.github.io/feed.xml', 1563092630, 1563092630);
-- VCNC
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('TECH', 'VcncTech', 'http://engineering.vcnc.co.kr/atom.xml', 1560065629, 1560065629);
-- Kakao
insert ignore into timeline_site(site_type, site_name, site_url, update_at, create_at) VALUES ('TECH', 'KakaoTech', 'http://tech.kakao.com/rss', 1560065629, 1560065629);

