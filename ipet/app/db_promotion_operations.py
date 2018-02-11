from .models import Promotion, Clinic, Vet
import datetime


'''
This functions checks whether a user has the permission to post a promotion.
The function takes in an entry to a user and returns a boolean.
If the user has the access to posting a promotion, the functions returns a true, vice versa.
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
The function takes in 3 parameters, user, title and content and creates a new Promotion object if applicable.

user should be an entry to a verified vet or clinic, otherwise a PermissionError would be raised.

title is the title or headline of this promotion and should be a non-empty string not longer than 128 characters.
Otherwise a ValueError would be raised.

content is the content of this promotion and should be a string not longer than 4096 characters.
Otherwise a ValueError would be raised.
'''


def insert_promotion(user, title, content):
	if not check_permission(user):
		raise PermissionError('Only verified clinics or vets can post here')
	if len(title) == 0:
		raise ValueError('Title cannot be empty')
	if len(title) > 128:
		raise ValueError('Title is too long')
	if len(content) > 4096:
		raise ValueError('Content is too long')

	current_time = datetime.datetime.now()
	Promotion.objects.create(user = user.phone_number, time = current_time, title = title, content = content)


'''
This function lists all the available promotions at the time of query.
A nested python list would be returned, whose elements are lists of 4 elements, the primary key of the promotion, 
the name of the vet/clinic who made the post, the title and the time of the promotion.
The members of the returned list are sorted in time order, the most recent one appears at the first.
The content of promotions are not listed with the consideration that copying a large string from disk to memory is 
costly (both in time and space) and unnecessary (most of the promotions will not be viewed by the user).
'''


def list_promotions():
	q_set = list(Promotion.objects.order_by('-time'))
	ret = []

	for event in q_set:
		cur = [event.pk]
		try:
			cur.append(event.user.vet_name.name)
		except:
			cur.append(event.user.clinic_name.name)
		cur.append(event.title)
		cur.append(event.time)
		ret.append(cur)

	return ret


'''
This function takes in the primary key of the queried promotion and returns the corresponding content.
This function is designed to be used with list_promotion function.
'''


def query_promotion_content(promotion_pk):
	try:
		entry = Promotion.objects.get(pk = promotion_pk)
	except Promotion.DoesNotExist:
		raise FileNotFoundError('The promotion is not there')

	return entry.content


'''
This function is used to delete a particular promotion.
Two parameters, reply and user are taken in, which are entries to Promotion and User objects respectively.
Notice that the user could also be integer zero, which indicates the deletion is made by the system or administrators.

If the user is neither zero nor the entry of the user who posted the promotion, a PermissionError would be raised.
'''


def delete_promotion(promotion, user):
	if user != 0 and user.phone_number != promotion.user:
		raise PermissionError('Action defied')
	promotion.delete()
