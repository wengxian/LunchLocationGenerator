package Generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import Location.Location;

public class LunchLocationGenerator {

	static Vector vecLocationList = new Vector();
	static Vector vecWeeklyLocationList = new Vector();
	static Location lunchLocation = new Location();
	static String noOfPeople = null;
	static String isRaining = null;
	static String dayOfWeek = null;

	private static void getUserInput() {

		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Please enter the amount of people going for lunch: ");
			noOfPeople = reader.readLine();
			System.out.println("No of people going for lunch: " + noOfPeople);

			System.out.print("Is it raining?: ");
			isRaining = reader.readLine();
			System.out.println("Rain: " + isRaining);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception occurred at getUserInput().");
			System.out.println("Program will exit with exit code 1 now.");
			System.exit(1);

		} finally {

			try {

				if (reader != null) {
					reader.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Exception occurred at getUserInput() finally clause.");
				System.out.println("Program will exit with exit code 1 now.");
				System.exit(1);

			}

		}

	}

	private static boolean getUserConfirmation(BufferedReader reader) {

		boolean isUserOk = false;

		try {

			reader = new BufferedReader(new InputStreamReader(System.in));

			boolean loop = true;

			while (loop) {

				System.out.println("Location Name           => " + lunchLocation.getLocationName());
				System.out.println("Is reachable when rain? => " + lunchLocation.getIsReachableWhenRain());
				System.out.println("Is air-con place?       => " + lunchLocation.getIsAirCon());
				System.out.println("Distance from MOM       => " + lunchLocation.getDistanceFromMom());
				System.out.println("Maximum No of People    => " + lunchLocation.getNoOfPeople());
				
				System.out.print("Confirm today's lunch location? (Enter Y for Yes OR N for No): ");
				String confirmation = reader.readLine();

				if ("y".equalsIgnoreCase(confirmation) || "n".equalsIgnoreCase(confirmation)) {

					if ("y".equalsIgnoreCase(confirmation)) {
						isUserOk = true;
						System.out.println("Today's lunch location will be written into WeeklyLocationList.txt");
					} else {
						System.out.println("New lunch location will be generated");
					}

					loop = false;

				} else {
					System.out.println("Please enter only Y or N only (upper or lower case does not matter)");
				}

				System.out.println("======================================================================");

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception occurred at getUserConfirmation().");
			System.out.println("Program will exit with exit code 1 now.");
			System.exit(1);

		}

		return isUserOk;

	}

	private static boolean weeklyLocationListReset(String fileName) {

		boolean isReset = false;
		BufferedWriter bufferedWriter = null;

		try {

			Date today = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("EEE");
			dayOfWeek = formatter.format(today).toUpperCase();

			if (dayOfWeek.indexOf("MON") >= 0) {

				isReset = true;
				System.out.println("Today is " + dayOfWeek + ". WeeklyLocationList.txt will be reset.");

				bufferedWriter = new BufferedWriter(new FileWriter(fileName));
				bufferedWriter.write("\n");

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception occurred at weeklyLocationListReset().");
			System.out.println("Program will exit with exit code 1 now.");
			System.exit(1);

		} finally {

			try {

				if (bufferedWriter != null) {
					bufferedWriter.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Exception occurred at weeklyLocationListReset() finally clause.");
				System.out.println("Program will exit with exit code 1 now.");
				System.exit(1);

			}

		}

		return isReset;

	}

	private static void fileReader(String fileName, String vecToAdd) {

		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(fileName));
			String line = null;

			while ((line = reader.readLine()) != null) {

				Location location = new Location();
				String[] temp = line.split("\\:");

				location.setLocationName(temp[0]);
				location.setIsReachableWhenRain(temp[1]);
				location.setIsAirCon(temp[2]);
				location.setDistanceFromMom(temp[3]);
				location.setNoOfPeople(Integer.parseInt(temp[4]));

				if ("vecLocationList".equalsIgnoreCase(vecToAdd)) {
					vecLocationList.add(location);
				} else if ("vecWeeklyLocationList".equalsIgnoreCase(vecToAdd)) {
					vecWeeklyLocationList.add(location);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception occurred at fileReader().");
			System.out.println("Program will exit with exit code 1 now.");
			System.exit(1);

		} finally {

			try {

				if (reader != null) {
					reader.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Exception occurred at fileReader() finally clause.");
				System.out.println("Program will exit with exit code 1 now.");
				System.exit(1);

			}

		}

	}

	private static boolean getLunchLocation() {

		boolean validLocation = true;

		try {

			if (vecWeeklyLocationList.size() == vecLocationList.size()) {
				return false;
			}

			boolean repeat = true;

			while (repeat) {

				lunchLocation = (Location) vecLocationList.elementAt((int) (Math.random() * vecLocationList.size()));

				// System.out.println("lunchLocation.getLocationName(): " + lunchLocation.getLocationName());

				if (vecWeeklyLocationList.size() > 0) {

					for (int i = 0; i < vecWeeklyLocationList.size(); i++) {

						Location temp = (Location) vecWeeklyLocationList.elementAt(i);

						if (lunchLocation.getLocationName().equalsIgnoreCase(temp.getLocationName())) {
							i = vecWeeklyLocationList.size();
							repeat = true;
						} else {
							repeat = false;
						}
					}

				} else {
					repeat = false;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception occurred at getLunchLocation().");
			System.out.println("Program will exit with exit code 1 now.");
			System.exit(1);

		}

		return validLocation;

	}

	private static void writeToWeeklyLocationList(String fileName) {

		BufferedWriter bufferedWriter = null;

		try {

			bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
			bufferedWriter.write(lunchLocation.toString() + ":[" + dayOfWeek + "]" + "\n");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception occurred at writeToWeeklyLocationList().");
			System.out.println("Program will exit with exit code 1 now.");
			System.exit(1);

		} finally {

			try {

				if (bufferedWriter != null) {
					bufferedWriter.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Exception occurred at writeToWeeklyLocationList() finally clause.");
				System.out.println("Program will exit with exit code 1 now.");
				System.exit(1);

			}

		}

	}

	public static void main(String[] args) {

		try {

			System.out.println("======================================================================");

			// getUserInput();

			boolean isReset = weeklyLocationListReset("./src/LocationTxtFile/WeeklyLocationList.txt");

			fileReader("./src/LocationTxtFile/LocationList.txt", "vecLocationList");

			if (!isReset) {
				fileReader("./src/LocationTxtFile/WeeklyLocationList.txt", "vecWeeklyLocationList");
			}

			boolean loop = true;
			BufferedReader reader = null;

			try {

				while (loop) {

					if (getLunchLocation()) {

						if (getUserConfirmation(reader)) {

							loop = false;
							writeToWeeklyLocationList("./src/LocationTxtFile/WeeklyLocationList.txt");

						}

					} else {
						loop = false;
						System.out.println("Please add new location, all lunch location has been visited this week");
						System.out.println("======================================================================");
					}

				}

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Exception occurred at main().");
				System.out.println("Program will exit with exit code 1 now.");
				System.exit(1);

			} finally {

				try {

					if (reader != null) {
						reader.close();
					}

				} catch (Exception e) {

					e.printStackTrace();
					System.out.println("Exception occurred at main() finally clause.");
					System.out.println("Program will exit with exit code 1 now.");
					System.exit(1);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception occurred at main().");
			System.out.println("Program will exit with exit code 1 now.");
			System.exit(1);

		}

	}

}
