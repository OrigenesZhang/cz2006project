from .models import Tip, Clinic, Vet
from .db_promotion_operations import get_abstract
import datetime


'''
This functions checks whether a user has the permission to post a tip.
The function takes in an entry to a user and returns a boolean.
If the user has the access to posting a tip, the functions returns a true, vice versa.
'''


def check_permission(user):
	try:
		entry = Clinic.objects.get(phone_number = user.phone_number)
		if entry.isVerified:
			return True
		return False
	except Clinic.DoesNotExist:
		pass

	try:
		entry = Vet.objects.get(phone_number = user.phone_number)
		if entry.isVerified:
			return True
		return False
	except Vet.DoesNotExist:
		return False


'''
The function takes in 3 parameters, user, title and content and creates a new Tip object if applicable.

user should be an entry to a verified vet or clinic, otherwise a PermissionError would be raised.

title is the title or headline of this tip and should be a non-empty string not longer than 128 characters.
Otherwise a ValueError would be raised.

content is the content of this tip and should be a string not longer than 4096 characters.
Otherwise a ValueError would be raised.
'''


def insert_tip(user, title, content):
	if not check_permission(user):
		raise PermissionError('Only verified clinics or vets can post here')
	if len(title) == 0:
		raise ValueError('Title cannot be empty')
	if len(title) > 128:
		raise ValueError('Title is too long')
	if len(content) > 4096:
		raise ValueError('Content is too long')

	abstract = get_abstract(content)
	current_time = datetime.datetime.now()

	Tip.objects.create(user = user.phone_number, time = current_time, title = title, content = content,
							 abstract = abstract)


'''
This function lists all the available tips at the time of query.
A nested python list would be returned, whose elements are lists of 5 elements, the primary key of the tip, 
the name of the vet/clinic who made the post, the title, the abstract, and the time of the tip.
The members of the returned list are sorted in time order, the most recent one appears at the first.
The content of tips are not listed with the consideration that copying a large string from disk to memory is 
costly (both in time and space) and unnecessary (most of the tips will not be viewed by the user).
'''


def list_tips():
	q_set = list(Tip.objects.order_by('-time'))
	ret = []

	for event in q_set:
		cur = [event.pk]
		try:
			cur.append(event.user.vet_name.name)
		except:
			cur.append(event.user.clinic_name.name)
		cur.append(event.title)
		cur.append(event.abstract)
		cur.append(event.time)
		ret.append(cur)

	return ret


'''
This function takes in the primary key of the queried tip and returns the corresponding content.
This function is designed to be used with list_tip function.
'''


def query_tip_content(tip_pk):
	try:
		entry = Tip.objects.get(pk = tip_pk)
	except Tip.DoesNotExist:
		raise FileNotFoundError('The tip is not there')

	return entry.content


'''
This function is used to delete a particular tip.
Two parameters, reply and user are taken in, which are entries to Promotion and User objects respectively.
Notice that the user could also be integer zero, which indicates the deletion is made by the system or administrators.

If the user is neither zero nor the entry of the user who posted the tip, a PermissionError would be raised.
'''


def delete_tip(tip, user):
	if user != 0 and user.phone_number != tip.user:
		raise PermissionError('Action defied')
	tip.delete()
