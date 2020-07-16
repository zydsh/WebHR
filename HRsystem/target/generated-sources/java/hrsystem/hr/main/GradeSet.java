package hrsystem.hr.main;


import hrsystem.hr.main.PaySet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface GradeSet extends IInstanceSet<GradeSet,Grade> {

    // attributes


    // selections
    public PaySet R4_is_a_Pay() throws XtumlException;


}
