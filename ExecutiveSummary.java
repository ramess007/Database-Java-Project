import java.util.ArrayList; //imports java Arraylist package

/*
 * The class provides Executive Summary of COMPANY DATABASE
 * It manipulated the data obtained from sql table to provide formatted output
 * The class also used the values obtained from ReportBreakout class and manipulates it
 * Arraylist data structure is used extensively to store and transmit the values
 */
public class ExecutiveSummary {
	private ArrayList<String> DepartmentName;
	private ArrayList<String> ManagerName;
	private ArrayList<String> ProjectName;
	private ArrayList<Integer> TotalPeople;
	private ArrayList<Float> TotalHours;
	private ArrayList<Float> TotalCommitment;

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
	 * The method gets the Arraylist with total employees in the project
	 * 
	 * @return ArrayList containing total employees in the project
	 */
	public ArrayList<Integer> getTotalPeople() {
		return TotalPeople;
	}

	/*
	 * The method sets the Arraylist with total employees in the project
	 * 
	 * @param totalPeople arraylist of total employees in the project
	 */
	public void setTotalPeople(ArrayList<Integer> totalPeople) {
		TotalPeople = totalPeople;
	}

	/*
	 * The method gets the Arraylist with Hours of employees in the project
	 * 
	 * @return ArrayList containing employees working hours in the project
	 */
	public ArrayList<Float> getTotalHours() {
		return TotalHours;
	}

	/*
	 * The method sets the Arraylist with employees working hours in the project
	 * 
	 * @param totalHours arraylist of employees working hours in the project
	 */
	public void setTotalHours(ArrayList<Float> totalHours) {
		TotalHours = totalHours;
	}

	/*
	 * The method gets the Arraylist with commitment of employees in project
	 * 
	 * @return ArrayList containing commitment amount of employess in project
	 */
	public ArrayList<Float> getTotalCommitment() {
		return TotalCommitment;
	}

	/*
	 * The method sets the Arraylist with commitment amount in particular project
	 * 
	 * @param totalCommitment arraylist of commitment amount in particular project
	 */
	public void setTotalCommitment(ArrayList<Float> totalCommitment) {
		TotalCommitment = totalCommitment;
	}

	/*
	 * It goes through every data in the table and filter them to get the required
	 * output in the output variable It uses for data structure to go through each
	 * row of the table and if-conditional statement for required conditions
	 * 
	 * @return the formatted output of report breakout
	 */
	public String toString() {
		String output = "";
		for (int i = 0; i < TotalHours.size(); i++) {
			if (i == 0) {
				output += String.format("\n%-5s - %-25s\n", DepartmentName.get(i), ManagerName.get(i));
				output += String.format("    %-25s%-8d%-10.1f$ %,.2f\n", ProjectName.get(i), TotalPeople.get(i),
						TotalHours.get(i), TotalCommitment.get(i));

			} else {
				if (!DepartmentName.get(i - 1).equals((DepartmentName).get(i))) {
					output += String.format("\n%-5s - %-25s\n", DepartmentName.get(i), ManagerName.get(i));
				}
				if (!ProjectName.get(i - 1).equals(ProjectName.get(i))) {
					output += String.format("    %-25s%-8d%-10.1f$ %,.2f\n", ProjectName.get(i), TotalPeople.get(i),
							TotalHours.get(i), TotalCommitment.get(i));

				}
			}

		}

		return output;

	}
}
