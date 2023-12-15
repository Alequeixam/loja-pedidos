package com.alex.demo.lojapedidos.model;

import com.alex.demo.lojapedidos.model.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "payment")
public class Payment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id")
    private Long paymentId;

    @NotNull
    @Column(name = "method")
    private PaymentMethod paymentMethod;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT")
    @DateTimeFormat
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

}
