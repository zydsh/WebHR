package hrsystem.hr.main;


import hrsystem.hr.main.Employee_LeaveSet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface LeaveSet extends IInstanceSet<LeaveSet,Leave> {

    // attributes
    public void setLeave_ID( int m_Leave_ID ) throws XtumlException;
    public void setName( String m_Name ) throws XtumlException;
    public void setNumberOfAllowedDays( int m_NumberOfAllowedDays ) throws XtumlException;


    // selections
    public Employee_LeaveSet R1_Employee_Leave() throws XtumlException;


}
