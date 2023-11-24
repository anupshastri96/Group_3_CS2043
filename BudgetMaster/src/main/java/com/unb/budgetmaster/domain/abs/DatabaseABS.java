package com.unb.budgetmaster.domain.abs;
import java.sql.Connection;

public interface DatabaseABS {
    Connection connectDatabase();
    void disconnectDatabase();
}
