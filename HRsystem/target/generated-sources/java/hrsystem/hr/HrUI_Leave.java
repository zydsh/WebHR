package hrsystem.hr;


import hrsystem.Hr;
import hrsystem.hr.main.Employee;
import hrsystem.hr.main.Leave;
import hrsystem.hr.main.impl.EmployeeImpl;
import hrsystem.hr.main.impl.LeaveImpl;

import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.IMessage;
import io.ciera.runtime.summit.interfaces.IPort;
import io.ciera.runtime.summit.interfaces.Port;
import io.ciera.runtime.summit.types.IntegerUtil;
import io.ciera.runtime.summit.types.StringUtil;

import ui.shared.ILeave;


public class HrUI_Leave extends Port<Hr> implements ILeave {

    public HrUI_Leave( Hr context, IPort<?> peer ) {
        super( context, peer );
    }

    // inbound messages
    public void Return( final int p_National_ID ) throws XtumlException {
        Employee employee = context().Employee_instances().anyWhere(selected -> ((Employee)selected).getNational_ID() == p_National_ID);
        if ( !employee.isEmpty() ) {
            context().generate(new EmployeeImpl.returnFromLeave(getRunContext(), context().getId()).to(employee));
            context().UI().Reply( "Employee request is successfull ", true );
        }
        else {
            context().LOG().LogInfo( "Employee is not registered!" );
            context().UI().Reply( "Employee is not found.", false );
        }
    }

    public void New_Leave( final String p_Name,  final int p_NumberOfAllowedDays ) throws XtumlException {
        Leave leave = context().Leave_instances().anyWhere(selected -> StringUtil.equality( ((Leave)selected).getName(), p_Name ));
        if ( leave.isEmpty() ) {
            new LeaveImpl.CLASS(context()).createLeave( p_Name, p_NumberOfAllowedDays );
            context().UI().Reply( "Leave created successfully.", true );
        }
        else {
            context().LOG().LogInfo( "Leave already exists!" );
        }
    }

    public void Request( final String p_Starting,  final String p_Ending,  final int p_National_ID,  final String p_Name ) throws XtumlException {
        Employee employee = context().Employee_instances().anyWhere(selected -> ((Employee)selected).getNational_ID() == p_National_ID);
        if ( !employee.isEmpty() ) {
            context().generate(new EmployeeImpl.requestLeave(getRunContext(), context().getId(),  p_Starting, p_Ending, p_National_ID, p_Name ).to(employee));
            context().UI().Reply( "Employee has requested leave successfully ", true );
        }
        else {
            context().LOG().LogInfo( "Employee is not registered!" );
            context().UI().Reply( "Employee is not found.", false );
        }
    }



    // outbound messages


    @Override
    public void deliver( IMessage message ) throws XtumlException {
        if ( null == message ) throw new BadArgumentException( "Cannot deliver null message." );
        switch ( message.getId() ) {
            case ILeave.SIGNAL_NO_RETURN:
                Return(IntegerUtil.deserialize(message.get(0)));
                break;
            case ILeave.SIGNAL_NO_NEW_LEAVE:
                New_Leave(StringUtil.deserialize(message.get(0)), IntegerUtil.deserialize(message.get(1)));
                break;
            case ILeave.SIGNAL_NO_REQUEST:
                Request(StringUtil.deserialize(message.get(0)), StringUtil.deserialize(message.get(1)), IntegerUtil.deserialize(message.get(2)), StringUtil.deserialize(message.get(3)));
                break;
        default:
            throw new BadArgumentException( "Message not implemented by this port." );
        }
    }



    @Override
    public String getName() {
        return "UI_Leave";
    }

}
