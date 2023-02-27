##Use Case 1: Log Into the system

• Goal in Context – Verifies and allows the user to log into the system

• Scope – Organisational

• Level – Too Low.

• Preconditions – User has the correct log in detail. Enters a correct Username and Password

• Success Condition – Correct username and associated password

• Failed Condition – Error message specifying the failure to input correct details. No entry into the system

• Primary Actor - the population analyst.

• Trigger – First entering the software system.

• Main Success Scenario – Entry into the website

• Extensions – Display Login Error

• Sub-variations – Validation of details.

• Schedule – Complete by Sprint 4

## Use Case 2: Largest to smallest Population

• Goal in Context - All the countries can be organised by largest population to smallest via Geographical conditions (Continent, Region, World)

• Scope – System

• Level – User Goal.

• Preconditions – Define Geographic Condition and Select

• Success Condition – Enter the correct Geographic information

• Failed Condition – Error message specifying the failure to input geographical information or query of interest correctly

• Primary Actor - the population analyst.

• Trigger – Upon user inputting geographic information and query of interest into the User Interface. Then pressing search

• Main Success Scenario – Returning an Desc list of population from largest to smallest by the user defined geographical conditions

• Extensions – Display input error

• Sub-variations – Must validate inputs and queries inputs

• Schedule – sprint 2 deadline

##Use Case 3: Select top countries

• Goal in Context - Can find the top N populated countries where N is provided by the user by Geographical conditions (Continent, Region, World)

• Scope – System

• Level – User Goal.

• Preconditions – Define the N and geographic regions

• Success Condition – Inputs an integer N and select a geographic critea

• Failed Condition – Error message specifying the failure to input geographical information or an integer value

• Primary Actor - the population analyst.

• Trigger – Upon user inputting geographic information and integer N then queries information into the User Interface. Then pressing search

• Main Success Scenario – Returning an Desc list of population of top N countries by the user defined geographical conditions

• Extensions – Display input error if

• Sub-variations – Must validate inputs and queries inputs

• Schedule – sprint 2 deadline

##Use Case 4: Select cities population

• Goal in Context - All the cities organised by largest population to smallest via by Geographical conditions (Continent, Region, World, district, counties)

• Scope – System

• Level – User Goal.

• Preconditions – Define context being city and by geographic conditions

• Success Condition – Inputs city and select a geographic criterion

• Failed Condition – Error message specifying the failure to input geographical information or a city

• Primary Actor - the population analyst.

• Trigger – Upon user inputting geographic information and condition via cities then queries information into the User Interface. Then pressing search

• Main Success Scenario – Returning an Desc list of most populated cities by the user defined geographical conditions

• Extensions – Display input error

• Sub-variations – Must validate inputs and queries inputs

• Schedule – sprint 2 deadline

##Use Case 5: Breakdown of peoples living locations

• Goal in Context - The population of people, people living in cities, and people not living in cities Via Geographic conditions (Continent, Region, cities, World)

• Scope – System

• Level – User Goal.

• Preconditions – Define context by geographic conditions

• Success Condition – Inputs city and select a geographic criterion. Includes condition outwith city

• Failed Condition – Error message specifying the failure to input geographical information.

• Primary Actor - the population analyst.

• Trigger – Upon user inputting geographic information and condition via cities then queries information into the User Interface. Then pressing search

• Main Success Scenario – Returning a three-column list containing population, people by cities and not cities by the user defined geographical conditions

• Extensions – Display input error

• Sub-variations – Must validate inputs and queries inputs

• Schedule – sprint 3 deadline

##Use Case 6: Find the top N cities where N is a value in

• Goal in Context - Can find the top N populated Cities where N is provided by the user by Geographical conditions (Continent, Region, World, district, counties))

• Scope – System

• Level – User Goal.

• Preconditions – Define context by geographic conditions, s city condition and some user-input value N

• Success Condition – Inputs city, integer N and select a geographic criterion.

• Failed Condition – Error message specifying the failure to input geographical information or an Integer

• Primary Actor - the population analyst.

• Trigger – Upon user inputting geographic information, integer N and condition via cities then queries information into the User Interface. Then pressing search

• Main Success Scenario – Returning a three-column list containing population, people by cities and not cities by the user defined geographical conditions

• Extensions – Display input error

• Sub-variations – Must validate inputs and queries inputs

• Schedule – sprint 3 deadline

##Use Case 7: General population of the world data

• Goal in Context - The user must be able to find all population date for all levels in the system (Continent, Region, City, District, World)

• Scope – System

• Level – Very High Summary

• Preconditions – Define context by geographic

• Success Condition – Inputs level on the data hierarchy criterion.

• Failed Condition – N/A no conditions supplied.

• Primary Actor - the population analyst.

• Trigger – Upon user inputting geographic information then queries information into the User Interface. Then pressing search

• Main Success Scenario – Returning columns containing population at level specified

• Extensions – Display input error

• Sub-variations – Must validate inputs and queries inputs

• Schedule – sprint 4 deadline

##Use Case 8: Able to change Language

• Goal in Context - The user must be able to change the language of the system to the following system

• Level – Subfunction

• Preconditions – Define language of choice

• Success Condition – Inputs language of choice.

• Failed Condition – Impossible. Must select a language when using the system

• Primary Actor - the population analyst.

• Trigger – Opening the system.

• Main Success Scenario – Returning the system interface and DB into the specified language of choice

• Extensions – N/A

• Sub-variations –Take inputs and finds the appropriate DB and GUI

• Schedule – sprint 4 deadline