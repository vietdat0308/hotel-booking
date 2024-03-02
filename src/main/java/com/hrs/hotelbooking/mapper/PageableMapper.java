package com.hrs.hotelbooking.mapper;

import com.hrs.hotelbooking.openapi.model.PageableDto;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * This interface support mappings related to Paging.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Component
public class PageableMapper {

  // this should be externalize
  private static final int defaultPage = 0;
  private static final int defaultPageSize = 2;

  /**
   * To class pageable.
   *
   * @param pageableDto the pageable dto
   * @return the pageable
   */
  public Pageable toPageable(PageableDto pageableDto) {

    if (Objects.isNull(pageableDto)) {
      pageableDto = new PageableDto().page(defaultPage).pageSize(defaultPageSize);
    }
    if (Objects.isNull(pageableDto.getPage())) {
      pageableDto.page(defaultPage);
    }
    if (Objects.isNull(pageableDto.getPageSize()) || pageableDto.getPageSize() == 0) {
      pageableDto.pageSize(defaultPageSize);
    }
    return PageRequest.of(pageableDto.getPage(), pageableDto.getPageSize());
  }

}
