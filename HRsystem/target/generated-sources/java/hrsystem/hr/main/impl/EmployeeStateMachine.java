package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Employee;
import hrsystem.hr.main.Employee_Leave;
import hrsystem.hr.main.Leave;
import hrsystem.hr.main.impl.Employee_LeaveImpl;

import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.statemachine.ITransition;
import io.ciera.runtime.summit.statemachine.StateMachine;
import io.ciera.runtime.summit.types.StringUtil;


public class EmployeeStateMachine extends StateMachine<Employee,Hr> {

    public static final int On_Leave = 1;
    public static final int Recruited = 0;
    public static final int Training = 2;
    public static final int Working = 3;


    private Employee self;

    public EmployeeStateMachine(Employee self, Hr context) {
        this(self, context, Recruited);
    }

    public EmployeeStateMachine(Employee self, Hr context, int initialState) {
        super(context, initialState);
        this.self = self;
    }

    private void On_Leave_entry_action( final String p_Starting,  final String p_Ending,  final int p_National_ID,  final String p_Name ) throws XtumlException {
        Employee_Leave assignTo = Employee_LeaveImpl.create( context() );
        Employee employee = context().Employee_instances().anyWhere(selected -> ((Employee)selected).getNational_ID() == p_National_ID);
        Leave leave = context().Leave_instances().anyWhere(selected -> StringUtil.equality( ((Leave)selected).getName(), p_Name ));
        context().relate_R1_Employee_Leave_Employee( assignTo, employee );
        context().relate_R1_Employee_Leave_Leave( assignTo, leave );
        context().LOG().LogInfo( ( employee.getFName() + " " ) + employee.getLName() );
        assignTo.setStarting( p_Starting );
        assignTo.setEnding( p_Ending );
        context().LOG().LogInfo( ( "Requested" + leave.getName() ) + "leave" );
        context().LOG().LogInfo( "From" + assignTo.getStarting() );
        context().LOG().LogInfo( "To" + assignTo.getEnding() );
    }

    private void Recruited_entry_action() throws XtumlException {
        context().LOG().LogInfo( "Waiting for employee to commence." );
    }

    private void Training_entry_action() throws XtumlException {
    }

    private void Working_entry_action() throws XtumlException {
    }



    @Override
    public ITransition[][] getStateEventMatrix() {
        return new ITransition[][] {
            { CANT_HAPPEN,
              CANT_HAPPEN,
              CANT_HAPPEN,
              (event) -> {Working_entry_action(); return Working;}
            },
            { (event) -> {Working_entry_action(); return Working;},
              (event) -> {On_Leave_entry_action((String)event.get(0),  (String)event.get(1),  (int)event.get(2),  (String)event.get(3)); return On_Leave;},
              CANT_HAPPEN,
              CANT_HAPPEN
            },
            { CANT_HAPPEN,
              CANT_HAPPEN,
              CANT_HAPPEN,
              CANT_HAPPEN
            },
            { CANT_HAPPEN,
              (event) -> {On_Leave_entry_action((String)event.get(0),  (String)event.get(1),  (int)event.get(2),  (String)event.get(3)); return On_Leave;},
              CANT_HAPPEN,
              CANT_HAPPEN
            }
        };
    }

    @Override
    public Employee self() {
        return self;
    }

    @Override
    public String getClassName() {
        return "Employee";
    }

}
