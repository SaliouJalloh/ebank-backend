package org.msd.ebankingbackend.storage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistory extends AbstractModel {
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<Operation> operations;
}
