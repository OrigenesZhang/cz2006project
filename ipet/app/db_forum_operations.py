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
Warning: 	For this very first version, replies can only be made towards main posts.
			Replying to existing replies is not allowed.


This function can be used to insert a reply of an existing post in the forum.
It takes in three parameters, user, post and content.

user is an entry to a user (can be either a pet owner, a vet or a clinic) of the app.
No check of the user inside the function is provided so the validity of user has to be checked in advance.

post is an entry to an existing post.
No check of the post inside the function is provided so the validity of post has to be checked in advance.

content is the reply content, which is a string not longer than 2048 characters.
If the content is too long, a ValueError would be raised.
'''


def insert_reply(user, thread, content):
	if len(content) > 2048:
		raise ValueError('The reply is too long')
	time = datetime.datetime.now()
	Reply.objects.create(user = user.phone_number, thread = thread, time = time, content = content)


'''
This function takes in a post object and returns a list of reply objects that replies this post.
If there is no such reply, a false would be returned.
If multiple replies are found, they would be sorted in the order of posting time. (The most recent would appear first)
'''


def query_reply(thread):
	q_set = Reply.objects.filter(thread = thread).order_by('-time')
	if len(q_set) == 0:
		return False
	return list(q_set)


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
This function is used to delete a particular reply.
Two parameters, reply and user are taken in, which are entries to Reply and User objects respectively.
Notice that the user could also be integer zero, which indicates the deletion is made by the system or administrators.

If the user is neither zero nor the entry of the user who posted the reply, a PermissionError would be returned.
'''


def delete_reply(reply, user):
	if user != 0 and user.phone_number != reply.user:  # user == 0 indicating it is system operation
		raise PermissionError('Action defied')
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
