from .models import User, ClinicRep
from django.core.exceptions import ObjectDoesNotExist
import datetime


'''
This is a function to check whether the entered phone number has been used yet.
A true indicates the phone number is unoccupied and vice versa.
'''


def check_phone_number(phone_number):
	try:
		User.objects.get(phone_number = phone_number)
		return False
	except User.DoesNotExist:
		pass
	try:
		ClinicRep.objects.get(phone_number = phone_number)
	except ClinicRep.DoesNotExist:
		return True
	return False


'''
The insert_user function takes in the name of the registrant, the phone number, and the password.

Both the name and the phone number should be unique. However, a user can, at least technically, take a name already
occupied by a clinic, the uniqueness of which would be checked inside the function.

The phone number should be unique for all the users, the uniqueness of which would be checked inside the function.

The length of the password should between 6 and 16.

ValueErrors would be returned if the length of the strings do not fit into the respective standards,
and FileExistsErrors would be returned if the phone number or name has already been used for registration.

If both types of errors are presented, a ValueError would be returned.
'''


def insert_user(name, phone_number, password):
	if len(name) > 32:
		raise ValueError('This name is too long')
	if len(phone_number) > 16 or len(phone_number) < 3:
		raise ValueError('Please enter a valid phone number')
	if len(password) > 16:
		raise ValueError('Passwords should not be longer that 16 characters')
	if len(password) < 6:
		raise ValueError('Passwords should consist of at least 6 characters')

	if not check_phone_number(phone_number):
		raise FileExistsError('The phone number has already been used for registration')

	try:
		User.objects.get(name = name)
		raise FileExistsError('The name is already taken for registration')
	except User.DoesNotExist:
		pass

	User.objects.create(name = name, phone_number = phone_number, password = password)


'''
The function queries the phone number, name and password of a user by either phone number or name.
The function takes in two parameters, info, which is either the phone number or the name, and type, a boolean,
in which a True indicates query is to be made by name and a False by phone number.
If the queried user does not exist in database, a ObjectDoesNotExist exception would be raised.
The function returns a tuple of the phone number, the name, and the password of the user in the mentioned order.
'''


def query_user(info, type):
	if type is True:
		q_set = User.objects.filter(name = info)
	else:
		q_set = User.objects.filter(phone_number = info)

	q_set = list(q_set)
	try:
		phone_number = q_set[0].phone_number
		name = q_set[0].name
		password = q_set[0].password
	except IndexError:
		raise ObjectDoesNotExist

	return phone_number, name, password
