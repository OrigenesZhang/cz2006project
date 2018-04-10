from .models import Clinic, ClinicRep
from .db_user_operations import check_phone_number


'''
The function takes in the name and the geometric hashing code of the clinic and do an insertion or update based on these.
If the length of the name is longer than 128 characters, a ValueError is to be raised.

If there already exists a clinic with the same name in the database, the geo_hash of the object would be updated to the 
passed-in one. However, if an existing geo_hash is passed in, an unhandled exception would be raised due to the unique 
constraint of it as in the .model file. (This should be avoided)
'''


def insert_clinic(name, geo_hash):
	if len(name) > 128:
		raise ValueError('The name is too long')

	entry, created = Clinic.objects.get_or_create(name = name, defaults = {"geo_hash" : geo_hash})
	if not created:
		entry.geo_hash = geo_hash
		entry.save()


'''
The function takes the name of a clinic and returns all the possible information about the clinic.
If such clinic does not exist, a False would be returned.
Otherwise, a python list of three elements, the primary key of the DB entry to the clinic, the geometric hash code of it, 
and the entry to the respective clinic_rep (if any) or None (if not found) would be returned.
'''


def query_clinic(name):
	try:
		entry = Clinic.objects.get(name = name)
	except Clinic.DoesNotExist:
		return False

	ret = [entry.pk, entry.geo_hash]
	if hasattr(entry, 'clinic_rep'):
		ret.append(entry.clinic_rep)
	else:
		ret.append(None)
	return ret


'''
The function creates a new ClinicRep object. It takes in 4 parameters, the name of the clinic rep, the name of the clinic,
the password, and the phone number of the registrant.

If the phone number is already used by someone else, a FileExistsError would be raised.
If the clinic does not exist, a Clinic.DoesNotExist would be raised.
If the clinic already has a rep, a FileExistsError would be raised (with the assumption that each clinic can only have 
one rep).
If the name is longer than 128 characters or the password is longer than 16 characters, a ValueError would be raised.
'''


def insert_clinic_rep(name, clinic_name, password, phone_number):
	if not check_phone_number(phone_number):
		raise FileExistsError('The phone number is already occupied')

	clinic = query_clinic(clinic_name)
	if clinic is False:
		raise Clinic.DoesNotExist

	if clinic[2] is not None:
		raise FileExistsError('The clinic already has a rep')

	if len(name) > 128 or len(password) > 16:
		raise ValueError('The name or the password is too long')

	clinic = Clinic.objects.get(pk = clinic[0])
	ClinicRep.objects.create(name = name, clinic = clinic, password = password, phone_number = phone_number)


'''
The function queries about the information of a ClinicRep object based on exactly one of the four information provided, 
name, clinic_name, phone_number, and password. The information is to be put in a list and all the elements except the 
given information are set to be None.

If the passed-in parameter is not a list of length 4, an AssertionError would be raised.
If the corresponding information cannot be found in the database, a ClinicRep.DoesNotExist would be raised.
If the query is successful, the returned value should be a list of the same format as the parameter be filled in with 
data queried.

If a list of 4 Nones is input, a False would be returned.
If multiple elements are not None in the parameter, the query would be made based on the first one.
'''


def query_clinic_rep(info):
	assert isinstance(info, list) and len(info) == 4

	for i in range(4):
		if info[i] is None:
			continue
		if i == 0:
			entry = ClinicRep.objects.get(name = info[0])
		elif i == 1:
			try:
				clinic = Clinic.objects.get(name = info[1])
			except Clinic.DoesNotExist:
				raise ClinicRep.DoesNotExist
			entry = ClinicRep.objects.get(clinic = clinic)
		elif i == 2:
			entry = ClinicRep.objects.get(phone_number = info[2])
		else:
			entry = ClinicRep.objects.get(password = info[3])

		ret = [entry.name, entry.clinic.name, entry.password, entry.phone_number]
		return ret

	return False

