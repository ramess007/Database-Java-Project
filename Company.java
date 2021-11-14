
/*
   -The program outputs Executive summary and Break out report for each project from the COMPANY DATABASE
   -The program uses sql query to get the table from aws server and manipulate the values in different classes
   -The output is displayed in screen and a rtf file is also created simultaneously
   : CSCI 4055- Fall 2021- Programming Project
   : Author: Ramesh Chapagain
   : Submitted to: Lon Smith, Ph.D
   : Date of Submission: 2 November 2021          
*/
import java.io.*; // import java io packages
import java.sql.*; // import java sql packages
import java.util.*; // import java utility packages

public class Company {
	/*
	 * The main method accesses the database from aws using sql query File reader is
	 * used to get the url, password, username and driver from examples.properties
	 * The connection is created with the server with the above information It uses
	 * ArrayList to store the values obtained from the COMPANY database Objects of
	 * two different classes are created to set the values and get variables from
	 * them The ArrayLists are sent to different classes for manipulation sing
	 * setter of those classes to get required output Required output is printed in
	 * the screen and a rtf file is also created simultaneously using
	 * DataOutputStream Try-catch statement is used extensively to check the
	 * exceptions
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt;
		ResultSet result1;
		String query1;
		String name1;
		try {
			FileReader reader = new FileReader("example.properties");
			Properties p = new Properties();
			p.load(reader);

			String dbdriver = p.getProperty("db.driver");
			String dbuser = p.getProperty("db.user");
			String dbpassword = p.getProperty("db.password");
			String dburl = p.getProperty("db.url");

			Class.forName(dbdriver).newInstance();
			con = DriverManager.getConnection(dburl, dbuser, dbpassword);

			if (!con.isClosed()) {
				stmt = con.createStatement();
				query1 = "Select * from (select Dname,Pname,Plocation,Fname,Minit,Lname,Address,Hours,(Salary*Hours/40) Commitment\r\n"
						+ "from PROJECT,WORKS_ON,DEPARTMENT,EMPLOYEE\r\n" + "where Pnumber = Pno\r\n"
						+ "      and Dnum = Dnumber\r\n" + "      and Essn = Ssn\r\n"
						+ "      and Hours is not null\r\n" + "group by Dname,Pname,Fname\r\n"
						+ "order by Dname,Pname,Fname)as A natural join\r\n"
						+ "(select Dname,Fname SupervisorFname, Minit SupervisorMinit, Lname SupervisorLname\r\n"
						+ "from EMPLOYEE JOIN DEPARTMENT ON Mgr_ssn = Ssn) as B\r\n" + "order by Dname,Pname,Lname;\r\n"
						+ "";
				result1 = stmt.executeQuery(query1);

				ExecutiveSummary exeSum = new ExecutiveSummary();
				ReportBreakout report = new ReportBreakout();

				ArrayList<String> DepartmentName = new ArrayList<String>();
				ArrayList<String> ProjectName = new ArrayList<String>();
				ArrayList<String> EmployeeName = new ArrayList<String>();
				ArrayList<Float> Hours = new ArrayList<Float>();
				ArrayList<Float> Commitment = new ArrayList<Float>();
				ArrayList<String> ProjectLocation = new ArrayList<String>();
				ArrayList<String> EmployeeLocation = new ArrayList<String>();
				ArrayList<String> ManagerName = new ArrayList<String>();

				float hours = 0.0f;
				float commitment = 0.0f;
				while (result1.next()) {
					DepartmentName.add(result1.getString("Dname"));
					ProjectName.add(result1.getString("Pname"));
					ProjectLocation.add(result1.getString("Plocation"));
					name1 = result1.getString("Fname") + " " + result1.getString("Minit") + ". "
							+ result1.getString("Lname");
					EmployeeName.add(name1);
					EmployeeLocation.add(result1.getString("Address"));
					hours = result1.getFloat("Hours");
					Hours.add(hours);
					commitment = result1.getFloat("Commitment");
					Commitment.add(commitment);
					name1 = result1.getString("SupervisorFname") + " " + result1.getString("SupervisorMinit") + ". "
							+ result1.getString("SupervisorLname");
					ManagerName.add(name1);
				}

				report.setDepartmentName(DepartmentName);
				report.setProjectName(ProjectName);
				report.setProjectLocation(ProjectLocation);
				report.setEmployeeName(EmployeeName);
				report.setEmployeeLocation(EmployeeLocation);
				report.setHours(Hours);
				report.setCommitment(Commitment);
				report.setManagerName(ManagerName);

				String ReportBreakOut = (report.toString());
				exeSum.setDepartmentName(report.ConsiceDepartment);
				exeSum.setManagerName(report.ConsiceManagerName);
				exeSum.setProjectName(report.ConsiceProject);
				exeSum.setTotalHours(report.TotalHours);
				exeSum.setTotalPeople(report.TotalEmployee);
				exeSum.setTotalCommitment(report.TotalCommitment);
				String ExecutiveSummary = exeSum.toString();
				System.out.println("Project Executive Summary");
				System.out.println(ExecutiveSummary);
				
				String space="";
				for(int i =0; i<20; i++) {
					space+="\n";
				}
				System.out.println(space);
				System.out.println("Project Details\n");
				System.out.println(ReportBreakOut);

				DataOutputStream dos;
				File file = new File("ProjectReport.rtf");
				try {
					dos = new DataOutputStream(new FileOutputStream(file));
					dos.writeBytes("Project Executive Summary\n");
					dos.writeBytes(ExecutiveSummary);
					dos.writeBytes(space);
					dos.writeBytes("Project Details\n\n");
					dos.writeBytes(ReportBreakOut);
					dos.close();
				} catch (Exception e) {
					System.err.println("Exception: " + e.getMessage());
				}
			}
		}

		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
}
