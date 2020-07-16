package gui;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/home")
public class Jax extends Application{
	
	private Set<Object> singletons = new HashSet<Object>();
	
	public Jax() {
		this.singletons.add(new Gui());
	}
	
	@Override
	public Set<Object> getSingletons(){
		return singletons;
	}

}
