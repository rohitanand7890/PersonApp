# Java Test

The brief overview of what has been done in this project are stated below
- We are fetching data from a flat(text) file which contains two attributes 'name' and 'age' of person which are separated by " "(white space)
- We are reading this data from file and populating it a in Database table called record having two columns as name and age.
    - Keeping in mind the data that we are populating in database should have unique name. 
    - And if a new record having same name which is already present in Database Table with different age is trying to get inserted,
     to handle this situation a stored procedure has been written as such that it will update the age of the existing name and remove the previous stored age from the column.
- Now for reading the Age of any particular person, a dao.PersonCache algorithm has been designed such that if the age of a certain person is required 
  then for the first time, the algorithm will query into Database table to fetch the age. From the next time if same name is called to get the age,
  a personCache backed by Hashmap has been used to store the name and age of the persons for which the DB call has already been made previously.
  This helps in faster retrieval of data and prevent misuse of resources.