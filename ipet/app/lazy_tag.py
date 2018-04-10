from .models import Clinic

'''
This file is used purely for speeding up rating queries and lazy propagation approach is used.
lazy_table is a hash table that hashes the entries of clinics to the respective lazy tag.
Each time there is an update, either insertion or deletion, the lazy tag of the clinic is updated 
(instead of the entry in the database).

When a query takes place, the respective lazy tag is pushed down to the database and the updated data will be retrieved.
The design is based on the assumption that writing to database takes much more time than reading from database, which is
likely to be true since django itself implemented all the queries and updates in a lazy manner.

This optimisation has to be done manually because the over-encapsulated django interface of sqlite would save the 
modified to database whenever a function is ended (which is not desired in this case) for the sake of security.
'''


# This is the hash table. This should not be accessed directly in other files except database testing file.
# Any modification of this table is dangerous and may result in unexpected behaviours.
# If there is a need to modify this table, through the provided functions below and backing up first is advisable.
lazy_table = {}


'''
The function adds all the entries to clinics to the hash table and sets their lazy tags as True, which indicates no 
operation piling here.

The function should be run every time the database management program starts.
'''


def init():
	global lazy_table
	lazy_table = {}
	q_set = list(Clinic.objects.all())
	for clinic in q_set:
		lazy_table[clinic] = True


'''
The function adds a new tag to the respective entry.

The function takes in three parameters, the entry to the clinic, a flag equals to +/- 1, and the rating with is an int.
If the flag is 1, it means an 'addition' tag would be piled on the top of the tags; otherwise a 'deletion' tag.
The function only modifies the hash table and returns nothing.

This function should not be called in files other than this file, db_rating_operations file and possibly the testing file.
No check on the validity of inputs is provided.
'''


def lazy(clinic, flag, rating):
	global lazy_table
	if lazy_table[clinic] is True:
		lazy_table[clinic] = flag, rating * flag
	else:
		lazy_table[clinic] = lazy_table[clinic][0] + flag, lazy_table[clinic][1] + rating * flag


'''
The function pushes the lazy tags on one particular clinic to the database.
The function takes in only one parameter, the entry to the clinic.
If no lazy tag is piles on the entry, the function would do nothing (neither would it throw exception).
After the function is called, the particular entry to the database should be up-to-date and the entry to the respective
column in the hash table should be reset.

Warning: Do NOT try to modify this function.
'''


def write_back(clinic):
	global lazy_table
	if lazy_table[clinic] is True:
		pass
	else:
		clinic.sum += lazy_table[clinic][1]
		clinic.cnt += lazy_table[clinic][0]
		lazy_table[clinic] = True
		clinic.save()


'''
The function pushes all the lazy tags down to the database. The function is slow if the hash table is almost full.
If not necessary, do not call this function.
Before exit the database management program, this function should be called to save everything to the database. 
Otherwise, the buffered data would be lost.

Warning: Do NOT try to loop through the hash table for the update. (The method may seem to be faster but it does not 
work. My guess is about the encapsulation of django database)
'''


def update_all():
	all_entries = list(Clinic.objects.all())
	for clinic in all_entries:
		write_back(clinic)
