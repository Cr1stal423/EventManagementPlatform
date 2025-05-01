package com.cr1stal423.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Long userId;

    private Long eventId;

    @Column(nullable = false)
    private LocalDateTime bookingTime;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public void addTicket(Ticket ticket){
        if(tickets == null){
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Booking booking)) return false;
        return Objects.equals(bookingId, booking.bookingId) && Objects.equals(userId, booking.userId) && Objects.equals(eventId, booking.eventId) && Objects.equals(bookingTime, booking.bookingTime) && Objects.equals(tickets, booking.tickets) && bookingStatus == booking.bookingStatus && Objects.equals(payment, booking.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, userId, eventId, bookingTime, tickets, bookingStatus, payment);
    }
}
