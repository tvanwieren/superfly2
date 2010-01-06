package com.payneteasy.superfly.dao;

import java.util.List;

import com.googlecode.jdbcproc.daofactory.annotation.AStoredProcedure;
import com.payneteasy.superfly.model.RoutineResult;
import com.payneteasy.superfly.model.ui.subsystem.UISubsystem;
import com.payneteasy.superfly.model.ui.subsystem.UISubsystemForFilter;
import com.payneteasy.superfly.model.ui.subsystem.UISubsystemForList;

/**
 * DAO to work with subsystems.
 * 
 * @author Roman Puchkovskiy
 */
public interface SubsystemDao {
	
	/**
	 * Returns a list of registered subsystems.
	 * 
	 * @return subsystems
	 */
	@AStoredProcedure(name = "ui_get_subsystems_list")
	List<UISubsystemForList> getSubsystems();
	
	/**
	 * Creates a subsystem.
	 * 
	 * @param subsystem	subsystem to create
	 * @return routine result
	 */
	@AStoredProcedure(name = "ui_create_subsystem")
	RoutineResult createSubsystem(UISubsystem subsystem);
	
	/**
	 * Deletes a subsystem.
	 * 
	 * @param subsystemId	ID of a subsystem to delete
	 * @return routine result
	 */
	@AStoredProcedure(name = "ui_delete_subsystem")
	RoutineResult deleteSubsystem(long subsystemId);
	
	/**
	 * Returns a list of all subsystems for a subsystem-based filter.
	 * 
	 * @return subsystems
	 */
	@AStoredProcedure(name = "ui_filter_subsystems")
	List<UISubsystemForFilter> getSubsystemsForFilter();
}