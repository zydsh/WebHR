package hrsystem.hr.main;


import hrsystem.Hr;
import hrsystem.hr.main.Employee_Leave;
import hrsystem.hr.main.Employee_LeaveSet;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Employee_PaySet;

import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.types.Date;


public interface Employee extends IModelInstance<Employee,Hr> {

    // attributes
    public String getFName() throws XtumlException;
    public void setFName( String m_FName ) throws XtumlException;
    public int getNational_ID() throws XtumlException;
    public void setNational_ID( int m_National_ID ) throws XtumlException;
    public String getLName() throws XtumlException;
    public void setLName( String m_LName ) throws XtumlException;
    public Date getStart_Date() throws XtumlException;
    public void setStart_Date( Date m_Start_Date ) throws XtumlException;


    // operations


    // selections
    default public void addR1_Employee_Leave( Employee_Leave inst ) {}
    default public void removeR1_Employee_Leave( Employee_Leave inst ) {}
    public Employee_LeaveSet R1_Employee_Leave() throws XtumlException;
    default public void addR2_recieves_Employee_Pay( Employee_Pay inst ) {}
    default public void removeR2_recieves_Employee_Pay( Employee_Pay inst ) {}
    public Employee_PaySet R2_recieves_Employee_Pay() throws XtumlException;


}
