from .models import User, Pet
from django.core.exceptions import ObjectDoesNotExist
from datetime import date


'''
The function takes in the name of the pet owner and should return the key of it in the database.
If the person cannot be found in database, an ObjectDoesNotExist would be raised.
'''


def find_owner_key(owner_name):
	try:
		q_set = list(User.objects.filter(name = owner_name))
		owner_key = q_set[0].pk
	except IndexError:
		raise ObjectDoesNotExist("The owner is not found in database")

	owner_key = User.objects.get(pk = owner_key)
	return owner_key


'''
The functions inserts a pet (for the key combination not existing case) or modifies a pet (for key combination existing 
case). The function takes in three non-nullable variables, the name of the pet, the name of the pet's owner, and the 
breed of it, as well as four nullable variables, the gender, birthday, location and the weight of the pet.

The name and the breed of the pet are both string with at most 32 characters. If the string is too long, a ValueError 
would be raised.

If the name of the owner cannot be found among the registered users, a ObjectDoesNotExist exception is to be raised.

The gender an integer can either be 1 or 2, 1 for female and 2 for male. A zero in gender indicates the gender field is 
left empty but zero is not considered as a valid parameter in this field. For all invalid inputs in this field, a 
ValueError would be raised.

The birthday is a datetime.datetime.date object. If it is not in this type, a ValueError would be raised.

The location is a string of length not exceeding 128 characters. If not, a ValueError would be raised.

The weight is a positive float. If it is not a float, a TypeError would be raised. If it is not positive, a ValueError
would be raised.
'''


def insert_pet(name, owner_name, breed, gender = None, birthday = None, location = None, weight = None):
	try:
		owner_key = find_owner_key(owner_name)
	except ObjectDoesNotExist as ex:
		raise ex

	if len(name) > 32:
		raise ValueError("The name is too long")
	if len(breed) > 32:
		raise ValueError("The breed is too long")

	if gender is not None:
		if gender != 1 and gender != 2:
			raise ValueError("Pls input a valid gender or leave it blank")

	if birthday is not None:
		try:
			date.today() - birthday
		except TypeError:
			raise ValueError("Pls input a valid birthday or leave it blank")

	if location is not None:
		if len(location) > 128:
			raise ValueError("The location is too long")

	if weight is not None:
		try:
			if weight > 0:
				pass
			else:
				raise ValueError("The weight should be positive")
		except TypeError:
			raise ValueError("Pls input a valid weight or leave it blank")

	pet, created = Pet.objects.get_or_create(name = name, owner = owner_key)
	pet.breed = breed

	if gender is not None:
		pet.gender = gender
	if birthday is not None:
		pet.birthday = birthday
	if location is not None:
		pet.location = location
	if weight is not None:
		pet.weight = weight

	pet.save()
	return created


'''
The functions is used to query all the available information about a pet.
The pet's name and the owner's name is taken in.

If the owner does not exist, an ObjectDoesNotExist exception would be raised.
If the pet does not exist, a Pet.DoesNotExist exception would be raised.

Otherwise, a list of 5 objects would be returned. They are the breed, gender, birthday, location, and weight of the pet, 
respectively. However, all except the first two terms are nullable. Also for gender, if the returned value is 0, the 
gender of the pet is not specified yet.
'''


def query_pet(name, owner_name):
	try:
		owner_key = find_owner_key(owner_name)
	except ObjectDoesNotExist as ex:
		raise ex

	q_set = list(Pet.objects.filter(owner = owner_key, name = name))

	try:
		q_set = q_set[0]
	except IndexError:
		raise Pet.DoesNotExist

	ret = [None] * 5
	ret[0] = q_set.breed
	ret[1] = q_set.gender
	ret[2] = q_set.birthday
	ret[3] = q_set.location
	ret[4] = q_set.weight

	return ret


'''
The functions is used to calculate a pet's age given the birthday.
If the birthday is None, a FileNotFoundError would be raised.
If the birthday does not belong to datetime.datetime.date type, a TypeError would be raised.

The function returns a tuple of the year and the month the age respectively.
'''


def get_pet_age(birthday):
	if birthday is None:
		raise FileNotFoundError("The birthday is not given")

	try:
		age = date.today() - birthday
	except TypeError:
		raise TypeError("Pls input a valid birthday")

	years = age.days // 365
	months = age.days // 30 - years * 12

	return years, months


'''
The function takes in the name of the pet and the name of the pet owner and deletes the respective pet.

If the pet owner is not found, an ObjectDoesNotExist exception would be raised. If the pet is not found, a 
Pet.DoesNotExist exception is raised. 
'''


def delete_pet(name, owner_name):
	try:
		owner_key = find_owner_key(owner_name)
	except ObjectDoesNotExist as ex:
		raise ex

	try:
		pet = Pet.objects.filter(name = name, owner = owner_key)[0]
	except IndexError:
		raise Pet.DoesNotExist

	pet.delete()


'''
The function takes in at least 2 and up to 7 parameters to modify the information of an existing pet.

If the pet owner is not found, an ObjectDoesNotExist exception would be raised. If the pet is not found, a 
Pet.DoesNotExist exception is raised.

All the parameters are nullable except the name of the pet and the owner.

If the breed is omitted in the parameter list, the original breed would be remained unchanged. However, for other 
attributes, an omission in parameter list means setting the corresponding attribute to None.
'''


def modify_pet_info(name, owner_name, breed = None, gender = None, birthday = None, location = None, weight = None):
	try:
		owner_key = find_owner_key(owner_name)
	except ObjectDoesNotExist as ex:
		raise ex

	q_set = list(Pet.objects.filter(owner = owner_key, name = name))

	try:
		q_set = q_set[0]
	except IndexError:
		raise Pet.DoesNotExist

	if breed is not None:
		insert_pet(name = name, owner_name = owner_name, breed = breed, gender = gender, birthday = birthday,
				   location = location, weight = weight)
	else:
		insert_pet(name = name, owner_name = owner_name, breed = q_set.breed, gender = gender, birthday = birthday,
				   location = location, weight = weight)
