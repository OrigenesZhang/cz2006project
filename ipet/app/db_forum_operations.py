from .models import Post, Reply
import datetime


'''
This function can be used to insert a main post in the forum.
It takes in three parameters, user, title and content.

user is an entry to a user (can be either a pet owner, a vet or a clinic) of the app.
No check of the user inside the function is provided so the validity of user has to be checked in advance.

title is the title of the post. Each post must have a title of length ranging from 1 to 128 characters.
If the title is too long or too short, a ValueError would be raised.

content is the post content, which is a string not longer than 4096 characters.
If the content is too long, a ValueError would be raised.
'''


def insert_post(user, title, content):
	time = datetime.datetime.now()
	if len(title) == 0:
		raise ValueError('Title cannot be empty')
	if len(title) > 128:
		raise ValueError('Title is too long')
	if len(content) > 4096:
		raise ValueError('The content is too long')
	Post.objects.create(user = user.phone_number, title = title, time = time, content = content)


'''
This function takes in a title of the post and returns a list of Post objects having the exactly same title.
If there is no such post, a false would be returned.
If multiple posts are found, they would be sorted in the order of posting time. (The most recent would appear first)
'''


def query_post_title(title):
	q_set = Post.objects.filter(title = title).order_by('-time')
	if len(q_set) == 0:
		return False
	return list(q_set)


'''
This function takes in a user object (can be of either type) and returns a list of Post objects posted by him or her.
If there is no such post, a false would be returned.
If multiple posts are found, they would be sorted in the order of posting time. (The most recent would appear first)
'''


def query_post_user(user):
	q_set = Post.objects.filter(user = user.phone_number).order_by('-time')
	if len(q_set) == 0:
		return False
	return list(q_set)


'''
This function is used to support the upvote or like function.
Two parameters, post and user are taken in, which are entries to Post and User objects respectively.
If this particular user has already upvoted this post, a PermissionError would be raised.
Nothing would be returned if the process is done successfully.
'''


def like_post(post, user):
	if post.likes.filter(pk = user.phone_number.pk).count() > 0:
		raise PermissionError('Already liked')

	post.likes.add(user.phone_number)
	post.save()


'''
This function is used to support the report function.
Two parameters, post and user are taken in, which are entries to Post and User objects respectively.
If this particular user has already reported this post, a PermissionError would be raised.

If the total number of reports of the particular post reaches 5 or above, including the just created one,
the function would return a tuple of current time and the entry to the post in order to notice the administrator.
A false would be returned otherwise.
'''


def report_post(post, user):
	if post.reports.filter(pk = user.phone_number.pk).count() > 0:
		raise PermissionError('Already reported')
	post.reports.add(user.phone_number)
	post.save()

	if post.reports.all().count() >= 5:
		return datetime.datetime.now(), post
	return False


'''
This function can be used to insert a reply of an existing post or reply in the forum.
It takes in four parameters, user, target, thread, and content.

user is an entry to a user (can be either a pet owner, a vet or a clinic) of the app.
No check of the user inside the function is provided so the validity of user has to be checked in advance.

target is a boolean indicating whether the reply is made to a post or a reply.
If target is True, the reply is made to a post, otherwise, it is made to a reply.

post is an entry to an existing post or a reply.
No check of the post or reply inside the function is provided so the validity of post has to be checked in advance.

content is the reply content, which is a string not longer than 2048 characters.
If the content is too long, a ValueError would be raised.
'''


def insert_reply(user, target, thread, content):
	if len(content) > 2048:
		raise ValueError('The reply is too long')
	time = datetime.datetime.now()
	if target:
		Reply.objects.create(user = user.phone_number, thread = thread, time = time, content = content)
	else:
		Reply.objects.create(user = user.phone_number, dependency = thread, time = time, content = content)


'''
This function takes two parameters and returns tuple of a list of reply objects and a list of booleans.
The first parameter is an entry of the queried thread and the second one is a boolean, indicating whether the thread is
a post or a reply.
If there is no such reply, a false would be returned.
If multiple replies are found, they would be sorted in the order of posting time. (The most recent would appear first)
The second returned list should have the same number of elements as the first one. The respective member indicates 
whether the reply has replies.
'''


def query_reply(thread, target):
	if target:
		q_set = Reply.objects.filter(thread = thread).order_by('-time')
	else:
		q_set = Reply.objects.filter(dependency = thread).order_by('-time')
	if len(q_set) == 0:
		return False
	ret1 = list(q_set)
	ret2 = []
	for entry in ret1:
		q_set = Reply.objects.filter(dependency = entry)
		if len(q_set) == 0:
			ret2.append(False)
		else:
			ret2.append(True)
	return ret1, ret2


'''
This function is used to support the upvote or like function.
Two parameters, reply and user are taken in, which are entries to Reply and User objects respectively.
If this particular user has already upvoted this reply, a PermissionError would be raised.
Nothing would be returned if the process is done successfully.
'''


def like_reply(reply, user):
	if reply.likes.filter(pk = user.phone_number.pk).count() > 0:
		raise PermissionError('Already liked')

	reply.likes.add(user.phone_number)
	reply.save()


'''
This function is used to support the report function.
Two parameters, reply and user are taken in, which are entries to Reply and User objects respectively.
If this particular user has already reported this reply, a PermissionError would be raised.

If the total number of reports of the particular reply reaches 5 or above, including the just created one,
the function would return a tuple of current time and the entry to the reply in order to notice the administrator.
A false would be returned otherwise.
'''


def report_reply(reply, user):
	if reply.reports.filter(pk = user.pk).count() > 0:
		raise PermissionError('Already reported')
	reply.reports.add(user)
	reply.save()

	if reply.reports.all().count() >= 5:
		return datetime.datetime.now(), reply
	return False


'''
This function is used to delete a particular reply and all the replies made towards it.
Two parameters, reply and user are taken in, which are entries to Reply and User objects respectively.
Notice that the user could also be integer zero, which indicates the deletion is made by the system or administrators.

If the user is neither zero nor the entry of the user who posted the reply, a PermissionError would be returned.

The function works in a recursive manner.
'''


def delete_reply(reply, user):
	if user != 0 and user.phone_number != reply.user:  # user == 0 indicating it is system operation
		raise PermissionError('Action defied')

	q_set = list(Reply.objects.filter(dependency = reply))
	for entry in q_set:
		delete_reply(entry, 0)

	reply.delete()


'''
This function is used to delete a particular post.
Two parameters, post and user are taken in, which are entries to Post and User objects respectively.
Notice that the user could also be integer zero, which indicates the deletion is made by the system or administrators.

If the user is neither zero nor the entry of the user who posted the reply, a PermissionError would be returned.

The function would also delete all the replies to the post if it works properly.
'''


def delete_post(post, user):
	if user != 0 and user.phone_number != post.user:
		raise PermissionError('Action defied')

	q_set = list(Reply.objects.filter(thread = post))
	for reply in q_set:
		delete_reply(reply, 0)

	post.delete()
