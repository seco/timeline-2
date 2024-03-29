package com.wedul.wedul_timeline.core.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 공통 dto 클래
 *
 * @author wedul
 * @date 2017. 11. 4.
 * @name CommonDto
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonEntity implements Serializable {

  @CreatedDate
  private long updateAt;

  @LastModifiedDate
  private long createAt;
}
