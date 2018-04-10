from .models import PromoTips, ClinicRep
from datetime import datetime


'''
This functions checks whether a user has the permission to post a promotion.
The function takes in the name of a user and returns a boolean.
If the user has the access to posting a promotion or a tip, the functions returns a true, vice versa.
'''


def check_permission(name):
	try:
		ClinicRep.objects.get(name = name)
	except ClinicRep.DoesNotExist:
		return False

	return True


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
The function takes in 4 parameters, user, title and content and creates a new Promotion object if applicable.

user should be the name of a registered clinic, otherwise a PermissionError would be raised.

title is the title or headline of this promotion and should be a non-empty string not longer than 128 characters.
Otherwise a ValueError would be raised.

content is the content of this promotion or tip and should be a string not longer than 4096 characters.
Otherwise a ValueError would be raised.

type is a boolean, a true indicates the current inserting post is a promotion and false a tip.
'''


def insert_post(user, title, content, type):
	if not check_permission(user):
		raise PermissionError('Only verified clinics can post here')
	if len(title) == 0:
		raise ValueError('Title cannot be empty')
	if len(title) > 128:
		raise ValueError('Title is too long')
	if len(content) > 4096:
		raise ValueError('Content is too long')

	abstract = get_abstract(content)
	current_time = datetime.now()
	user = ClinicRep.objects.get(name = user)

	PromoTips.objects.create(user = user, time = current_time, title = title, type = type, content = content, abstract = abstract)


'''
This function lists all the available promotions or tips at the time of query.
A true in the passed in parameter indicating the being-queried is a promotion and false a tip.

A nested python list would be returned, whose elements are lists of 5 elements, the primary key of the post, 
the name of the clinic who made the post, the title, the abstract, and the time of the post.
The members of the returned list are sorted in time order, the most recent one appears at the first.
The content of posts are not listed with the consideration that copying a large string from disk to memory is 
costly (both in time and space) and unnecessary (most of the posts will not be viewed by the user).
'''


def list_posts(type):
	q_set = list(PromoTips.objects.filter(type = type).order_by('-time'))
	ret = []

	for event in q_set:
		cur = [event.pk]
		cur.append(event.user.name)
		cur.append(event.title)
		cur.append(event.title)
		cur.append(event.abstract)
		cur.append(event.time)
		ret.append(cur)

	return ret


'''
This function takes in the primary key of the queried post and returns the corresponding content.
This function is designed to be used with list_post function.
'''


def query_post_content(post_pk):
	try:
		entry = PromoTips.objects.get(pk = post_pk)
	except PromoTips.DoesNotExist:
		raise FileNotFoundError('The post is not there')

	return entry.content


'''
This function is used to delete a particular post.
Two parameters, reply and user are taken in, which are entries to PromoTips and ClinicRep objects respectively.
Notice that the user could also be integer zero, which indicates the deletion is made by the system or administrators.

If the user is neither zero nor the entry of the user who posted the post, a PermissionError would be raised.
'''


def delete_post(post, user):
	if user != 0 and user != post.user:
		raise PermissionError('Action defied')
	post.delete()
