package hrsystem.hr.main;


import hrsystem.hr.main.EmployeeSet;
import hrsystem.hr.main.LeaveSet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface Employee_LeaveSet extends IInstanceSet<Employee_LeaveSet,Employee_Leave> {

    // attributes
    public void setStarting( String m_Starting ) throws XtumlException;
    public void setNational_ID( int ref_National_ID ) throws XtumlException;
    public void setApproved( boolean m_Approved ) throws XtumlException;
    public void setEnding( String m_Ending ) throws XtumlException;
    public void setLeave_ID( int ref_Leave_ID ) throws XtumlException;


    // selections
    public EmployeeSet R1_Employee() throws XtumlException;
    public LeaveSet R1_Leave() throws XtumlException;


}
