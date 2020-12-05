package diplomski.autoceste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 *
 * An invoice is a bill that businesses send to customers or clients, asking for payment for goods or services.
 * Invoices usually include a description of the items you’re charging for along with payment terms, amongst other information.
 *
 *
 */

@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate issuedDate;
    private LocalDate dueDate;
    private String description;
    private Double amount;
    private Double vat;
    private Double vatAmount;
    private Double totalAmount;




}
