package hrsystem.hr.main;


import hrsystem.Hr;
import hrsystem.hr.main.Employee_Pay;

import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface Payment extends IModelInstance<Payment,Hr> {

    // attributes


    // operations


    // selections
    default public void setR3_Employee_Pay( Employee_Pay inst ) {}
    public Employee_Pay R3_Employee_Pay() throws XtumlException;


}
