import java.util.ArrayList; //import java arraylist package

/**
 * This class breaks out the report of the projects of COMPANY DATABASE It
 * manipulated the data obtained from sql table to provide formatted output The
 * class forms the basic for the data to be supplied in ExecutiveSummary class
 * Arraylist data structure is used extensively to store and transmit the values
 */

public class ReportBreakout {
	private ArrayList<String> DepartmentName;
	private ArrayList<String> EmployeeName;
	private ArrayList<String> EmployeeLocation;
	private ArrayList<String> ProjectName;
	private ArrayList<String> ProjectLocation;
	private ArrayList<Float> Hours;
	private ArrayList<Float> Commitment;
	private ArrayList<String> ManagerName;
	public ArrayList<Float> TotalCommitment = new ArrayList<Float>();
	public ArrayList<Float> TotalHours = new ArrayList<Float>();
	public ArrayList<String> ConsiceDepartment = new ArrayList<String>();
	public ArrayList<String> ConsiceProject = new ArrayList<String>();
	public ArrayList<Integer> TotalEmployee = new ArrayList<Integer>();
	public ArrayList<String> ConsiceManagerName = new ArrayList<String>();

	/*
	 * The method gets the Arraylist with Department Names
	 * 
	 * @return ArrayList containing Department Names
	 */
	public ArrayList<String> getDepartmentName() {
		return DepartmentName;
	}

	/*
	 * The method sets the Arraylist with department names
	 * 
	 * @param departmentName arraylist of department names
	 */
	public void setDepartmentName(ArrayList<String> departmentName) {
		DepartmentName = departmentName;
	}

	/*
	 * The method gets the Arraylist with Employee Names
	 * 
	 * @return ArrayList containing Employee Names
	 */
	public ArrayList<String> getEmployeeName() {
		return EmployeeName;
	}

	/*
	 * The method sets the Arraylist with employee names
	 * 
	 * @param employeeName arraylist of employee names
	 */
	public void setEmployeeName(ArrayList<String> employeeName) {
		EmployeeName = employeeName;
	}

	/*
	 * The method gets the Arraylist with Employees Location
	 * 
	 * @return ArrayList containing Employees location
	 */
	public ArrayList<String> getEmployeeLocation() {
		return EmployeeLocation;
	}

	/*
	 * The method sets the Arraylist with employee location
	 * 
	 * @param employeeLocation arraylist of employee location
	 */
	public void setEmployeeLocation(ArrayList<String> employeeLocation) {
		EmployeeLocation = employeeLocation;
	}

	/*
	 * The method gets the Arraylist with Project Names
	 * 
	 * @return ArrayList containing project names
	 */
	public ArrayList<String> getProjectName() {
		return ProjectName;
	}

	/*
	 * The method sets the Arraylist with project names
	 * 
	 * @param projectName arraylist of project names
	 */
	public void setProjectName(ArrayList<String> projectName) {
		ProjectName = projectName;
	}

	/*
	 * The method gets the Arraylist with Hours of employees
	 * 
	 * @return ArrayList containing employees working hours
	 */
	public ArrayList<Float> getHours() {
		return Hours;
	}

	/*
	 * The method sets the Arraylist with employees working hours
	 * 
	 * @param hours arraylist of employees working hours
	 */
	public void setHours(ArrayList<Float> hours) {
		Hours = hours;
	}

	/*
	 * The method gets the Arraylist with project location
	 * 
	 * @return ArrayList containing project location
	 */
	public ArrayList<String> getProjectLocation() {
		return ProjectLocation;
	}

	/*
	 * The method sets the Arraylist with project locations
	 * 
	 * @param projectLocation arraylist of project locations
	 */
	public void setProjectLocation(ArrayList<String> projectLocation) {
		ProjectLocation = projectLocation;
	}

	/*
	 * The method gets the Arraylist with commitment
	 * 
	 * @return ArrayList containing commitment amount
	 */
	public ArrayList<Float> getCommitment() {
		return Commitment;
	}

	/*
	 * The method sets the Arraylist with commitment amount
	 * 
	 * @param commitment arraylist of commitment amount
	 */
	public void setCommitment(ArrayList<Float> commitment) {
		Commitment = commitment;
	}

	/*
	 * The method gets the Arraylist with manager names
	 * 
	 * @return ArrayList containing Manager Names
	 */
	public ArrayList<String> getManagerName() {
		return ManagerName;
	}

	/*
	 * The method sets the Arraylist with manager names
	 * 
	 * @param managerName arraylist of manager names
	 */
	public void setManagerName(ArrayList<String> managerName) {
		ManagerName = managerName;
	}

	/*
	 * It goes through every data in the table and filter them to get the required
	 * output in the output variable It uses for data structure to go through each
	 * row of the table and if-conditional statement for required conditions
	 * 
	 * @return the formatted output of report breakout
	 */

	public String toString() {
		float sumHours = 0.0f;
		float sumCommitment = 0.0f;
		int counter = 0;
		String output = "";
		for (int i = 0; i < EmployeeName.size(); i++) {
			if (i == 0) {
				output += DepartmentName.get(i) + "\n" + "    " + ProjectName.get(i) + "  (" + ProjectLocation.get(i)
						+ ")\n";
				sumHours = Hours.get(i);
				sumCommitment = Commitment.get(i);
				ConsiceDepartment.add(DepartmentName.get(i));
				ConsiceManagerName.add(ManagerName.get(i));
				ConsiceProject.add(ProjectName.get(i));
			} else {
				if (!ProjectName.get(i - 1).equals(ProjectName.get(i))) {
					TotalCommitment.add(sumCommitment);
					TotalHours.add(sumHours);
					TotalEmployee.add(counter);
					counter = 0;

					ConsiceDepartment.add(DepartmentName.get(i));
					ConsiceManagerName.add(ManagerName.get(i));
					ConsiceProject.add(ProjectName.get(i));
					output += String.format("        %-25s%-10s%10s  %-10s\n", " ", "----------------------", " ",
							" ");
					output += String.format("        %-25s%-10.1f$ %,10.2f  %-10s\n", "", sumHours, sumCommitment,
							"");
					sumHours = 0.0f;
					sumCommitment = 0.0f;
					if (!DepartmentName.get(i - 1).equals((DepartmentName).get(i))) {
						output += "\n" + DepartmentName.get(i) + "\n";
					}
					output += "    " + ProjectName.get(i) + "  (" + ProjectLocation.get(i) + ")\n";

					sumHours = Hours.get(i);
					sumCommitment = Commitment.get(i);
				} else if (ProjectName.get(i - 1).equals(ProjectName.get(i))) {
					sumHours += Hours.get(i);
					sumCommitment += Commitment.get(i);
				}
			}
			counter++;
			output += String.format("        %-25s%-10.1f$ %,10.2f  %-10s\n", EmployeeName.get(i), Hours.get(i),
					Commitment.get(i), (EmployeeLocation.get(i).split(",")[1]));
			if (i == (EmployeeName.size() - 1)) {
				TotalEmployee.add(counter);
				TotalCommitment.add(sumCommitment);
				TotalHours.add(sumHours);
				output += String.format("        %-25s%-10s%10s  %-10s\n", " ", "----------------------", " ", " ");
				output += String.format("        %-25s%-10.1f$ %,10.2f  %-10s\n", "", sumHours, sumCommitment, "");
			}
		}

		return output;
	}

}
