package com.wedul.wedul_timeline.core.type;

/**
 * TECH, JOB 회사 정보 분류
 *
 * TODO TIMELINE_SITE 테이블에 LOGO_URL 추가하면 아래 ENUM 필요없음
 */
public enum CompanyType {

    VCNC("https://image.rocketpunch.com/company/30/vcnc_logo_1559872041.jpg?s=400x400&t=inside"),

    WOOWAHAN("https://www.woowahan.com/img/pc/common-logo.png"),

    ZUM_INTERNET("http://lego.zumst.com/resources/current/images/img_logo_2x_20190604.png"),

    KAKAO("https://careers.kakao.com/images/logo/logo_dk.png");

    private String logoUrl;

    CompanyType(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

}