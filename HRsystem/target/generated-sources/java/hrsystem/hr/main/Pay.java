package hrsystem.hr.main;


import hrsystem.Hr;
import hrsystem.hr.main.Bonus;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.Grade;
import hrsystem.hr.main.Overtime;

import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface Pay extends IModelInstance<Pay,Hr> {

    // attributes


    // operations


    // selections
    default public void addR2_earned_Employee_Pay( Employee_Pay inst ) {}
    default public void removeR2_earned_Employee_Pay( Employee_Pay inst ) {}
    public Employee_PaySet R2_earned_Employee_Pay() throws XtumlException;
    default public void setR4_is_a_Bonus( Bonus inst ) {}
    public Bonus R4_is_a_Bonus() throws XtumlException;
    default public void setR4_is_a_Grade( Grade inst ) {}
    public Grade R4_is_a_Grade() throws XtumlException;
    default public void setR4_is_a_Overtime( Overtime inst ) {}
    public Overtime R4_is_a_Overtime() throws XtumlException;


}
