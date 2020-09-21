package fr.eurler.invoicemanager.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Invoice {

    private String id;

    private LocalDate date;

}
