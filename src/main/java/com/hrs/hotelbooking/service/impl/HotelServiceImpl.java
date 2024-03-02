package com.hrs.hotelbooking.service.impl;


import com.hrs.hotelbooking.mapper.HotelMapper;
import com.hrs.hotelbooking.mapper.PageableMapper;
import com.hrs.hotelbooking.openapi.model.HotelFilterDto;
import com.hrs.hotelbooking.openapi.model.HotelsDto;
import com.hrs.hotelbooking.openapi.model.PageableDto;
import com.hrs.hotelbooking.repository.HotelRepository;
import com.hrs.hotelbooking.repository.view.HotelView;
import com.hrs.hotelbooking.service.HotelService;
import com.hrs.hotelbooking.util.StringUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

  private final HotelRepository hotelRepository;
  private final HotelMapper hotelMapper;
  private final PageableMapper pageableMapper;

  @Override
  public HotelsDto getHotels(String searchKey, HotelFilterDto filter, PageableDto pageableDto) {
    String processedSearchKey = Objects.isNull(searchKey) ? null : StringUtil.processValidSearchKey(searchKey.trim());
    Pageable pageable = pageableMapper.toPageable(pageableDto);

    Page<HotelView> hotelViewPage = hotelRepository.getHotels(filter, processedSearchKey, pageable);
    return hotelMapper.toHotelsDto(hotelViewPage);
  }
}
