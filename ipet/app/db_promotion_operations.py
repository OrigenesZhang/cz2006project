from .models import Promotion, Clinic, Vet
import datetime


'''
This functions checks whether a user has the permission to post a promotion.
The function takes in an entry to a user and returns a boolean.
If the user has the access to posting a promotion, the functions returns a true, vice versa.
'''


def check_permission(user):
	try:
		entry = Clinic.objects.get(pk = user.pk)
		if entry.isVerified:
			return True
		return False
	except Clinic.DoesNotExist:
		return False


'''
This function is used to get the abstract of the content, which takes in a string (content) and returns the abstract.

The function works like this:
1.	If the length of the content is shorter than 128 characters, then the whole content is regarded as the abstract.
2.	If the length is greater than or equal to 128 characters. Only the first few words would be chosen to be displayed, 
	the total length of which cannot exceed 123 characters and a three dots is added at the end.

The function is used for both promotions and tips.
'''


def get_abstract(content):
	if len(content) < 128:
		return content

	tmp = content.split(' ')
	ret = ''
	for word in tmp:
		if len(word) + len(ret) > 123:
			return ret + '...'
		else:
			ret += word
			ret += ' '


'''
The function takes in 3 parameters, user, title and content and creates a new Promotion object if applicable.

user should be an entry to a verified clinic, otherwise a PermissionError would be raised.

title is the title or headline of this promotion and should be a non-empty string not longer than 128 characters.
Otherwise a ValueError would be raised.

content is the content of this promotion and should be a string not longer than 4096 characters.
Otherwise a ValueError would be raised.
'''


def insert_promotion(user, title, content):
	if not check_permission(user):
		raise PermissionError('Only verified clinics can post here')
	if len(title) == 0:
		raise ValueError('Title cannot be empty')
	if len(title) > 128:
		raise ValueError('Title is too long')
	if len(content) > 4096:
		raise ValueError('Content is too long')

	abstract = get_abstract(content)
	current_time = datetime.datetime.now()

	Promotion.objects.create(user = user, time = current_time, title = title, content = content, abstract = abstract)


'''
This function lists all the available promotions at the time of query.
A nested python list would be returned, whose elements are lists of 5 elements, the primary key of the promotion, 
the name of the vet/clinic who made the post, the title, the abstract, and the time of the promotion.
The members of the returned list are sorted in time order, the most recent one appears at the first.
The content of promotions are not listed with the consideration that copying a large string from disk to memory is 
costly (both in time and space) and unnecessary (most of the promotions will not be viewed by the user).
'''


def list_promotions():
	q_set = list(Promotion.objects.order_by('-time'))
	ret = []

	for event in q_set:
		cur = [event.pk]
		cur.append(event.user.name)
		cur.append(event.title)
		cur.append(event.abstract)
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
	if user != 0 and user != promotion.user:
		raise PermissionError('Action defied')
	promotion.delete()
