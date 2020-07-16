package hrsystem.hr.main;


import hrsystem.hr.main.PaySet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface OvertimeSet extends IInstanceSet<OvertimeSet,Overtime> {

    // attributes


    // selections
    public PaySet R4_is_a_Pay() throws XtumlException;


}
