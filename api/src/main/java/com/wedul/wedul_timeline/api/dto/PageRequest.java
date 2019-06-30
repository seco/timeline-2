package com.wedul.wedul_timeline.api.dto;

import lombok.Setter;
import org.springframework.data.domain.Sort;

/**
 * wedul_timeline
 *
 * @author wedul
 * @since 2019-06-30
 **/
@Setter
public final class PageRequest {

    private int page;
    private int size;
    private Sort sort;
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 50;

    public int getPage() {
        return  (this.page <= 0 ? 1: this.page) - 1;
    }

    public int getSize() {
        return (this.size > MAX_SIZE || this.size <= 0) ? DEFAULT_SIZE : this.size;
    }

    public Sort getSort(String sortField) {
        return null == this.sort ? new Sort(Sort.Direction.DESC, sortField) : this.sort;
    }

    /**
     * 기본 요청 값
     *
     * @return
     */
    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(getPage(), getSize(), getSort("updateAt"));
    }

    /**
     * 특정 필드 기준 정렬
     *
     * @param updateField
     * @return
     */
    public org.springframework.data.domain.PageRequest of(String updateField) {
        return org.springframework.data.domain.PageRequest.of(getPage(), getSize(), getSort(updateField));
    }
}
