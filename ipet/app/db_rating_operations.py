from django.db.models import F
from .models import Rate, Clinic, User
from .lazy_tag import lazy, write_back, update_all
from datetime import date


'''
The function takes in the user name, the clinic name, the score, the date and the comment as parameters and try to insert
a rate based on that.

If user name or clinic name cannot be found in the database, a User.DoesNotExist or Clinic.DoesNotExist would be raised.
If the input score is not an int between 1 to 5, inclusive, a ValueError would be raised.

If the date does not belong to datetime.date type, a ValueError would be raised. However, the date can be omitted in 
parameter list, by doing so, the date is set to be today.

If the comment is not a string not longer than 4096 characters, a ValueError would be raised. However, the comment can 
be omitted in parameter list, by doing so, it is set to be to an empty string.

If the user has already rated this clinic on the given date, a PermissionError is raised.
'''


def insert_rate(user_name, clinic_name, score, day = date.today(), comment = ""):
	user = User.objects.get(name = user_name)
	clinic = Clinic.objects.get(name = clinic_name)

	try:
		date.today() - day
	except TypeError:
		raise ValueError('Incorrect type of date')

	try:
		assert isinstance(score, int) and 0 < score <= 5
	except AssertionError:
		raise ValueError('Pls input a valid integer between 1 to 5 as the score')

	try:
		assert isinstance(comment, str) and len(comment) <= 4096
	except AssertionError:
		raise ValueError('Pls input a valid string not longer than 4096 characters')

	try:
		Rate.objects.get(user = user, clinic = clinic, date = day)
		raise PermissionError('Already rated today')
	except Rate.DoesNotExist:
		pass

	Rate.objects.create(user = user, clinic = clinic, date = day, score = score, comment = comment)
	lazy(clinic = clinic, flag = 1, rating = score)


'''
The function deletes a rate record from database based on the user name, clinic name, and the date of rating. (this 
combination ensures the uniqueness)

If user name or clinic name cannot be found in the database, a User.DoesNotExist or Clinic.DoesNotExist would be raised.
If the date does not belong to datetime.date type, a ValueError would be raised.
If such record does not exist, a Rate.DoesNotExist would be raised.
'''


def delete_rate(user_name, clinic_name, day):
	user = User.objects.get(name = user_name)
	clinic = Clinic.objects.get(name = clinic_name)
	try:
		date.today() - day
	except TypeError:
		raise ValueError('Incorrect type of date')

	entry = Rate.objects.get(user = user, clinic = clinic, date = day)
	lazy(clinic = clinic, flag = -1, rating = entry.score)
	entry.delete()


'''
The function queries the average of rating score of a particular clinic based on the name of it.

If the clinic cannot be found in database, a Clinic.DoesNotExist would be raised.
If no one has rated this clinic, or all the rates have been deleted, the function would return 0.
'''


def query_rate(clinic_name):
	clinic = Clinic.objects.get(name = clinic_name)
	write_back(clinic)
	if clinic.cnt == 0:
		return 0
	return clinic.sum / clinic.cnt


'''
The function queries all the clinics whose average rating is higher than the given bar.

The bar has to be an int or a float, otherwise a TypeError would be raised.

The function returns a list of information of all the clinics whose rating is higher than the given bar. Each clinic is 
returned as a list of its name, average rating, primary key and the entry to it.

Notice that the order of the returned clinics is undefined.
'''


def sieve_rating(lower_bound):
	try:
		assert isinstance(lower_bound, int) or isinstance(lower_bound, float)
	except AssertionError:
		raise TypeError('Pls input an integer or floating point number')
	update_all()

	q_set = Clinic.objects.filter(cnt__gt = 0)
	q_set = q_set.filter(sum__gt = lower_bound * F('cnt'))

	ret = []
	for q_obj in q_set:
		cur = q_obj.name, q_obj.sum / q_obj.cnt, q_obj.pk
		ret.append(cur)

	return ret


'''
The function lists all the rating recording be a particular user based on the user name.
If the user cannot be found, a User.DoesNotExist would be returned.

A list of lists would be returned. Each list inside it would consist of 5 objects, the entry to the clinic, the date of
the rate, the score, the comment and an entry to this rate record.

The returned lists would be sorted in terms of time in decreasing order, which means most recent would appear first.
'''


def list_rating(user_name):
	user = User.objects.get(name = user_name)

	q_set = list(Rate.objects.filter(user = user).order_by('-date'))
	ret = []
	for q_obj in q_set:
		cur = q_obj.clinic, q_obj.date, q_obj.score, q_obj.comment
		ret.append(cur)

	return ret
