from .models import Reminder, SingleReminder, PetOwner
import datetime


'''
This function is used to create a "single reminder".
Unlike user-created reminders, single reminders are used to reduce the complexity of database organisation.
Almost all database related reminder queries are implemented on this single reminder level.
Single reminders are generally auto created and are used to alert the user for only one event.
After altering for that particular event, the single reminder should be discarded and replaced by a new one
(of the same category) to indicate the next event (if any).
The call of this function, as well as the update_reminder function, should only be made by test functions and
database functions. Otherwise, serious and unpredictable problems may rise.

This function takes in the id (primary key) of a "reminder", which would be explained later, and tries to create
the nearest instance of that type of reminder. If the reminder is expired (no further alerts should be made), the
function would delete the reminder from the database and a false would be returned indicating the failed try of
creating a new alert. If the reminder it takes in is not in the database, a KeyError would be raised.

The function also updates the reminder to make itself easier to be called again.
A true would be returned when both the creation and the update procession finish.
'''


def create_single_reminder(reminder_id):
	try:
		entry = Reminder.objects.get(id = reminder_id)
	except Reminder.DoesNotExist:
		raise KeyError('The key is wrong')

	if entry.remain_times == 0:
		entry.delete()
		return False

	SingleReminder.objects.create(link = entry, time = entry.next_time, user = entry.user, type = entry.type)
	entry.remain_times -= 1
	entry.next_time = entry.next_time + entry.period
	entry.save()

	return True


'''
This function creates a reminder that allows the create_single_reminder to further the maintain and query process.
The first simple reminder of this reminder would be created if execution is successful.
The function takes in 7 parameters.

user is an entry to a pet owner object.
If the entry is not found in database, a KeyError would be raised.

name is the way the user likes to call the reminder, which should be a string not longer than 128 characters.

remarks is a string not longer than 1024 characters.

is_permanent is a python list of one or two elements.
The first element is a boolean, indicating whether the reminder is permanent. A true indicates a permanent reminder.
The second element should only exist if the first element is false. Under this condition, it is a datetime type
(string or byte string) indicating the end time of the reminder (after which the reminder would no longer be effective).

start_time is the time the reminder becomes effective. It is a datetime type.
(A check may be performed outside the function for the validity of the time, e.g. should not be earlier than now, etc.)

frequency is a number indicating how frequent the user want to be notified by this reminder.
The unit of this is once a day. For example, if the user wants to be alerted twice a day, this should be 2.
This number need not be an integer. For example if the user wants to be alerted every two days, setting frequency to 0.5
will do. However, it should always be greater than zero. If it is smaller or equal to zero, a ValueError is to be raised.

reminder_type is an integer indicating the type of this reminder. The relation of the numbers and types are below:
0: medication reminder
1: hygiene reminder
2: exercise reminder
For any number else input as reminder_type, a ValueError would be raised.
'''


def insert_reminder(user, name, remarks, is_permanent, start_time, frequency, reminder_type):
	try:
		entry = PetOwner.objects.get(pk = user)
	except PetOwner.DoesNotExist:
		raise KeyError('The key is wrong')

	try:
		if frequency < 0:
			raise ValueError('Frequency cannot be negative')
		period = datetime.timedelta(days = 1) / frequency
	except ZeroDivisionError:
		raise ValueError('Frequency cannot be zero')

	if reminder_type < 0 or reminder_type > 2:
		raise ValueError('Reminder type out of range')

	if is_permanent[0]:
		entry = Reminder.objects.create(user = entry, name = name, remarks = remarks, next_time = start_time,
										period = period, type = reminder_type)
	else:
		duration = is_permanent[1] - start_time
		total_actions = duration // period
		entry = Reminder.objects.create(user = entry, name = name, remarks = remarks, next_time = start_time,
										remain_times = total_actions, period = period, type = reminder_type)

	if not create_single_reminder(entry.id):
		raise SystemError('Insertion failed')


'''
This function takes in the entry to a user and would return a tuple of python lists representing the query answer.
The first member would be a boolean list of three elements, indicating whether the user has a medication, hygiene, and 
exercise reminder respectively.
The second member would be a list of entries to the next medication, hygiene, and exercise reminder respectively 
(if any). The default value is None, but it should not be accessed due to flags array.
'''


def query_reminder(user):
	flags = [False, False, False]
	ret = [None, None, None]

	try:
		medications = SingleReminder.objects.filter(user = user, type = 0).order_by('time')[0]
		ret[0] = medications
		flags[0] = True
	except IndexError:
		pass

	try:
		hygiene = SingleReminder.objects.filter(user = user, type = 1).order_by('time')[0]
		ret[1] = hygiene
		flags[1] = True
	except IndexError:
		pass

	try:
		exercise = SingleReminder.objects.filter(user = user, type = 2).order_by('time')[0]
		ret[2] = exercise
		flags[2] = True
	except IndexError:
		pass

	return flags, ret


'''
This function updates single reminder.
The function takes in an entry to the single reminder is to update, creates the next alert of this reminder type first 
(if possible), then deletes the passed-in old entry itself.
'''


def update_reminder(single_reminder):
	reminder = single_reminder.link
	create_single_reminder(reminder_id = reminder.pk)
	single_reminder.delete()


'''
This function returns a tuple of python lists.
The first member is the same as the first member in query_reminder.
The second member is either None (the reminder does not exist) or a list of 4 elements, indicating the name, time, 
remarks and period of the reminder respectively.
Again, the None elements of the second member of returned tuple should not be accessed.
'''


def show_reminder_query(flags, result):
	ans = [None, None, None]
	for i in range(3):
		if flags[i]:
			ans[i] = [
				result[i].link.name,
				result[i].time,
				result[i].link.remarks,
				result[i].link.period
			]
	return flags, ans
