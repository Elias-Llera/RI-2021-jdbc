package uo.ri.cws.application.persistence.workorder;

import java.time.LocalDateTime;

public class WorkOrderRecord {
	public String id;
	public String description;
	public LocalDateTime date;
	public String status;
	public long version;
	public String vehicleId;
	public double amount;
	public String mechanicId;
	public String invoiceId;
	public boolean usedForVoucher;
}
