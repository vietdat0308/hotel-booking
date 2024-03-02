package com.hrs.hotelbooking.messaging;

import com.hrs.hotelbooking.entity.Booking;
import com.hrs.hotelbooking.enumeration.BookingStatus;
import com.hrs.hotelbooking.messaging.event.BookingEvent;
import com.hrs.hotelbooking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * BookingConsumer class supports listen jms messages on the booking queue.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BookingConsumer implements MessageConsumer<BookingEvent> {

  private final BookingRepository bookingRepository;


  @JmsListener(destination = "${integration.activemq.queue.creating.booking}")
  @Transactional
  @Override
  public void receive(BookingEvent bookingEvent) {
    log.info("Received BookingEvent: BookingId={}, RoomId={}, CheckIn={}, CheckOut={}", bookingEvent.getBookingId(), bookingEvent.getRoomId(), bookingEvent.getCheckIn(),
        bookingEvent.getCheckOut());
    final Booking booking = bookingRepository.getReferenceById(bookingEvent.getBookingId());

    if(bookingRepository.isExistedBookingsForRoomOnThePeriod(bookingEvent.getRoomId(), bookingEvent.getCheckIn(), bookingEvent.getCheckOut(), BookingStatus.BOOKED.getValue())){
      booking.setStatus(BookingStatus.FAILED);
      booking.setFailReason("The room is already booked in that period time");
      bookingRepository.save(booking);
      return;
    }

    booking.setStatus(BookingStatus.BOOKED);
    bookingRepository.save(booking);
  }
}
