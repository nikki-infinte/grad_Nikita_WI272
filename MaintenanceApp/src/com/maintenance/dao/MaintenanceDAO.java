package com.maintenance.dao;

import com.maintenance.model.Maintenance;
import java.util.List;

public interface MaintenanceDAO {
    void addMaintenance(Maintenance maintenance);
    List<Maintenance> getPendingMaintenances();
    boolean payMaintenance(int sid);
}
