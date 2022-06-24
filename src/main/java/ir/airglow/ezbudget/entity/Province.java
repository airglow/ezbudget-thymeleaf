package ir.airglow.ezbudget.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Province {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    @NonNull
    private String name;

}
