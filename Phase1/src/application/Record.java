package application;

public class Record {
	private String Day;
	private double isrealline;
	private double gazaPower;
	private double eygetLine;
	private double totalDaily;
	private double Demand;
	private double powerCutsHourDay;
	private double Temp;

	public Record(String day, double isrealline, double gazaPower, double eygetLine, double totalDaily, double demand,
			double powerCutsHourDay, double temp) {
		Day = day;
		this.isrealline = isrealline;
		this.gazaPower = gazaPower;
		this.eygetLine = eygetLine;
		this.totalDaily = totalDaily;
		Demand = demand;
		this.powerCutsHourDay = powerCutsHourDay;
		Temp = temp;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public double getIsrealline() {
		return isrealline;
	}

	public void setIsrealline(double isrealline) {
		this.isrealline = isrealline;
	}

	public double getGazaPower() {
		return gazaPower;
	}

	public void setGazaPower(double gazaPower) {
		this.gazaPower = gazaPower;
	}

	public double getEygetLine() {
		return eygetLine;
	}

	public void setEygetLine(double eygetLine) {
		this.eygetLine = eygetLine;
	}

	public double getTotalDaily() {
		return totalDaily;
	}

	public void setTotalDaily(double totalDaily) {
		this.totalDaily = totalDaily;
	}

	public double getDemand() {
		return Demand;
	}

	public void setDemand(double demand) {
		Demand = demand;
	}

	public double getPowerCutsHourDay() {
		return powerCutsHourDay;
	}

	public void setPowerCutsHourDay(double powerCutsHourDay) {
		this.powerCutsHourDay = powerCutsHourDay;
	}

	public double getTemp() {
		return Temp;
	}

	public void setTemp(double temp) {
		Temp = temp;
	}

	@Override
	public String toString() {
		return "Record [Day=" + Day ;
	}

}
